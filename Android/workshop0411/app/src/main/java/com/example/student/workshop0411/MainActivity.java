package com.example.student.workshop0411;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void clickBt(View v) {
        Intent intent = null;
        if(v.getId() == R.id.bt_lifecycle) {
            intent = new Intent(getApplicationContext(), LifecycleActivity.class);
        }

        startActivity(intent);
    }
}
