package com.example.student.client;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

public class MainActivity extends AppCompatActivity {
    Client client;
    EditText speed;
    ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        speed = findViewById(R.id.editText);
        imageView = findViewById(R.id.imageView);
        client = new Client();
        client.start();
    }

    public void clickBtn(View v) {
        String msg = speed.getText().toString();
        client.sendMsg(msg);
    }

    public void convertImg(final String str) {
        Runnable r = new Runnable() {
            @Override
            public void run() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if(str.equals("1")) {
                            imageView.setImageResource(R.drawable.car1);
                        } else if(str.equals("2")) {
                            imageView.setImageResource(R.drawable.car22);
                        } else if(str.equals("3")) {
                            imageView.setImageResource(R.drawable.car3);
                        }
                    }
                });
            }
        };

        new Thread(r).start();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        client.stopClient();
    }

    public class Client extends Thread {
        String TAG = "Client App ::: ";
        boolean cflag = true;
        boolean flag = true;
        String address = "192.168.0.15";
        int port = 8888;

        Socket socket;

        public Client() {

        }

        @Override
        public void run() {
            // 재접속을 위한 while
            while (cflag) {
                try {
                    Log.d(TAG, "Try Connecting Server ..");
                    socket = new Socket(address, port);
                    //setSoTimeout()  : 일정시간 동안 서버와 교류가 없으면 Socket이 종료됨
                    //socket.setSoTimeout(5000);
                    Log.d(TAG, "Connected Server ..");
                    cflag = false;
                    break;
                } catch (UnknownHostException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    Log.d(TAG, "Connected Retry ..");
                    try {
                        Thread.sleep(2000);
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
            DataInputStream din;

            public Receiver(Socket socket) throws IOException {
                this.socket = socket;
                in = socket.getInputStream();
                din = new DataInputStream(in);
            }

            @Override
            public void run() {
                try {
                    // 계속 스레드가 실행하며 문자열을 받는다.
                    while (flag == true && din != null) {
                        String str = din.readUTF();
                        Log.d(TAG, "recieve MSG : " + str);
                        convertImg(str);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                } finally {
                    try {
                        if (din != null)
                            din.close();
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
    }
}
