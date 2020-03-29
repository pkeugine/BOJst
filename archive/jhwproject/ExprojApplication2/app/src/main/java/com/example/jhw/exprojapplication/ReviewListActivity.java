package com.example.jhw.exprojapplication;

import android.content.ContentValues;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class ReviewListActivity extends AppCompatActivity {

    RecyclerView mRecyclerView = null;
    ReviewAdapter mAdapter = null;
    ArrayList<ReviewItem> mList = new ArrayList<ReviewItem>();
    Button addReview;
    String url;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_review_list);
        //url = "http://13.125.143.148/select.jsp";
        url = "http://10.20.29.44:8000/mavenweb/select.jsp";

        addReview = findViewById(R.id.addReview);
        addReview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ReviewListActivity.this,ReviewAddActivity.class);
                startActivity(intent);
            }
        });

        mRecyclerView = findViewById(R.id.recycler1);
        mAdapter = new ReviewAdapter(mList,this);
        mAdapter.setOnItemClickListener(new ReviewAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View v, ContentValues values) {
                NetworkTask networkTask = new NetworkTask("http://13.125.143.148/delete.jsp",values);
                networkTask.execute();
                onResume();
            }
        });
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

    }


    @Override
    protected void onResume() {
        super.onResume();
        mList.clear();
        NetworkTask networkTask = new NetworkTask(url,null);
        networkTask.execute();
    }

    public class NetworkTask extends AsyncTask<Void, Void, String> {

        private String url;
        private ContentValues values;

        public NetworkTask(String url, ContentValues values) {
            this.url = url;
            this.values = values;
        }

        @Override
        protected String doInBackground(Void... voids) {
            String result; // 요청 결과를 저장할 변수.
            RequestHttpConnection requestHttpURLConnection = new RequestHttpConnection();
            result = requestHttpURLConnection.request(url, values); // 해당 URL로 부터 결과물을 얻어온다.
            return result;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            if(s!=null) jsonParse(s);
            else Toast.makeText(ReviewListActivity.this,"서버와의 통신에 문제가 발생했습니다",Toast.LENGTH_SHORT).show();
        }
    }



    public void jsonParse(String pRecvServerPage) {
        try {
            JSONArray jArr = new JSONArray(pRecvServerPage);
            JSONObject json;
            for (int i = 0; i <jArr.length(); i++) {
                json = jArr.getJSONObject(i);
                String userId = json.getString("userId");
                String opinion = json.getString("opinion");
                int grade = json.getInt("grade");
                String date = json.getString("date");
                String imagePath = json.getString("imagePath");

                ReviewItem item = new ReviewItem(userId,opinion,grade,date);
                item.setImage(imagePath);
                mList.add(item);
            }
            mAdapter.notifyDataSetChanged() ;
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }



/*
    public void addItem(Drawable image, String userId, String opinion,) {
        ReviewItem item = new ReviewItem();

        item.setImage(image);
        item.setUserId((name);
        item.setComment(comment);

        mList.add(item);
    }*/
}
