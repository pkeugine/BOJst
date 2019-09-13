package com.example.jhw.exblockdetailapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.location.LocationManager;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.skt.Tmap.TMapData;
import com.skt.Tmap.TMapInfo;
import com.skt.Tmap.TMapMarkerItem;
import com.skt.Tmap.TMapPoint;
import com.skt.Tmap.TMapPolyLine;
import com.skt.Tmap.TMapView;

import java.util.ArrayList;
import java.util.List;

import static com.skt.Tmap.TMapData.*;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
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
    public void back() {
        onBackPressed();
    }


    @OnClick(R.id.path_search)
    public void pathSearch() {
        Intent intent = new Intent(MainActivity.this, PathSearchActivity.class);
        intent.putExtra("endLat", placeLat);
        intent.putExtra("endLon", placeLon);
        startActivity(intent);
    }

    @OnClick(R.id.current_pos)
    public void current() {
        ArrayList<TMapPoint> arrays = new ArrayList<>();
        arrays.add(myPoint);
        arrays.add(placePoint);
        TMapInfo info = tMapView.getDisplayTMapInfo(arrays);
        Log.d("result", "info : "+info.getTMapZoomLevel()+","+tMapView.getZoomLevel());
        tMapView.setZoomLevel(info.getTMapZoomLevel());
     /*   double leftLon = Math.min(gpsTracker.getLongitude(),placeLon);
        double leftLat = Math.min(gpsTracker.getLatitude(),placeLat);
        double rightLon = Math.max(gpsTracker.getLongitude(),placeLon);
        double rightLat = Math.max(gpsTracker.getLatitude(),placeLat);
       //
        tMapView.zoomToTMapPoint(new TMapPoint(leftLat,leftLon),new TMapPoint(leftLat,leftLon));
        int first = tMapView.getZoomLevel();
        if(first<=tMapView.getZoomLevel()) {
            tMapView.MapZoomOut();
        }*/
      // tMapView.setZoomLevel(tMapView.getZoomLevel()-1);
      /* // tMapView.zoomToSpan(Math.abs(gpsTracker.getLatitude()-placeLat), Math.abs(gpsTracker.getLongitude()-placeLon));
        Log.d("result", "current: "+leftLon+", "+leftLat);
        Log.d("result", "curren2: "+rightLon+", "+rightLat);
//        Log.d("result", "current2: "+gpsTracker.getLatitude()+", "+placeLat);
        Log.d("result", "current3: "+Math.abs(gpsTracker.getLatitude()-placeLat)+", "+Math.abs(gpsTracker.getLongitude()-placeLon));
*/

        tMapView.setCenterPoint(gpsTracker.getLongitude(), gpsTracker.getLatitude());
    }


    private GpsTracker gpsTracker;
    private static final int GPS_ENABLE_REQUEST_CODE = 2001;
    private static final int PERMISSIONS_REQUEST_CODE = 100;
    String[] REQUIRED_PERMISSIONS = {Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION};

    private TMapView tMapView;
    private TMapPoint myPoint;
    private TMapPoint placePoint;

    private TMapPoint tMapPointStart;
    private TMapPoint tMapPointEnd;
    private TMapData tmapData;

    private TMapMarkerItem placeMarker;
    private TMapMarkerItem myMarker;

    private String TmapapiKey;
    private String poiId;
    private double startLat;
    private double startLon;

    private double placeLat;
    private double placeLon;
    private Detail.PoiDetailInfo detail;
    private Review review;
    private int responseCount;
    private View rootView;
    private LayoutInflater layoutInflater;

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
        responseCount = 0;
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
        Call<Detail> call = retrofitExService.getData(poiId, 1, TmapapiKey);
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
        Log.d("pppath : ", call.request().url().toString() + "");
        call.enqueue(new Callback<List<Review>>() {
            @Override
            public void onResponse(@NonNull Call<List<Review>> call, @NonNull Response<List<Review>> response) {
                if (response.isSuccessful()) {
                    List<Review> body = response.body();
                    if (body != null) {
                        review = body.get(0);
                        Log.d("getData unfail", "==============================count" + responseCount);
                        if (responseCount >= 1) init();
                        else responseCount++;
                    }
                }
            }

            @Override
            public void onFailure(@NonNull Call<List<Review>> call, @NonNull Throwable t) {
                Log.e("getData fail", "======================================");
                Log.d("fail : ", t.getMessage());
            }
        });
    }

    // 뷰 초기화 및 보여주기
    private void init() {
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        placeLat = detail.getLat();
        placeLon = detail.getLon();

        tMapView = new TMapView(this);
        tMapView.setSKTMapApiKey(TmapapiKey);
        tMapView.setCenterPoint(placeLon, placeLat);

        placePoint = new TMapPoint(placeLat, placeLon);
        placeMarker = new TMapMarkerItem();
        placeMarker.setTMapPoint(placePoint);


        //placeMarker.setName("현위치");
      //  placeMarker.setVisible(TMapMarkerItem.VISIBLE);
        //tMarker.setIcon(BitmapFactory.decodeResource(getResources(),R.drawable.map_pin_red));
      //  placeMarker.setPosition((float) 0.5, 1);

        placeMarker.setCanShowCallout(true);
        placeMarker.setAutoCalloutVisible(true);
        placeMarker.setCalloutTitle(detail.getName());

        tMapView.addMarkerItem("placepos", placeMarker);

        linearLayoutTmap.addView(tMapView);
        //tmapData = new TMapData();
        //tMapPointStart = new TMapPoint(startLat, startLon); // SKT타워(출발지)
        //tMapPointEnd = new TMapPoint(endLat, endLon); // N서울타워(목적지)\

//        ArrayList<TMapPoint> arrays = new ArrayList<>();
//        arrays.add(tMapPointStart);
//        arrays.add(tMapPointEnd);
//        TMapInfo info = tMapView.getDisplayTMapInfo(arrays);
        //tMapView.setCenterPoint(info.getTMapPoint().getLongitude(),info.getTMapPoint().getLatitude());

        //tMapView.zoomToSpan(Math.abs(gpsTracker.getLatitude()-startLat), Math.abs(gpsTracker.getLongitude()-startLon));


//        double leftLon = Math.min(endLon,startLon);
//        double leftLat = Math.min(endLat,startLat);
//        double rightLon = Math.max(endLon,startLon);
//        double rightLat = Math.max(endLat,startLat);

        //tMapView.zoomToTMapPoint(new TMapPoint(leftLat,leftLon),new TMapPoint(rightLat,rightLon));

        //findPath(TMapPathType.PEDESTRIAN_PATH);

        // 현위치
        if (!checkLocationServicesStatus()) showDialogForLocationServiceSetting();
        else checkRunTimePermission();
        gpsTracker = new GpsTracker(MainActivity.this);
        myPoint = new TMapPoint(gpsTracker.getLatitude(), gpsTracker.getLongitude());
        myMarker = new TMapMarkerItem();
        myMarker.setTMapPoint(myPoint);
        myMarker.setName("현위치");
        myMarker.setVisible(TMapMarkerItem.VISIBLE);
        myMarker.setIcon(BitmapFactory.decodeResource(getResources(),R.drawable.i_location));
        myMarker.setPosition((float) 0.5, 1);
      //tMapView.setCenterPoint(gpsTracker.getLongitude(), gpsTracker.getLatitude());



        tMapView.addMarkerItem("mypos", myMarker);


        textTitle.setText(detail.getName());

        addressView.setText(detail.getAddress() + " 지번 : " + detail.getFirstNo() + "-" + detail.getSecondNo());
        telNoView.setText(detail.getTel());

        if (detail.getParkFlag().equals("1")) parkFlagView.setText("가능");
        else if (detail.getParkFlag().equals("0")) parkFlagView.setText("불가능");
        else {
            parkField.setVisibility(View.INVISIBLE);
            parkFlagView.setVisibility(View.INVISIBLE);
        }

        if (!detail.getAdditionalInfo().equals(""))
            additionalInfoView.setText(detail.getAdditionalInfo());
        else {
            additionalInfoView.setVisibility(View.INVISIBLE);
            additionalField.setVisibility(View.INVISIBLE);
        }

        descView.setMovementMethod(ScrollingMovementMethod.getInstance());
        if (!detail.getDesc().equals("")) descView.setText(detail.getDesc());
        else {
            descView.setVisibility(View.INVISIBLE);
            descField.setVisibility(View.INVISIBLE);
        }

        nickNameView.setText(review.getUserId());
        pointView.setText(review.getGrade() + "");
        commentView.setText(review.getOpinion());

        cancelWaitingDialog();
    }


    // ----------------GPS 권한 관련 ---------------- //

    @Override
    public void onRequestPermissionsResult(int permsRequestCode,
                                           @NonNull String[] permissions,
                                           @NonNull int[] grandResults) {

        if (permsRequestCode == PERMISSIONS_REQUEST_CODE && grandResults.length == REQUIRED_PERMISSIONS.length) {
            // 요청 코드가 PERMISSIONS_REQUEST_CODE 이고, 요청한 퍼미션 개수만큼 수신되었다면
            boolean check_result = true;
            // 모든 퍼미션을 허용했는지 체크합니다.
            for (int result : grandResults) {
                if (result != PackageManager.PERMISSION_GRANTED) {
                    check_result = false;
                    break;
                }
            }

            if (check_result) {
                //위치 값을 가져올 수 있음;
            } else {
                // 거부한 퍼미션이 있다면 앱을 사용할 수 없는 이유를 설명해주고 앱을 종료합니다.2 가지 경우가 있습니다.
                if (ActivityCompat.shouldShowRequestPermissionRationale(this, REQUIRED_PERMISSIONS[0])
                        || ActivityCompat.shouldShowRequestPermissionRationale(this, REQUIRED_PERMISSIONS[1])) {
                    Toast.makeText(MainActivity.this, "퍼미션이 거부되었습니다. 앱을 다시 실행하여 퍼미션을 허용해주세요.", Toast.LENGTH_LONG).show();
                    finish();
                } else {
                    Toast.makeText(MainActivity.this, "퍼미션이 거부되었습니다. 설정(앱 정보)에서 퍼미션을 허용해야 합니다. ", Toast.LENGTH_LONG).show();
                }
            }
        }
    }

    // 위치 퍼미션을 가지고 있는지 체크
    void checkRunTimePermission() {
        int hasFineLocationPermission = ContextCompat.checkSelfPermission(MainActivity.this,
                Manifest.permission.ACCESS_FINE_LOCATION);
        int hasCoarseLocationPermission = ContextCompat.checkSelfPermission(MainActivity.this,
                Manifest.permission.ACCESS_COARSE_LOCATION);

        if (hasFineLocationPermission == PackageManager.PERMISSION_GRANTED &&
                hasCoarseLocationPermission == PackageManager.PERMISSION_GRANTED) {
        } else {
            // 3-1. 사용자가 퍼미션 거부를 한 적이 있는 경우에는
            if (ActivityCompat.shouldShowRequestPermissionRationale(MainActivity.this, REQUIRED_PERMISSIONS[0])) {
                // 3-2. 요청을 진행하기 전에 사용자가에게 퍼미션이 필요한 이유를 설명해줄 필요가 있습니다.
                Toast.makeText(MainActivity.this, "이 앱을 실행하려면 위치 접근 권한이 필요합니다.", Toast.LENGTH_LONG).show();
                // 3-3. 사용자게에 퍼미션 요청을 합니다. 요청 결과는 onRequestPermissionResult에서 수신됩니다.
                ActivityCompat.requestPermissions(MainActivity.this, REQUIRED_PERMISSIONS,
                        PERMISSIONS_REQUEST_CODE);
            } else {
                // 4-1. 사용자가 퍼미션 거부를 한 적이 없는 경우에는 퍼미션 요청을 바로 합니다.
                // 요청 결과는 onRequestPermissionResult에서 수신됩니다.
                ActivityCompat.requestPermissions(MainActivity.this, REQUIRED_PERMISSIONS,
                        PERMISSIONS_REQUEST_CODE);
            }
        }
    }

    // GPS 활성화 메소드
    private void showDialogForLocationServiceSetting() {
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        builder.setTitle("위치 서비스 비활성화");
        builder.setMessage("앱을 사용하기 위해서는 위치 서비스가 필요합니다.\n"
                + "위치 설정을 수정하실래요?");
        builder.setCancelable(true);
        builder.setPositiveButton("설정", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int id) {
                Intent callGPSSettingIntent
                        = new Intent(android.provider.Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                startActivityForResult(callGPSSettingIntent, GPS_ENABLE_REQUEST_CODE);
            }
        });
        builder.setNegativeButton("취소", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int id) {
                dialog.cancel();
            }
        });
        builder.create().show();
    }

    public boolean checkLocationServicesStatus() {
        LocationManager locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);

        return locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)
                || locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch (requestCode) {
            case GPS_ENABLE_REQUEST_CODE:
                //사용자가 GPS 활성 시켰는지 검사
                if (checkLocationServicesStatus()) {
                    Log.d("@@@", "onActivityResult : GPS 활성화 되있음");
                    checkRunTimePermission();
                    return;
                }
                break;
        }
    }
}