package com.inlook.or.study.service;


import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.IBinder;
import android.util.Log;

import com.inlook.or.study.activity.LockScreenActivity;

public class LockScreenService extends Service {

    private static final String TAG = "LockScreenService";
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        Log.e(TAG, "onCreate");
        super.onCreate();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.e(TAG, "onDestroy");
        unRegisterBroadcast();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.e(TAG, "onStartCommand");
         super.onStartCommand(intent, flags, startId);
         registerBroadcast();
        return START_STICKY;
    }
    

    private void registerBroadcast() {
        IntentFilter filter = new IntentFilter();
        filter.addAction(Intent.ACTION_SCREEN_OFF);
        getApplicationContext().registerReceiver(mScreenOffReceiver, filter);
    }

    private void unRegisterBroadcast() {
        getApplicationContext().unregisterReceiver(mScreenOffReceiver);
    }

    private BroadcastReceiver mScreenOffReceiver = new BroadcastReceiver() {

        @Override
        public void onReceive(Context context, Intent intent) {

            String action = intent.getAction();
            
            if (action.equals(Intent.ACTION_SCREEN_OFF)) {
                Intent lockscreen = new Intent(LockScreenService.this, LockScreenActivity.class);
                lockscreen.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(lockscreen);
            }
        }
    };
}
