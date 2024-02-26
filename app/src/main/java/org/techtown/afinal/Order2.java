package org.techtown.afinal;

import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

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

public class Order2 extends AppCompatActivity {
    private DatabaseReference databaseReference;
    private TextToSpeech textToSpeech;
    private SharedPreferences sharedPreferences;
    private boolean isTextToSpeechStopped;
    TextToSpeech tts;

    ImageView image;
    TextView textView9, textView11, textView16, textView28;

    Intent intent;

    private Button button13, button14, button15, button16, button17;

    private int count = 0;
    public int mTotalPrice = 0;
    int price = 0;
    int type = 2;

    private CheckBox checkBox14, checkBox15, checkBox16, checkBox17, checkBox18, checkBox19,
                     checkBox20, checkBox21, checkBox22, checkBox23;

    FirebaseDatabase database;
    DatabaseReference reference;

    Item2 item2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.order_2);

        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        reference = database.getInstance().getReference().child("order");
        item2 = new Item2();

        String HOT = "HOT";
        String ICED = "ICED";
        String small = "small(작은)  -300원";
        String regular = "regular(평균)";
        String tall = "tall(큰)  +500원";
        String cup1 = "매장 컵";
        String cup2 = "개인 텀블러";
        String cup3 = "일회용 컵";
        String option8 = "매장";
        String option9 = "포장";

        String key = reference.push().getKey();
        item2.setKey(key);
        item2.setType(type);

        DecimalFormat myFormatter = new DecimalFormat("###,###");
        String formattedStringformat = myFormatter.format(mTotalPrice);

        button13 = findViewById(R.id.button13);
        button14 = findViewById(R.id.button14);
        button15 = findViewById(R.id.button15);

        textView16 = findViewById(R.id.textView16);

        checkBox14 = findViewById(R.id.checkBox14);
        checkBox15 = findViewById(R.id.checkBox15);
        checkBox16 = findViewById(R.id.checkBox16);
        checkBox17 = findViewById(R.id.checkBox17);
        checkBox18 = findViewById(R.id.checkBox18);
        checkBox19 = findViewById(R.id.checkBox19);
        checkBox20 = findViewById(R.id.checkBox20);
        checkBox21 = findViewById(R.id.checkBox21);
        checkBox22 = findViewById(R.id.checkBox22);
        checkBox23 = findViewById(R.id.checkBox23);

        checkBox14.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(checkBox14.isChecked()) {
                    textToSpeech.setSpeechRate(0.9f);
                    textToSpeech.speak("뜨거운", TextToSpeech.QUEUE_FLUSH, null);
                    item2.setOption1(HOT);
                }
            }
        });

        checkBox15.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(checkBox15.isChecked()) {
                    textToSpeech.setSpeechRate(0.9f);
                    textToSpeech.speak("차가운", TextToSpeech.QUEUE_FLUSH, null);
                    item2.setOption1(ICED);
                }
            }
        });

        checkBox16.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(checkBox16.isChecked()) {
                    textToSpeech.setSpeechRate(0.9f);
                    textToSpeech.speak("작은사이즈", TextToSpeech.QUEUE_FLUSH, null);
                    mTotalPrice = mTotalPrice - 300;
                    textView16.setText("가격 : " + myFormatter.format(mTotalPrice) + "원");

                    item2.setSize(small);
                    item2.setCount(count);
                    item2.setPrice(mTotalPrice);

                } else {
                    textToSpeech.setSpeechRate(0.9f);
                    textToSpeech.speak("취소되었습니다.", TextToSpeech.QUEUE_FLUSH, null);
                    mTotalPrice = mTotalPrice + 300;
                    textView16.setText("가격 : " + myFormatter.format(mTotalPrice) + "원");

                    item2.setSize(small);
                    item2.setCount(count);
                    item2.setPrice(mTotalPrice);
                }
                reference.child(key).setValue(item2);
            }
        });

        checkBox17.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (checkBox17.isChecked()) {
                    textToSpeech.setSpeechRate(0.9f);
                    textToSpeech.speak("평균사이즈", TextToSpeech.QUEUE_FLUSH, null);
                    mTotalPrice = mTotalPrice;
                    item2.setSize(regular);
                    item2.setPrice(mTotalPrice);
                } else {
                    textToSpeech.setSpeechRate(0.9f);
                    textToSpeech.speak("취소되었습니다.", TextToSpeech.QUEUE_FLUSH, null);
                }
                reference.child(key).setValue(item2);
            }
        });

        checkBox18.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(checkBox18.isChecked()) {
                    textToSpeech.setSpeechRate(0.9f);
                    textToSpeech.speak("큰 사이즈 ", TextToSpeech.QUEUE_FLUSH, null);
                    mTotalPrice = mTotalPrice + 500;
                    textView16.setText("가격 : " + myFormatter.format(mTotalPrice) + "원");

                    item2.setSize(tall);
                    item2.setCount(count);
                    item2.setPrice(mTotalPrice);

                } else {
                    textToSpeech.setSpeechRate(0.9f);
                    textToSpeech.speak("취소되었습니다.", TextToSpeech.QUEUE_FLUSH, null);
                    mTotalPrice = mTotalPrice - 500;
                    textView16.setText("가격 : " + myFormatter.format(mTotalPrice) + "원");

                    item2.setSize(tall);
                    item2.setCount(count);
                    item2.setPrice(mTotalPrice);
                }
                reference.child(key).setValue(item2);
            }
        });

        checkBox19.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (checkBox19.isChecked()) {
                    textToSpeech.setSpeechRate(0.9f);
                    textToSpeech.speak("매장컵을 선택하였습니다.", TextToSpeech.QUEUE_FLUSH, null);

                    item2.setCup(cup1);

                } else {
                    textToSpeech.setSpeechRate(0.9f);
                    textToSpeech.speak("취소되었습니다.", TextToSpeech.QUEUE_FLUSH, null);
                }
                reference.child(key).setValue(item2);
            }
        });

        checkBox20.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (checkBox20.isChecked()) {
                    textToSpeech.setSpeechRate(0.9f);
                    textToSpeech.speak("개인텀블러를 선택하였습니다.", TextToSpeech.QUEUE_FLUSH, null);
                    item2.setCup(cup2);

                } else {
                    textToSpeech.setSpeechRate(0.9f);
                    textToSpeech.speak("취소되었습니다.", TextToSpeech.QUEUE_FLUSH, null);
                }
                reference.child(key).setValue(item2);
            }
        });

        checkBox21.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(checkBox21.isChecked()) {
                    textToSpeech.setSpeechRate(0.9f);
                    textToSpeech.speak("일회용 컵을 선택하였습니다.", TextToSpeech.QUEUE_FLUSH, null);
                    item2.setCup(cup3);

                }else {
                    textToSpeech.setSpeechRate(0.9f);
                    textToSpeech.speak("취소되었습니다.", TextToSpeech.QUEUE_FLUSH, null);
                }
                reference.child(key).setValue(item2);
            }
        });

        checkBox22.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(checkBox22.isChecked()) {
                    textToSpeech.setSpeechRate(0.9f);
                    textToSpeech.speak("매장식사를 선택하였습니다.", TextToSpeech.QUEUE_FLUSH, null);
                    item2.setOption3(option8);
                }else {
                    textToSpeech.setSpeechRate(0.9f);
                    textToSpeech.speak("취소되었습니다.", TextToSpeech.QUEUE_FLUSH, null);
                }
                reference.child(key).setValue(item2);
            }
        });

        checkBox23.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(checkBox23.isChecked()) {
                    textToSpeech.setSpeechRate(0.9f);
                    textToSpeech.speak("포장하기를 선택하였습니다.", TextToSpeech.QUEUE_FLUSH, null);
                    item2.setOption3(option9);
                }else {
                    textToSpeech.setSpeechRate(0.9f);
                    textToSpeech.speak("취소되었습니다.", TextToSpeech.QUEUE_FLUSH, null);
                }
                reference.child(key).setValue(item2);
            }
        });


        button13.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                count++;
                button14.setText(count+"");

                mTotalPrice = price * count;
                textView16.setText("가격 : " + myFormatter.format(mTotalPrice)  + "원" );

                item2.setCount(count);
                item2.setPrice(mTotalPrice);
            }
        });

        button15.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(count>0) {
                    count--;
                    button14.setText(count+"");

                    mTotalPrice = price * count;
                    textView16.setText("가격 : " + myFormatter.format(mTotalPrice)  + "원" );

                    item2.setCount(count);
                    item2.setPrice(mTotalPrice);
                }
            }
        });

        image = findViewById(R.id.imageView9);
        textView9 = findViewById(R.id.textView9);
        textView11 = findViewById(R.id.textView11);
        textView28 = findViewById(R.id.textView28);

        Intent intent = getIntent();

        image.setImageResource(intent.getIntExtra("image",0));
        String imageUri = getIntent().getStringExtra("imageUri");
        item2.setImage(imageUri);
        reference.child("image");

        String name = getIntent().getStringExtra("name");
        item2.setName(name);
        reference.child("name");

        textView9.setText(name);
        textView11.setText(intent.getStringExtra("info"));
        textView28.setText(intent.getStringExtra("price"));
        mTotalPrice = getIntent().getIntExtra("price2", 0);
        price = getIntent().getIntExtra("price2", 0);

        button16 = findViewById(R.id.button16);

        button16.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Cart.class);
                startActivity(intent);
            }
        });


        databaseReference = FirebaseDatabase.getInstance().getReference("order");
        BootpayAnalytics.init(this, "[ 647319fe3049c8001cf8bf57 ]");


        Button button17 = findViewById(R.id.button17);
        button17.setOnClickListener(new View.OnClickListener() {
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
                                    }
                                }).requestPayment();
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }


                });
            }
        });
        textToSpeech = new TextToSpeech(this, new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if (status == TextToSpeech.SUCCESS) {
                    // 초기화 성공한 경우
                    int result = textToSpeech.setLanguage(Locale.KOREA);
                    Locale locale = Locale.getDefault();
                    if (textToSpeech.isLanguageAvailable(locale) == TextToSpeech.LANG_AVAILABLE) {
                        textToSpeech.setLanguage(locale);
                    }
                    if (!MainActivity.isTextToSpeechStopped) {
                        textToSpeech.setSpeechRate(0.9f);
                        textToSpeech.speak("차 주문화면입니다. 선택사항을 체크해주세요", TextToSpeech.QUEUE_FLUSH, null);
                    }
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
