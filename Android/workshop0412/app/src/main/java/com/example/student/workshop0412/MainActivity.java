package com.example.student.workshop0412;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity -- ";

    static LinearLayout container;
    LayoutInflater inflater;
    ProgressDialog progressDialog;
    ProgressBar progressBar;
    ImageView imageView;
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        makeUI();
    }

    public void makeUI() {
        container = findViewById(R.id.container);
        inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    public void stopServiceSleep(Intent intent) {
        stopService(intent);
        progressDialog.dismiss();
        inflater.inflate(R.layout.service, container, true);
        progressBar = findViewById(R.id.progBar);
        imageView = findViewById(R.id.imageView);
    }

    @Override
    protected void onNewIntent(Intent intent) {
        if (intent != null) {
            processIntent(intent);
        }
    }

    private void processIntent(Intent intent) {
        String command = intent.getStringExtra("command");
        int state = intent.getIntExtra("state", 0);

        if (command.equals("ServiceSleep")) {
            if (state == 0) {
                stopServiceSleep(intent);
            }
        } else if (command.equals("ProgressService")) {
            int progress = intent.getIntExtra("progress", 0);
            progressBar.setProgress(progress * 10);
            if (state == 0) {
                stopService(intent);
            }
        } else if(command.equals("ImageService")) {
            int cnt = intent.getIntExtra("cnt", 0);
            if(cnt%3 == 0) {
                imageView.setImageResource(R.mipmap.pills);
            } else if(cnt%3 == 1) {
                imageView.setImageResource(R.mipmap.icon1);
            } else {
                imageView.setImageResource(R.mipmap.icon2);
            }
            if (state == 0) {
                stopService(intent);
            }
        }
    }


    public void onclickStart(View v) {
        intent = new Intent(this, ServiceSleep.class);
        intent.putExtra("command", "show");

        // startActivity 아니고 startService
        startService(intent);

        progressDialog = new ProgressDialog(MainActivity.this);
        progressDialog.setCancelable(false);

        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDialog.setMessage("3초 후에 시작됩니다");
        progressDialog.show();
    }

    public void onclickStartProgress(View v) {
        intent = new Intent(this, ProgressService.class);
        intent.putExtra("command", "show");

        startService(intent);

        intent = new Intent(this, ImageService.class);
        intent.putExtra("command", "show");

        startService(intent);
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
        if(intent != null)
            stopService(intent);
    }
}
