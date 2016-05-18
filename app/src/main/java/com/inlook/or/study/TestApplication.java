package com.inlook.or.study;

import android.app.Application;

/**
 * desc TestApplication
 *
 * @author: or
 * @since: on 2016/5/18.
 */
public class TestApplication extends Application {
    private static TestApplication mInstance;
    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = this;
    }

    public static TestApplication getInstance() {
        return mInstance;
    }
}
