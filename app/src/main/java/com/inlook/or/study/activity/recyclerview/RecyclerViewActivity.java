package com.inlook.or.study.activity.recyclerview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.inlook.or.study.R;

public class RecyclerViewActivity extends AppCompatActivity {

    public static final String PARAM_RECYCLERVIEW_TYPE = "RecyclerViewActivity.type";
    private int type = 0;
    private RecyclerView mRecyclerView;
    private RecyclerViewAdapter mAdapter;


    private String[] title = {"Blog : http://blog.csdn.net/Leejizhou.",
            "A good laugh and a long sleep are the best cures in the doctor's book.",
            "all or nothing, now or never ",
            "Be nice to people on the way up, because you'll need them on your way down.",
            "Be confident with yourself and stop worrying what other people think. Do what's best for your future happiness!",
            "Blessed is he whose fame does not outshine his truth.",
            "Create good memories today, so that you can have a good past"
    };

    /**
     * 图片资源版权归属于Smartisan.com
     */
    private int[] pic = {R.drawable.aa1, R.drawable.aa0, R.drawable.aa2, R.drawable.aa3, R.drawable.aa4, R.drawable.aa5, R.drawable.aa6};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_view);

        type =  getIntent().getIntExtra(PARAM_RECYCLERVIEW_TYPE,0);
        init();
    }

    private void init() {
        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerview);

        RecyclerView.LayoutManager layoutManager= getLayoutManager(type);
        if(layoutManager != null)
             mRecyclerView.setLayoutManager(layoutManager);

        mAdapter = new RecyclerViewAdapter(this,title,pic);
        mRecyclerView.setAdapter(mAdapter);
    }


    private RecyclerView.LayoutManager getLayoutManager(int openType) {
        if(openType == 0) {
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
            linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
            return linearLayoutManager;
        } else if(openType == 1) {
            GridLayoutManager gridLayoutManager = new GridLayoutManager(this,2);
            return gridLayoutManager;
        } else {
            StaggeredGridLayoutManager staggeredGridLayoutManager = new StaggeredGridLayoutManager(2, OrientationHelper.VERTICAL);
            return staggeredGridLayoutManager;
        }

    }


}
