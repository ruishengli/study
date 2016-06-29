package com.inlook.or.study.activity.recyclerview;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.inlook.or.study.R;

public class RecyclerViewMainActivity extends AppCompatActivity implements View.OnClickListener{

    private Button mListBtn;
    private Button mGridBtn;
    private Button mStaggeredgridBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_main_view);

        init();
    }

    private void init() {
        mListBtn = (Button) findViewById(R.id.list_btn);
        mGridBtn = (Button) findViewById(R.id.grid_btn);
        mStaggeredgridBtn = (Button) findViewById(R.id.staggeredgrid_btn);
        mListBtn.setOnClickListener(this);
        mGridBtn.setOnClickListener(this);
        mStaggeredgridBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.list_btn:
                openRecyclerViewActivity(0);
                break;
            case R.id.grid_btn:
                openRecyclerViewActivity(1);
                break;
            case R.id.staggeredgrid_btn:
                openRecyclerViewActivity(2);
                break;

        }

    }


    private void openRecyclerViewActivity(int openType) {
        Intent intent = new Intent(this,RecyclerViewActivity.class);
        intent.putExtra(RecyclerViewActivity.PARAM_RECYCLERVIEW_TYPE,openType);
        startActivity(intent);
    }
}
