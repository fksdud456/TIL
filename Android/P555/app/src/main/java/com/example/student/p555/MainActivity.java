package com.example.student.p555;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.concurrent.ExecutionException;

public class MainActivity extends AppCompatActivity {
    EditText editText, editText2;
    TextView textView;
    LoginTask loginTask;
    ProgressDialog progressDialog;
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editText = findViewById(R.id.et_ID);
        editText2 = findViewById(R.id.et_pw);
        textView = findViewById(R.id.textView3);
        button = findViewById(R.id.button);
        progressDialog = new ProgressDialog(MainActivity.this);
        progressDialog.setMessage("Progress . . . ");
        progressDialog.setCancelable(false);
    }

    public void clickLogin(View v) throws ExecutionException, InterruptedException {

        String id = editText.getText().toString();
        String pw = editText2
                .getText().toString();
        String result = "";

        loginTask = new LoginTask();

        loginTask.execute(id, pw);

    }

    // 실행 인자값 / update 인자값 / return 값
    class LoginTask extends AsyncTask<String, Void, String> {

        @Override
        protected void onPreExecute() {
            button.setEnabled(false);
            progressDialog.show();

            super.onPreExecute();
        }

        @Override
        protected String doInBackground(String... strings) {
            String id = strings[0];
            String pwd = strings[1];

            // HTTP request
            String result = "";

            if (requestLogin(id, pwd)) {
                result = "1";
            } else {
                result = "0";
            }

            return result;
        }


        @Override
        protected void onPostExecute(String s) {
            progressDialog.dismiss();
            button.setEnabled(true);

            if (s.equals("1")) {
                textView.setText("Login Success");
            } else {
                textView.setText("Login Fail !");
            }
            super.onPostExecute(s);
        }

        private boolean requestLogin(String id, String pwd) {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if (id.equals("qq") && pwd.equals("12")) {
                return true;
            } else {
                return false;
            }
        }
    }
}
