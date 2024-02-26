package org.techtown.afinal;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;


import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.MissingResourceException;

public class CartAdapter<textToSpeech> extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static final int TYPE_LAYOUT_1 = 1;
    private static final int TYPE_LAYOUT_2 = 2;
    private static final int TYPE_LAYOUT_3 = 3;

    private List<Item3> item3List;
    private DatabaseReference databaseReference4;
    private TextToSpeech textToSpeech;
    TextToSpeech tts;


    public CartAdapter() {
        item3List = new ArrayList<>();
    }

    public void setItem3List(List<Item3> item3List) {
        this.item3List = item3List;
        notifyDataSetChanged();

    }

    @Override
    public int getItemViewType(int position) {
        // 데이터의 타입에 따라 레이아웃의 종류를 반환합니다.
        Item3 item3 = item3List.get(position);
        if (item3.getType() == 1) {
            return TYPE_LAYOUT_1;
        } else if (item3.getType() == 2) {
            return TYPE_LAYOUT_2;
        } else {
            return TYPE_LAYOUT_3;
        }
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view;
        RecyclerView.ViewHolder viewHolder;

        switch (viewType) {
            case TYPE_LAYOUT_1:
                view = inflater.inflate(R.layout.cart_item, parent, false);
                viewHolder = new Layout1ViewHolder(view);
                break;
            case TYPE_LAYOUT_2:
                view = inflater.inflate(R.layout.cart_item2, parent, false);
                viewHolder = new Layout2ViewHolder(view);
                break;
            case TYPE_LAYOUT_3:
                view = inflater.inflate(R.layout.cart_item3, parent, false);
                viewHolder = new Layout3ViewHolder(view);
                break;
            default:
                throw new IllegalArgumentException("Invalid view type");
        }

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, @SuppressLint("RecyclerView") int position) {

        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference4 = firebaseDatabase.getReference("order");

        Item3 item3 = item3List.get(position);

        DecimalFormat myFormatter = new DecimalFormat("###,###");
        String formatStringformat = myFormatter.format(item3.getPrice());


        switch (holder.getItemViewType()) {
            case TYPE_LAYOUT_1:
                Layout1ViewHolder layout1ViewHolder = (Layout1ViewHolder) holder;
                // Layout 1에 대한 데이터 바인딩 작업 수행

                layout1ViewHolder.textView3.setText(item3.getName());
                layout1ViewHolder.textView4.setText("옵션 1 : " + item3.getOption1());
                layout1ViewHolder.textView5.setText("수량 : " + String.valueOf(item3.getCount()));

                layout1ViewHolder.textView6.setText("사이즈 : " + item3.getSize());
                layout1ViewHolder.textView7.setText("컵 선택 : " + item3.getCup());

                DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("order").child(item3.getKey()).child("option2");
                databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        String option2 = snapshot.getValue(String.class);
                        if (option2 == null) {
                            layout1ViewHolder.textView8.setText("옵션 2 : ");
                        } else {
                            layout1ViewHolder.textView8.setText("옵션 2 : " + "\n" + item3.getOption2());
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });

                layout1ViewHolder.textView9.setText("포장 옵션 : " + item3.getOption3());
                layout1ViewHolder.textView10.setText("가격 : " +myFormatter.format(item3.getPrice()) + "원");

                Glide.with(layout1ViewHolder.itemView)
                        .load(item3.getImage())
                        .into(layout1ViewHolder.image2);

                tts = new TextToSpeech(holder.itemView.getContext(), new TextToSpeech.OnInitListener() {
                    @Override
                    public void onInit(int status) {
                        if (status == TextToSpeech.SUCCESS) {
                            int result = tts.setLanguage(Locale.KOREA);
                            if (result == TextToSpeech.LANG_MISSING_DATA || result == TextToSpeech.LANG_NOT_SUPPORTED) {
                                Log.e("TTS", "지원되지 않는 언어입니다.");
                            }
                        } else {
                            Log.e("TTS", "TTS 초기화 실패");
                        }
                    }
                });

                layout1ViewHolder.button18.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        tts.setSpeechRate(0.9f);
                        if (tts!= null &&!tts.isSpeaking()) {
                            tts.stop();
                        }
                        tts.speak("주문이 취소되었습니다.", TextToSpeech.QUEUE_FLUSH, null);
                        item3List.remove(position);
                        notifyItemRemoved(position);
                        notifyItemRangeChanged(position, item3List.size());
                        databaseReference4.child(item3.getKey()).removeValue();

                    }

                });

                layout1ViewHolder.button19.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent;//인텐트 선언
                        intent = new Intent(v.getContext(), Product.class);
                        v.getContext().startActivity(intent); //액티비티 열기

                    }
                });
                break;

            case TYPE_LAYOUT_2:
                Layout2ViewHolder layout2ViewHolder = (Layout2ViewHolder) holder;
                // Layout 2에 대한 데이터 바인딩 작업 수행

                layout2ViewHolder.textView3.setText(item3.getName());
                layout2ViewHolder.textView4.setText("옵션 1 : " + item3.getOption1());
                layout2ViewHolder.textView5.setText("수량 : " + String.valueOf(item3.getCount()) + "개");
                layout2ViewHolder.textView6.setText("사이즈 : " + item3.getSize());
                layout2ViewHolder.textView7.setText("컵 선택 : " + item3.getCup());
                layout2ViewHolder.textView8.setText("포장 옵션 : " + item3.getOption3());
                layout2ViewHolder.textView9.setText("가격 : " +myFormatter.format(item3.getPrice()) + "원");

                Glide.with(layout2ViewHolder.itemView)
                        .load(item3.getImage())
                        .into(layout2ViewHolder.image2);

                tts = new TextToSpeech(holder.itemView.getContext(), new TextToSpeech.OnInitListener() {
                    @Override
                    public void onInit(int status) {
                        if (status == TextToSpeech.SUCCESS) {
                            int result = tts.setLanguage(Locale.KOREA);
                            if (result == TextToSpeech.LANG_MISSING_DATA || result == TextToSpeech.LANG_NOT_SUPPORTED) {
                                Log.e("TTS", "지원되지 않는 언어입니다.");
                            }
                        } else {
                            Log.e("TTS", "TTS 초기화 실패");
                        }
                    }
                });


                layout2ViewHolder.button18.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        tts.setSpeechRate(0.9f);
                        if (tts!= null &&!tts.isSpeaking()) {
                            tts.stop();
                        }
                        tts.speak("주문이 취소되었습니다.", TextToSpeech.QUEUE_FLUSH, null);
                        item3List.remove(position);
                        notifyItemRemoved(position);
                        notifyItemRangeChanged(position, item3List.size());
                        databaseReference4.child(item3.getKey()).removeValue();
                    }

                });

                layout2ViewHolder.button19.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent;//인텐트 선언
                        intent = new Intent(v.getContext(), Product.class);
                        v.getContext().startActivity(intent); //액티비티 열기

                    }
                });
                break;

            case TYPE_LAYOUT_3:
                Layout3ViewHolder layout3ViewHolder = (Layout3ViewHolder) holder;
                // Layout 3에 대한 데이터 바인딩 작업 수행

                layout3ViewHolder.textView3.setText(item3.getName());

                DatabaseReference databaseReference2 = FirebaseDatabase.getInstance().getReference("order").child(item3.getKey()).child("option1");
                databaseReference2.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        String option1 = snapshot.getValue(String.class);
                        if (option1 == null) {
                            layout3ViewHolder.textView4.setText("옵션 1 : ");
                        } else {
                            layout3ViewHolder.textView4.setText("옵션 1 : " + item3.getOption1());
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });

                layout3ViewHolder.textView5.setText("수량 : " + String.valueOf(item3.getCount()) + "개");
                layout3ViewHolder.textView6.setText("포장 옵션 : " + item3.getOption3());
                layout3ViewHolder.textView7.setText("가격 : " +myFormatter.format(item3.getPrice()) + "원");

                Glide.with(layout3ViewHolder.itemView)
                        .load(item3.getImage())
                        .into(layout3ViewHolder.image2);

                tts = new TextToSpeech(holder.itemView.getContext(), new TextToSpeech.OnInitListener() {
                    @Override
                    public void onInit(int status) {
                        if (status == TextToSpeech.SUCCESS) {
                            int result = tts.setLanguage(Locale.KOREA);
                            if (result == TextToSpeech.LANG_MISSING_DATA || result == TextToSpeech.LANG_NOT_SUPPORTED) {
                                Log.e("TTS", "지원되지 않는 언어입니다.");
                            }
                        } else {
                            Log.e("TTS", "TTS 초기화 실패");
                        }
                    }
                });

                layout3ViewHolder.button18.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        tts.setSpeechRate(0.9f);
                        if (tts!= null &&!tts.isSpeaking()) {
                            tts.stop();
                        }
                        tts.speak("주문이 취소되었습니다.", TextToSpeech.QUEUE_FLUSH, null);
                        item3List.remove(position);
                        notifyItemRemoved(position);
                        notifyItemRangeChanged(position, item3List.size());
                        databaseReference4.child(item3.getKey()).removeValue();
                    }
                });

                layout3ViewHolder.button19.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent;//인텐트 선언
                        intent = new Intent(v.getContext(), Product.class);
                        v.getContext().startActivity(intent); //액티비티 열기

                    }
                });
                break;
            default:
                throw new IllegalArgumentException("Invalid view type");
        }
    }

    @Override
    public int getItemCount() {

        return item3List.size();
    }

    private class Layout1ViewHolder extends RecyclerView.ViewHolder {

        public TextView textView3, textView4, textView5, textView6,
                textView7,textView8, textView9, textView10;

        public ImageView image2;

        public Button button18, button19;

        public Layout1ViewHolder(View itemView) {
            super(itemView);

            textView3 = itemView.findViewById(R.id.textView3);
            textView4 = itemView.findViewById(R.id.textView4);
            textView5 = itemView.findViewById(R.id.textView5);
            textView6 = itemView.findViewById(R.id.textView6);
            textView7 = itemView.findViewById(R.id.textView7);
            textView8 = itemView.findViewById(R.id.textView8);
            textView9 = itemView.findViewById(R.id.textView9);
            textView10 = itemView.findViewById(R.id.textView10);

            image2 = itemView.findViewById(R.id.image2);

            button18 = itemView.findViewById(R.id.button18);
            button19 = itemView.findViewById(R.id.button19);
        }
    }

    private class Layout2ViewHolder extends RecyclerView.ViewHolder {

        public TextView textView3, textView4, textView5, textView6,
                textView7,textView8, textView9;

        public ImageView image2;

        public Button button18, button19;

        public Layout2ViewHolder(View itemView) {
            super(itemView);

            textView3 = itemView.findViewById(R.id.textView3);
            textView4 = itemView.findViewById(R.id.textView4);
            textView5 = itemView.findViewById(R.id.textView5);
            textView6 = itemView.findViewById(R.id.textView6);
            textView7 = itemView.findViewById(R.id.textView7);
            textView8 = itemView.findViewById(R.id.textView8);
            textView9 = itemView.findViewById(R.id.textView9);

            image2 = itemView.findViewById(R.id.image2);

            button18 = itemView.findViewById(R.id.button18);
            button19 = itemView.findViewById(R.id.button19);
        }
    }

    private class Layout3ViewHolder extends RecyclerView.ViewHolder {
        public TextView textView3, textView4, textView5, textView6, textView7;

        public ImageView image2;

        public Button button18, button19;

        public Layout3ViewHolder(View itemView) {
            super(itemView);

            textView3 = itemView.findViewById(R.id.textView3);
            textView4 = itemView.findViewById(R.id.textView4);
            textView5 = itemView.findViewById(R.id.textView5);
            textView6 = itemView.findViewById(R.id.textView6);
            textView7 = itemView.findViewById(R.id.textView7);

            image2 = itemView.findViewById(R.id.image2);

            button18 = itemView.findViewById(R.id.button18);
            button19 = itemView.findViewById(R.id.button19);

        }
    }
}