package com.inlook.or.study.view;

import android.content.Context;
import android.os.Handler;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.widget.TextView;

public class DynamicPointTextView extends TextView {
    
    private static final int MAX_POINT = 6;
    private static final int TIME = 500;
    private int mCurrentPoint = 0;
    private Handler mHandler;
    private CharSequence mSetStr = "";
    
    public DynamicPointTextView(Context context) {
        super(context);
    }
    public DynamicPointTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }
    
    public DynamicPointTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public void startDyncPoint(String text){
        mSetStr = text;
        mHandler = new Handler();
        mCurrentPoint = 0;
        setText(text);
        start();
    }
    
    
    @Override
    public void setText(CharSequence text, BufferType type) {
        super.setText(text, type);
    }
    
    
    public void stopDyncPoint(){
        setText(mSetStr);
        mHandler.removeCallbacks(pointTask);
        mCurrentPoint = 0;
    }
    
    private void start(){
        mHandler.post(pointTask);
    }
    
    
    private Runnable  pointTask = new Runnable(){
        @Override
        public void run() {
            if(!TextUtils.isEmpty(mSetStr)) {
                calcPoint();
                setText(mSetStr+getPoint());
                mHandler.postDelayed(pointTask, TIME);
            }
        }
    };
    
    private void calcPoint() {
        mCurrentPoint++;
        if(mCurrentPoint > MAX_POINT) {
            mCurrentPoint = 1;
        }
    }
    private String getPoint(){
        StringBuffer sb = new StringBuffer();
        for(int i=0;i<mCurrentPoint;i++){
            sb.append(".");
        }
        return sb.toString();
    }
    
}
