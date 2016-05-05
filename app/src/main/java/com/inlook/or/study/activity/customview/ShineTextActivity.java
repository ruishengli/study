package com.inlook.or.study.activity.customview;

import com.study.android.R;
import com.study.android.view.ShineTextView;

import android.app.Activity;
import android.os.Bundle;

public class ShineTextActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.shine_text_activity);
        
       ShineTextView shineText = (ShineTextView) findViewById(R.id.shine_txt);
       
       shineText.startShine();
    }
    
}
