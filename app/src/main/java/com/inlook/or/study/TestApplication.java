package com.inlook.or.study;

import android.app.ActivityManager;
import android.app.Application;
import android.content.Context;
import android.os.Process;

import com.inlook.or.study.dagger.AnalyticsManager;
import com.inlook.or.study.dagger.AppComponent;
import com.inlook.or.study.dagger.AppModule;
import com.inlook.or.study.dagger.DaggerActivityCompontent;
import com.inlook.or.study.dagger.DaggerAppComponent;
import com.inlook.or.study.dagger.DomainModule;
import com.yolanda.nohttp.NoHttp;

import java.util.List;

import javax.inject.Inject;

/**
 * desc TestApplication
 *
 * @author: or
 * @since: on 2016/5/18.
 */
public class TestApplication extends Application {
    private static TestApplication mInstance;

    AppComponent mAppComponent = null;
   // @Inject
    AnalyticsManager analyticsManager;

    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = this;
        mAppComponent = DaggerAppComponent.builder()
                .appModule(new AppModule(this))
                .domainModule(new DomainModule())
                .build();

        mAppComponent.inject(this);

        analyticsManager = mAppComponent.getAnalyticsManager();
        analyticsManager.register();

        NoHttp.initialize(this);
    }


    public AppComponent getAppComponent() {
        return mAppComponent;
    }

    public static TestApplication getInstance() {
        return mInstance;
    }


    private boolean isMainProcess() {
        ActivityManager activityManager = (ActivityManager) this.getSystemService(Context.ACTIVITY_SERVICE);
        List<ActivityManager.RunningAppProcessInfo> processInfos = activityManager.getRunningAppProcesses();
        int pid = Process.myPid();
        String packageName = getPackageName();
        for (ActivityManager.RunningAppProcessInfo info :processInfos ) {
            if(info.pid == pid && info.processName.equalsIgnoreCase(packageName)) {
                return true;
            }
        }

       return false;
    }
}
