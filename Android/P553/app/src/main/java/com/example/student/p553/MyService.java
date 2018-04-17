package com.example.student.p553;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

public class MyService extends Service {
    int resArr2[] = {R.drawable.icon1, R.drawable.icon2};
    boolean flag = true;
    private static final String TAG = "ImageService -- ";

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
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d(TAG, "Service onStartCommand .........");
        if (intent == null) {
            return Service.START_STICKY;
        } else {
            processCommand(intent);
        }
        return super.onStartCommand(intent, flags, startId);
    }

    private void processCommand(Intent intent) {
        Log.d(TAG, "processCommand.......");

        final Intent sintent = new Intent(getApplicationContext(), MainActivity.class);
        sintent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_SINGLE_TOP | Intent.FLAG_ACTIVITY_CLEAR_TOP);

        Runnable run = new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 100 & flag; i++) {
                    Log.d(TAG, "Waiting" + i + "seconds.");
                    sintent.putExtra("command", "MyService");
                    sintent.putExtra("state", 1);
                    sintent.putExtra("cnt", resArr2[i%2]);
                    startActivity(sintent);

                    try {
                        Thread.sleep(300);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                }

                sintent.putExtra("state", 0);
                sintent.putExtra("command", "MyService");
                startActivity(sintent);
            }
        };

        new Thread(run).start();
    }

    @Override
    public boolean stopService(Intent name) {
        //flag = false;

        return super.stopService(name);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        flag = false;

        Log.d(TAG,"onDestroy........... ");
    }
}
