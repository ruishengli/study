package com.inlook.or.study.activity.customview;

import com.study.android.R;
import com.study.android.view.CircleBar;

import android.app.Activity;
import android.os.Bundle;

public class CircleBarActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        
        setContentView(R.layout.circle_bar_view_activity);
        
        CircleBar cb = (CircleBar) findViewById(R.id.circlebar);
        cb.setProgress(80);
    }
}
