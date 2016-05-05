package com.inlook.or.study.activity;

import java.util.List;

import com.study.android.R;
import com.study.android.R.id;
import com.study.android.R.layout;
import com.study.android.adapter.CommonAdapter;
import com.study.android.adapter.ViewHolder;
import com.study.android.model.UIListItem;
import com.study.android.service.FloatWindowService;
import com.study.android.service.LockScreenService;
import com.study.android.utils.StudyUIMap;

import android.app.ListActivity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

public class MainActivity extends ListActivity {

    private static final String EXTRA_CATEGORY = "category";
    private Adapter mAdapter ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        init();
      //  startFloatWindow();
        
        startLockScreenService();
    }
    
    
    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
       UIListItem item = mAdapter.getItem(position);
       Intent intent = null;
       if(item.getClazz() != null) {
           intent  = new Intent(this, item.getClazz());
       } else {
           intent  = new Intent(this, MainActivity.class);
           intent.putExtra(EXTRA_CATEGORY, item.getTitle());
           intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
       }
       startActivity(intent);
    }

    private void startLockScreenService(){
        Intent service = new Intent(this,LockScreenService.class);
        startService(service);
    }
    
    private void startFloatWindow() {
        Intent service = new Intent(this,FloatWindowService.class);
        startService(service);
        this.finish();
    }
    
    private void init() {
        
       String category = getIntent().getStringExtra(EXTRA_CATEGORY);
       mAdapter = new Adapter(this,R.layout.main_list_item,  StudyUIMap.get(category) );
        setListAdapter(mAdapter);
    }
    
    
    private class Adapter extends CommonAdapter<UIListItem> {

        public Adapter(Context context, int layoutId, List<UIListItem> datas) {
            super(context, layoutId, datas);
        }

        @Override
        public void convert(ViewHolder viewHolder, UIListItem item) {
            viewHolder.setText(R.id.txt,item.getTitle());
        }
    }
    
}