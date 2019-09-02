package com.example.jhw.exblockdetailapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Main2Activity extends BaseActivity {

    private String poiId;
    private double[] preloc = {37.4445394, 126.702133};

    @OnClick({R.id.button1,R.id.button2,R.id.button3,R.id.button4})
    void buttonEvents(View v) {
        Intent intent = new Intent(Main2Activity.this, MainActivity.class);
        switch (v.getId()) {
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
        intent.putExtra("preloc",preloc);
        intent.putExtra("poiId",poiId);
        startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        ButterKnife.bind(this);
    }
}
