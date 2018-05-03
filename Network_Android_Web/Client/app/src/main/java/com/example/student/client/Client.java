/*
package com.example.student.client;

import android.util.Log;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

public class Client extends Thread {
    String TAG = "Client App ::: ";
    boolean cflag = true;
    boolean flag = true;
    String address = "70.12.114.149";
    int port = 8888;

    Socket socket;

    public Client() {

    }

    @Override
    public void run() {
        // 재접속을 위한 while
        while (cflag) {
            try {
                socket = new Socket(address, port);
                Log.d(TAG, "Connected Server ..");
                cflag = false;
                break;
            } catch (UnknownHostException e) {
                e.printStackTrace();
            } catch (IOException e) {
                Log.d(TAG, "Connected Retry ..");
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e1) {
                    e1.printStackTrace();
                }
            }
        }

        // After Connected . .
        try {
            new Receiver(socket).start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void sendMsg(String msg) {
        try {
            Sender sender = new Sender(socket);
            sender.setSendMsg(msg);
            new Thread(sender).start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    class Sender implements Runnable {
        Socket socket;
        OutputStream out;
        DataOutputStream outw;
        String sendMsg;

        public Sender(Socket socket) throws IOException {
            this.socket = socket;
            out = socket.getOutputStream();
            outw = new DataOutputStream(out);
        }

        public void setSendMsg(String sendMsg) {
            this.sendMsg = sendMsg;
        }

        @Override
        public void run() {
            try {
                if (outw != null) {
                    Log.d(TAG, "sendMSG : " + sendMsg);
                    outw.writeUTF(sendMsg);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    class Receiver extends Thread {
        Socket socket;
        InputStream in;
        DataInputStream inr;

        public Receiver(Socket socket) throws IOException {
            this.socket = socket;
            in = socket.getInputStream();
            inr = new DataInputStream(in);
        }

        @Override
        public void run() {
            try {
                // 계속 스레드가 실행하며 문자열을 받는다.
                while (flag == true && inr != null) {
                    String str = inr.readUTF();
                    Log.d(TAG, "recieve MSG : " + str);

                }
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                try {
                    if (inr != null)
                        inr.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void stopClient() {
        try {
            Thread.sleep(1000);
            flag = false;
            if (socket != null)
                socket.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}*/
