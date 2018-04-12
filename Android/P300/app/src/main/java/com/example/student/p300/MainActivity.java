package com.example.student.p300;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    EditText editText;
    TextView tx_service;
    Intent intent;
    ProgressBar progressBar;
    ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editText = findViewById(R.id.editText);
        tx_service = findViewById(R.id.tx_service);
        progressBar= findViewById(R.id.progressBar);
        imageView = findViewById(R.id.imageView);
    }

    @Override
    protected void onNewIntent(Intent intent) {
        processIntent(intent);
    }

    private void processIntent(Intent intent) {
        if (intent != null) {
            String command = intent.getStringExtra("command");
            int cnt = intent.getIntExtra("cnt", 0);
            tx_service.setText(command + " " + cnt);
            progressBar.setProgress(cnt*10);
            if(cnt%2 == 0) {
                imageView.setImageResource(R.drawable.bg4);
            } else {
                imageView.setImageResource(R.drawable.bg0);
            }
        }
    }

    public void clickBt(View v) {
        String name = editText.getText().toString();
        // 암시적인 intent, 명시적인 Intent를 통해
        //어떤 activity나 객체를 호출할 때 사용한다.
        // 값을 전달할 때도 사용
        intent = new Intent(this, MyService.class);
        intent.putExtra("command", "show");
        intent.putExtra("name", name);
        // startActivity 아니고 startService
        startService(intent);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (intent != null) {
            stopService(intent);
        }
    }
}
