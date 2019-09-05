package test_1.com.example.mypase3;

import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import android.view.View;

import androidx.core.view.GravityCompat;
import androidx.appcompat.app.ActionBarDrawerToggle;

import android.view.MenuItem;

import com.google.android.material.navigation.NavigationView;

import androidx.drawerlayout.widget.DrawerLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.Menu;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.LinearLayout;

public class MainActivity extends AppCompatActivity {

        //슬라이드 열기/닫기 플래그
        boolean isPageOpen = false;
        //슬라이드 열기 애니메이션
        Animation translateLeftAnim;
        //슬라이드 닫기 애니메이션
        Animation translateRightAnim;
        //슬라이드 레이아웃
        LinearLayout slidingPage01;

        Button button1;


        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);

            //UI
            //slidingPage01 = (LinearLayout)findViewById(R.id.slidingPage01);
            //button1 = (Button)findViewById(R.id.buton1);

            //애니메이션
            translateLeftAnim = AnimationUtils.loadAnimation(this, R.anim.translate_left);
            translateRightAnim = AnimationUtils.loadAnimation(this, R.anim.translate_right);

            //애니메이션 리스너 설정
            SlidingPageAnimationListener animationListener = new SlidingPageAnimationListener();
            translateLeftAnim.setAnimationListener(animationListener);
            translateRightAnim.setAnimationListener(animationListener);
        }

        //버튼
        public void onButton1Clicked(View v){
            //닫기
            if(isPageOpen){
                //애니메이션 시작
                slidingPage01.startAnimation(translateRightAnim);
            }
            //열기
            else{
                slidingPage01.setVisibility(View.VISIBLE);
                slidingPage01.startAnimation(translateLeftAnim);
            }
        }

        //애니메이션 리스너
                    private class SlidingPageAnimationListener implements Animation.AnimationListener {
                        @Override
                        public void onAnimationEnd(Animation animation) {
                            //슬라이드 열기->닫기
                            if(isPageOpen){
                                slidingPage01.setVisibility(View.INVISIBLE);
                                button1.setText("Open");
                                isPageOpen = false;
                            }
                            //슬라이드 닫기->열기
                            else{
                    button1.setText("Close");
                    isPageOpen = true;
                }
            }
            @Override
            public void onAnimationRepeat(Animation animation) {

            }
            @Override
            public void onAnimationStart(Animation animation) {

            }
        }

}
