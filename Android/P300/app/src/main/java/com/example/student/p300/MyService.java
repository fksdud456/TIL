package com.example.student.p300;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

public class MyService extends Service {

    private static final String TAG ="MyService ------------ ";
    public MyService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d(TAG, "Service onCreate .........");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        // start Service
        Log.d(TAG, "Service onStartCommand .........");
        if(intent == null) {
            return Service.START_STICKY;
        } else {
            processCommand(intent);
        }
        return super.onStartCommand(intent, flags, startId);
    }

    //parameter로 넘어온 intent는 MainActivity에서 넘어온 intent
    private void processCommand(Intent intent) {
        String command = intent.getStringExtra("command");
        String name = intent.getStringExtra("name");

        Log.d(TAG, "command : " + command + ", name : "+ name);

        final Intent sintent = new Intent(getApplicationContext(), MainActivity.class);
        sintent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_SINGLE_TOP | Intent.FLAG_ACTIVITY_CLEAR_TOP);

        Runnable run = new Runnable() {
            @Override
            public void run() {
                for(int i =0;  i <10; i++) {
                    Log.d(TAG, "Waiting" + i + "seconds.");
                    sintent.putExtra("command", "cmd");
                    sintent.putExtra("cnt", i);
                    startActivity(sintent);
                    try{
                        Thread.sleep(2000);
                    } catch(Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        };

        new Thread(run).start();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "Service onDestroy .........");
    }
}
