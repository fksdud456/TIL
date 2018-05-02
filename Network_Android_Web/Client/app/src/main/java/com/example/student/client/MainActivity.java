package com.example.student.client;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    Client client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        client = new Client();
        client.start();
    }

    public void clickBtn(View v) {
        EditText edit = findViewById(R.id.editText);
        String msg = edit.getText().toString();
        client.sendMessage(msg);
    }
}
