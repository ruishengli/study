package com.inlook.or.study.floatwindow;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import com.study.android.floatwindow.BigView.OnBigViewClickListener;
import com.study.android.floatwindow.SmallView.OnSmallViewClickListener;
import com.study.android.service.FloatWindowService;

import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.PixelFormat;
import android.view.Gravity;
import android.view.WindowManager;
import android.view.WindowManager.LayoutParams;

public class FloatWindowManager {

    private  WindowManager mWindowManager;
    private  ActivityManager mActivityManager;
    private  SmallView mSmallView;
    private  BigView mBigView;
    private Context mContext;
    
    
    public FloatWindowManager(Context context){
        mContext = context;
    }
    /**
     * 小悬浮窗View的参数
     */
    private  LayoutParams mSmallWindowParams;

    /**
     * 大悬浮窗View的参数
     */
    private  LayoutParams bigWindowParams;

    public  void createSmallView() {

        WindowManager wmg = getWindowManager();
        int screenWidth = wmg.getDefaultDisplay().getWidth();
        int screenHeight = wmg.getDefaultDisplay().getHeight();

        if (mSmallView == null) {
            mSmallView = new SmallView(mContext);

            if (mSmallWindowParams == null) {
                mSmallWindowParams = new LayoutParams();
                mSmallWindowParams.type = LayoutParams.TYPE_PHONE;
                mSmallWindowParams.format = PixelFormat.RGBA_8888;
                mSmallWindowParams.flags = LayoutParams.FLAG_NOT_TOUCH_MODAL
                        | LayoutParams.FLAG_NOT_FOCUSABLE;
                mSmallWindowParams.gravity = Gravity.LEFT | Gravity.TOP;
                mSmallWindowParams.width = SmallView.viewWidth;
                mSmallWindowParams.height = SmallView.viewHeight;

                mSmallWindowParams.x = screenWidth;
                mSmallWindowParams.y = screenHeight / 2;
            }

            mSmallView.setListener(new OnSmallViewClickListener() {
                @Override
                public void onClick() {
                    removeSmallView();
                    createBigView();
                }

                @Override
                public void onMove(int x, int y) {
                    if(mSmallView != null && mSmallWindowParams != null) {
                        mSmallWindowParams.x = x;
                        mSmallWindowParams.y = y;
                        getWindowManager().updateViewLayout(mSmallView, mSmallWindowParams);
                    }
                }
            });
        }

        mSmallView.setMemoryPercent(getPercent());
        wmg.addView(mSmallView, mSmallWindowParams);
    }

    public  void removeSmallView() {

        if (mSmallView != null) {
            WindowManager wmg = getWindowManager();
            wmg.removeView(mSmallView);
            mSmallView = null;
        }
    }

    public  void createBigView() {

        WindowManager wmg = getWindowManager();
        int screenWidth = wmg.getDefaultDisplay().getWidth();
        int screenHeight = wmg.getDefaultDisplay().getHeight();

        if (mBigView == null) {
            mBigView = new BigView(mContext);

            if (bigWindowParams == null) {
                bigWindowParams = new LayoutParams();
                bigWindowParams.type = LayoutParams.TYPE_PHONE;
                bigWindowParams.format = PixelFormat.RGBA_8888;
                bigWindowParams.flags = LayoutParams.FLAG_NOT_TOUCH_MODAL
                        | LayoutParams.FLAG_NOT_FOCUSABLE;
                bigWindowParams.gravity = Gravity.LEFT | Gravity.TOP;
                
                bigWindowParams.width = BigView.viewWidth;
                bigWindowParams.height = BigView.viewHeight;

                bigWindowParams.x = screenWidth / 2 - BigView.viewWidth / 2;
                bigWindowParams.y = screenHeight / 2 - BigView.viewHeight / 2;
            }

            mBigView.setListener(new OnBigViewClickListener() {

                @Override
                public void onClose() {
                    closeWindow();
                }

                @Override
                public void onBack() {
                    removeBigView();
                    createSmallView();
                }
            });
        }

        wmg.addView(mBigView, bigWindowParams);
    }

    private  void closeWindow() {
        removeBigView();
        removeSmallView();
        Intent intent = new Intent(mContext, FloatWindowService.class);
        mContext.stopService(intent);
    }

    public  void removeBigView() {

        if (mBigView != null) {
            WindowManager wmg = getWindowManager();
            wmg.removeView(mBigView);
            mBigView = null;
        }
    }

    public  void updatePerent() {
        if (mSmallView != null) {
            mSmallView.setMemoryPercent(getPercent());
        }
    }

    public  WindowManager getWindowManager() {
        if (mWindowManager == null) {
            mWindowManager = (WindowManager) mContext.getSystemService(Context.WINDOW_SERVICE);
        }
        return mWindowManager;
    }

    public  String getPercent() {
        String dir = "/proc/meminfo";
        try {
            FileReader fr = new FileReader(dir);
            BufferedReader br = new BufferedReader(fr, 2048);
            String memoryLine = br.readLine();
            String subMemoryLine = memoryLine.substring(memoryLine.indexOf("MemTotal:"));
            br.close();
            long totalMemorySize = Integer.parseInt(subMemoryLine.replaceAll("\\D+", ""));
            long availableSize = getAvailableMemory(mContext) / 1024;
            int percent = (int) ((totalMemorySize - availableSize) / (float) totalMemorySize * 100);
            return percent + "%";
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "悬浮窗";
    }

    /**
     * 获取当前可用内存，返回数据以字节为单位。
     * 
     * @param context
     *            可传入应用程序上下文。
     * @return 当前可用内存。
     */
    private  long getAvailableMemory(Context context) {
        ActivityManager.MemoryInfo mi = new ActivityManager.MemoryInfo();
        getActivityManager().getMemoryInfo(mi);
        return mi.availMem;
    }

    private  ActivityManager getActivityManager() {
        if (mActivityManager == null) {
            mActivityManager = (ActivityManager) mContext.getSystemService(Context.ACTIVITY_SERVICE);
        }
        return mActivityManager;
    }

    public  boolean isShowWindowing() {
        return mSmallView != null || mBigView !=null;
    }
}
