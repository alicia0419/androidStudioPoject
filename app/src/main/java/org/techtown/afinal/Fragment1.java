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

public class Fragment1 extends Fragment {

    Context mContext;
    List<Item> itemList;
    RecyclerView mRecyclerView;
    RecyclerViewAdapter mRecyclerAdapter;
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

        rootView = inflater.inflate(R.layout.fragment_1, container, false);

        mRecyclerView = rootView.findViewById(R.id.recyclerView1);
        itemList = new ArrayList<>();

        fragmentManager = getActivity().getSupportFragmentManager();

        itemList.add(new Item("콜드 브루", "4,900원",R.drawable.coldbrew));
        itemList.add(new Item("카푸치노",  "5,000원", R.drawable.espresso8));
        itemList.add(new Item("돌체 라떼", "5,900원", R.drawable.espresso10));
        itemList.add(new Item("카페 라떼", "5,000원", R.drawable.espresso14));
        itemList.add(new Item("카페 모카", "5,500원", R.drawable.espresso17));
        itemList.add(new Item("에스프레소", "4,000원", R.drawable.espresso));
        itemList.add(new Item("돌체\n콜드 브루", "6,000원", R.drawable.coldbrew1));
        itemList.add(new Item("시그니처\n초콜릿", "5,700원", R.drawable.etc1));
        itemList.add(new Item("카라멜\n마키아또", "5,900원", R.drawable.espresso6));
        itemList.add(new Item("카페\n아메리카노",  "4,500원", R.drawable.espresso4));
        itemList.add(new Item("자바 칩\n프라푸치노", "6,300원", R.drawable.frappuccino4));
        itemList.add(new Item("카라멜\n프라푸치노", "5,900원", R.drawable.frappuccino5));
        itemList.add(new Item("망고\n바나나\n블렌디드", "6,300원", R.drawable.blended3));
        itemList.add(new Item("바닐라\n크림\n프라푸치노", "5,100원", R.drawable.frappuccino7));
        itemList.add(new Item("초콜릿\n크림칩\n프라푸치노", "6,000원", R.drawable.frappuccino11));
        itemList.add(new Item("에스프레소\n콘 파나\n", "4,200원", R.drawable.espresso1));
        itemList.add(new Item("에스프레소\n마키아또", "4,000원", R.drawable.espresso2));
        itemList.add(new Item("에스프레소\n프라푸치노", "5,500원", R.drawable.frappuccino3));
        itemList.add(new Item("돌체\n콜드 브루\n오트 라떼\n", "5,800원", R.drawable.coldbrew3));
        itemList.add(new Item("더블\n에스프레소 칩\n프라푸치노",  "6,300원", R.drawable.frappuccino1));
        itemList.add(new Item("화이트\n초콜릿\n모카\n프라푸치노", "6,000원",R.drawable.frappuccino6));
        itemList.add(new Item("딸기\n딜라이트\n요거트\n블렌디드", "6,300원", R.drawable.blended2));
        itemList.add(new Item("화이트\n초콜릿\n모카", "5,900원", R.drawable.espresso19));
        itemList.add(new Item("바닐라\n플랫\n화이트", "5,900원", R.drawable.espresso20));
        itemList.add(new Item("제주 유기농\n말차로\n만든 크림\n프라푸치노", "6,300원", R.drawable.frappuccino10));

        int img[] = {
                R.drawable.coldbrew,
                R.drawable.espresso8,
                R.drawable.espresso10,
                R.drawable.espresso14,
                R.drawable.espresso17,
                R.drawable.espresso,
                R.drawable.coldbrew1,
                R.drawable.etc1,
                R.drawable.espresso6,
                R.drawable.espresso4,
                R.drawable.frappuccino4,
                R.drawable.frappuccino5,
                R.drawable.blended3,
                R.drawable.frappuccino7,
                R.drawable.frappuccino11,
                R.drawable.espresso1,
                R.drawable.espresso2,
                R.drawable.frappuccino3,
                R.drawable.coldbrew3,
                R.drawable.frappuccino1,
                R.drawable.frappuccino6,
                R.drawable.blended2,
                R.drawable.espresso19,
                R.drawable.espresso20,
                R.drawable.frappuccino10
        };

