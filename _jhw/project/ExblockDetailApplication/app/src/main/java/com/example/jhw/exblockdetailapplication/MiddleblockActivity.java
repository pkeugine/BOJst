package com.example.jhw.exblockdetailapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatSpinner;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.skt.Tmap.TMapView;

import java.util.ArrayList;
import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnItemSelected;

public class MiddleblockActivity extends BaseActivity {


    @BindView(R.id.linearLayoutTmap2)
    LinearLayout linearLayoutTmap;
    @BindView(R.id.middle_recycler)
    RecyclerView mRecyclerView;
    @BindView(R.id.cate_name)
    TextView cateName;
    /*@OnClick({R.id.accommodation,R.id.restaurant,R.id.leisure,R.id.festival})
    void buttonEvents(View v) {
        switch (v.getId()) {
            case R.id.accommodation:
                if(mCate==0) break;
                mAdapter.updateList(accommodationList);
                mRecyclerView.smoothScrollToPosition(0);
                mCate = 0;
                break;
            case R.id.restaurant:
                if(mCate==1) break;
                mAdapter.updateList(restaurantList);
                mRecyclerView.smoothScrollToPosition(0);
                mCate = 1;
                break;
            case R.id.leisure:
                if(mCate==2) break;
                mAdapter.updateList(leisureList);
                mRecyclerView.smoothScrollToPosition(0);
                mCate = 2;
                break;
            default:
                break;
        }
    }*/


    private ArrayList<String> accommodationArrayList;
    private ArrayAdapter<String> accommodationArrayAdapter;
    private ArrayList<String> restaurantArrayList;
    private ArrayAdapter<String> restaurantArrayAdapter;
    private ArrayList<String> leisureArrayList;
    private ArrayAdapter<String> leisureArrayAdapter;

    //private ArrayList<ArrayList<PoiRepo>> verticalList;
    private ArrayList<PoiRepo> accommodationList, restaurantList, leisureList, festivalList;
    private LinearLayoutManager layoutManager;
    private MiddleRecyclerAdapter mAdapter;
    private TMapView tMapView;
    private String TmapapiKey;
    private int mvPosition;
    private int mhPosition;
    private int mCate;
    private HorizonRepo mHr;
    private boolean sw;// = false;

    private HashMap<String,HorizonRepo>[] horizontalList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_middleblock);
        ButterKnife.bind(this);
        Intent intent = this.getIntent();
        if (intent != null) {
            if(intent.getIntExtra("addposition",-1)==-1) {
                mvPosition = intent.getIntExtra("vposition",0);
            } else {
                mvPosition = intent.getIntExtra("addposition",0)+1;
            }
            //mhPosition = intent.getIntExtra("hposition",0);
            if(intent.getSerializableExtra("horizonrepo")!=null) {
                mHr = (HorizonRepo) intent.getSerializableExtra("horizonrepo");
            }
            if (intent.getSerializableExtra("list") != null) {
                horizontalList = (HashMap<String,HorizonRepo>[]) intent.getSerializableExtra("list");
            } else {
                Log.e("getData error", "======================================");
                onBackPressed();
            }
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
                        mCate = finalI;
                        mAdapter.updateList(horizontalList[mCate].get(secondCate).getPoiList());
                        sw = false;
                    }
                }

                @Override
                public void onNothingSelected(AdapterView<?> adapterView) {
                }
            });
        }



        mAdapter = new MiddleRecyclerAdapter();
        if(mHr!=null) {
            mvPosition = mHr.getvIndex();
            mhPosition = mHr.gethIndex();
            cateName.setText(mHr.getSecondCategory());
            mAdapter.updateList(mHr.getPoiList());
            mCate = mHr.getFirstCategory();
            spinners[mCate].setSelection(sItemLists[mCate].indexOf(mHr.getSecondCategory()));
        }

        else {
            mCate = 0;
            mAdapter.updateList(horizontalList[mCate].get("숙박").getPoiList());
            cateName.setText("숙박");
        }

        layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        layoutManager.scrollToPosition(mhPosition);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setAdapter(mAdapter);
        mAdapter.setOnClickListener(new MiddleRecyclerAdapter.OnClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                switch (view.getId()) {
                    case R.id.addBtn:
                        Intent resultIntent = new Intent();
                        HorizonRepo hr = new HorizonRepo(mCate,cateName.getText().toString(),mAdapter.getList(),mvPosition,position);
                        resultIntent.putExtra("returnHR", hr);
                        setResult(RESULT_OK,resultIntent);
                        //Toast.makeText(getApplicationContext(), "button " + position, Toast.LENGTH_SHORT).show();
                        finish();
                        break;
                    case R.id.cardview:
                        Intent intent = new Intent(MiddleblockActivity.this, MainActivity.class);
                        intent.putExtra("poiId",mAdapter.getList().get(position).getPoiId());
                        startActivity(intent);
                        //Toast.makeText(getApplicationContext(), "default " + position, Toast.LENGTH_SHORT).show();
                        break;
                    default:
                        Toast.makeText(getApplicationContext(), "default " + position, Toast.LENGTH_SHORT).show();
                        break;
                }
            }
        });
        StartSnapHelper snapHelper = new StartSnapHelper();
        snapHelper.attachToRecyclerView(mRecyclerView);

//        for (int i = 1; i <verticalList.size() ; i++) {
//            ArrayList<PoiRepo> hList = verticalList.get(i);
//            Log.d("getTag11", "onCreate: !!");
//            for(PoiRepo poi : hList) {
//                Log.d("getTag11", "onCreate: "+poi.getName());
//            }
//        }
    }




}
