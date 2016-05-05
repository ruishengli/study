package com.inlook.or.study.activity.ui;

import com.study.android.R;
import com.study.android.activity.BaseAppCompatActivity;

import android.content.Intent;
import android.view.View;

public class TranslucentSystemBarActivity extends BaseAppCompatActivity {


    public void imageTranslucent(View view) {
        Intent intent = new Intent(this, ImageTranslucentBarActivity.class);
        startActivity(intent);
    }

    public void colorTranslucent(View view) {
        Intent intent = new Intent(this, ColorTranslucentBarActivity.class);
        startActivity(intent);
    }

    @Override
    public int getLayoutResId() {
        return R.layout.activity_translucent_system_bar;
    }
}
