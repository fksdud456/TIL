package com.example.student.workshop0411;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class TransitionActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transition);

        Intent intent = getIntent();
        int result = intent.getIntExtra("num1", 0);
        intent.putExtra("result", result * 2000);
        setResult(RESULT_OK, intent);
        finish();
    }
}
