package com.inlook.or.study.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;


import android.app.ActivityManager;
import android.app.ActivityManager.RunningTaskInfo;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.os.Handler;
import android.os.IBinder;
import android.util.Log;

import com.inlook.or.study.floatwindow.FloatWindowManager;

public class FloatWindowService extends Service {

    private Handler mHandler = new Handler();
    private FloatWindowManager mFloatManager;
    private Timer mTimer;

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        // TODO Auto-generated method stub
        super.onCreate();
        
        mFloatManager = new FloatWindowManager(getApplicationContext());
    }
    
    
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        if (mTimer == null) {
            mTimer = new Timer();

            mTimer.scheduleAtFixedRate(new RefreshTask(), 0, 500);
        }
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        if (mTimer != null) {
            mTimer.cancel();
            mTimer = null;
        }
    }

    class RefreshTask extends TimerTask {
        @Override
        public void run() {
            if (isHome() && !mFloatManager.isShowWindowing()) {
                mHandler.post(new Runnable() {

                    @Override
                    public void run() {
                        mFloatManager.createSmallView();
                    }
                });
            } else if (!isHome() && mFloatManager.isShowWindowing()) {
                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        mFloatManager.removeSmallView();
                        mFloatManager.removeBigView();
                    }
                });
            } else if (isHome() && mFloatManager.isShowWindowing()) {
                mHandler.post(new Runnable() {

                    @Override
                    public void run() {
                        mFloatManager.updatePerent();
                    }
                });
            }
        }
    }

    /**
     * 判断当前界面是否是桌面
     */
    private boolean isHome() {
        ActivityManager mActivityManager = (ActivityManager)getApplicationContext().getSystemService(Context.ACTIVITY_SERVICE);  
        List<RunningTaskInfo> rti = mActivityManager.getRunningTasks(1);
        List<String> strs = getLaunchers();
        boolean ret = false;
        if(strs != null && strs.size() > 0){
            ret =  strs.contains(rti.get(0).topActivity.getPackageName());
        }
        Log.e("FloatWindowService", "isHome:"+ret);
        return ret;
    }

    /**
     * 获得属于桌面的应用的应用包名称
     * 
     * @return 返回包含所有包名的字符串列表
     */
    private List<String> getLaunchers() {
        List<String> names = new ArrayList<String>();
        PackageManager packageManager = this.getPackageManager();
        // 属性
        Intent intent = new Intent(Intent.ACTION_MAIN);
        intent.addCategory(Intent.CATEGORY_HOME);
        List<ResolveInfo> resolveInfo = packageManager.queryIntentActivities(intent,
                PackageManager.MATCH_DEFAULT_ONLY);
        for (ResolveInfo ri : resolveInfo) {
            names.add(ri.activityInfo.packageName);
            Log.i("FloatWindowService", "packageName =" + ri.activityInfo.packageName);
        }
        return names;
    }
}
