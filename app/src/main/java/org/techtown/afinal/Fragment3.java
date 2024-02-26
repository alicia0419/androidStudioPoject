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

public class Fragment3 extends Fragment {

    Context mContext;
    List<Item> itemList ;
    RecyclerView mRecyclerView;
    RecyclerViewAdapter3 mRecyclerAdapter;
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

        rootView = inflater.inflate(R.layout.fragment_3, container, false);

        mRecyclerView = rootView.findViewById(R.id.recyclerView3);
        itemList = new ArrayList<>();

        fragmentManager = getActivity().getSupportFragmentManager();

        itemList.add(new Item("너티\n크루아상", "4,900원", R.drawable.bread1));
        itemList.add(new Item("클래식\n스콘", "3,300원", R.drawable.bread6));
        itemList.add(new Item("리얼\n블루베리\n베이글", "3,000원", R.drawable.bread2));
        itemList.add(new Item("미니\n클래식\n스콘", "4,000원", R.drawable.bread5));
        itemList.add(new Item("바질\n토마토\n크림치즈\n베이글", "5,300원", R.drawable.bread4));
        itemList.add(new Item("매콤\n소시지\n불고기\n베이크", "4,800원", R.drawable.bread10));
        itemList.add(new Item("미니\n리프 파이", "3,900원", R.drawable.bread11));
        itemList.add(new Item("연유\n밀크모닝", "4,300원", R.drawable.bread15));
        itemList.add(new Item("에그에그\n샌드위치", "4,400원", R.drawable.sandwich8));
        itemList.add(new Item("탕종 땅콩\n샌드위치", "5,300원", R.drawable.bread21));
        itemList.add(new Item("하트 파이\n", "3,200원", R.drawable.bread23));
        itemList.add(new Item("한 입에 쏙\n치즈 꿀 볼", "4,700원", R.drawable.bread24));
        itemList.add(new Item("우리\n단호박\n보늬밤\n브레드", "4,500원", R.drawable.bread18));
        itemList.add(new Item("부드러운\n고구마\n생크림\n케이크","5,900원", R.drawable.cake2));
        itemList.add(new Item("레드벨벳\n크림치즈\n케이크", "5,500원", R.drawable.cake4));
        itemList.add(new Item("7 레이어\n가나슈\n케이크", "5,700원", R.drawable.cake12));
        itemList.add(new Item("바비큐\n치킨\n치즈\n치아바타", "5,800원", R.drawable.sandwich5));
        itemList.add(new Item("The 촉촉\n초콜릿\n생크림\n케이크", "5,900원", R.drawable.cake13));
        itemList.add(new Item("마스카포네\n티라미수\n케이크", "5,900원", R.drawable.cake15));
        itemList.add(new Item("번트\n치즈\n케이크", "6,900원", R.drawable.cake18));
        itemList.add(new Item("부드러운\n생크림\n카스텔라", "4,500원", R.drawable.cake19));
        itemList.add(new Item("부드러운\n티라미수\n롤","5,900원", R.drawable.cake20));
        itemList.add(new Item("블루베리\n쿠키 치즈\n케이크", "6,900원", R.drawable.cake21));
        itemList.add(new Item("슈크림\n가득\n바움쿠헨", "6,900원", R.drawable.cake22));
        itemList.add(new Item("클라우드\n치즈\n케이크", "5,500원", R.drawable.cake23));
        itemList.add(new Item("더블 에그\n샐러드\n밀 박스", "6,500원", R.drawable.salad1));
        itemList.add(new Item("베이컨\n치즈\n토스트", "4,900원", R.drawable.sandwich6));
        itemList.add(new Item("크랜베리\n치킨 치즈\n샌드위치", "4,500원", R.drawable.sandwich11));
        itemList.add(new Item("콥&화이트\n샐러드\n밀 박스", "6,500원", R.drawable.salad3));
        itemList.add(new Item("트리플 치즈\n크로크 무슈\n", "5,200원", R.drawable.sandwich12));
        itemList.add(new Item("유기농\n아이스크림\n바닐라", "2,900원", R.drawable.icecream1));
        itemList.add(new Item("유기농\n아이스크림\n초콜릿", "2,900원", R.drawable.icecream2));
        itemList.add(new Item("자바 칩\n유기농\n아이스크림\n바닐라", "3,600원", R.drawable.icecream3));
        itemList.add(new Item("자바 칩\n유기농\n아이스크림\n초콜릿", "3,600원", R.drawable.icecream4));
        itemList.add(new Item("B.E.L.T\n샌드위치", "5,900원", R.drawable.sandwich2));


