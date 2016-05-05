package com.inlook.or.study.activity.sensor;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Build;
import android.os.Bundle;
import android.widget.TextView;

import com.study.android.R;
import com.study.android.activity.BaseAppCompatActivity;

public class StepActivity extends BaseAppCompatActivity {

    private boolean mIsSupport = false;
    private Sensor mStepSensor;
    private SensorManager mSensorManager;
    
    private TextView mStepCount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            mIsSupport = true;
        }
        
        mStepCount = (TextView) findViewById(R.id.step_count);
        
        if(!mIsSupport) {
            mStepCount.setText("你的手机暂不支持");
        }
        
        initSensor();
    }

    @Override
    public int getLayoutResId() {
        return R.layout.activity_step;
    }
    
    @Override
    protected void onResume() {
        super.onResume();
        registerSensorListener();
    }

    @Override
    protected void onPause() {
        super.onPause();
        unRegisterSensorListener();
    }
    
    private void initSensor(){
        if(mIsSupport) {
            mSensorManager =  (SensorManager) getSystemService(Context.SENSOR_SERVICE);
            mStepSensor = mSensorManager.getDefaultSensor(Sensor.TYPE_STEP_COUNTER);
        }
        
    }

    private void registerSensorListener() {
        if(mSensorManager != null && mStepSensor != null)
            mSensorManager.registerListener(mSensorEventListener, mStepSensor,
                    SensorManager.SENSOR_DELAY_GAME);
    }

    private void unRegisterSensorListener() {
        if (mSensorManager != null && mSensorEventListener != null && mStepSensor != null) {
            mSensorManager.unregisterListener(mSensorEventListener);
        }
    }
    

    private SensorEventListener mSensorEventListener = new SensorEventListener(){
        @Override
        public void onSensorChanged(SensorEvent event) {
            mStepCount.setText(Float.valueOf(event.values[0]).intValue()+"步");  
        }
        
        @Override
        public void onAccuracyChanged(Sensor sensor, int accuracy) {
        }
    };
}
