package com.example.jhw.exprojapplication;

import android.content.ContentValues;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class ReviewAddActivity extends AppCompatActivity {

    EditText userId;
    EditText opinion;
    EditText grade;
    Button addReivew;
    String url;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_review_add);

        url = "http://10.20.31.56:8085/mavenweb/insert.jsp";

        userId = findViewById(R.id.userId);
        opinion = findViewById(R.id.opinion);
        grade = findViewById(R.id.grade);
        addReivew = findViewById(R.id.addReview);
        addReivew.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!userId.getText().toString().trim().equals("") && !opinion.getText().toString().trim().equals("") && !grade.getText().toString().trim().equals("")) {
                    ContentValues values = new ContentValues();
                    values.put("userId",userId.getText().toString());
                    values.put("opinion",opinion.getText().toString());
                    values.put("grade",grade.getText().toString());
                    NetworkTask networkTask = new NetworkTask(url,values);
                    networkTask.execute();
                } else {
                    Toast.makeText(ReviewAddActivity.this,"빈칸을 채워주세요",Toast.LENGTH_SHORT).show();
                }
            }
        });

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
            if(s==null) Toast.makeText(ReviewAddActivity.this,"서버와의 통신에 문제가 발생했습니다",Toast.LENGTH_SHORT).show();
            finish();

        }
    }
}
