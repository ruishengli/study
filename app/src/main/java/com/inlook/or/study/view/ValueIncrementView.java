package com.inlook.or.study.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

public class ValueIncrementView extends View implements View.OnClickListener {

    private static final String TAG = ValueIncrementView.class.getSimpleName();
    private int mCount;
    private Paint mPaint;
    private Rect mBound;
    
    public ValueIncrementView(Context context) {
        super(context);
        init();
    }
    
    public ValueIncrementView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }
    
    public ValueIncrementView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mBound = new Rect();
        setOnClickListener(this);
    }
    
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        mPaint.setColor(Color.BLUE);
        canvas.drawRect(0, 0, getWidth(), getHeight(), mPaint);  
        
        mPaint.setColor(Color.RED);
        mPaint.setTextSize(60);
        String text = String.valueOf(mCount);
        mPaint.getTextBounds(text, 0, text.length(), mBound);
        
        float textWidth = mBound.width();
        float textHeight = mBound.height();
        
        Log.d(TAG, "textWidth:" + textWidth + ",textHeight:"+textHeight);
        canvas.drawText(text, getWidth() /2 - textWidth /2, getHeight()/2 + textHeight/2, mPaint);
    }

    @Override
    public void onClick(View v) {
           mCount ++;
           invalidate();
    }
}
