package com.example.jhw.exloginapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.kakao.network.ErrorResult;
import com.kakao.usermgmt.UserManagement;
import com.kakao.usermgmt.callback.MeV2ResponseCallback;
import com.kakao.usermgmt.response.MeV2Response;
import com.kakao.util.OptionalBoolean;
import com.kakao.util.helper.log.Logger;

public class SampleSignupActivity extends BaseActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestMe();
    }

    protected void requestMe() {
        UserManagement.getInstance().me(new MeV2ResponseCallback() {
            @Override
            public void onFailure(ErrorResult errorResult) { // 다른 이유로 실패
                String message = "failed to get user info. msg=" + errorResult;
                Logger.d(message);
            }
            @Override
            public void onSuccess(MeV2Response result) { // 정보 요청 성공
                /*if (result.hasSignedUp() == OptionalBoolean.FALSE) { // 미가입 상태
                    showSignup();
                } else {
                    redirectMainActivity();
                }*/
                if (result.hasSignedUp() != OptionalBoolean.FALSE) redirectMainActivity();
            }
            @Override
            public void onSessionClosed(ErrorResult errorResult) { // 세션이 닫혀 실패
                Logger.e("onSessionClosed");
                redirectLoginActivity();
            }
        });
    }

    private void redirectMainActivity() {
        Intent intent = new Intent(this, Main2Activity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }
}