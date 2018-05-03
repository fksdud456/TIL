package com.example.student.server;

import android.net.DhcpInfo;
import android.net.wifi.WifiManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

public class MainActivity extends AppCompatActivity {
    private Server server;
    private TextView txtV,txtV2;
    private TextView txtV_IP;
    private EditText editText;
    private boolean rflag;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtV = findViewById(R.id.textView);
        txtV2 = findViewById(R.id.textView2);
        txtV_IP = findViewById(R.id.textView_IP);
        editText = findViewById(R.id.editText);

        rflag = true;

        try {
            server = new Server();
        } catch (IOException e) {
            e.printStackTrace();
        }
        server.start();
    }

    public void clickBtn(View v) {
        String str = editText.getText().toString();
        server.sendMsg(str);
    }


    public void setSpeed(final String msg) {
        Runnable r = new Runnable() {
            @Override
            public void run() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        txtV.setText(msg);
                    }
                });
            }
        };
        new Thread(r).start();
    }

    public void setConnect(final String msg, final String ip) {
        Runnable r = new Runnable() {
            @Override
            public void run() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if(msg.equals("t")) {
                            txtV2.setText(ip  + "연결!");
                        } else {
                            txtV2.setText("실푸ㅐ");
                        }
                    }
                });
            }
        };
        new Thread(r).start();
    }

    // ServerSocket Start . . .
    public class Server extends Thread {
        ServerSocket serversocket;
        boolean flag = true;
        boolean rflag = true;
        HashMap<String, DataOutputStream> hmap = new HashMap<>();

        public Server() throws IOException {
            // ServerSocket Create
            serversocket = new ServerSocket(8888);
        }

        @Override
        public void run() {
            // Accept Client Connection
            try {
                while (flag) {
                    System.out.println("Ready Accept");
                    Socket socket = serversocket.accept(); // 멈춰있다가 반응이 오면 작동
                    new Receiver(socket).start(); // socket을 receiver에게 전달
                    setConnect("t" , socket.getInetAddress().getHostAddress());
                }
            } catch (Exception e) {

            }
        }

        public void sendMsg(String msg) {
            Sender sender = new Sender();
            sender.setMsg(msg);
            sender.start();
        }
        // Inner Class Receiver & Sender
        class Receiver extends Thread {
            // 여러 클라이언트가 서버에 들어오면 가장 먼저 거치는 스레드
            InputStream in;
            DataInputStream din;
            OutputStream out;
            DataOutputStream dout;
            Socket socket;
            String ip;

            public Receiver(Socket socket) {
                try {
                    System.out.println("Connected Count : "+ hmap.size());

                    this.socket = socket;
                    in = socket.getInputStream();
                    din = new DataInputStream(in);
                    out = socket.getOutputStream();
                    dout = new DataOutputStream(out);
                    ip = socket.getInetAddress().toString();
                    hmap.put(ip, dout);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } // end Receiver

            @Override
            public void run() {
                try {
                    while (rflag) {
                        if (socket.isConnected() && din != null && din.available() > 0) {
                            String str = din.readUTF();
                            setSpeed(str);
                            Log.d("Receiver :: " , str);
                        }
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    setConnect(null, "f");
                    stopServer();
                    try {
                        Thread.sleep(200);
                        if (din != null) {
                            din.close();
                        }
                        if (dout != null) {
                            dout.close();
                        }
                        if (socket != null) {
                            socket.close();
                        }
                    } catch (Exception e) {
                    }
                }
            }
        }

        class Sender extends Thread {
            String msg;

            public void setMsg(String msg) {
                this.msg = msg;
            }

            @Override
            public void run() {
                // 클라이언트에 보내주기
                try {
                    Set<String> keys = hmap.keySet();
                    Iterator<String> it = keys.iterator();
                    if (hmap.size() > 0) {
                        while (it.hasNext()) {
                            String str = it.next();
                            DataOutputStream dout2 = hmap.get(str);
                            Log.d("TTT", msg);
                            dout2.writeUTF(msg);
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }

        public void stopServer() {
            Log.d("sopServer" , "close");
            rflag = false;
        }
    }
    // ServerSocket End . . .

} // end MainActivity . .