        String name[] = {
                "콜드 브루",
                "카푸치노",
                "돌체 라떼",
                "카페 라떼",
                "카페 모카",
                "에스프레소",
                "돌체 콜드 브루",
                "시그니처 초콜릿",
                "카라멜 마키아또",
                "카페 아메리카노",
                "자바 칩 프라푸치노",
                "카라멜 프라푸치노",
                "망고 바나나 블렌디드",
                "바닐라 크림 프라푸치노",
                "초콜릿 크림 칩\n프라푸치노",
                "에스프레소 콘 파나",
                "에스프레소 마키아또",
                "에스프레소 프라푸치노",
                "돌체 콜드 브루 오트\n라떼",
                "더블 에스프레소 칩\n프라푸치노",
                "화이트 초콜릿 모카\n프라푸치노",
                "딸기 딜라이트 요거트\n블렌디드",
                "화이트 초콜릿 모카",
                "바닐라 플랫 화이트",
                "제주 유기농 말차로 만든 크림 프라푸치노"
        };

        String info[] = {
                "콜드 브루 전용 원두를 차가운\n물로 추출하여 한정된 양만 제공\n됩니다.\n\n깊은 풍미의 새로운 커피 경험을\n즐겨보세요!",

                "풍부하고 진한 에스프레소에\n따뜻한 우유와 벨벳 같은 우유\n거품이 1:1 비율로 어우러져\n마무리된 커피 음료입니다.",

                "다른 커피 음료보다 더욱 깊은\n커피의 맛과 향에 깔끔한 무지방\n우유와 부드러운 돌체 시럽이\n들어간 음료로 달콤하고 진한\n커피 라떼입니다.",

                "풍부하고 진한 에스프레소가\n신선한 스팀 밀크를 만나\n부드러워진 커피 위에 우유 거품을 살짝 얹은 대표적인 커피 라떼\n입니다.",

                "진한 초콜릿 모카 시럽과 풍부한\n에스프레소를 스팀 밀크와 섞어\n휘핑크림으로 마무리한 음료로\n진한 에스프레소와 초콜릿 맛이\n어우러진 커피입니다.",

                "에스프레소는 향기로운\n크레마 층과 바디 층, 하트 층으로 이루어져 있으며,\n입안 가득히 커피와 달콤한\n카라멜 향이 느껴지는 커피\n입니다.",

                "무더운 여름철, 동남아 휴가지에서 즐기는 커피를 떠오르게 하는\n베스트 x 베스트 조합인\n돌체 콜드 브루를 만나보세요!",

                "깊고 진한 초콜릿과 부드러운\n휘핑크림이 입안에서 사르르\n녹는 프리미엄 초콜릿 음료\n입니다.",

                "향긋한 바닐라 시럽과 따뜻한 스팀 밀크 위에 풍성한 우유 거품을 얹고 점을 찍듯이 에스프레소를 부은 후 벌집 모양으로 카라멜 드리즐을\n올린 달콤한 커피입니다.",

                "진한 에스프레소와 뜨거운 물을\n섞어 스타벅스의 깔끔하고 강렬한\n에스프레소를 가장 부드럽게\n잘 느낄 수 있는 커피입니다.",

                "커피, 모카 소스, 진한 초콜릿 칩이 입안 가득 느껴지는 프라푸치노\n입니다.",

                "카라멜과 커피가 어우러진\n프라푸치노입니다.",

                "달콤한 망고 패션 푸르트 주스에\n바나나 1개가 통째로 들어간\n신선한 블렌디드입니다.",

                "신선한 우유와 바닐라 시럽이\n어우러진 크림 프라푸치노\n입니다.",

                "모카 소스와 진한 초콜릿 칩,\n초콜릿 드리즐이 올라간 달콤한\n크림 프라푸치노입니다.",

                "신선한 에스프레소 샷에 풍부한\n휘핑크림을 얹은 커피 음료로서,\n뜨거운 커피의 맛과 차갑고 달콤한\n생크림의 맛을 같이 즐길 수 있는\n커피입니다.",

                "신선한 에스프레소 샷에\n우유 거품을 살짝 얹은 커피 음료\n로써, 강렬한 에스프레소의 맛과\n우유의 부드러움을 같이 즐길 수\n있는 커피입니다.",

                "에스프레소 샷의 강렬함과 약간의 단맛이 어우러진 프라푸치노\n입니다.",

                "콜드 브루의 풍미와 깔끔한 오트\n밀크가 어우러진 달콤 고소한 라떼.\n\n식물성 밀크를 사용해 모든 고객이 부담없이 즐길 수 있는 콜드 브루 음료입니다.",

                "리스트레토 에스프레소 2샷과\n에스프레소 칩, 하프앤하프가\n진하게 어우러진 커피의 기본에\n충실한 프라푸치노입니다.",

                "화이트 초콜릿, 커피와 우유가\n조화로운 프라푸치노입니다.",

                "유산균이 살아있는 리얼 요거트와 풍성한 딸기 과육이 더욱 상큼하게 어우러진 과일 요거트 블렌디드입니다.",

                "달콤하고 부드러운 화이트 초콜릿 시럽과 에스프레소를 스팀 밀크와 섞어 휘핑크림으로 마무리한 음료로 달콤함과 강렬한 에스프레소가 부드럽게 어우러진 커피입니다.",

                "바닐라 플랫 화이트는 진하고\n고소한 리스트레토 샷과 바닐라 시럽\n그리고 스팀 밀크로 즐기는 진한\n커피 라떼입니다.",

                "깊고 진한 말차 본연의 맛과 향을 시원하고 부드럽게 즐길 수 있는\n프라푸치노입니다.",

        };

