package org.techtown.afinal;

import android.Manifest;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.speech.tts.TextToSpeech;
import android.telephony.SmsManager;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class Wait extends AppCompatActivity implements TextToSpeech.OnInitListener {

    private TextToSpeech textToSpeech;

    // 다이얼로그 인스턴스 변수
    private AlertDialog dialog;

    private Handler handler;
    private Runnable runnable;

    TextView textView30;
    TextView textView31;

    EditText editText;

    Button button6, button12;

    private int waitingNumber = 1;
    private long dialogDisplayTime = 0; // 다이얼로그 표시 시간

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.wait);

        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        textToSpeech = new TextToSpeech(this, this);

        textView30 = findViewById(R.id.textView30);

        handler = new Handler(); // 핸들러

        // 1초마다 현재 시간 갱신
        runnable = new Runnable() {
            @Override
            public void run() {
                updateTime();
                handler.postDelayed(this, 1000);
            }
        };

        // 액티비티가 화면에 보여질 때마다 시간 업데이트 시작
        handler.postDelayed(runnable, 0);

        textView31 = findViewById(R.id.textView31);
        editText = findViewById(R.id.editText);
        button6 = findViewById(R.id.button6);
        button12 = findViewById(R.id.button12);

        // 이전 화면으로 돌아가기 버튼 클릭 시 대기 번호를 감소시키고 텍스트뷰에 반영
        button12.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (waitingNumber > 0) {
                    waitingNumber--;
                    String formattedNumber = String.format("%03d", waitingNumber);
                    textView31.setText(formattedNumber);

                    // 음성 중지
                    if (textToSpeech != null && textToSpeech.isSpeaking()) {
                        textToSpeech.stop();
                    }

                    // TextToSpeech 인스턴스 종료
                    textToSpeech.shutdown();

                    // 화면 전환 코드
                    Intent intent = new Intent(Wait.this, MainActivity.class);
                    startActivity(intent);
                    finish(); // 현재 액티비티를 종료하여 음성 인식이 중단되도록 함
                }
            }
        });

        // 확인 버튼 클릭 시 대기 번호를 증가시키고 텍스트뷰에 반영
        button6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String phoneNumber = editText.getText().toString();
                if (!phoneNumber.isEmpty()) {
                    if (textToSpeech != null && textToSpeech.isSpeaking()) {
                        textToSpeech.stop();
                    }

                    waitingNumber++;
                    String formattedNumber = String.format("%03d", waitingNumber);
                    textView31.setText(formattedNumber);

                    textToSpeech.setSpeechRate(0.7f);
                    speak("대기번호는 " + formattedNumber + " 번입니다. 주문이 준비되면 알려드리겠습니다.");


                    // 40초 후에 다이얼로그 표시
                    dialogDisplayTime = System.currentTimeMillis() + 20 * 1000;
                }
            }
        });

        // 40초 마다 다이얼로그 표시 확인
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                long currentTime = System.currentTimeMillis();
                if (currentTime >= dialogDisplayTime) {

                    showDialog();
                }
                handler.postDelayed(this, 20000);
            }
        }, 20000);
    }

    // 현재 시간 업데이트하는 메소드
    private void updateTime() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss", Locale.getDefault());
        String currentTime = dateFormat.format(new Date());
        textView30.setText(currentTime);
    }

    private void showDialog() {
        // 다이얼로그 빌더 생성
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        // 레이아웃 인플레이션
        LayoutInflater inflater = getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.wait2, null);

        // 현재 시간 표시
        textView30 = dialogView.findViewById(R.id.textView30);
        updateTime(textView30);

        // 음성 출력
        if (textToSpeech != null && !textToSpeech.isSpeaking()) {
            textToSpeech.setSpeechRate(0.9f);
            textToSpeech.speak("주문하신 제품이 나왔습니다. 이용해주셔서 감사합니다.", TextToSpeech.QUEUE_FLUSH, null, null);
        }

        // 확인 버튼 클릭 리스너 설정
        Button button = dialogView.findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // 대기 번호 초기화
                waitingNumber = 0;
                String formattedNumber = String.format("%03d", waitingNumber);
                textView31.setText(formattedNumber);

                // 버튼 비활성화
                button6.setEnabled(false);
                button12.setEnabled(false);

                // 음성 중지
                if (textToSpeech != null && textToSpeech.isSpeaking()) {
                    textToSpeech.stop();
                }

                // TextToSpeech 인스턴스 종료
                textToSpeech.shutdown();

                Intent intent = new Intent(Wait.this, MainActivity.class);
                startActivity(intent);

                // 다이얼로그 닫기
                dialog.dismiss();
            }
        });

        // 레이아웃 설정
        builder.setView(dialogView);

        // 다이얼로그 생성
        dialog = builder.create();

        // 다이얼로그 표시
        dialog.show();
    }

    private void updateTime(TextView textView30) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss", Locale.getDefault());
        String currentTime = dateFormat.format(new Date());
        textView30.setText(currentTime);

    }

    protected void onDestroy() {
        super.onDestroy();
        if (handler != null) {
            handler.removeCallbacks(runnable);
        }
        if (dialog != null && dialog.isShowing()) {
            dialog.dismiss();
        }

        // 음성 출력 중지 및 TextToSpeech 인스턴스 종료
        if (textToSpeech != null) {
            textToSpeech.stop();
            textToSpeech.shutdown();
        }
    }

    @Override
    public void onInit(int status) {
        if (status == TextToSpeech.SUCCESS) {
            int result = textToSpeech.setLanguage(Locale.KOREA);

            if (result == TextToSpeech.LANG_MISSING_DATA || result == TextToSpeech.LANG_NOT_SUPPORTED) {
                Toast.makeText(this, "지원되지 않는 언어입니다.", Toast.LENGTH_SHORT).show();

            }
        }
    }

    private void speak(String text) {
        if (textToSpeech != null && !textToSpeech.isSpeaking()) {
            textToSpeech.speak(text, TextToSpeech.QUEUE_FLUSH, null, null);
        }
    }
}