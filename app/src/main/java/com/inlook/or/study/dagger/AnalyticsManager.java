package com.inlook.or.study.dagger;

import android.content.Context;
import android.util.Log;

import com.inlook.or.study.utils.Logs;

/**
 * desc AnalyticsManager
 *
 * @author: or
 * @since: on 2016/7/1.
 */
public class AnalyticsManager {
    private static final String TAG = "AnalyticsManager";
    public AnalyticsManager(Context context) {
    }

    public void register() {
        Log.d(TAG, "register()");
    }

    public void send(String data) {
        Logs.d(TAG, data);
    }
}
