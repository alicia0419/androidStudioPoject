package org.techtown.afinal;

import static android.net.Uri.fromFile;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;


import kr.co.bootpay.android.Bootpay;
import kr.co.bootpay.android.BootpayAnalytics;
import kr.co.bootpay.android.events.BootpayEventListener;
import kr.co.bootpay.android.models.BootExtra;
import kr.co.bootpay.android.models.BootUser;
import kr.co.bootpay.android.models.Payload;

import java.io.File;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

public class Order1 extends AppCompatActivity {
    ImageView image;
    private DatabaseReference databaseReference;
    private TextToSpeech textToSpeech;
    private SharedPreferences sharedPreferences;
    private boolean isTextToSpeechStopped;
    TextToSpeech tts;


    TextView textView6, textView8, textView27, textView29;

    Intent intent;

    private Button button7, button8, button9, button10, button11;

    public int count = 0;

    private CheckBox checkBox, checkBox2, checkBox3, checkBox4, checkBox5, checkBox6, checkBox7,
            checkBox8, checkBox9, checkBox10, checkBox11, checkBox12, checkBox13;

    FirebaseDatabase database;
    DatabaseReference reference;

    Item2 item2;

    int mTotalPrice = 0;
    int price = 0;
    int type = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.order_1);

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
        String option1 = "휘핑크림 추가  +1,000원";
        String option2 = "샷 추가  +500원";
        String option3 = "설탕(시럽) 추가  +500원";
        String option4 = "휘핑크림 추가  +1,000원,  샷 추가  +500원";
        String option5 = "휘핑크림 추가  +1,000원,  설탕(시럽) 추가  +500원";
        String option6 = "샷 추가  +500원,  설탕(시럽) 추가  +500원";
        String option7 = "휘핑크림 추가  +1,000원, 샷 추가  +500원, 설탕(시럽) 추가  +500원";
        String option8 = "매장";
        String option9 = "포장";

        String key = reference.push().getKey();
        item2.setKey(key);
        item2.setType(type);


        DecimalFormat myFormatter = new DecimalFormat("###,###");
        String formatStringformat = myFormatter.format(mTotalPrice);

        button7 = findViewById(R.id.button7);
        button8 = findViewById(R.id.button8);
        button9 = findViewById(R.id.button9);

        textView29 = findViewById(R.id.textView29);

        checkBox = findViewById(R.id.checkBox);
        checkBox2 = findViewById(R.id.checkBox2);
        checkBox3 = findViewById(R.id.checkBox3);
        checkBox4 = findViewById(R.id.checkBox4);
        checkBox5 = findViewById(R.id.checkBox5);
        checkBox6 = findViewById(R.id.checkBox6);
        checkBox7 = findViewById(R.id.checkBox7);
        checkBox8 = findViewById(R.id.checkBox8);
        checkBox9 = findViewById(R.id.checkBox9);
        checkBox10 = findViewById(R.id.checkBox10);
        checkBox11 = findViewById(R.id.checkBox11);
        checkBox12 = findViewById(R.id.checkBox12);
        checkBox13 = findViewById(R.id.checkBox13);

        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    textToSpeech.setSpeechRate(0.9f);
                    textToSpeech.speak("뜨거운", TextToSpeech.QUEUE_FLUSH, null);

                    item2.setOption1(HOT);
                }
            }
        });

        checkBox2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    textToSpeech.setSpeechRate(0.9f);
                    textToSpeech.speak("차가운", TextToSpeech.QUEUE_FLUSH, null);

                    item2.setOption1(ICED);
                }
            }
        });

        checkBox3.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    textToSpeech.setSpeechRate(0.9f);
                    textToSpeech.speak("작은사이즈", TextToSpeech.QUEUE_FLUSH, null);
                    mTotalPrice = mTotalPrice - 300;
                    textView29.setText("가격 : " + myFormatter.format(mTotalPrice) + "원");

                    item2.setSize(small);
                    item2.setPrice(mTotalPrice);


                } else {
                    textToSpeech.setSpeechRate(0.9f);
                    textToSpeech.speak("취소되었습니다.", TextToSpeech.QUEUE_FLUSH, null);
                    mTotalPrice = mTotalPrice + 300;
                    textView29.setText("가격 : " + myFormatter.format(mTotalPrice) + "원");

                    item2.setSize(small);
                    item2.setPrice(mTotalPrice);
                }
                reference.child(key).setValue(item2);
            }
        });

        checkBox4.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (checkBox4.isChecked()) {
                    textToSpeech.setSpeechRate(0.9f);
                    textToSpeech.speak("평균사이즈", TextToSpeech.QUEUE_FLUSH, null);
                    mTotalPrice = mTotalPrice;
                    item2.setSize(regular);
                    item2.setPrice(mTotalPrice);
                } else {
                    textToSpeech.setSpeechRate(0.9f);
                    textToSpeech.speak("취소되었습니다.", TextToSpeech.QUEUE_FLUSH, null);
                    reference.child(key).setValue(item2);

                }
            }
        });

        checkBox5.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (checkBox5.isChecked()) {
                    textToSpeech.setSpeechRate(0.9f);
                    textToSpeech.speak("큰 사이즈 ", TextToSpeech.QUEUE_FLUSH, null);
                    mTotalPrice = mTotalPrice + 500;
                    textView29.setText("가격 : " + myFormatter.format(mTotalPrice) + "원");

                    item2.setSize(tall);
                    item2.setPrice(mTotalPrice);


                } else {
                    textToSpeech.setSpeechRate(0.9f);
                    textToSpeech.speak("최소되었습니다. ", TextToSpeech.QUEUE_FLUSH, null);
                    mTotalPrice = mTotalPrice - 500;
                    textView29.setText("가격 : " + myFormatter.format(mTotalPrice) + "원");

                    item2.setSize(tall);
                    item2.setPrice(mTotalPrice);
                }
                reference.child(key).setValue(item2);
            }
        });

        checkBox6.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (checkBox6.isChecked()) {
                    textToSpeech.setSpeechRate(0.9f);
                    textToSpeech.speak("매장컵을 선택하였습니다.", TextToSpeech.QUEUE_FLUSH, null);
                    item2.setCup(cup1);
                }
                reference.child(key).setValue(item2);
            }
        });

        checkBox7.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (checkBox7.isChecked()) {
                    textToSpeech.setSpeechRate(0.9f);
                    textToSpeech.speak("개인텀블러를 선택하였습니다.", TextToSpeech.QUEUE_FLUSH, null);
                    item2.setCup(cup2);
                }
                reference.child(key).setValue(item2);
            }
        });

        checkBox8.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (checkBox8.isChecked()) {
                    textToSpeech.setSpeechRate(0.9f);
                    textToSpeech.speak("일회용 컵을 선택하였습니다.", TextToSpeech.QUEUE_FLUSH, null);
                    item2.setCup(cup3);
                }
                reference.child(key).setValue(item2);

            }
        });

        checkBox9.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (checkBox9.isChecked()) {
                    textToSpeech.setSpeechRate(0.9f);
                    textToSpeech.speak("휘핑크림을 추가힙니다.", TextToSpeech.QUEUE_FLUSH, null);
                    mTotalPrice = mTotalPrice + 1000;
                    textView29.setText("가격 : " + myFormatter.format(mTotalPrice) + "원");

                    item2.setOption2(option1);
                    item2.setPrice(mTotalPrice);

                } else {
                    textToSpeech.setSpeechRate(0.9f);
                    textToSpeech.speak("취소되었습니다.", TextToSpeech.QUEUE_FLUSH, null);
                    mTotalPrice = mTotalPrice - 1000;
                    textView29.setText("가격 : " + myFormatter.format(mTotalPrice) + "원");
                    item2.setOption2(option1);
                    item2.setPrice(mTotalPrice);
                }
                reference.child(key).setValue(item2);
            }
        });

        checkBox10.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (checkBox10.isChecked()) {
                    textToSpeech.setSpeechRate(0.9f);
                    textToSpeech.speak("샷 추가합니다.", TextToSpeech.QUEUE_FLUSH, null);
                    mTotalPrice = mTotalPrice + 500;
                    textView29.setText("가격 : " + myFormatter.format(mTotalPrice) + "원");

                    item2.setOption2(option2);
                    item2.setPrice(mTotalPrice);

                } else {
                    textToSpeech.setSpeechRate(0.9f);
                    textToSpeech.speak("최소합니다.", TextToSpeech.QUEUE_FLUSH, null);
                    mTotalPrice = mTotalPrice - 500;
                    textView29.setText("가격 : " + myFormatter.format(mTotalPrice) + "원");

                    item2.setOption2(option2);
                    item2.setPrice(mTotalPrice);

                }
                if (checkBox9.isChecked() && checkBox10.isChecked()) {
                    textToSpeech.speak("휘핑크림과 샷추가 선택", TextToSpeech.QUEUE_FLUSH, null);

                    item2.setOption2(option4);
                }
                reference.child(key).setValue(item2);
            }
        });

        checkBox11.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (checkBox11.isChecked()) {
                    textToSpeech.setSpeechRate(0.9f);
                    textToSpeech.speak("시럽을 추가합니다.", TextToSpeech.QUEUE_FLUSH, null);
                    mTotalPrice = mTotalPrice + 500;
                    textView29.setText("가격 : " + myFormatter.format(mTotalPrice) + "원");

                    item2.setOption2(option3);
                    item2.setPrice(mTotalPrice);

                } else {
                    textToSpeech.setSpeechRate(0.9f);
                    textToSpeech.speak("취소되었습니다.", TextToSpeech.QUEUE_FLUSH, null);
                    mTotalPrice = mTotalPrice - 500;
                    textView29.setText("가격 : " + myFormatter.format(mTotalPrice) + "원");

                    item2.setOption2(option3);

                    item2.setPrice(mTotalPrice);

                }
                if (checkBox9.isChecked() && checkBox11.isChecked()) {
                    textToSpeech.speak("휘핑크림과 시럽 추가", TextToSpeech.QUEUE_FLUSH, null);
                    item2.setOption2(option5);

                }
                if (checkBox10.isChecked() && checkBox11.isChecked()) {
                    textToSpeech.speak("샷추가와 시럽추가", TextToSpeech.QUEUE_FLUSH, null);
                    item2.setOption2(option6);

                }
                if (checkBox9.isChecked() && checkBox10.isChecked() && checkBox11.isChecked()) {
                    textToSpeech.speak("휘핑크림,샷추가와 시럽추가", TextToSpeech.QUEUE_FLUSH, null);
                    item2.setOption2(option7);
                }
                reference.child(key).setValue(item2);
            }
        });

        checkBox12.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (checkBox12.isChecked()) {
                    textToSpeech.setSpeechRate(0.9f);
                    textToSpeech.speak("매장식사를 선택하였습니다.", TextToSpeech.QUEUE_FLUSH, null);
                    item2.setOption3(option8);
                }
                reference.child(key).setValue(item2);
            }
        });

        checkBox13.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (checkBox13.isChecked()) {
                    textToSpeech.setSpeechRate(0.9f);
                    textToSpeech.speak("포장하기를 선택하였습니다.", TextToSpeech.QUEUE_FLUSH, null);
                    item2.setOption3(option9);
                }
                reference.child(key).setValue(item2);
            }
        });


        image = findViewById(R.id.imageView8);

        textView6 = findViewById(R.id.textView6);

        textView8 = findViewById(R.id.textView8);

        textView27 = findViewById(R.id.textView27);

        button10 = findViewById(R.id.button10);

        button11 = findViewById(R.id.button11);

        Intent intent = getIntent();
        image.setImageResource(intent.getIntExtra("image", 0));
        String imageUri = getIntent().getStringExtra("imageUri");
        item2.setImage(imageUri);
        reference.child("image");

        String name = getIntent().getStringExtra("name");
        item2.setName(name);
        reference.child("name");

        textView6.setText(name);
        textView8.setText(intent.getStringExtra("info"));
        textView27.setText(intent.getStringExtra("price"));
        mTotalPrice = getIntent().getIntExtra("price2", 0);

        price = getIntent().getIntExtra("price2", 0);

        button7.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                count++;
                button8.setText("" + count);

                mTotalPrice = price * count;
                textView29.setText("가격 : " + myFormatter.format(mTotalPrice) + "원");

                item2.setCount(count);
                item2.setPrice(mTotalPrice);
            }
        });


        button9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (count > 0) {
                    count--;
                    button8.setText("" + count);

                    mTotalPrice = price * count;
                    textView29.setText("가격 : " + myFormatter.format(mTotalPrice) + "원");

                    item2.setCount(count);
                    item2.setPrice(mTotalPrice);
                }
            }
        });

        button10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Cart.class);
                startActivity(intent);
            }
        });

        databaseReference = FirebaseDatabase.getInstance().getReference("order");
        BootpayAnalytics.init(this, "[ 647319fe3049c8001cf8bf57 ]");

        Button button11 = findViewById(R.id.button11);
        button11.setOnClickListener(new View.OnClickListener() {
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
                    textToSpeech.setSpeechRate(0.9f);
                    if (!MainActivity.isTextToSpeechStopped) {
                        textToSpeech.speak("커피주문화면입니다. 선택사항을 체크해주세요", TextToSpeech.QUEUE_FLUSH, null);
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