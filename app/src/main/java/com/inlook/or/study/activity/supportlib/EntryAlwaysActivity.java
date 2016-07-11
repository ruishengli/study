package com.inlook.or.study.activity.supportlib;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import com.inlook.or.study.R;
import com.inlook.or.study.activity.BaseAppCompatActivity;

public class EntryAlwaysActivity extends BaseAppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_entry_always);
        init();
    }

    @Override
    public int getLayoutResId() {
        return R.layout.activity_entry_always;
    }

    private void init() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("EntryAlwaysActivity");
    }
}
