package com.example.jhw.exblockdetailapplication;

import androidx.annotation.BinderThread;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.skt.Tmap.TMapData;
import com.skt.Tmap.TMapPoint;
import com.skt.Tmap.TMapPolyLine;
import com.skt.Tmap.TMapView;

import java.io.IOException;
import java.util.List;

import static com.skt.Tmap.TMapData.*;

import butterknife.BindString;
import butterknife.BindView;
import butterknife.BindViews;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends BaseActivity {

    @BindView(R.id.linearLayoutTmap)
    LinearLayout linearLayoutTmap;
    @BindView(R.id.text_title)
    TextView textTitle;
    @BindView(R.id.address)
    TextView addressView;
    @BindView(R.id.tel_no)
    TextView telNoView;
    @BindView(R.id.park_flag)
    TextView parkFlagView;
    @BindView(R.id.additional_info)
    TextView additionalInfoView;
    @BindView(R.id.desc)
    TextView descView;
    @BindView(R.id.park_field)
    TextView parkField;
    @BindView(R.id.additional_field)
    TextView additionalField;
    @BindView(R.id.desc_field)
    TextView descField;
    @BindView(R.id.nick_name)
    TextView nickNameView;
    @BindView(R.id.point)
    TextView pointView;
    @BindView(R.id.comment)
    TextView commentView;

    @BindView(R.id.add_review)
    Button addReview;
    @BindView(R.id.more_reviews)
    Button moreReviews;

    @OnClick(R.id.title_back)
    public void back(View view) {
        onBackPressed();
    }

    private TMapView tMapView;
    private TMapPoint tMapPointStart;
    private TMapPoint tMapPointEnd;
    private TMapData tmapData;
    ;
    private String TmapapiKey;
    private String poiId;
    private double startLat;
    private double startLon;
    private double endLat;
    private double endLon;
    private Detail.PoiDetailInfo detail;
    private Review review;
    private int responseCount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        TmapapiKey = getMetadata(this, "com.example.jhw.TmapKey");
        showWaitingDialog();
        // 이전 액티비티에서 넘어온 정보 받아오기
        Intent intent = this.getIntent();
        if (intent != null) {
            double[] preloc = intent.getDoubleArrayExtra("preloc");
            if (preloc != null && intent.getStringExtra("poiId") != null) {
                startLat = preloc[0];
                startLon = preloc[1];
                poiId = intent.getStringExtra("poiId");
            } else {
                Log.e("getData error", "======================================");
                onBackPressed();
            }
        }
        getData();
    }

    // 상세정보, 리뷰 정보 받아오기 ( 네트워크 통신 )
    private void getData() {
        responseCount=0;
        getDetails();
        getReviews();
    }
    // 상세정보 받아오기
    private void getDetails() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(RetrofitExService.Tmap_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        RetrofitExService retrofitExService = retrofit.create(RetrofitExService.class);
        Call<Detail> call = retrofitExService.getData(poiId,1,TmapapiKey);
        Log.d("pppath : ",call.request().url().toString()+"");
        call.enqueue(new Callback<Detail>() {
            @Override
            public void onResponse(@NonNull Call<Detail> call, @NonNull Response<Detail> response) {
                if (response.isSuccessful()) {
                    Log.d("getData unfail", "==============================count"+responseCount);
                    detail = response.body().getPoiDetailInfo();
                    if(responseCount>=1) init();
                    else responseCount++;
                }
            }
            @Override
            public void onFailure(@NonNull Call<Detail> call, @NonNull Throwable t) {
                Log.e("getData fail", "======================================");
            }
        });
    }

    // 리뷰정보 받아오기
    private void getReviews() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(RetrofitExService.DB_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        RetrofitExService retrofitExService = retrofit.create(RetrofitExService.class);
        Call<List<Review>> call = retrofitExService.useDB("select.jsp",Integer.parseInt(poiId));
        Log.d("pppath : ",call.request().url().toString()+"");
        call.enqueue(new Callback<List<Review>>() {
            @Override
            public void onResponse(@NonNull Call<List<Review>> call, @NonNull Response<List<Review>> response) {
                if (response.isSuccessful()) {
                    List<Review> body = response.body();
                    if(body != null) {
                        review = body.get(0);
                        Log.d("getData unfail", "==============================count"+responseCount);
                        if(responseCount>=1) init();
                        else responseCount++;
                    }
                }
            }
            @Override
            public void onFailure(@NonNull Call<List<Review>> call, @NonNull Throwable t) {
                Log.e("getData fail", "======================================");
                Log.d("fail : ",t.getMessage());
            }
        });
    }

    // 뷰 초기화 및 보여주기
    private void init() {
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        tMapView = new TMapView(this);
        tMapView.setSKTMapApiKey(TmapapiKey);
        linearLayoutTmap.addView(tMapView);

        endLat = detail.getLat();
        endLon = detail.getLon();
        tmapData = new TMapData();
        tMapPointStart = new TMapPoint(startLat, startLon); // SKT타워(출발지)
        tMapPointEnd = new TMapPoint(endLat, endLon); // N서울타워(목적지)\
        tMapView.setCenterPoint(endLon,endLat);
        findPath();

        textTitle.setText(detail.getName());

        addressView.setText(detail.getAddress()+" 지번 : "+detail.getFirstNo()+"-"+detail.getSecondNo());
        telNoView.setText(detail.getTel());

        if(detail.getParkFlag().equals("1")) parkFlagView.setText("가능");
        else if(detail.getParkFlag().equals("0")) parkFlagView.setText("불가능");
        else {
            parkField.setVisibility(View.INVISIBLE);
            parkFlagView.setVisibility(View.INVISIBLE);
        }

        if(!detail.getAdditionalInfo().equals("")) additionalInfoView.setText(detail.getAdditionalInfo());
        else {
            additionalInfoView.setVisibility(View.INVISIBLE);
            additionalField.setVisibility(View.INVISIBLE);
        }

        descView.setMovementMethod(ScrollingMovementMethod.getInstance());
        if(!detail.getDesc().equals("")) descView.setText(detail.getDesc());
        else {
            descView.setVisibility(View.INVISIBLE);
            descField.setVisibility(View.INVISIBLE);
        }

        nickNameView.setText(review.getUserId());
        pointView.setText(review.getGrade()+"");
        commentView.setText(review.getOpinion());

        cancelWaitingDialog();
    }


    // 경로 탐색
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