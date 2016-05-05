package com.inlook.or.study.activity.bitmap;

import com.study.android.R;
import com.study.android.utils.ImageUtils;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.ViewGroup.LayoutParams;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
/**
 * 显示圆角图片
 * RoundBitmapActivity
 * @author or<br/>
 * description: TODO<br/>
 * create: 2015年8月19日 下午3:33:18<br/>
 *
 */
public class RoundBitmapActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        Drawable drawable =this.getResources().getDrawable(R.drawable.user_homepage_cover);
        
        ImageView imageView = new ImageView(this);
        imageView.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
        imageView.setScaleType(ScaleType.FIT_CENTER);
        Bitmap bitmap = ImageUtils.drawableToBitmap(drawable);
        imageView.setImageBitmap(ImageUtils.getRoundedCornerBitmap(bitmap, 30)); 
      
        
        setContentView(imageView);
    }
    
    
}
