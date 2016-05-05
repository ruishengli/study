package com.inlook.or.study.activity.customview;


import android.app.Activity;
import android.os.Bundle;

import com.inlook.or.study.R;
import com.inlook.or.study.view.ShineTextView;

public class ShineTextActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.shine_text_activity);
        
       ShineTextView shineText = (ShineTextView) findViewById(R.id.shine_txt);
       
       shineText.startShine();
    }
    
}
