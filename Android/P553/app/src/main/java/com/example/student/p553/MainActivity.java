package com.example.student.p553;

import android.content.Intent;
import android.graphics.Color;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import java.util.LinkedList;
import java.util.Queue;

public class MainActivity extends AppCompatActivity {
    ImageView imageView1, imageView2, imageView3, imageView4;
    MainHandler mainHandler;
    MyHandler myHandler;

    MyService myService;
    Queue<Intent> intents;
    int resArr[] = {R.drawable.bg_3, R.drawable.jenny, R.drawable.bg, R.drawable.bg_2, R.drawable.bg_4};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        makeUI();
    }

    private void makeUI() {
        imageView1 = findViewById(R.id.imageView);
        imageView2 = findViewById(R.id.imageView2);
        imageView3 = findViewById(R.id.imageView3);
        imageView4 = findViewById(R.id.imageView4);

        mainHandler = new MainHandler();
        myHandler = new MyHandler(imageView3);
        intents = new LinkedList<>();
    }

    Thread t1, t3;
    Thread t2 = new Thread(new Runnable() {
        @Override
        public void run() {
            for (int i = 0; i < 5; i++) {
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                Message msg = new Message();
                Bundle bundle = msg.getData();
                bundle.putInt("data", i);
                mainHandler.sendMessage(msg);
            }
        }
    });

    Runnable r1 = new Runnable() {
        @Override
        public void run() {
            for (int i = 0; i < 5; i++) {
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                final int cnt = i;
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        imageView1.setImageResource(resArr[cnt % 4]);
                    }
                });
            }
        }
    };

    Runnable r3 = new Runnable() {
        @Override
        public void run() {
            for(int i =0; i < 5; i++) {
                try{
                    Thread.sleep(300);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                Message msg = new Message();
                Bundle bundle = msg.getData();
                bundle.putInt("data", i);
                myHandler.sendMessage(msg);
            }
        }
    };

    public void onclickBt(View v) {
        t1 = new Thread(r1);
        t1.start();

        Log.d("thread2 state",t2.getState().toString());
        if(t2.getState() == Thread.State.NEW)
            t2.start();

        t3 = new Thread(r3);
        t3.start();


        Intent intent = new Intent(this, MyService.class);
        intent.putExtra("cmd", true);
        startService(intent);
        intents.add(intent);
        v.setBackgroundColor(Color.parseColor("#F1B1BB"));
    }

    public void onclickStop(View v) {
        Intent intent = intents.poll();
        if(intent!= null)
            stopService(intent);
    }

    public class MainHandler extends Handler {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);

            Bundle bundle = msg.getData();

            int res = bundle.getInt("data");
            imageView2.setImageResource(resArr[(res + 1) % 4]);
        }
    }

    @Override
    protected void onNewIntent(Intent intent) {
        if (intent != null) {
            processIntent(intent);
        }
    }

    private void processIntent(Intent intent) {
        if(intent.getIntExtra("state", 0) == 0) {
            return;
        }

        int cnt = intent.getIntExtra("cnt", 0);
        imageView4.setImageResource(cnt);
    }
}
