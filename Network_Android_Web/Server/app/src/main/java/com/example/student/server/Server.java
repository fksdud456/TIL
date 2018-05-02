package com.example.student.server;

import android.os.AsyncTask;
import android.util.Log;
import android.widget.TextView;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class Server extends AsyncTask<Void, String, Void> {
    ServerSocket serverSocket;
    ArrayList<DataOutputStream> list = new ArrayList<>();

    private int port = 9999;
    private boolean flag = true;
    TextView textView;
    public Server(TextView textView) throws IOException {
        this.textView = textView;
        serverSocket = new ServerSocket(9999);
        Log.d("Server() :: ","Server Ready . . .");
    }

    @Override
    protected void onPreExecute() {

        Log.d("pre :::::",serverSocket.getInetAddress().getHostAddress());
    }

    @Override
    protected Void doInBackground(Void... voids) {
        while (flag) {
            Socket socket = null;
            try {
                Log.d("doInBackground :: "," Waiting. . .");
                socket = serverSocket.accept();
                publishProgress(socket.getInetAddress().getHostAddress());
            } catch (IOException e) {
                e.printStackTrace();
            }
            Log.d("doInBackground",socket.getInetAddress() + "Connected");
            try {
                Receiver receiver = new Receiver(socket, textView);
                receiver.execute();

                    Thread.sleep(1000);

            } catch (IOException | InterruptedException e) {
                e.printStackTrace();
            }

        }
        return null;
    }

    @Override
    protected void onProgressUpdate(String... strs) {
        textView.setText(strs[0]);
        super.onProgressUpdate(strs);
    }
}
