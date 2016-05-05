package com.inlook.or.study.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

public class CircleBar extends View {
    
    private Context mContext;
    private int mMax = 100;
    private Paint mSeekBarBackgroundPaint = null;
    private Paint mSeekbarProgressPaint = null;
    private Paint mProgressTextPaint = null;
    
    private RectF mArcRectF = null;
    private int mViewHeight = 0;
    private int mViewWidth = 0;
    private int mSeekBarSize = 0;
    private int mSeekBarRadius = 0;
    private int mSeekBarCenterX = 0;
    private int mSeekBarCenterY = 0;
    private float mSeekBarDegree = 0;
    private int barWidth = 12;
    private int mCurrentProgress;
    private int mProgressTextSize = 36;
    
    public CircleBar(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        mContext = context;
        initView();
    }
    
    public CircleBar(Context context, AttributeSet attrs) {
        super(context, attrs);
        mContext = context;
        initView();
    }
    
    public CircleBar(Context context) {
        super(context);
        mContext = context;
        initView();
    }
    
    
    private void initView(){
        
        mSeekBarBackgroundPaint = new Paint();
        mSeekbarProgressPaint = new Paint();
        
        mSeekbarProgressPaint.setColor(Color.RED);
        mSeekBarBackgroundPaint.setColor(Color.GRAY);
        
        mSeekbarProgressPaint.setAntiAlias(true);
        mSeekBarBackgroundPaint.setAntiAlias(true);
        
        mSeekbarProgressPaint.setStyle(Paint.Style.STROKE);
        mSeekBarBackgroundPaint.setStyle(Paint.Style.STROKE);
        
        
        mSeekbarProgressPaint.setStrokeWidth(barWidth);
        mSeekBarBackgroundPaint.setStrokeWidth(barWidth);
        
        mArcRectF = new RectF();
        
        mProgressTextPaint = new Paint();
        mProgressTextPaint.setColor(Color.RED);
        mProgressTextPaint.setAntiAlias(true);
        mProgressTextPaint.setStrokeWidth(3);
        mProgressTextPaint.setTextSize(mProgressTextSize);
    }
    
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        mViewWidth = getWidth();
        mViewHeight = getHeight();
        
        mSeekBarSize = mViewWidth > mViewHeight ? mViewHeight : mViewWidth;

        mSeekBarCenterX = mViewWidth / 2;
        mSeekBarCenterY = mViewHeight / 2;
        
        mSeekBarRadius = mSeekBarSize / 2 - barWidth/2;
        
        int left = mSeekBarCenterX - mSeekBarRadius;
        int right = mSeekBarCenterX + mSeekBarRadius;
        int top = mSeekBarCenterY - mSeekBarRadius;
        int bottom = mSeekBarCenterY + mSeekBarRadius;
        mArcRectF.set(left, top, right, bottom);
        
    }
    
    public void setProgress(int progress) {
        if (progress > mMax){
            progress = mMax;
        }
        if (progress < 0){
            progress = 0;
        }
        mCurrentProgress = progress;
        
        mSeekBarDegree =  (progress * 360 / mMax);
        invalidate();
    }
    
    @Override
    protected void onDraw(Canvas canvas) {
        canvas.drawCircle(mSeekBarCenterX, mSeekBarCenterY, mSeekBarRadius,
                mSeekBarBackgroundPaint);
        canvas.drawArc(this.mArcRectF, 270, mSeekBarDegree, false, mSeekbarProgressPaint);
        drawProgressText(canvas);
        
        super.onDraw(canvas);
    }
    
    private void drawProgressText(Canvas canvas) {
        
            float textWidth = mProgressTextPaint.measureText("" + mCurrentProgress);
            canvas.drawText("" + mCurrentProgress, mSeekBarCenterX - textWidth / 2, mSeekBarCenterY
                    + mProgressTextSize / 2, mProgressTextPaint);
    }
}
