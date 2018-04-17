package com.example.student.workshop0412;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

public class ProgressService extends Service {
    private static final String TAG = "ProgressService -- ";

    public ProgressService() {
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
        Log.d(TAG, "progress");

        final Intent sintent = new Intent(getApplicationContext(), MainActivity.class);
        sintent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_SINGLE_TOP | Intent.FLAG_ACTIVITY_CLEAR_TOP);

        Runnable run = new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i <= 10; i++) {
                    Log.d(TAG, "Waiting" + i + "seconds.");
                    sintent.putExtra("command", "ProgressService");
                    sintent.putExtra("progress", i);
                    startActivity(sintent);
                    try {
                        Thread.sleep(1000);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }

                sintent.putExtra("state", 0);
                sintent.putExtra("command", "ProgressService");
                startActivity(sintent);
            }
        };

        new Thread(run).start();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}
