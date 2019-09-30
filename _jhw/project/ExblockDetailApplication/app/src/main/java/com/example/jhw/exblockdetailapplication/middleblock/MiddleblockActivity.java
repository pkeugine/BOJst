package com.example.jhw.exblockdetailapplication.middleblock;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.jhw.exblockdetailapplication.detail.DetailActivity;
import com.example.jhw.exblockdetailapplication.common.BaseActivity;
import com.example.jhw.exblockdetailapplication.data.HorizonRepo;
import com.example.jhw.exblockdetailapplication.R;
import com.example.jhw.exblockdetailapplication.common.StartSnapHelper;
import com.skt.Tmap.TMapView;

import java.util.ArrayList;
import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MiddleblockActivity extends BaseActivity {

    @BindView(R.id.linearLayoutTmap2)
    LinearLayout linearLayoutTmap;
    @BindView(R.id.middle_recycler)
    RecyclerView mRecyclerView;
    @BindView(R.id.cate_name)
    TextView cateName;

    private LinearLayoutManager layoutManager;
    private MiddleRecyclerAdapter mAdapter;
    private HashMap<String, HorizonRepo>[] horizontalList;
    private StartSnapHelper snapHelper;
    private HorizonRepo mHr;
    private TMapView tMapView;
    private String TmapapiKey;
    private int mvPosition;
    private int mFirstCate;
    private boolean sw;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_middleblock);
        ButterKnife.bind(this);

        Intent intent = this.getIntent();
        if (intent != null) {
            // 공통
            if (intent.getSerializableExtra("list") != null) { horizontalList = (HashMap<String,HorizonRepo>[]) intent.getSerializableExtra("list"); }
            else {
                Log.e("getData error", "======================================");
                onBackPressed();
            }
            // 추가 버튼
            if(intent.getIntExtra("addposition",-1)!=-1) { mvPosition = intent.getIntExtra("addposition",0)+1; }
            // 카드
            if(intent.getSerializableExtra("horizonrepo")!=null) { mHr = (HorizonRepo) intent.getSerializableExtra("horizonrepo"); }
        }

        TmapapiKey = getMetadata(this, "com.example.jhw.TmapKey");
        tMapView = new TMapView(this);
        tMapView.setSKTMapApiKey(TmapapiKey);
        linearLayoutTmap.addView(tMapView);

        sw = false;
        CustomSpinner[] spinners = new CustomSpinner[3];
        ArrayList[] sItemLists = new ArrayList[3];
        ArrayAdapter[] sAdapterList = new ArrayAdapter[3];
        spinners[0] =  findViewById(R.id.accommodation);
        spinners[1] = findViewById(R.id.restaurant);
        spinners[2] = findViewById(R.id.leisure);

        for (int i = 0; i <3 ; i++) {
            sItemLists[i] = new ArrayList<>(horizontalList[i].keySet());
            sAdapterList[i] =  new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_spinner_dropdown_item, sItemLists[i]);
            spinners[i].setAdapter(sAdapterList[i]);
            if(i==1) spinners[i].setSelection(sItemLists[i].indexOf("TV맛집"));
            int finalI = i;
            spinners[i].setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View view, MotionEvent motionEvent) {
                    sw = true;
                    return false;
                }
            });
            spinners[i].setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> adapterView, View view, int index, long l) {
                    if(sw) {
                        String secondCate = (String) adapterView.getItemAtPosition(index);
                        cateName.setText(secondCate);
                        mFirstCate = finalI;
                        mAdapter.updateList(horizontalList[mFirstCate].get(secondCate).getPoiList());
                        sw = false;
                    }
                }

                @Override
                public void onNothingSelected(AdapterView<?> adapterView) {
                }
            });
        }


        snapHelper = new StartSnapHelper();
        mAdapter = new MiddleRecyclerAdapter();
        layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setAdapter(mAdapter);
        snapHelper.attachToRecyclerView(mRecyclerView);

        if(mHr!=null) {
            mFirstCate = mHr.getFirstCategory();
            mvPosition = mHr.getvIndex();
            layoutManager.scrollToPosition(mHr.gethIndex());
            mAdapter.updateList(mHr.getPoiList());
            cateName.setText(mHr.getSecondCategory());
            spinners[mFirstCate].setSelection(sItemLists[mFirstCate].indexOf(mHr.getSecondCategory()));
        } else {
            mFirstCate = 0;
            mAdapter.updateList(horizontalList[mFirstCate].get("숙박").getPoiList());
            cateName.setText("숙박");
        }



        mAdapter.setOnClickListener(new MiddleRecyclerAdapter.OnClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                switch (view.getId()) {
                    case R.id.addBtn:
                        HorizonRepo hr = new HorizonRepo(mFirstCate,cateName.getText().toString(),mAdapter.getList(),mvPosition,position);
                        Intent resultIntent = new Intent();
                        resultIntent.putExtra("returnHR", hr);
                        setResult(RESULT_OK,resultIntent);
                        finish();
                        break;
                    case R.id.cardview:
                        Intent intent = new Intent(MiddleblockActivity.this, DetailActivity.class);
                        intent.putExtra("poiId",mAdapter.getList().get(position).getPoiId());
                        startActivity(intent);
                        break;
                    default:
                        Toast.makeText(getApplicationContext(), "default " + position, Toast.LENGTH_SHORT).show();
                        break;
                }
            }
        });

    }

}
