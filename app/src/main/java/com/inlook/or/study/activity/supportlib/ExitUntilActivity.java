package com.inlook.or.study.activity.supportlib;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import com.inlook.or.study.R;
import com.inlook.or.study.activity.BaseAppCompatActivity;

public class ExitUntilActivity extends BaseAppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        init();
    }

    @Override
    public int getLayoutResId() {
        return R.layout.activity_exit_until;
    }

    private void init() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("ExitUntilActivity");
    }
}
