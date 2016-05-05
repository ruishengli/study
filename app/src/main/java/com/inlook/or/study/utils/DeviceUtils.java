package com.inlook.or.study.utils;

import android.content.Context;
import android.util.DisplayMetrics;

public class DeviceUtils {

    public static int getScreenSize(Context context) {
        DisplayMetrics  displayMetrics = context.getResources().getDisplayMetrics();
        return displayMetrics.widthPixels;
    }
    public static int dip2Px(Context context,int dipValue) {
        DisplayMetrics  displayMetrics = context.getResources().getDisplayMetrics();
        float scale  = displayMetrics.density;
        return (int) (dipValue * scale + 0.5f);
    }
    
    public static int sp2px(Context context, float spValue) {
        final float fontScale = context.getResources().getDisplayMetrics().scaledDensity;
        return (int) (spValue * fontScale + 0.5f);
}
}
