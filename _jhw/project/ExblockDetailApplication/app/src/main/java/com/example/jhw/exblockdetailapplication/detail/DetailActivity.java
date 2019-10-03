package com.example.jhw.exblockdetailapplication.detail;

import androidx.annotation.NonNull;

import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.jhw.exblockdetailapplication.R;
import com.example.jhw.exblockdetailapplication.common.BaseActivity;
import com.example.jhw.exblockdetailapplication.data.Detail;
import com.example.jhw.exblockdetailapplication.data.Review;
import com.example.jhw.exblockdetailapplication.common.GpsTracker;
import com.example.jhw.exblockdetailapplication.common.RetrofitExService;
import com.skt.Tmap.TMapInfo;
import com.skt.Tmap.TMapMarkerItem;
import com.skt.Tmap.TMapPoint;
import com.skt.Tmap.TMapView;

import java.util.ArrayList;
import java.util.List;


import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class DetailActivity extends BaseActivity {

    @BindView(R.id.linearLayoutTmap)
    LinearLayout linearLayoutTmap;
    @BindView(R.id.text_title)
    TextView textTitle;
    @BindView(R.id.address)
    TextView addressView;
    @BindView(R.id.address_num)
    TextView addressNumView;
    @BindView(R.id.tel_no)
    TextView telNoView;
    @BindView(R.id.park_flag)
    TextView parkFlagView;
    @BindView(R.id.additional_info)
    TextView additionalInfoView;
    @BindView(R.id.desc)
    TextView descView;
    @BindView(R.id.tel_field)
    LinearLayout telField;
    @BindView(R.id.park_field)
    LinearLayout parkField;
    @BindView(R.id.additional_field)
    LinearLayout additionalField;
    @BindView(R.id.desc_field)
    LinearLayout descField;
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
    @BindView(R.id.confirm)
    Button confirmButton;


    // 뒤로가기 버튼
    @OnClick(R.id.title_back)
    public void back() {
        onBackPressed();
    }

    // 경토 탐색 버튼
    @OnClick(R.id.path_search)
    public void pathSearch() {
        Intent intent = new Intent(DetailActivity.this, PathSearchActivity.class);
        intent.putExtra("endLat", placeLat);
        intent.putExtra("endLon", placeLon);
        startActivity(intent);
    }

    // 현 위치 버튼
    @OnClick(R.id.current_pos)
    public void current() {
        ArrayList<TMapPoint> arrays = new ArrayList<>();
        arrays.add(myPoint);
        arrays.add(placePoint);
        TMapInfo info = tMapView.getDisplayTMapInfo(arrays);
        Log.d("result", "info : " + info.getTMapZoomLevel() + "," + tMapView.getZoomLevel());
        tMapView.setZoomLevel(info.getTMapZoomLevel());
        tMapView.setCenterPoint(gpsTracker.getLongitude(), gpsTracker.getLatitude());
    }


    private GpsTracker gpsTracker;

    private TMapView tMapView;
    private TMapPoint myPoint;
    private TMapPoint placePoint;
    private TMapMarkerItem placeMarker;
    private TMapMarkerItem myMarker;

    private String TmapapiKey;

    private String poiId;
    private String addressName;

    private double placeLat;
    private double placeLon;
    private Detail.PoiDetailInfo detail;
    private Review review;
    private int responseCount;
    private int mposition;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        ButterKnife.bind(this);

        TmapapiKey = getMetadata(this, "com.example.jhw.TmapKey");
        showWaitingDialog();
        // 이전 액티비티에서 넘어온 정보 받아오기
        Intent intent = this.getIntent();
        if (intent != null) {
            if (intent.getIntExtra("position", 0) != 0) {
                mposition = intent.getIntExtra("position", 0);
            }
            if (intent.getStringExtra("poiId") != null && intent.getStringExtra("addressname") != null) {
                poiId = intent.getStringExtra("poiId");
                addressName = intent.getStringExtra("addressname");
            } else {
                Log.e("getData error", "======================================");
                onBackPressed();
            }
        }

        getData();
    }

    // 상세정보, 리뷰 정보 받아오기 ( 네트워크 통신 )
    private void getData() {
        responseCount = 1;
        getDetails();
        //getReviews();
    }

    // 상세정보 받아오기
    private void getDetails() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(RetrofitExService.Tmap_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        RetrofitExService retrofitExService = retrofit.create(RetrofitExService.class);
        Call<Detail> call = retrofitExService.getDetailData(poiId, 1, TmapapiKey);
        Log.d("pppath : ", call.request().url().toString() + "");
        call.enqueue(new Callback<Detail>() {
            @Override
            public void onResponse(@NonNull Call<Detail> call, @NonNull Response<Detail> response) {
                if (response.isSuccessful()) {
                    Log.d("getData unfail", "==============================count" + responseCount);
                    detail = response.body().getPoiDetailInfo();
                    if (responseCount >= 1) init();
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
        Call<List<Review>> call = retrofitExService.useDB("select.jsp", Integer.parseInt(poiId));
        call.enqueue(new Callback<List<Review>>() {
            @Override
            public void onResponse(@NonNull Call<List<Review>> call, @NonNull Response<List<Review>> response) {
                if (response.isSuccessful()) {
                    List<Review> body = response.body();
                    if (body != null) {
                        review = body.get(0);
                        Log.d("getData unfail", "==============================count" + responseCount);
                        nickNameView.setText(review.getUserId());
                        pointView.setText(review.getGrade() + "");
                        commentView.setText(review.getOpinion());
                        if (responseCount >= 1) init();
                        else responseCount++;

                    }
                }
            }

            @Override
            public void onFailure(@NonNull Call<List<Review>> call, @NonNull Throwable t) {
                Log.e("getData fail", "======================================");
            }
        });
    }

    // 뷰 초기화 및 보여주기
    private void init() {
        textTitle.setText(detail.getName());

        // 마커 표시
        placeLat = detail.getLat();
        placeLon = detail.getLon();
        tMapView = new TMapView(this);
        tMapView.setSKTMapApiKey(TmapapiKey);
        tMapView.setCenterPoint(placeLon, placeLat);
        placePoint = new TMapPoint(placeLat, placeLon);
        placeMarker = new TMapMarkerItem();
        placeMarker.setTMapPoint(placePoint);
        placeMarker.setCanShowCallout(true);
        placeMarker.setAutoCalloutVisible(true);
        placeMarker.setCalloutTitle(detail.getName());
        tMapView.addMarkerItem("placepos", placeMarker);
        linearLayoutTmap.addView(tMapView);

        // 현위치
        gpsTracker = new GpsTracker(DetailActivity.this);
        myPoint = new TMapPoint(gpsTracker.getLatitude(), gpsTracker.getLongitude());
        myMarker = new TMapMarkerItem();
        myMarker.setTMapPoint(myPoint);
        myMarker.setName("현위치");
        myMarker.setVisible(TMapMarkerItem.VISIBLE);
        myMarker.setIcon(BitmapFactory.decodeResource(getResources(), R.drawable.i_location));
        myMarker.setPosition((float) 0.5, 1);
        tMapView.addMarkerItem("mypos", myMarker);


        // 상세 정보
        addressView.setText(addressName);
        addressNumView.setText(detail.getFirstNo() + "-" + detail.getSecondNo());
        if (!detail.getTel().equals("")) {
            telNoView.setText(detail.getTel());
        } else {
            telField.setVisibility(View.GONE);
//            telNoView.setVisibility(View.GONE);

        }


        if (detail.getParkFlag().equals("1")) parkFlagView.setText("가능");
        else if (detail.getParkFlag().equals("0")) parkFlagView.setText("불가능");
        else {
            parkField.setVisibility(View.GONE);
//            parkFlagView.setVisibility(View.GONE);
        }

        additionalInfoView.setMovementMethod(ScrollingMovementMethod.getInstance());
        if (!detail.getAdditionalInfo().equals("")) {
            String[] strs = detail.getAdditionalInfo().split(";");
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < strs.length; i++) {
                sb.append(strs[i] + "\n");
            }
            sb.deleteCharAt(sb.length() - 1);
            additionalInfoView.setText(sb.toString());
        } else {
//            additionalInfoView.setVisibility(View.GONE);
            additionalField.setVisibility(View.GONE);
        }

        descView.setMovementMethod(ScrollingMovementMethod.getInstance());
        if (!detail.getDesc().equals("")) descView.setText(detail.getDesc());
        else {
//            descView.setVisibility(View.GONE);
            descField.setVisibility(View.GONE);
        }

        confirmButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent resultIntent = new Intent();
                resultIntent.putExtra("returnPos", mposition);
                setResult(RESULT_OK,resultIntent);
                finish();
            }
        });


        cancelWaitingDialog();
    }


}