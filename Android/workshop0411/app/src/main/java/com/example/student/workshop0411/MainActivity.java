package com.example.student.workshop0411;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {
    TextView tv_main;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tv_main = findViewById(R.id.tv_main);
    }

    public void clickBt(View v) {
        Intent intent = null;
        if (v.getId() == R.id.bt_lifecycle) {
            intent = new Intent(getApplicationContext(), LifecycleActivity.class);
            startActivity(intent);
        } else if (v.getId() == R.id.bt_result) {
            intent = new Intent(getApplicationContext(), TransitionActivity.class);
            intent.putExtra("num1", 1000);
            startActivityForResult(intent, 101);
        }

        startActivity(intent);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 101 || requestCode == 100 && resultCode == RESULT_OK) {
            int result = data.getIntExtra("result", 0);
            tv_main.setText(result + "");
        }
    }
}
