package com.example.student.server;

import android.os.AsyncTask;
import android.util.Log;
import android.widget.TextView;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.Socket;
import java.net.URL;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Receiver extends AsyncTask<Void, String, String> {
    InputStream in;
    DataInputStream din;

    OutputStream out;
    DataOutputStream dout;
    ArrayList<DataOutputStream> list = new ArrayList<>();
    TextView textView;

    public Receiver(Socket socket, TextView textView) throws IOException {
        this.textView = textView;
        in = socket.getInputStream();
        din = new DataInputStream(in);

        out = socket.getOutputStream();
        dout = new DataOutputStream(out);
        list.add(dout);
        System.out.println("Client count : " + list.size() +din);
    }

    @Override
    protected void onPreExecute() {
        Log.d("Receiver :::", "pre");
    }

    @Override
    protected String doInBackground(Void... voids) {
        String str = "";
        Log.d("Receiver :::", str);
        publishProgress("들어오긴하니");

        while (true) {
            try {
                Log.d("Receiver :::", str);
                str = din.readUTF();
                publishProgress(str);
            } catch (IOException e) {
                e.printStackTrace();
            }

            if (str != null && str.equals("q")) {
                break;
            }
            System.out.println(str);
        }

        list.remove(dout);
        try {
            if (dout != null) {
                dout.close();
            }
            if (din != null) {
                din.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return str;
    }

    @Override
    protected void onProgressUpdate(String... strs) {
        textView.setText(strs[0]);
        super.onProgressUpdate(strs);
    }

    @Override
    protected void onPostExecute(String s) {
        textView.setText(s);
        super.onPostExecute(s);
    }
}