        int img[] = {

                R.drawable.bread1,
                R.drawable.bread6,
                R.drawable.bread2,
                R.drawable.bread5,
                R.drawable.bread4,
                R.drawable.bread10,
                R.drawable.bread11,
                R.drawable.bread15,
                R.drawable.sandwich8,
                R.drawable.bread21,
                R.drawable.bread23,
                R.drawable.bread24,
                R.drawable.bread18,
                R.drawable.cake2,
                R.drawable.cake4,
                R.drawable.cake12,
                R.drawable.sandwich5,
                R.drawable.cake13,
                R.drawable.cake15,
                R.drawable.cake18,
                R.drawable.cake19,
                R.drawable.cake20,
                R.drawable.cake21,
                R.drawable.cake22,
                R.drawable.cake23,
                R.drawable.salad1,
                R.drawable.sandwich6,
                R.drawable.sandwich11,
                R.drawable.salad3,
                R.drawable.sandwich12,
                R.drawable.icecream1,
                R.drawable.icecream2,
                R.drawable.icecream3,
                R.drawable.icecream4,
                R.drawable.sandwich2
        };


        String name[] = {

                "너티 크루아상",
                "클래식 스콘",
                "리얼 블루베리 베이글",
                "미니 클래식 스콘",
                "바질 토마토 크림치즈\n베이글",
                "매콤 소시지 불고기\n베이크",
                "미니 리프 파이",
                "연유 밀크모닝",
                "에그에그 샌드위치",
                "탕종땅콩 샌드위치",
                "하트 파이",
                "한 입에 쏙 치즈 꿀 볼",
                "우리 단호박 보늬밤\n브레드",
                "부드러운 고구마 생크림 케이크",
                "레드벨벳 크림치즈\n케이크",
                "7 레이어 가나슈 케이크",
                "바비큐 치킨 치즈\n치아바타",
                "The 촉촉 초콜릿 생크림 케이크",
                "마스카포네 티라미수\n케이크",
                "번트 치즈 케이크",
                "부드러운 생크림\n카스텔라",
                "부드러운 티라미수 롤",
                "블루베리 쿠키 치즈\n케이크",
                "슈크림 가득 바움쿠헨",
                "클라우드 치즈 케이크",
                "더블 에그 샐러드 밀\n박스",
                "베이컨 치즈 토스트",
                "크랜베리 치킨 치즈\n샌드위치",
                "콥 & 화이트 샐러드 밀 박스",
                "트리플 치즈 크로크무슈",
                "유기농 아이스크림\n바닐라",
                "유기농 아이스크림\n초콜릿",
                "자바 칩 유기농\n아이스크림 바닐라",
                "자바 칩 유기농\n아이스크림 초콜릿",
                "B.E.L.T 샌드위치"
        };

