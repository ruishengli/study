package com.inlook.or.study.activity.blurredview;

import android.annotation.TargetApi;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.Bundle;
import android.renderscript.Allocation;
import android.renderscript.Element;
import android.renderscript.RenderScript;
import android.renderscript.ScriptIntrinsicBlur;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

import com.inlook.or.study.R;
import com.inlook.or.study.activity.BaseAppCompatActivity;

public class BaseBlurredViewActivity extends BaseAppCompatActivity {

    private SeekBar mSeekBar;
    private TextView mProgressText;
    private ImageView mBlurredImg;
    private ImageView mOriginImg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        initView();
    }

    @Override
    public int getLayoutResId() {
        return R.layout.activity_base_blurred_view;
    }


    private void initView() {
        mSeekBar = (SeekBar) findViewById(R.id.seekbar);
        mSeekBar.setMax(100);
        mProgressText = (TextView) findViewById(R.id.progress_tv);
        mBlurredImg = (ImageView) findViewById(R.id.blurred_img);
        mOriginImg = (ImageView) findViewById(R.id.origin_img);

        mBlurredImg.setImageBitmap(getBlurredBitmap());


        mSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

                int alpha =  (int)(255f - progress*2.55f);
                mOriginImg.setImageAlpha(alpha);
                mProgressText.setText(String.valueOf(progress));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });


    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN_MR1)
    private Bitmap getBlurredBitmap() {
        float scale = 0.4f;
        Bitmap mOriginBitmap = BitmapFactory.decodeResource(getResources(),R.drawable.dayu);
        int width = Math.round(mOriginBitmap.getWidth()*scale);
        int height = Math.round(mOriginBitmap.getHeight()*scale);

        Bitmap inputBitmap = Bitmap.createScaledBitmap(mOriginBitmap,width ,height,false);

        Bitmap outputBitmap = Bitmap.createBitmap(inputBitmap);

        RenderScript rs = RenderScript.create(this);
        ScriptIntrinsicBlur scriptIntrinsicBlur = ScriptIntrinsicBlur.create(rs, Element.U8_4(rs));

        Allocation temIn = Allocation.createFromBitmap(rs,inputBitmap);
        Allocation temOut = Allocation.createFromBitmap(rs,outputBitmap);

        scriptIntrinsicBlur.setRadius(25f);
        scriptIntrinsicBlur.setInput(temIn);
        scriptIntrinsicBlur.forEach(temOut);

        temOut.copyTo(outputBitmap);

        return outputBitmap;
    }
}
