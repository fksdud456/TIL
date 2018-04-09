package com.example.student.p181;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView text1;
    LinearLayout bg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        text1 = findViewById(R.id.text1);
        bg = findViewById(R.id.bg);
    }

    public void onclickbt1(View v) {
        text1.setText("A");
        text1.setTextColor(getResources().getColor(R.color.colorA));
        bg.setBackgroundColor(getResources().getColor(R.color.colorC));
    }

    public void onclickbt2(View v) {
        text1.setText("B");
        text1.setTextColor(getResources().getColor(R.color.colorB));
        bg.setBackground(getResources().getDrawable(R.drawable.bg4));
    }

    public void onclickbt3(View v) {
        text1.setText("C");
        text1.setTextColor(getResources().getColor(R.color.colorC));
        bg.setBackgroundResource(R.drawable.bg6);
    }

    public void onclickbt4(View v) {
        text1.setText("D");
        text1.setTextColor(getResources().getColor(R.color.colorD));
        bg.setBackgroundResource(R.drawable.bg7);
    }

    public void onclickbt5(View v) {
        text1.setText("E");
        text1.setTextColor(getResources().getColor(R.color.colorA));
        bg.setBackgroundResource(R.drawable.bg8);
    }

    public void onclickbt6(View v) {
        text1.setText("F");
        text1.setTextColor(getResources().getColor(R.color.colorB));
        bg.setBackgroundResource(R.drawable.bg9);
    }
}
