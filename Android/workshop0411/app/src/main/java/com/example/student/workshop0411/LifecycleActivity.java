package com.example.student.workshop0411;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class LifecycleActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lifecycle);
    }

    public void clickBt(View v) {
        Intent intent = null;
        switch (v.getId()) {
            case R.id.button:
                intent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://m.naver.com"));
                break;
            case R.id.button2:
                intent = new Intent(Intent.ACTION_VIEW, Uri.parse("tel:010-2659-0000"));
                break;
            case R.id.button3:
                intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:010-2659-0000"));
                break;
        }
        startActivity(intent);
    }
}
