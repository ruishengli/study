package com.inlook.or.study.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.widget.ImageView;

public class CompassView extends ImageView {
    
    private float mDirection;// 方向旋转浮点数  
    private Drawable compass;// 图片资源 
    
    public CompassView(Context context) {
        super(context);
        initView();
    }
    
    public CompassView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView();
    }
    
    public CompassView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();
    }

    
    private void initView(){
        mDirection = 0.0f;
        compass = null;
    }

    
    public void updateDirection(float direction){
        mDirection = direction;
        invalidate();
    }
    
    @Override
    protected void onDraw(Canvas canvas) {
        // TODO Auto-generated method stub
       // super.onDraw(canvas);
        
        if(compass == null){
            compass = getDrawable();
            compass.setBounds(0, 0, getWidth(), getHeight());
        }
        
        canvas.save();
        canvas.rotate(mDirection,getWidth()/2,getHeight()/2);
        
        compass.draw(canvas);
        canvas.restore();
    }
}
