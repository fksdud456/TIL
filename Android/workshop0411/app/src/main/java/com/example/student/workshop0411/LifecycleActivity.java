package com.example.student.workshop0411;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Date;

public class LifecycleActivity extends AppCompatActivity {
    TextView lifecycle_tx;
    Date date = new Date();
    SharedPreferences sp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lifecycle);

        lifecycle_tx = findViewById(R.id.lifecycle_tx);
        sp = getSharedPreferences("pref", Activity.MODE_PRIVATE);
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


    @Override
    protected void onPause() {
        super.onPause();
        lifecycle_tx.setText("onPause");
        saveState();
    }

    @Override
    protected void onResume() {
        super.onResume();
        lifecycle_tx.setText("onResume");
        restoreState();
    }

    protected void restoreState() {
        if (sp != null) {
            if (sp.contains("cnt") && sp.contains("date")) {
                String rdate = sp.getString("date", "");
                int rcnt = sp.getInt("cnt", 0);
                Toast.makeText(this, rdate + " " + rcnt, Toast.LENGTH_LONG).show();
            }

        }
    }

    protected void saveState() {
        SharedPreferences.Editor editor = sp.edit();
        if (sp != null) {
            int cnt = 0;
            if (sp.contains("cnt"))
                cnt = (sp.getInt("cnt", 0)) + 1;

            editor.putInt("cnt", cnt);
            editor.putString("date", date.toString());
            editor.commit();
        }
    }

    @Override
    public void onBackPressed() {
        //super.onBackPressed();

        // context : 현재 실행되는 Activity 위에 Dialog를 실행하겠다
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Alert Message !!");
        builder.setMessage("Are you want to exit & clear ? ");

        builder.setNegativeButton("NO", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                finish();
            }
        });
        builder.setPositiveButton("YES", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                SharedPreferences.Editor editor = sp.edit();
                editor.putInt("cnt", 0);
                editor.commit();
                finish();
            }
        });
        builder.show();
    }

    @Override
    protected void onDestroy() {
        lifecycle_tx.setText("onDestroy");
        super.onDestroy();
    }
}
