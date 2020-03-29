package dduwcom.mobile.nickname01;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText text;
    Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        text = (EditText)findViewById(R.id.nickname);
        btn = (Button)findViewById(R.id.btn);

        btn.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                if(text.getText().length() == 0){
                    Toast.makeText(getApplicationContext(), "별명을 설정해주세요. 공백입니다", Toast.LENGTH_SHORT).show();
                }
                else
                    Toast.makeText(getApplicationContext(), text.getText(), Toast.LENGTH_SHORT).show();
            }
        });


    }
}
