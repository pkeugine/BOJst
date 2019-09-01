package com.example.jhw.exblockdetailapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Main2Activity extends Activity implements View.OnClickListener {

    private String appKey = "0d7d31d9-eb6b-4540-a1cc-864ca37b8bf8";
    private String poiId;
    private double[] preloc = {37.4445394, 126.702133};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        Button button1 =findViewById(R.id.button1);
        Button button2 =findViewById(R.id.button2);
        Button button3 =findViewById(R.id.button3);
        Button button4 =findViewById(R.id.button4);
        button1.setOnClickListener(this);
        button2.setOnClickListener(this);
        button3.setOnClickListener(this);
        button4.setOnClickListener(this);
    }
    @Override
    public void onClick(View view) {
        Intent intent = new Intent(Main2Activity.this, MainActivity.class);

        switch (view.getId()) {
            case R.id.button1:
                poiId = "6399400";
                break;
            case R.id.button2:
                poiId = "5336789";
                break;
            case R.id.button3:
                poiId = "1272460";
                break;
            case R.id.button4:
                poiId = "4707024";
                break;
            default:
                break;
        }

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(RetrofitExService.URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        RetrofitExService retrofitExService = retrofit.create(RetrofitExService.class);
        Call<Detail> call = retrofitExService.getData(poiId,1,appKey);
        Log.d("pppath : ",call.request().url().toString()+"");
        call.enqueue(new Callback<Detail>() {
            @Override
            public void onResponse(@NonNull Call<Detail> call, @NonNull Response<Detail> response) {
                if (response.isSuccessful()) {
                    Detail.PoiDetailInfo body = response.body().getPoiDetailInfo();
                    if (body != null) {
                        intent.putExtra("detail",body);
                        intent.putExtra("preloc",preloc);
                    }
                }
                startActivity(intent);
            }
            @Override
            public void onFailure(@NonNull Call<Detail> call, @NonNull Throwable t) {
                Log.e("getData fail", "======================================");
            }
        });
    }

}
