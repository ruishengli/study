package com.inlook.or.study.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.RadialGradient;
import android.graphics.Shader;
import android.util.AttributeSet;
import android.widget.TextView;

public class ShineTextView extends TextView {

    private LinearGradient mLinearGradient;
    private Matrix mGradientMatrix;
    private Paint mPaint;
    private Shader mDefaultShader;
    private int mViewWidth = 0;
    private int mTranslate = 0;

    private boolean mAnimating = false;

    public void startShine(){
        if(!mAnimating) {
            mAnimating = true;
            invalidate();
        }
    }
    public void stopShine(){
        if(mAnimating) {
            mAnimating = false;
            invalidate();
        }
    }

    public ShineTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        if (mViewWidth == 0) {
            mViewWidth = getMeasuredWidth();
            if (mViewWidth > 0) {
                mPaint = getPaint();
             /*  mLinearGradient = new LinearGradient(-mViewWidth, 0, 0, 0, new int[] { 0x3307bc08,
                        0xff07bc08, 0x3307bc08 }, new float[] { 0, 0.5f, 1 }, Shader.TileMode.CLAMP);*/
                mLinearGradient = new LinearGradient(-mViewWidth, 0, 0, 0,
                        new int[] { 0x33ffffff, 0xffffffff, 0x33ffffff },
                        new float[] { 0, 0.5f, 1 }, Shader.TileMode.CLAMP);
                mDefaultShader = mPaint.getShader();
                mGradientMatrix = new Matrix();
            }
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        mPaint.setShader(mDefaultShader);
        if (mAnimating && mGradientMatrix != null) {
            mPaint.setShader(mLinearGradient);
            mTranslate += mViewWidth / 10;
            if (mTranslate > 2 * mViewWidth) {
                mTranslate = -mViewWidth;
            }
            mGradientMatrix.setTranslate(mTranslate, 0);
            mLinearGradient.setLocalMatrix(mGradientMatrix);
            postInvalidateDelayed(50);
        }
    }

}
