package com.inlook.or.study.activity.bitmap;


import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.ViewGroup.LayoutParams;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;

import com.inlook.or.study.R;
import com.inlook.or.study.utils.ImageUtils;

public class PorterDuffBitmapActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Drawable drawable = this.getResources().getDrawable(R.drawable.icon_about);

        ImageView imageView = new ImageView(this);
        imageView.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
        imageView.setScaleType(ScaleType.FIT_CENTER);
        Bitmap bitmap = ImageUtils.drawableToColorFilterBitmap(drawable);
        imageView.setImageBitmap(bitmap);


        setContentView(imageView);
    }


}
