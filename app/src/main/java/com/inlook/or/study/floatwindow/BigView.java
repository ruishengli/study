package com.inlook.or.study.floatwindow;

import com.study.android.R;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;

public class BigView extends LinearLayout implements View.OnClickListener{
    
    public interface OnBigViewClickListener {
       public void onClose();
      public void  onBack();
    }
    private OnBigViewClickListener mListener;
    
    public void setListener(OnBigViewClickListener listener){
        mListener = listener;
    }
    
    public static int viewWidth;
    public static int viewHeight;
    
    public BigView(Context context) {
        super(context);
        initView(context);
    }

    public BigView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView(context);
    }

    public BigView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context);
    }

    private void initView(Context context) {
         LayoutInflater.from(context).inflate(R.layout.float_window_big_view, this);
        
        View view = findViewById(R.id.big_window_view);
        viewWidth = view.getLayoutParams().width;
        viewHeight = view.getLayoutParams().height;
        findViewById(R.id.close_btn).setOnClickListener(this);
        findViewById(R.id.back_btn).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()) {
        case R.id.close_btn:
            if(mListener != null) {
                mListener.onClose();
            }
            break;
        case R.id.back_btn:
            
            if(mListener != null) {
                mListener.onBack();
            }
            break;
        }
    }
}
