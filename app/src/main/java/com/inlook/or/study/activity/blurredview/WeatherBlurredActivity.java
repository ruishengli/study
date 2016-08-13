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
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.inlook.or.study.R;
import com.inlook.or.study.activity.BaseAppCompatActivity;
import com.inlook.or.study.utils.DeviceUtils;
import com.inlook.or.study.utils.Logs;

public class WeatherBlurredActivity extends BaseAppCompatActivity {

    private final String TAG = WeatherBlurredActivity.class.getSimpleName();

    private ImageView mBlurredImg;
    private ImageView mOriginImg;
    private RecyclerView mWeatherView;

    private int mScrolledY = 0;
    private int defaultAddHeight = 100;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        initView();

        mWeatherView.setLayoutManager(new LinearLayoutManager(this));
        WeatherAdapter adapter = new WeatherAdapter(this);
        mWeatherView.setAdapter(adapter);

        updateLayoutParams();

        setBlurredImg();
    }

    @Override
    public int getLayoutResId() {
        return R.layout.activity_weatch_blurred;
    }


    private void initView() {
        mBlurredImg = (ImageView) findViewById(R.id.blurred_img);
        mOriginImg = (ImageView) findViewById(R.id.origin_img);
        mWeatherView = (RecyclerView) findViewById(R.id.weather_view);

        mWeatherView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                mScrolledY += dy;

                Logs.d(TAG,"dx " + dx +  " dy " + dy + " scrolledY " + mScrolledY);

                int alpha ;
                if(Math.abs(mScrolledY) > 1000) {
                    alpha = 100;
                    setMoveHeight(defaultAddHeight);
                } else {
                    setMoveHeight(mScrolledY / 10);
                    alpha =  Math.abs(mScrolledY) / 10;
                }

                setOriginAlpha(alpha);
            }
        });
    }

    private void updateLayoutParams() {
        int height = DeviceUtils.getScreenHeight(this);

        FrameLayout.LayoutParams originParams = (FrameLayout.LayoutParams) mOriginImg.getLayoutParams();
        originParams.height = height + defaultAddHeight;
        originParams.width = FrameLayout.LayoutParams.MATCH_PARENT;

        FrameLayout.LayoutParams blurredParams = (FrameLayout.LayoutParams) mBlurredImg.getLayoutParams();
        blurredParams.height = height + defaultAddHeight;
        blurredParams.width = FrameLayout.LayoutParams.MATCH_PARENT;

        mOriginImg.requestLayout();
        mBlurredImg.requestLayout();
    }


    private void setBlurredImg() {
        mBlurredImg.setImageBitmap(getBlurredBitmap());
    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    private void setOriginAlpha(int v) {
        int alpha =  (int)(255f - v*2.55f);
        mOriginImg.setImageAlpha(alpha);
    }


    private void setMoveHeight(int height) {
        mOriginImg.setTop(height * -1);
        mBlurredImg.setTop(height * -1);
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
