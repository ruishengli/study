package com.inlook.or.study.activity.customview;


import android.app.Activity;
import android.os.Bundle;
import com.inlook.or.study.R;
import com.inlook.or.study.view.DynamicPointTextView;

public class DynamicPointTextActivity extends Activity {

    private DynamicPointTextView mTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dync_point_text_activity);
        
        
        mTextView = (DynamicPointTextView) findViewById(R.id.alpha_txt);
        
        mTextView.startDyncPoint("aaaa");
        
      /*  AlphaAnimation alphaAnimation1 = new AlphaAnimation(0.1f, 1.0f);
        alphaAnimation1.setDuration(3000);
        alphaAnimation1.setRepeatCount(Animation.INFINITE);
        alphaAnimation1.setRepeatMode(Animation.REVERSE);
        mTextView.setAnimation(alphaAnimation1);
        alphaAnimation1.start();*/
    }
    
}
