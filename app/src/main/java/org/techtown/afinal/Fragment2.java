package org.techtown.afinal;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class Fragment2 extends Fragment {

    Context mContext;
    List<Item> itemList;
    RecyclerView mRecyclerView;
    RecyclerViewAdapter2 mRecyclerAdapter;
    FragmentManager fragmentManager;
    View rootView;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mContext = getActivity().getApplicationContext();
    }




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        rootView = inflater.inflate(R.layout.fragment_2, container, false);

        mRecyclerView = rootView.findViewById(R.id.recyclerView2);
        itemList = new ArrayList<>();

        fragmentManager = getActivity().getSupportFragmentManager();

        itemList.add(new Item("유자\n민트 티", "5,900원", R.drawable.tea2));
        itemList.add(new Item("차이\n티 라떼","5,500원", R.drawable.tea5));
        itemList.add(new Item("돌체 블랙\n밀크 티" ,"5,900원", R.drawable.tea3));
        itemList.add(new Item("자몽 허니\n블랙 티", "5,700원", R.drawable.tea4));
        itemList.add(new Item("얼 그레이 티", "4,500원", R.drawable.tea7));
        itemList.add(new Item("유스베리 티", "4,500원", R.drawable.tea8));
        itemList.add(new Item("히비스커스 블렌드 티", "4,500원", R.drawable.tea9));
        itemList.add(new Item("민트\n블렌드 티","4,500원", R.drawable.tea10));
        itemList.add(new Item("캐모마일\n블렌드 티","4,500원", R.drawable.tea11));
        itemList.add(new Item("제주\n유기 녹차", "5,300원", R.drawable.tea12));
        itemList.add(new Item("잉글리쉬\n브렉퍼스트\n티","4,500원", R.drawable.tea6));
        itemList.add(new Item("제주 유기농\n말차로\n만든 라떼", "6,100원", R.drawable.tea1));

        int img[] = {

                R.drawable.tea2,
                R.drawable.tea5,
                R.drawable.tea3,
                R.drawable.tea4,
                R.drawable.tea7,
                R.drawable.tea8,
                R.drawable.tea9,
                R.drawable.tea10,
                R.drawable.tea11,
                R.drawable.tea12,
                R.drawable.tea6,
                R.drawable.tea1
        };


        String name[] = {

                "유자 민트 티",
                "차이 티 라떼",
                "돌체 블랙 밀크 티" ,
                "자몽 허니 블랙 티",
                "얼 그레이 티",
                "유스베리 티",
                "히비스커스 블렌드 티",
                "민트 블렌드 티",
                "캐모마일 블렌드 티",
                "제주 유기 녹차",
                "잉글리쉬 브렉퍼스트 티",
                "제주 유기농 말차로 만든 라떼"
        };

        String info[] = {

                "달콤한 국내산 고흥 유자와 알싸\n하고 은은한 진저, 우릴 수록 상쾌한 민트 티가 조화로운 유자 민트 티 입니다.",

                "스파이시한 향과 독특한 계피향,\n달콤한 차이로 만든 부드러운 티\n라떼입니다.",

                "진한 홍차에 부드러운 우유와 연유 시럽으로 향긋하고 달콤하게 맛을 낸 스타벅스만의 돌체 블랙 밀크 티입니다.",

                "새콤한 자몽과 달콤한 꿀이 깊고\n그윽한 풍미의 스타벅스 티바나\n블랙 티의 조화",

                "꽃향 가득한 라벤더와 베르가못\n향이 진한 홍차와 블렌딩된 향긋한\n블랙 티입니다.",

                "제주산 유기농 찻잎으로 만든 황차에 사과, 망고, 파인애플, 히비스커스, 로즈힙 등이 블렌딩되어\n핑크빛 컬러가 감도는 수색과 베리류의 새콤함을 느낄 수 있는 옐로우 티입니다.",

                "히비스커스, 사과, 파파야, 망고,\n레몬그라스 등이 블렌딩된 상큼한\n허브 티입니다.",

                "스피어민트, 페퍼민트, 레몬머틀이 블렌딩된 상쾌한 허브 티입니다.",

                "캐모마일과 레몬 그라스, 레몬밤, 히비스커스 등 블렌딩되어 은은\n하고 차분한 향이 기분을 좋게하는\n허브 티입니다.",

                "우수한 품질의 제주도 유기농 녹차로 만든 맑은 수색과 신선한 향,\n맛이 뛰어난 녹차입니다.\n\n티백을 우리는 적정한 시간은 3분입니다.",

                "인도 아삼, 제주도 유기농 홍차가 블렌딩되어 진한 벌꿀향과 그윽한 몰트향이 특징인 블랙 티입니다.",

                "차광재배한 어린 녹찻잎을 곱게\n갈아 깊고 진한 말차 본연의 맛과 향을 부드럽게 즐길 수 있는 제주\n유기농 말차로 만든 라떼입니다."

        };

        String price[] = {

                "5,900원",
                "5,500원",
                "5,900원",
                "5,700원",
                "4,500원",
                "4,500원",
                "4,500원",
                "4,500원",
                "4,500원",
                "5,300원",
                "4,500원",
                "6,100원"
        };

        int price2[] = {

                5900,
                5500,
                5900,
                5700,
                4500,
                4500,
                4500,
                4500,
                4500,
                5300,
                4500,
                6100

        };

        String imageUri[] = {
                "https://firebasestorage.googleapis.com/v0/b/coffee-38c29.appspot.com/o/images%2Ftea2.png?alt=media&token=fc3a353a-d843-4f37-aa93-1b123c57bf23",
                "https://firebasestorage.googleapis.com/v0/b/coffee-38c29.appspot.com/o/images%2Ftea5.png?alt=media&token=3e7702ac-890c-49f5-9f15-0063d30f3986",
                "https://firebasestorage.googleapis.com/v0/b/coffee-38c29.appspot.com/o/images%2Ftea3.png?alt=media&token=d103a4e0-4486-4a52-86b5-66c16c6d36e2",
                "https://firebasestorage.googleapis.com/v0/b/coffee-38c29.appspot.com/o/images%2Ftea4.png?alt=media&token=52157e08-d101-4fde-b670-f78109867c5b",
                "https://firebasestorage.googleapis.com/v0/b/coffee-38c29.appspot.com/o/images%2Ftea7.png?alt=media&token=4fed04d1-2405-4a91-acc6-118864f58442",
                "https://firebasestorage.googleapis.com/v0/b/coffee-38c29.appspot.com/o/images%2Ftea8.png?alt=media&token=293b4ffd-f925-4f14-8088-ad6b8aab20ae",
                "https://firebasestorage.googleapis.com/v0/b/coffee-38c29.appspot.com/o/images%2Ftea9.png?alt=media&token=5b2256ad-a4c8-4c5f-a549-e478317d0702",
                "https://firebasestorage.googleapis.com/v0/b/coffee-38c29.appspot.com/o/images%2Ftea10.png?alt=media&token=4554b6e1-cbdd-4ba4-acb5-bb317bea5d24",
                "https://firebasestorage.googleapis.com/v0/b/coffee-38c29.appspot.com/o/images%2Ftea11.png?alt=media&token=cd5a8f73-2575-47e0-b836-f91ba12da84e",
                "https://firebasestorage.googleapis.com/v0/b/coffee-38c29.appspot.com/o/images%2Ftea12.png?alt=media&token=3e409f69-303b-48f5-8b87-bcf833ff551a",
                "https://firebasestorage.googleapis.com/v0/b/coffee-38c29.appspot.com/o/images%2Ftea6.png?alt=media&token=f0f6ec73-1d8d-4500-b7b6-681f129a123d",
                "https://firebasestorage.googleapis.com/v0/b/coffee-38c29.appspot.com/o/images%2Ftea1.png?alt=media&token=d14cffc5-e005-4594-bb3a-ccf4095184c5"
        };

        mRecyclerAdapter = new RecyclerViewAdapter2(getContext(),itemList, img, name, info, price, price2, imageUri);
        mRecyclerView.setLayoutManager(new GridLayoutManager(getContext(),2));
        mRecyclerView.setAdapter(mRecyclerAdapter);

        return rootView;
    }
}
