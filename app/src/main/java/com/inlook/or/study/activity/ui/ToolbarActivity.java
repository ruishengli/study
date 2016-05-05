package com.inlook.or.study.activity.ui;

import  com.inlook.or.study.R;
import  com.inlook.or.study.activity.BaseAppCompatActivity;

import android.support.v7.widget.Toolbar;
import android.os.Bundle;

public class ToolbarActivity extends BaseAppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.inflateMenu(R.menu.base_toolbar_menu);
    }

    @Override
    public int getLayoutResId() {
        return R.layout.activity_toolbar;
    }

}
