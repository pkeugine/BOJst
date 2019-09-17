package com.example.jhw.exblockdetailapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.LinearLayout;

import com.skt.Tmap.TMapView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RecordInfoActivity extends BaseActivity {

    @BindView(R.id.linearLayoutTmap)
    LinearLayout linearLayoutTmap;

    private TMapView tMapView;
    private String TmapapiKey;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_record_info);
        ButterKnife.bind(this);
        TmapapiKey = getMetadata(this, "com.example.jhw.TmapKey");

        tMapView = new TMapView(this);
        tMapView.setSKTMapApiKey(TmapapiKey);
        linearLayoutTmap.addView(tMapView);


    }
}
