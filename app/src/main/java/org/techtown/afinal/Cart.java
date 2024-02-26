package org.techtown.afinal;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import kr.co.bootpay.android.Bootpay;
import kr.co.bootpay.android.BootpayAnalytics;
import kr.co.bootpay.android.events.BootpayEventListener;
import kr.co.bootpay.android.models.BootExtra;
import kr.co.bootpay.android.models.BootUser;
import kr.co.bootpay.android.models.Payload;

public class Cart extends AppCompatActivity {
    private RecyclerView recyclerView;
    private CartAdapter cartAdapter;
    private DatabaseReference databaseReference;
    private FirebaseDatabase database;
    private TextToSpeech textToSpeech;
    private SharedPreferences sharedPreferences;
    TextToSpeech tts;
    private boolean isTextToSpeechStopped;

    Button button24;
    TextView textView17;
    int totalPrice = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cart);

        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        BootpayAnalytics.init(this, "[ 647319fe3049c8001cf8bf57 ]");

        DecimalFormat myFormatter = new DecimalFormat("###,###");
        String formatStringformat = myFormatter.format(totalPrice);

        databaseReference = FirebaseDatabase.getInstance().getReference("order");

        textView17 = findViewById(R.id.textView17);

        button24 = findViewById(R.id.button24);
        button24.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BootUser user = new BootUser().setPhone("010-1234-5678");

                BootExtra extra = new BootExtra()
                        .setCardQuota("0,2,3");

                databaseReference.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        List<Item3> item3List = new ArrayList<>();
                        for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                            Item3 item3 = dataSnapshot.getValue(Item3.class);
                            item3List.add(item3);
                        }


                        double totalPrice = 0;
                        for (Item3 item : item3List) {
                            totalPrice += item.getPrice();
                        }

                        Payload payload = new Payload();
                        payload.setApplicationId("647319fe3049c8001cf8bf57 ")
                                .setOrderName("부트페이 결제테스트")
                                .setOrderId("1234")
                                .setPrice(totalPrice)
                                .setUser(user)
                                .setExtra(extra);


                        Map<String, Object> map = new HashMap<>();
                        map.put("1", "abcdef");
                        map.put("2", "abcdef55");
                        map.put("3", 1234);
                        payload.setMetadata(map);


                        Bootpay.init(getSupportFragmentManager(), getApplicationContext())
                                .setPayload(payload)
                                .setEventListener(new BootpayEventListener() {
                                    @Override
                                    public void onCancel(String data) {
                                        Log.d("bootpay", "cancel: " + data);
                                    }

                                    @Override
                                    public void onError(String data) {
                                        Log.d("bootpay", "error: " + data);
                                    }

                                    @Override
                                    public void onClose() {
                                        Bootpay.removePaymentWindow();
                                    }

                                    @Override
                                    public void onIssued(String data) {
                                        Log.d("bootpay", "issued: " + data);
                                    }

                                    @Override
                                    public boolean onConfirm(String data) {
                                        Log.d("bootpay", "confirm: " + data);
//                        Bootpay.transactionConfirm(data); //재고가 있어서 결제를 진행하려 할때 true (방법 1)
                                        return true; //재고가 있어서 결제를 진행하려 할때 true (방법 2)
//                        return false; //결제를 진행하지 않을때 false
                                    }

                                    @Override
                                    public void onDone(String data) {
                                        Log.d("done", data);

                                        // 대기 번호 페이지로 화면 전환
                                        Intent intent = new Intent(Cart.this, Wait.class);
                                        startActivity(intent);
                                    }
                                }).requestPayment();
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
            }
        });

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        cartAdapter = new CartAdapter();

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                List<Item3> item3List = new ArrayList<>();
                item3List.clear();

                int totalPrice = 0;

                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    Item3 item3 = dataSnapshot.getValue(Item3.class);
                    item3List.add(item3);
                    int price = dataSnapshot.child("price").getValue(Integer.class);
                    totalPrice += price;
                }
                cartAdapter.setItem3List(item3List);
                recyclerView.setAdapter(cartAdapter);

                if (item3List.isEmpty()) {
                    textView17.setText(""); // 데이터가 없을 때 공백으로 표시
                } else {
                    textView17.setText("가격 : " + myFormatter.format(totalPrice) + "원");
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        textToSpeech = new TextToSpeech(this, new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if (status == TextToSpeech.SUCCESS) {
                    // 초기화 성공한 경우
                    int result = textToSpeech.setLanguage(Locale.KOREA);
                    if (result == TextToSpeech.LANG_MISSING_DATA || result == TextToSpeech.LANG_NOT_SUPPORTED) {
                        Log.e("TTS", "지원되지 않는 언어입니다.");
                    } else {
                    if (!MainActivity.isTextToSpeechStopped) {
                        textToSpeech.setSpeechRate(0.9f);
                        textToSpeech.speak("장바구니 화면입니다. 주문한 메뉴를 확인해주세요", TextToSpeech.QUEUE_FLUSH, null);
                    }
                }
                } else {
                    Log.e("TTS", "TTS 초기화 실패");
                }
            }
        });
    }

    @Override
    protected void onPause() {
        super.onPause();
        textToSpeech.stop();
        isTextToSpeechStopped = true;
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (textToSpeech != null && !textToSpeech.isSpeaking()) {
            // 이어서 음성을 출력하거나 새로운 음성을 출력할 수 있습니다.
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (textToSpeech != null) {
            textToSpeech.stop();
            textToSpeech.shutdown();
        }
    }
}