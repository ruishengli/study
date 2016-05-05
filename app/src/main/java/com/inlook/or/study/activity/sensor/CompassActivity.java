package com.inlook.or.study.activity.sensor;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.AccelerateInterpolator;

import com.inlook.or.study.R;
import com.inlook.or.study.activity.BaseAppCompatActivity;
import com.inlook.or.study.view.CompassView;


public class CompassActivity extends BaseAppCompatActivity {

    private static final int DEFALUT_REFRESH_TIME = 20;
    private final float MAX_ROATE_DEGREE = 1.0f;// 最多旋转一周，即360
    private AccelerateInterpolator mInterpolator;
    private CompassView mCompassView;
    private SensorManager mSensorManager;
    private Sensor mSensor;
    private float mTargetDirection;
    private float mCurDirection;
    private boolean mStopDrawing;// 是否停止指南针旋转的标志位
    private Handler mHandler;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mHandler = new Handler();
        mInterpolator = new AccelerateInterpolator();
        mCompassView = (CompassView) findViewById(R.id.compassview);
        initSensor();
    }
    
    
    @Override
    public int getLayoutResId() {
        return R.layout.activity_compass;
    }
    
    @Override
    protected void onResume() {
        super.onResume();
        mStopDrawing = false;
        registerSensorListener();
        mHandler.postDelayed(mCompassViewUpdater, 20);
    }

    
    @Override
    protected void onPause() {
        super.onPause();
        mStopDrawing = true;
        unRegisterSensorListener();
        mHandler.removeCallbacks(mCompassViewUpdater);
    }
    
    
    private void initSensor(){
        mSensorManager =  (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        mSensor = mSensorManager.getDefaultSensor(Sensor.TYPE_ORIENTATION);
    }
    
    
    private Runnable mCompassViewUpdater = new Runnable() {
        @Override
        public void run() {
            if(!mStopDrawing && mTargetDirection != mCurDirection && mCompassView != null) {
                
             // calculate the short routine  
                float to = mTargetDirection;  
                if (to - mCurDirection > 180) {  
                    to -= 360;  
                } else if (to - mCurDirection < -180) {  
                    to += 360;  
                }  

                // limit the max speed to MAX_ROTATE_DEGREE  
                float distance = to - mCurDirection;  
                if (Math.abs(distance) > MAX_ROATE_DEGREE) {  
                    distance = distance > 0 ? MAX_ROATE_DEGREE  
                            : (-1.0f * MAX_ROATE_DEGREE);  
                }  

                // need to slow down if the distance is short  
                mCurDirection = normalizeDegree(mCurDirection  
                        + ((to - mCurDirection) * mInterpolator  
                                .getInterpolation(Math.abs(distance) > MAX_ROATE_DEGREE ? 0.4f  
                                        : 0.3f)));// 用了一个加速动画去旋转图片，很细致  
                mCompassView.updateDirection(mCurDirection);// 更新指南针旋转 
                
            }
            
            mHandler.postDelayed(mCompassViewUpdater, 20);
        }
    };
    
    private SensorEventListener mSensorEventListener = new SensorEventListener(){
        @Override
        public void onSensorChanged(SensorEvent event) {
            float direction  = event.values[0] * -1.0f;
            mTargetDirection = normalizeDegree(direction);
        }
        
        @Override
        public void onAccuracyChanged(Sensor sensor, int accuracy) {
        }
    };
    
    
 // 调整方向传感器获取的值  
    private float normalizeDegree(float degree) {  
        return (degree + 720) % 360;  
    }
    
    private void registerSensorListener() {
        mSensorManager.registerListener(mSensorEventListener, mSensor, SensorManager.SENSOR_DELAY_GAME);
    }
    
    private void unRegisterSensorListener() {
        if(mSensorManager !=null && mSensorEventListener != null) {
            mSensorManager.unregisterListener(mSensorEventListener);
        }
    }
}
