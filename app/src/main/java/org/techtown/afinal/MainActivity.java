package org.techtown.afinal;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;

import java.util.Locale;

public class MainActivity extends AppCompatActivity implements TextToSpeech.OnInitListener {

    private TextToSpeech textToSpeech;
    private Button speakButton;
    private DelayAndSpeak delayAndSpeak;
    public static boolean isTextToSpeechStopped = false;
    private SharedPreferences sharedPreferences;
    public static boolean isTextToSpeechInitialized = false;


    Dialog dialog01;
    TextToSpeech tts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textToSpeech = new TextToSpeech(this, this);
        sharedPreferences = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);


        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        Button button2 = findViewById(R.id.button2);
        button2.setOnClickListener(new View.OnClickListener() { // 클릭리스너 장착
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Product.class);
                startActivity(intent);
            }
        });

        Button button3 = findViewById(R.id.button3);
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Wait.class);
                startActivity(intent);
            }
        });

        Button stopButton = findViewById(R.id.stopButton);
        stopButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                stopTextToSpeech();
                stopButton.setEnabled(false); // 버튼 비활성화
                isTextToSpeechStopped = true; // 음성 중지 변수 업데이트

            }
        });
    }

    private void stopTextToSpeech() {
        if (textToSpeech != null && textToSpeech.isSpeaking()) {
            textToSpeech.stop();
        }
    }
    private void saveTextToSpeechState(boolean isStopped) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean("isTextToSpeechStopped", isStopped);
        editor.apply();
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (textToSpeech != null) {
            textToSpeech.stop();
            textToSpeech.shutdown();
        }
    }




            @Override
            public void onInit(int status) {
                if (status == TextToSpeech.SUCCESS) {
                    // 언어 설정
                    int result = textToSpeech.setLanguage(Locale.KOREA);

                    if (result == TextToSpeech.LANG_MISSING_DATA || result == TextToSpeech.LANG_NOT_SUPPORTED) {
                        Log.e("TTS", "Language not supported");
                    } else {
                        // 자동 음성 출력
                        if (!isTextToSpeechStopped) {
                            textToSpeech.setSpeechRate(0.9f);
                            speak("안녕하세요 달보드레 카페 앱에 오신걸 환영합니다.주문을 원하시면 주문하기를 눌러주세요. 음성인식을 원하지 않으시다면 음성인식 버튼을 눌러주세요");
                             }
                        }
                } else {
                    Log.e("TTS", "Initialization failed");
                }
            }
            private void speak(String text) {
                if (!isTextToSpeechStopped) {
                    // 텍스트를 음성으로 변환하여 출력
                    textToSpeech.speak(text, TextToSpeech.QUEUE_FLUSH, null, null);
                }

            }
    @Override
    protected void onPause() {
        super.onPause();
        if (textToSpeech != null) {
            textToSpeech.stop();
        }
    }
    @Override
    protected void onResume() {
        super.onResume();
        if (textToSpeech != null && !textToSpeech.isSpeaking()) {
            // 이어서 음성을 출력하거나 새로운 음성을 출력할 수 있습니다.
        }
    }
}