        String price[] = {

                "4,900원",
                "5,000원",
                "5,900원",
                "5,000원",
                "5,500원",
                "4,000원",
                "6,000원",
                "5,700원",
                "5,900원",
                "4,500원",
                "6,300원",
                "5,900원",
                "6,300원",
                "5,100원",
                "6,000원",
                "4,200원",
                "4,000원",
                "5,500원",
                "5,800원",
                "6,300원",
                "6,000원",
                "6,300원",
                "5,900원",
                "5,900원",
                "6,300원",


        };

        int price2[] = {

                4900,
                5000,
                5900,
                5500,
                4000,
                6000,
                5700,
                5900,
                4500,
                5900,
                6300,
                5100,
                6000,
                4200,
                4000,
                5500,
                5800,
                6300,
                6000,
                6300,
                5900,
                5900,
                6300
        };

        String imageUri[] = {
                "https://firebasestorage.googleapis.com/v0/b/coffee-38c29.appspot.com/o/images%2Fcoldbrew.jpg?alt=media&token=e833f9f8-d6e1-45fe-a37d-8a483f997fe4",
                "https://firebasestorage.googleapis.com/v0/b/coffee-38c29.appspot.com/o/images%2Fespresso8.png?alt=media&token=c140785e-7c37-409d-9031-26ea040bae7e",
                "https://firebasestorage.googleapis.com/v0/b/coffee-38c29.appspot.com/o/images%2Fespresso10.png?alt=media&token=3ab7bf8e-a968-4cd6-aa14-3e88d7bc8d16",
                "https://firebasestorage.googleapis.com/v0/b/coffee-38c29.appspot.com/o/images%2Fespresso14.png?alt=media&token=6b5a36bf-12d7-4515-af08-8c0373691180",
                "https://firebasestorage.googleapis.com/v0/b/coffee-38c29.appspot.com/o/images%2Fespresso17.png?alt=media&token=90733901-836a-40f9-9580-d704b7e06c51",
                "https://firebasestorage.googleapis.com/v0/b/coffee-38c29.appspot.com/o/images%2Fespresso.png?alt=media&token=0d0af371-cfdb-43cf-80c1-6e3a549bacce",
                "https://firebasestorage.googleapis.com/v0/b/coffee-38c29.appspot.com/o/images%2Fcoldbrew1.png?alt=media&token=dcf5ee2d-e929-4d86-93ce-e37d5f56186c",
                "https://firebasestorage.googleapis.com/v0/b/coffee-38c29.appspot.com/o/images%2Fetc1.png?alt=media&token=1cd886d9-f703-49cc-98c7-51f5e6473175",
                "https://firebasestorage.googleapis.com/v0/b/coffee-38c29.appspot.com/o/images%2Fespresso6.png?alt=media&token=b0c898b3-63bc-47f7-8a34-64223bccd28a",
                "https://firebasestorage.googleapis.com/v0/b/coffee-38c29.appspot.com/o/images%2Fespresso4.png?alt=media&token=2513cb6a-ecfa-48aa-a74c-3e1c5290a0dd",
                "https://firebasestorage.googleapis.com/v0/b/coffee-38c29.appspot.com/o/images%2Ffrappuccino4.png?alt=media&token=8cc2b695-2908-48db-8cfc-a5ab15494b57",
                "https://firebasestorage.googleapis.com/v0/b/coffee-38c29.appspot.com/o/images%2Ffrappuccino5.png?alt=media&token=f4a982a1-c4ec-4b14-8cc2-21b151fae9fd",
                "https://firebasestorage.googleapis.com/v0/b/coffee-38c29.appspot.com/o/images%2Fblended3.png?alt=media&token=835dab31-be19-4cb3-b163-cda933fcd9c6",
                "https://firebasestorage.googleapis.com/v0/b/coffee-38c29.appspot.com/o/images%2Ffrappuccino7.png?alt=media&token=f3088129-3749-4397-a1f3-901f3aea7ce7",
                "https://firebasestorage.googleapis.com/v0/b/coffee-38c29.appspot.com/o/images%2Ffrappuccino11.png?alt=media&token=02c21601-62c6-4a78-a586-a617166e080b",
                "https://firebasestorage.googleapis.com/v0/b/coffee-38c29.appspot.com/o/images%2Fespresso1.png?alt=media&token=9a34adc6-9ef5-4ddb-a636-0b3e57de6a0c",
                "https://firebasestorage.googleapis.com/v0/b/coffee-38c29.appspot.com/o/images%2Fespresso2.png?alt=media&token=8a50aa3d-d9bb-49ca-b7c3-ae8a550975e4",
                "https://firebasestorage.googleapis.com/v0/b/coffee-38c29.appspot.com/o/images%2Ffrappuccino3.png?alt=media&token=52031ebf-c0e2-47ca-9d03-b174a86a85a4",
                "https://firebasestorage.googleapis.com/v0/b/coffee-38c29.appspot.com/o/images%2Fcoldbrew3.png?alt=media&token=791cf519-cdaf-4e26-8c8d-c243e3dc2e70",
                "https://firebasestorage.googleapis.com/v0/b/coffee-38c29.appspot.com/o/images%2Ffrappuccino1.png?alt=media&token=ee41aa13-0c6b-47d5-a717-9d0ac2fb31b5",
                "https://firebasestorage.googleapis.com/v0/b/coffee-38c29.appspot.com/o/images%2Ffrappuccino6.png?alt=media&token=295b2d6e-0c4f-4b79-87d8-b78e74011060",
                "https://firebasestorage.googleapis.com/v0/b/coffee-38c29.appspot.com/o/images%2Fblended2.png?alt=media&token=aa11b36e-d452-4259-8f58-9569eb4bdeb0",
                "https://firebasestorage.googleapis.com/v0/b/coffee-38c29.appspot.com/o/images%2Fespresso19.png?alt=media&token=a4725b03-3bfb-4bc2-aa5a-0afb3edfab14",
                "https://firebasestorage.googleapis.com/v0/b/coffee-38c29.appspot.com/o/images%2Fespresso20.png?alt=media&token=602b6ad2-e058-4031-a976-9866ae802b39",
                "https://firebasestorage.googleapis.com/v0/b/coffee-38c29.appspot.com/o/images%2Ffrappuccino10.png?alt=media&token=b3268399-2f68-448d-8f05-de95b6f49c54"
        };


        mRecyclerAdapter = new RecyclerViewAdapter(getContext(),itemList, img,  name, info, price, price2, imageUri);
        mRecyclerView.setLayoutManager(new GridLayoutManager(getContext(),2));
        mRecyclerView.setAdapter(mRecyclerAdapter);

        return rootView;
    }
}