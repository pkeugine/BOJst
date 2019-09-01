package com.example.jhw.exblockdetailapplication;

import androidx.annotation.BinderThread;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.skt.Tmap.TMapData;
import com.skt.Tmap.TMapPoint;
import com.skt.Tmap.TMapPolyLine;
import com.skt.Tmap.TMapView;
import static com.skt.Tmap.TMapData.*;
import retrofit2.Retrofit;

public class MainActivity extends Activity {

    private LinearLayout linearLayoutTmap;
    private TMapView tMapView;
    private String appKey = "0d7d31d9-eb6b-4540-a1cc-864ca37b8bf8";
    private TMapPoint tMapPointStart;
    private TMapPoint tMapPointEnd;
    private TMapData tmapData;
    private TextView addressView;
    private TextView telNoView;
    private TextView parkFlagView;
    private TextView parkField;
    private TextView additionalInfoView;
    private TextView additionalField;
    private TextView descView;
    private TextView descField;
    private Button addReview;
    private Button moreReviews;
    private double startLat;
    private double startLon;
    private double endLat;
    private double endLon;
    private Detail.PoiDetailInfo detail;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = this.getIntent();
        if (intent != null) {
            Detail.PoiDetailInfo body = intent.getParcelableExtra("detail");
            double[] preloc = intent.getDoubleArrayExtra("preloc");
            if(body != null && preloc != null) {
                this.detail = body;
                startLat = preloc[0];
                startLon = preloc[1];
                Log.d("data.getPoiId()", body.getId() + "");
                Log.d("data.getName()", body.getName() + "");
                Log.d("data.getAddress()", body.getAddress() + "");
                Log.d("data.getFirstNo()", body.getFirstNo() + "");
                Log.d("data.getSecondNo()", body.getSecondNo() + "");
                Log.d("data.getLat()", body.getLat() + "");
                Log.d("data.getLon()", body.getLon() + "");
                Log.e("getData end", "======================================");
            } else {
                Log.e("getData error", "======================================");
            }
        }
        init();
    }

    private void init() {
        setContentView(R.layout.activity_main);

        tMapView = new TMapView(this);
        tMapView.setSKTMapApiKey(appKey);
        linearLayoutTmap = findViewById(R.id.linearLayoutTmap);
        linearLayoutTmap.addView(tMapView);

        endLat = detail.getLat();
        endLon = detail.getLon();
        tmapData = new TMapData();
        tMapPointStart = new TMapPoint(startLat, startLon); // SKT타워(출발지)
        tMapPointEnd = new TMapPoint(endLat, endLon); // N서울타워(목적지)\
        tMapView.setCenterPoint(endLon,endLat);

        addressView = findViewById(R.id.address);
        addressView.setText(detail.getAddress()+" 지번 : "+detail.getFirstNo()+"-"+detail.getSecondNo());
        telNoView = findViewById(R.id.tel_no);
        telNoView.setText(detail.getTel());

        parkFlagView = findViewById(R.id.park_flag);
        parkField = findViewById(R.id.park_field);
        String parkFlag = detail.getParkFlag();
        if(parkFlag.equals("1")) parkFlag = "가능";
        else if(parkFlag.equals("0")) parkFlag = "불가능";
        else {
            parkField.setVisibility(View.INVISIBLE);
            parkFlagView.setVisibility(View.INVISIBLE);
        }
        parkFlagView.setText(parkFlag);
        additionalInfoView = findViewById(R.id.additional_info);
        additionalField = findViewById(R.id.additional_field);
        if(!detail.getAdditionalInfo().equals("")) additionalInfoView.setText(detail.getAdditionalInfo());
        else {
            additionalInfoView.setVisibility(View.INVISIBLE);
            additionalField.setVisibility(View.INVISIBLE);
        }
        descView = findViewById(R.id.desc);
        descView.setMovementMethod(ScrollingMovementMethod.getInstance());
        descField = findViewById(R.id.desc_field);
        if(!detail.getDesc().equals("")) descView.setText(detail.getDesc());
        else {
            descView.setVisibility(View.INVISIBLE);
            descField.setVisibility(View.INVISIBLE);
        }

        addReview = findViewById(R.id.add_review);
        moreReviews = findViewById(R.id.more_reviews);

        findPath();
    }
    private void findPath() {
        tmapData.findPathDataWithType(TMapPathType.PEDESTRIAN_PATH, tMapPointStart, tMapPointEnd,new FindPathDataListenerCallback() {
            @Override
            public void onFindPathData(TMapPolyLine polyLine) {
                polyLine.setLineColor(Color.BLUE);
                polyLine.setLineWidth(6);
                tMapView.addTMapPath(polyLine);
            }
        });
    }
}