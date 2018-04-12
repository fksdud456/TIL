package com.example.student.workshop0410;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.text.BreakIterator;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity {
    Button bt_home;
    TextView tx_time, login_id, login_pwd;
    EditText login_tx_id, login_tx_pwd;
    LinearLayout l_login, l_home, l_register;
    WebView wv_mv, wv_blog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        makeUI();
    }

    public void makeUI() {
        tx_time         = findViewById(R.id.tx_time);
        login_id        = findViewById(R.id.login_id);
        login_pwd       = findViewById(R.id.login_pwd);
        login_tx_id     = findViewById(R.id.login_tx_id);
        login_tx_pwd    = findViewById(R.id.login_tx_pwd);
        l_login         = findViewById(R.id.l_login);
        l_home          = findViewById(R.id.l_home);
        l_register      = findViewById(R.id.l_register);
        wv_mv           = findViewById(R.id.wv_mv);
        wv_blog         = findViewById(R.id.wv_blog);

        // webview setting
        wv_mv.setWebViewClient(new WebViewClient());
        wv_mv.getSettings().setJavaScriptEnabled(true);

        wv_blog.setWebViewClient(new WebViewClient());
        wv_blog.getSettings().setJavaScriptEnabled(true);
        wv_blog.loadUrl("https://yumdevelop.blogspot.com");

        l_home.setVisibility(View.VISIBLE);
        l_login.setVisibility(View.INVISIBLE);
        l_register.setVisibility(View.INVISIBLE);
        wv_mv.setVisibility(View.INVISIBLE);
    }

    public void onClickBt(View v) {
        if(v.getId() == R.id.bt_home) {
            l_login.setVisibility(View.INVISIBLE);
            l_home.setVisibility(View.VISIBLE);
            l_register.setVisibility(View.INVISIBLE);
            wv_mv.setVisibility(View.INVISIBLE);
        } else if(v.getId() == R.id.bt_login) {
            l_login.setVisibility(View.VISIBLE);
            l_home.setVisibility(View.INVISIBLE);
            l_register.setVisibility(View.INVISIBLE);
            wv_mv.setVisibility(View.INVISIBLE);
        } else if(v.getId() == R.id.bt_register) {
            l_login.setVisibility(View.INVISIBLE);
            l_home.setVisibility(View.INVISIBLE);
            l_register.setVisibility(View.VISIBLE);
            wv_mv.setVisibility(View.INVISIBLE);
        } else if(v.getId() == R.id.bt_analysis) {
            l_login.setVisibility(View.INVISIBLE);
            l_home.setVisibility(View.INVISIBLE);
            l_register.setVisibility(View.INVISIBLE);

            wv_mv.loadUrl("http://70.12.114.150:80/mv/");
            wv_mv.setVisibility(View.VISIBLE);
        }
    }

    public void updateTime() {
        Thread thread = new Thread() {
            @Override
            public void run() {
                while (!isInterrupted()) {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Calendar calendar = Calendar.getInstance();
                            int hour = calendar.get(Calendar.HOUR_OF_DAY); // 시
                            int minute = calendar.get(Calendar.MINUTE); // 분
                            int second = calendar.get(Calendar.SECOND); // 초


                            tx_time.setText(hour + ":" + minute + ":" + second + "\n");
                        }
                    });
                    try {
                        Thread.sleep(1000); // 1000 ms = 1초
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                } // while
            } // run()
        }; // new Thread() { };


    }
}
