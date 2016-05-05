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
public class PorterDuffBitmapActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        Drawable drawable =this.getResources().getDrawable(R.drawable.icon_about);
        
        ImageView imageView = new ImageView(this);
        imageView.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
        imageView.setScaleType(ScaleType.FIT_CENTER);
        Bitmap bitmap = ImageUtils.drawableToColorFilterBitmap(drawable);
        imageView.setImageBitmap(bitmap); 
      
        
        setContentView(imageView);
    }
    
    
}