        String info[] = {

                "달콤하고 고소한 견과류가 토핑된 발효버터 풍미의 바삭하고 촉촉한 식감의 정통 크루아상입니다.",

                "프랑스산 고급 버터로 만든 담백한 스콘입니다.",

                "블루베리의 상큼한 풍미가 매력\n적인 베이글로 국내산 감자, 보리\n가루를 넣어 더욱 촉촉하고 건강\n하게 만들었습니다.",

                "미니 사이즈의 담백한 스콘입니다.",

                "바질, 썬드라이 토마토, 크림치즈의 조합이 이색적인 베이글로 크림\n치즈가 듬뿍 샌드되어 든든하게\n즐길 수 있습니다.",

                "할라피뇨로 매콤한 맛을 낸 소시지 불고기 베이크입니다.",

                "버터 풍미가 가득한 나뭇잎 모양의 바삭 바삭한 파이입니다.",

                "부드럽고 폭신한 질감의 빵 안에\n연유 버터크림이 들어있어 따뜻\n하게 드시면 크림이 사르르 녹아\n더욱 맛있게 드실 수 있습니다.",

                "화이트 식빵 사이에 고소한 에그\n스프레드를 넣은 부드러운\n샌드위치입니다.",

                "탕종법으로 만든 촉촉하고 풍미\n있는 탕종식빵에 땅콩과 버터를\n가미한 고소한 크림을 샌드하여\n부드럽고 쫄깃한 식감이 매력적인\n브레드입니다.",

                "하트 모양의 바삭한 파이입니다.",

                "네 가지의 다양한 치즈가 들어가\n짭짤한 맛과 달콤한 아카시아 꿀이\n더해져 단짠의 조화가 매력적인\n브레드입니다!",

                "우리 농산물 국산 단호박 보늬밤\n찹쌀가루로 만들어 더욱 맛있는\n식사대용 미니 식빵입니다.\n\n지구를 위한 이유 있는 편식,\nPlant Based Food 입니다.",

                "부드러운 고구마 무스와 조각 형태의 고구마를 화이트 케이크 시트\n사이에 듬뿍 넣고 생크림을 올린\n달콤하고 고소한 케이크입니다.\n\n" +
                        "고구마에 포함된 폴리페놀이 공기와 접촉하게 되면 산화반응을 일으켜 푸른 빛을 띌 수 있습니다.\n\n" +
                        "제품 섭취 시 보이는 섬유질은\n고구마의 심지입니다.",

                "레드벨벳 시트 사이에 크림치즈\n무스를 샌드한 케이크입니다.",

                "초콜릿, 가나슈, 모카로 만든 시트와 크림이 7개의 층을 이루어 모양부터 매력적인 케이크입니다.",

                "바삭한 치아바타에 새콤달콤한\n바비큐 소스 치킨, 베이컨, 치즈가\n어우러진 샌드위치입니다.",

                "달콤한 초콜릿 케이크 시트에 진한 가나슈 생크림을 넣고 측면에 다크 초콜릿을 듬뿍 토핑한 달콤하고\n촉촉한 초콜릿 케이크입니다.",

                "고소한 마스카포네 치즈 크림에\n촉촉한 커피 시트가 입안을 감싸는\n기분 좋은 느낌의 떠먹는 티라미수\n입니다.",

                "크림치즈가 듬뿍 들어간 케이크를 높은 온도로 짧은 시간 구워 낸\n진한 맛의 치즈 케이크입니다.",

                "부드러운 생크림이 듬뿍 들어있는 촉촉한 카스텔라입니다.",

                "달콤한 초코 케이크 시트에 고소한 마스카포네 크림을 듬뿍 넣은\n부드러운 롤 케이크입니다.",

                "고소한 쿠키와 크림치즈를 듬뿍\n넣어 만든 케이크위에 달콤, 상큼한 블루베리가 올라간 진한 치즈 풍미의 케이크입니다.",

                "바닐라 빈이 들어간 부드러운\n슈크림과 바움쿠헨이 조화로운\n바닐라 풍미의 케이크입니다.",

                "사워크림의 상큼함과 진한 치즈의 맛을 동시에 맛볼 수 있는 케이크\n입니다.",

                "스마트 팜에서 재배한 프릴아이스 채소로 신선함과 아삭함을 더하였으며 반숙란 두개와 부드러운\n닭가슴살 그리고 반건조 토마토를\n넣어 영양 가득한 한끼를 책임지는\n샐러드입니다.",

                "계란과 우유를 적신 빵에 베이컨과 치즈, 에그 스프레드를 넣어 구운 프렌치 토스트 타입 샌드위치\n입니다.",

                "건강하고 고소한 곡물 식빵 안에\n크랜베리 치킨 스프레드와 토마토,\n치즈를 넣어 맛이 더욱 풍부해진\n콜드 샌드위치입니다.",

                "닭가슴살, 새우 그리고 반숙란이\n화이트 발사믹 드레싱과 어우러져\n가벼운 한 끼를 책임질 콥 샐러드\n밀 박스입니다.",

                "모짜렐라 치즈, 라티지아노 치즈, 콜비잭 치즈등 세가지 치즈를 사용하여 풍부한 치즈의 풍미를 즐길 수 있으며 할라피뇨로 매콤함을 더한 크로크무슈입니다.",

                "부드럽고 깔끔한 맛의 유기농\n아이스크림을 즐기세요.",

                "부드럽고 깔끔한 맛의 유기농\n아이스크림을 즐기세요.",

                "유기농 아이스크림에 달콤한 자바 칩(초콜릿 칩)을 토핑하였습니다.",

                "유기농 아이스크림에 달콤한 자바 칩(초콜릿 칩)을 토핑하였습니다.",

                "주 재료인 베이컨(Bacon), 계란(Egg), 로메인 상추(Lettuce-Romane), 토마토(Tomato)의 각각의 머리 글자를 따온 이름의 샌드위치 입니다."

        };

