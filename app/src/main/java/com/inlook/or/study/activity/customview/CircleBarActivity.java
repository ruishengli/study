package com.inlook.or.study.activity.customview;


import android.app.Activity;
import android.os.Bundle;

import com.inlook.or.study.R;
import com.inlook.or.study.view.CircleBar;

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
