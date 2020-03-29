package com.example.jhw.exloginapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.kakao.network.ErrorResult;
import com.kakao.usermgmt.UserManagement;
import com.kakao.usermgmt.callback.LogoutResponseCallback;
import com.kakao.usermgmt.callback.MeV2ResponseCallback;
import com.kakao.usermgmt.callback.UnLinkResponseCallback;
import com.kakao.usermgmt.response.MeV2Response;
import com.kakao.util.helper.log.Logger;

public class Main2Activity extends BaseActivity {

    private MeV2Response response;
    private TextView profileDescription;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initializeView();
        requestMe();
    }


    private void onClickLogout() {
        UserManagement.getInstance().requestLogout(new LogoutResponseCallback() {
            @Override
            public void onCompleteLogout() {
                redirectLoginActivity();
            }
        });
    }

    private void onClickUnlink() {
        final String appendMessage = getString(R.string.com_kakao_confirm_unlink);
        new AlertDialog.Builder(this)
                .setMessage(appendMessage)
                .setPositiveButton(getString(R.string.com_kakao_ok_button),
                        (dialog, which) -> {
                            UserManagement.getInstance().requestUnlink(new UnLinkResponseCallback() {
                                @Override
                                public void onFailure(ErrorResult errorResult) {
                                    Logger.e(errorResult.toString());
                                }
                                @Override
                                public void onSessionClosed(ErrorResult errorResult) {
                                    redirectLoginActivity();
                                }
                                @Override
                                public void onNotSignedUp() {
                                    redirectSignupActivity();
                                }
                                @Override
                                public void onSuccess(Long result) {
                                    redirectLoginActivity();
                                }
                            });
                            dialog.dismiss();
                        })
                .setNegativeButton(getString(R.string.com_kakao_cancel_button),
                        (dialog, which) -> dialog.dismiss()).show();

    }

    private void initializeView() {
        setContentView(R.layout.activity_main2);
        profileDescription = findViewById(R.id.profile_description);
        initializeButtons();
    }

    private void initializeButtons() {
        final Button logoutButton = findViewById(R.id.logout_button);
        logoutButton.setOnClickListener(view -> onClickLogout());
        final Button unlinkButton = findViewById(R.id.unlink_button);
        unlinkButton.setOnClickListener(view -> onClickUnlink());

    }
    public void requestMe() {
        UserManagement.getInstance().me(new MeV2ResponseCallback() {
            @Override
            public void onFailure(ErrorResult errorResult) {
                String message = "failed to get user info. msg=" + errorResult;
                Logger.e(message);
            }

            @Override
            public void onSessionClosed(ErrorResult errorResult) {
                redirectLoginActivity();
            }

            @Override
            public void onSuccess(MeV2Response result) {
                response = result;
                updateLayouts(result);
            }
        });
    }

    private void updateLayouts(MeV2Response result) {
        StringBuilder builder = new StringBuilder();
        builder.append(getResources().getString(R.string.com_kakao_profile_userId)).append("\n").append(String.valueOf(response.getId())).append("\n");
        if (profileDescription != null) {
            this.profileDescription.setText(builder.toString());
        }
    }

}