        String price[] = {

                "4,900원",
                "3,300원",
                "3,000원",
                "4,000원",
                "5,300원",
                "4,800원",
                "3,900원",
                "4,300원",
                "4,400원",
                "5,300원",
                "3,200원",
                "4,700원",
                "4,500원",
                "5,900원",
                "5,500원",
                "5,700원",
                "5,800원",
                "5,900원",
                "5,900원",
                "6,900원",
                "4,500원",
                "5,900원",
                "6,900원",
                "6,900원",
                "5,500원",
                "6,500원",
                "4,900원",
                "4,500원",
                "6,500원",
                "5,200원",
                "2,900원",
                "2,900원",
                "3,600원",
                "3,600원",
                "5,900원"
        };

        int price2[] = {

                4900,
                3300,
                3000,
                4000,
                5300,
                4800,
                3900,
                4300,
                4400,
                5300,
                3200,
                4700,
                4500,
                5900,
                5500,
                5700,
                5800,
                5900,
                5900,
                6900,
                4500,
                5900,
                6900,
                6900,
                5500,
                6500,
                4900,
                4500,
                6500,
                5200,
                2900,
                2900,
                3600,
                3600,
                5900
        };

        String imageUri[] = {
                "https://firebasestorage.googleapis.com/v0/b/coffee-38c29.appspot.com/o/images%2Fbread1.png?alt=media&token=5b1cd352-ff9f-460e-a267-ef1cdcf83740",
                "https://firebasestorage.googleapis.com/v0/b/coffee-38c29.appspot.com/o/images%2Fbread6.png?alt=media&token=b7e232e7-d4b8-4569-bfa6-87288fafa714",
                "https://firebasestorage.googleapis.com/v0/b/coffee-38c29.appspot.com/o/images%2Fbread2.png?alt=media&token=1ec2dba3-2c67-42f3-a2af-4668b21eb677",
                "https://firebasestorage.googleapis.com/v0/b/coffee-38c29.appspot.com/o/images%2Fbread5.png?alt=media&token=cbe463cb-5e40-443b-a23f-280372c1beed",
                "https://firebasestorage.googleapis.com/v0/b/coffee-38c29.appspot.com/o/images%2Fbread4.png?alt=media&token=1cc97a4c-5b2d-42a8-b93a-0b9879701d40",
                "https://firebasestorage.googleapis.com/v0/b/coffee-38c29.appspot.com/o/images%2Fbread10.png?alt=media&token=28c65b5b-1cd4-467e-985d-3e97a93bf48c",
                "https://firebasestorage.googleapis.com/v0/b/coffee-38c29.appspot.com/o/images%2Fbread11.png?alt=media&token=b15815de-aa57-4305-bd6d-3a429420e5dc",
                "https://firebasestorage.googleapis.com/v0/b/coffee-38c29.appspot.com/o/images%2Fbread15.png?alt=media&token=95002e47-2f4a-4020-96b2-32e9048b401c",
                "https://firebasestorage.googleapis.com/v0/b/coffee-38c29.appspot.com/o/images%2Fsandwich8.png?alt=media&token=e8809101-950e-48fe-8ae6-5b8d38cb2810",
                "https://firebasestorage.googleapis.com/v0/b/coffee-38c29.appspot.com/o/images%2Fbread21.png?alt=media&token=3aad921e-ed89-4361-8f32-7e0955cec30d",
                "https://firebasestorage.googleapis.com/v0/b/coffee-38c29.appspot.com/o/images%2Fbread23.png?alt=media&token=788bf794-2238-4f08-827e-2bb45bb5d172",
                "https://firebasestorage.googleapis.com/v0/b/coffee-38c29.appspot.com/o/images%2Fbread24.png?alt=media&token=f0444870-3581-4276-81c6-7ff4bee1705f",
                "https://firebasestorage.googleapis.com/v0/b/coffee-38c29.appspot.com/o/images%2Fbread18.png?alt=media&token=e95b8beb-e092-4ee5-ad3f-a8308dc58e7a",
                "https://firebasestorage.googleapis.com/v0/b/coffee-38c29.appspot.com/o/images%2Fcake2.png?alt=media&token=6aca2be8-9ea4-4424-8daf-c5aee1f09bdd",
                "https://firebasestorage.googleapis.com/v0/b/coffee-38c29.appspot.com/o/images%2Fcake4.png?alt=media&token=112c39f6-4930-4b1e-86c2-c99876489966",
                "https://firebasestorage.googleapis.com/v0/b/coffee-38c29.appspot.com/o/images%2Fcake12.png?alt=media&token=f957167b-c8ef-4bc7-a70e-eb55fc294f94",
                "https://firebasestorage.googleapis.com/v0/b/coffee-38c29.appspot.com/o/images%2Fsandwich5.png?alt=media&token=1019173a-b3a1-4deb-8e7b-e3bddb7e5d15",
                "https://firebasestorage.googleapis.com/v0/b/coffee-38c29.appspot.com/o/images%2Fcake13.png?alt=media&token=1c9e9ab7-eaa6-48e0-b03f-4b40ea5a86fa",
                "https://firebasestorage.googleapis.com/v0/b/coffee-38c29.appspot.com/o/images%2Fcake15.png?alt=media&token=64e16992-2962-4af5-940a-dcd780942985",
                "https://firebasestorage.googleapis.com/v0/b/coffee-38c29.appspot.com/o/images%2Fcake18.png?alt=media&token=9b37a4ee-1b2d-469a-8f92-6518193de6f6",
                "https://firebasestorage.googleapis.com/v0/b/coffee-38c29.appspot.com/o/images%2Fcake19.png?alt=media&token=8fecee01-4e94-4193-8935-5c4031139c18",
                "https://firebasestorage.googleapis.com/v0/b/coffee-38c29.appspot.com/o/images%2Fcake20.png?alt=media&token=a266c3a9-e33b-4105-9241-5459381d0988",
                "https://firebasestorage.googleapis.com/v0/b/coffee-38c29.appspot.com/o/images%2Fcake21.png?alt=media&token=8cc64604-5ec4-42a0-82f1-1196fbda6444",
                "https://firebasestorage.googleapis.com/v0/b/coffee-38c29.appspot.com/o/images%2Fcake22.png?alt=media&token=27539f97-1313-4f5a-9d53-92563b50f6bc",
                "https://firebasestorage.googleapis.com/v0/b/coffee-38c29.appspot.com/o/images%2Fcake23.png?alt=media&token=50508f19-e50f-4d6d-9ba9-eaa0a8088c7c",
                "https://firebasestorage.googleapis.com/v0/b/coffee-38c29.appspot.com/o/images%2Fsalad1.png?alt=media&token=80b759db-b16a-43e3-a45a-e37d07da89fa",
                "https://firebasestorage.googleapis.com/v0/b/coffee-38c29.appspot.com/o/images%2Fsandwich6.png?alt=media&token=d1bed1f6-c651-4ac5-8719-dd1a57a97f4e",
                "https://firebasestorage.googleapis.com/v0/b/coffee-38c29.appspot.com/o/images%2Fsandwich11.png?alt=media&token=8c2efc23-0d48-4e49-8c60-1c08e52dee75",
                "https://firebasestorage.googleapis.com/v0/b/coffee-38c29.appspot.com/o/images%2Fsalad3.png?alt=media&token=5c991782-6560-4ba7-8346-0610142ab321",
                "https://firebasestorage.googleapis.com/v0/b/coffee-38c29.appspot.com/o/images%2Fsandwich12.png?alt=media&token=4a6d81a3-5642-48b5-a431-e94f51737bf1",
                "https://firebasestorage.googleapis.com/v0/b/coffee-38c29.appspot.com/o/images%2Ficecream1.png?alt=media&token=1c73c7d8-15a7-4d7e-a330-2671cc814814",
                "https://firebasestorage.googleapis.com/v0/b/coffee-38c29.appspot.com/o/images%2Ficecream2.png?alt=media&token=68913ec2-f0ac-49e6-aa06-80abd3b0c9bc",
                "https://firebasestorage.googleapis.com/v0/b/coffee-38c29.appspot.com/o/images%2Ficecream3.png?alt=media&token=e7e39149-8988-4072-8ce6-5aabf90c1974",
                "https://firebasestorage.googleapis.com/v0/b/coffee-38c29.appspot.com/o/images%2Ficecream4.png?alt=media&token=fa0b33f2-fec2-42b1-bded-6c35e4451b57",
                "https://firebasestorage.googleapis.com/v0/b/coffee-38c29.appspot.com/o/images%2Fsandwich2.png?alt=media&token=2f8952e0-d80b-4cf2-80bb-1a5ea2058fd6"


        };

        mRecyclerAdapter = new RecyclerViewAdapter3(getContext(),itemList,img, name, info, price, price2, imageUri);
        mRecyclerView.setLayoutManager(new GridLayoutManager(getContext(),2));
        mRecyclerView.setAdapter(mRecyclerAdapter);

        return rootView;
    }

}
