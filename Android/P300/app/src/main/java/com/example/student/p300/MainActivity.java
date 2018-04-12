package com.example.student.p300;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
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

    ProgressDialog progressDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editText = findViewById(R.id.editText);
        tx_service = findViewById(R.id.tx_service);
        progressBar= findViewById(R.id.progressBar);
        imageView = findViewById(R.id.imageView);

        // MainActivity 에서 띄우기 위해서 MainActivity.this 로 생성
        // getApplicationContext()
        progressDialog = new ProgressDialog(MainActivity.this);
        progressDialog.setCancelable(false);
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

    public void clickBt2(View v) {
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDialog.setMessage("진행중");
        progressDialog.show();
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        progressDialog.dismiss();
    }

    @Override
    public void onBackPressed() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("경고");
        builder.setMessage("끝내시겠습니까?");
        builder.setIcon(R.mipmap.icon1);
        builder.setNegativeButton("아니요", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                return;
            }
        });

        builder.setPositiveButton("예", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                finish();
            }
        });

        builder.setNeutralButton("취소", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                return;
            }
        });

        builder.show();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (intent != null) {
            stopService(intent);
        }
    }
}
