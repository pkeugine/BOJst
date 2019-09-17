package com.example.jhw.exblockdetailapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class Main2Activity extends BaseActivity {

    private String poiId;

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
