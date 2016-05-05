package com.inlook.or.study.view;


import android.animation.TypeEvaluator;
import android.animation.ValueAnimator;
import android.animation.ValueAnimator.AnimatorUpdateListener;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.BounceInterpolator;

import com.inlook.or.study.model.Point;

public class MoveBallView extends View {

    private Paint mPaint;
    private Point mCurrentPoint;
    private static final  float RADIUS = 50f;
    
    public MoveBallView(Context context, AttributeSet attrs) {
        super(context, attrs);
        
        initView();
    }
    
    private void initView(){
        mPaint =  new Paint();
        mPaint.setColor(Color.BLUE);
    }

    
    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);
        if(mCurrentPoint == null) {
            mCurrentPoint =  new Point(getWidth()/2f,RADIUS);
            drawCircle(canvas);
            startAnimator();
        } else {
            drawCircle(canvas);
        }
        
    }
    
    private void drawCircle(Canvas canvas) {
        canvas.drawCircle(mCurrentPoint.getX(), mCurrentPoint.getY(), RADIUS, mPaint);
    }
    
    private void startAnimator(){
        Point startPoint =  new Point(getWidth()/2f,RADIUS);
        Point endPoint =   new Point(getWidth()/2f-RADIUS/2,getHeight()-RADIUS);
        
        ValueAnimator anim = ValueAnimator.ofObject(new PointEvaluator(), startPoint,endPoint);
        anim.addUpdateListener(new AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                Point point = (Point) animation.getAnimatedValue();
                mCurrentPoint = new Point(point.getX(), point.getY());
                invalidate();;
            }
        });
        
        anim.setInterpolator(new BounceInterpolator());
        anim.setDuration(4000);
        anim.start();
    }
    
    
    public class PointEvaluator implements TypeEvaluator{

        @Override
        public Object evaluate(float fraction, Object startValue, Object endValue) {
            Point startPoint = (Point) startValue;
            Point endPoint = (Point) endValue;
            float x = startPoint.getX() + fraction * (endPoint.getX() - startPoint.getX());
            float y = startPoint.getY() + fraction * (endPoint.getY() - startPoint.getY());
            Point point = new Point(x, y);
            return point;
        }
    }
}
