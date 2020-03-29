package com.example.eoyeoga_placeblock;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private LinearLayoutManager linearLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = (RecyclerView)findViewById(R.id.recycler1);
        linearLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)) ;

        List<Recycler_item> items = new ArrayList<>();
        Recycler_item[] item = new Recycler_item[5];
        // 이 부분은 DB와 연동 후 반복문으로 데이터 불러와서 깔끔하게 수정될 예정입니다.
        item[0] = new Recycler_item("서울식당", "별점 : ★★★★ (4점)", "가격 : 5만원 대 (2인)", "리뷰 : 423개");
        item[1] = new Recycler_item("역할맥", "별점 : ★★★ (3점)", "가격 : 3만원 대 (2인)", "리뷰 : 990개");
        item[2] = new Recycler_item("코다차야", "별점 : ★★★★★ (5점)", "가격 : 3만원 대 (2인)", "리뷰 : 500개");
        item[3] = new Recycler_item("참새방앗간", "별점 : ★★★★ (4점)", "가격 : 4만원 대 (2인)", "리뷰 : 333개");
        item[4] = new Recycler_item("박가닭갈비", "별점 : ★ (1점)", "가격 : 2만원 대 (2인)", "리뷰 : 10개");

        for(int i=0;i<item.length;i++) {
            items.add(item[i]);
        }

        recyclerView.setAdapter(new RecyclerAdapter(getApplicationContext(),items,R.layout.activity_main));
    }
}
