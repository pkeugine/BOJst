package com.example.jhw.exprojapplication;

import android.Manifest;
import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.CursorLoader;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ReviewAddActivity extends AppCompatActivity {

    private EditText userIdEdit;
    private EditText opinionEdit;
    private Button addReivew;
    private Button addImg;
    private String url;
    private TextView tv_message;
    private RatingBar ratingBar;





    private String mImgPath = null;
    private String mImgTitle = null;
    private String mImgOrient = null;



    private Uri mImageCaptureUri;
    private ImageView mPhotoImageView;
    private Button mButton;

    private static final int PICK_FROM_CAMERA = 0;
    private static final int PICK_FROM_ALBUM = 1;
    private static final int PICK_FROM_FILE = 100;



    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_review_add);
        verifyStoragePermissions(this);


        ratingBar = findViewById(R.id.ratingBar2);
        ratingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                if(ratingBar.getRating()<1.0f) {
                    ratingBar.setRating(1);
                }
            }
        });
        tv_message = (TextView)findViewById(R.id.tv);
        addImg = findViewById(R.id.addImg);
        addImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setType(android.provider.MediaStore.Images.Media.CONTENT_TYPE);
                intent.setData(android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                Intent chooser = Intent.createChooser(intent,"1a2a3a");

                startActivityForResult(chooser, PICK_FROM_FILE);
            }
        });



        url = "http://13.125.143.148/insert.jsp";
        //url = "http://10.20.29.44:8000/mavenweb/insert.jsp";
        mPhotoImageView = (ImageView) findViewById(R.id.image);
        mButton = findViewById(R.id.button);


        /*mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                doTakeAlbumAction();
            }
        });*/

        userIdEdit = findViewById(R.id.userId);
        opinionEdit = findViewById(R.id.opinion);
        addReivew = findViewById(R.id.addReview);


        /*
        addReivew.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!userId.getText().toString().trim().equals("") && !opinion.getText().toString().trim().equals("")) {
                    ContentValues values = new ContentValues();
                    values.put("userId", userId.getText().toString());
                    values.put("opinion", opinion.getText().toString());
                    values.put("grade", 2);
                    NetworkTask networkTask = new NetworkTask(url, values);
                    // new HttpRequestAsyncTask().execute(mImgPath, mImgTitle, mImgOrient);
                    networkTask.execute();
                } else {
                    Toast.makeText(ReviewAddActivity.this, "빈칸을 채워주세요", Toast.LENGTH_SHORT).show();
                }
            }
        });*/

    }


    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode != RESULT_OK && data == null && data.getData() == null) {
            return;
        }

        switch (requestCode) {
            /*
            case PICK_FROM_ALBUM: {
                if(requestCode == Activity.RESULT_OK) {
                    mImageCaptureUri = data.getData();
                    getImageNameToUri(mImageCaptureUri);
                    try{
                        Bitmap bm = MediaStore.Images.Media.getBitmap(getContentResolver(),mImageCaptureUri);
                        mPhotoImageView.setImageBitmap(bm);
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                break;
            }*/
            case PICK_FROM_FILE: {
                if(resultCode == RESULT_OK) {
                    Uri selectImage = data.getData();
                    Log.d("!!!", "onActivityResult: "+selectImage);
                    uploadImage(selectImage);
                }
            }
        }
    }

    public static final String MULTIPART_FORM_DATA = "multipart/form-data";

    @NonNull
    private RequestBody createPartFromString(String descriptionString) {
        return RequestBody.create(
                MediaType.parse(MULTIPART_FORM_DATA), descriptionString);
    }

    @NonNull
    private MultipartBody.Part prepareFilePart(String partName, Uri fileUri) {
        File file = new File(getRealPathFromURI(fileUri));
        RequestBody requestFile = RequestBody.create(MediaType.parse(MULTIPART_FORM_DATA), file);
        return MultipartBody.Part.createFormData(partName, file.getName(), requestFile);
    }

    private String getRealPathFromURI(Uri contentUri) {
        String[] proj = { MediaStore.Images.Media.DATA };
        CursorLoader loader = new CursorLoader(this, contentUri, proj, null, null, null);
        Cursor cursor = loader.loadInBackground();
        int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
        cursor.moveToFirst();
        String result = cursor.getString(column_index);
        cursor.close();
        return result;
    }



    public void uploadImage(Uri uri) {
        UploadService service = MyRetrofit2.getRetrofit2().create(UploadService.class);
        MultipartBody.Part body1 = prepareFilePart("image", uri);

        RequestBody userId = createPartFromString(userIdEdit.getText().toString());
        RequestBody grade = createPartFromString(String.valueOf((int)ratingBar.getRating()));
        RequestBody opinion = createPartFromString(opinionEdit.getText().toString());

        Call<ResponseBody> call = service.uploadFile(userId,grade,opinion,body1);
        tv_message.setText(call.request().url().toString()); //todo 디버깅용

        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
            }
        });

    }




    private void getImageNameToUri(Uri data) {
        String[] proj = {
                MediaStore.Images.Media.DATA,
                MediaStore.Images.Media.TITLE,
                MediaStore.Images.Media.ORIENTATION
        };
        Cursor cursor = this.getContentResolver().query(data,proj,null,null,null);
        cursor.moveToFirst();

        int column_data = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
        int column_title = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.TITLE);
        int column_orientation = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.ORIENTATION);

        mImgPath = cursor.getString(column_data);
        mImgTitle = cursor.getString(column_title);
        mImgOrient = cursor.getString(column_orientation);


    }





    // Permission method
    private static final int REQUEST_EXTERNAL_STORAGE = 1;
    private static String[] PERMISSIONS_STORAGE = {
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE
    };


    public static void verifyStoragePermissions(Activity activity) {
        int writePermission = ActivityCompat.checkSelfPermission(activity, Manifest.permission.WRITE_EXTERNAL_STORAGE);
        int readPermission = ActivityCompat.checkSelfPermission(activity, Manifest.permission.READ_EXTERNAL_STORAGE);

        if (writePermission != PackageManager.PERMISSION_GRANTED || readPermission != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions( activity, PERMISSIONS_STORAGE,REQUEST_EXTERNAL_STORAGE );
        }
    }


















/*

    */
/*** 앨범에서 이미지 가져오기*//*

    private void doTakeAlbumAction() {
        // 앨범 호출
        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setType(android.provider.MediaStore.Images.Media.CONTENT_TYPE);
        startActivityForResult(intent, PICK_FROM_ALBUM);
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
            if (s == null)
                Toast.makeText(ReviewAddActivity.this, "서버와의 통신에 문제가 발생했습니다", Toast.LENGTH_SHORT).show();
            finish();
        }
    }
*/

}
