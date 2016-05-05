package com.inlook.or.study.floatwindow;

import java.lang.reflect.Field;


import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.view.WindowManager.LayoutParams;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.inlook.or.study.R;

public class SmallView extends LinearLayout {

    public interface OnSmallViewClickListener{
        public void onClick();
        
        public void onMove(int x, int y);
    }
    
    private OnSmallViewClickListener mListener;
    
    public void setListener(OnSmallViewClickListener listener){
        mListener = listener;
    }
    
    private TextView mPercentView;

    public static int viewWidth;
    public static int viewHeight;

    private int statusBarHeight;

    private float xInView;
    private float yInView;
    private float xInScreen;
    private float yInScreen;
    private float xDownScreen;
    private float yDownScreen;

    public SmallView(Context context) {
        super(context);
        initView(context);
    }

    public SmallView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView(context);
    }

    public SmallView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context);
    }

    private void initView(Context context) {
        LayoutInflater.from(context).inflate(R.layout.float_window_small_view, this);
        View view = findViewById(R.id.small_window_view);
        mPercentView = (TextView) findViewById(R.id.memory_percent_txt);
        viewWidth = view.getLayoutParams().width;
        viewHeight = view.getLayoutParams().height;

    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {

        switch (event.getAction()) {
        case MotionEvent.ACTION_DOWN:
            xInView = event.getX();
            yInView = event.getY();
            xInScreen = event.getRawX();
            yInScreen = event.getRawY() - getStatusHeight();
            xDownScreen = event.getRawX();
            yDownScreen = event.getRawY() - getStatusHeight();
            break;
        case MotionEvent.ACTION_MOVE:
            xInScreen = event.getRawX();
            yInScreen = event.getRawY() - getStatusHeight();
            updateViewPoisition();
            break;
        case MotionEvent.ACTION_UP:

            if(xInScreen ==xDownScreen &&  yDownScreen ==yInScreen ) {
                openBigView();
            }
            break;

        default:
            break;
        }

        return true;
    }
    
    private void openBigView(){
        if(mListener != null ){
            mListener.onClick();
        }
    }

    private void updateViewPoisition() {
            if(mListener != null) {
                mListener.onMove( (int) (xInScreen - xInView),(int)(yInScreen - yInView));
            }
    }

    public void setMemoryPercent(String percent) {
        mPercentView.setText(percent);
    }

    public int getStatusHeight() {
        if (statusBarHeight == 0) {
            try {
                Class<?> c = Class.forName("com.android.internal.R$dimen");
                Object o = c.newInstance();
                Field field = c.getField("status_bar_height");
                int x = (Integer) field.get(o);
                statusBarHeight = getResources().getDimensionPixelSize(x);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return statusBarHeight;
    }
}
