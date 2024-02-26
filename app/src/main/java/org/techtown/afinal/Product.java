package org.techtown.afinal;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import android.speech.tts.TextToSpeech;
import android.widget.Button;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;

import java.util.Locale;

public class Product extends AppCompatActivity implements TextToSpeech.OnInitListener {
    private TextToSpeech textToSpeech;
    TextToSpeech tts;
    private SharedPreferences sharedPreferences;
    private boolean isTextToSpeechStopped;
    private Button speakButton;
    public static boolean isTextToSpeechInitialized = false;


    private final String[] PAGE_TITLES = new String[]{

            "커피",
            "차",
            "간식"
    };

    private final Fragment[] PAGES = new Fragment[]{

            new Fragment1(),
            new Fragment2(),
            new Fragment3(),
    };

    private ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.product);
        sharedPreferences = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);
        isTextToSpeechStopped = sharedPreferences.getBoolean("isTextToSpeechStopped", false);
        textToSpeech = new TextToSpeech(this, this);


        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        mViewPager = findViewById(R.id.viewPager);
        mViewPager.setAdapter(new MyPagerAdapter(getSupportFragmentManager()));

        TabLayout tabLayout = findViewById(R.id.tabLayout);
        tabLayout.setupWithViewPager(mViewPager);

    }


    @Override
    public void onInit(int status) {
        if (status == TextToSpeech.SUCCESS) {
            // 언어 설정
            int result = textToSpeech.setLanguage(Locale.KOREA);

            if (result == TextToSpeech.LANG_MISSING_DATA || result == TextToSpeech.LANG_NOT_SUPPORTED) {
                Log.e("TTS", "Language not supported");
            } else {
                // 음성 출력
                isTextToSpeechInitialized = true;
                if (!MainActivity.isTextToSpeechStopped) {
                    textToSpeech.setSpeechRate(0.9f);
                    speak("주문 화면입니다. 메뉴를 선택해주세요");
                }
            }
        } else {
            Log.e("TTS", "Initialization failed");
        }
    }

    private void speak(String text) {
        if (isTextToSpeechInitialized && !isTextToSpeechStopped) {
            textToSpeech.speak(text, TextToSpeech.QUEUE_FLUSH, null, null);
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

    public class MyPagerAdapter extends FragmentPagerAdapter {

        public MyPagerAdapter(FragmentManager fm) {

            super(fm);
        }

        @Override
        public Fragment getItem(int position) {

            return PAGES[position];
        }

        @Override
        public int getCount() {

            return PAGES.length;
        }

        @NonNull
        @Override
        public CharSequence getPageTitle(int position) {

            return PAGE_TITLES[position];
        }
    }
}