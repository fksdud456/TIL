package com.example.student.p553;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {
    ImageView imageView1, imageView2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        makeUI();
    }

    private void makeUI() {
        imageView1 = findViewById(R.id.imageView);
        imageView2 = findViewById(R.id.imageView2);
    }

    public void onclickBt(View v) {

    }
}
