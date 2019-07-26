package com.example.jhw.exblockapplication;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.appcompat.widget.Toolbar;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ArrayList<Integer>[] subList;
    ArrayList<ArrayList<Integer>> list;
    VerticalRecyclerAdapter mAdapter = null;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


//      세부 리스트
//      ArrayList<Integer>[] subList = new ArrayList[4];
        subList = new ArrayList[4];
        for (int i = 0; i <4 ; i++) {
            subList[i] = new ArrayList<>();
            for (int j = 0; j < 10; j++) {
                subList[i].add(i*10+j);
            }
        }

//      큰 리스트
//      ArrayList<ArrayList<Integer>> list = new ArrayList<>();
        list = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            list.add(subList[i]);
        }


        RecyclerView mRecyclerView = findViewById(R.id.recycler_view);
        mRecyclerView.setHasFixedSize(true); // 성능 개선
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(layoutManager);

        mAdapter = new VerticalRecyclerAdapter(list);
        mRecyclerView.setAdapter(mAdapter);


        mAdapter.SetOnClickListener(new VerticalRecyclerAdapter.OnClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                list.add(subList[1]);
                mAdapter.notifyDataSetChanged() ;
                Toast.makeText(getApplicationContext(), "button " + position, Toast.LENGTH_SHORT).show();
            }
        });
        mAdapter.SetOnItemClickListener(new HorizontalRecyclerAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Toast.makeText(getApplicationContext(), "click " + position, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onItemLongClick(View view, int position) {
                Toast.makeText(getApplicationContext(), "long click " + position, Toast.LENGTH_SHORT).show();
            }
        });

    }
}
