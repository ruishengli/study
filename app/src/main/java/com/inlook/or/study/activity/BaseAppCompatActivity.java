package com.inlook.or.study.activity;


import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;

public abstract class BaseAppCompatActivity extends AppCompatActivity {
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(getLayoutResId());
    }
    
    public abstract int getLayoutResId();

}
