package com.inlook.or.study.utils;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import android.text.TextUtils;

import com.inlook.or.study.activity.anim.SimpleBounceAnimatorActivity;
import com.inlook.or.study.activity.bitmap.BitmapActivity;
import com.inlook.or.study.activity.bitmap.PorterDuffBitmapActivity;
import com.inlook.or.study.activity.bitmap.RoundBitmapActivity;
import com.inlook.or.study.activity.customview.CircleBarActivity;
import com.inlook.or.study.activity.customview.DynamicPointTextActivity;
import com.inlook.or.study.activity.customview.ShineTextActivity;
import com.inlook.or.study.activity.customview.ValueIncrementActivity;
import com.inlook.or.study.activity.recyclerview.RecyclerViewMainActivity;
import com.inlook.or.study.activity.sensor.CompassActivity;
import com.inlook.or.study.activity.sensor.StepActivity;
import com.inlook.or.study.activity.supportlib.EntryAlwaysActivity;
import com.inlook.or.study.activity.supportlib.ExitUntilActivity;
import com.inlook.or.study.activity.supportlib.FloatingButtonActivity;
import com.inlook.or.study.activity.supportlib.SnackbarActivity;
import com.inlook.or.study.activity.ui.ToolbarActivity;
import com.inlook.or.study.activity.ui.TranslucentSystemBarActivity;
import com.inlook.or.study.model.UIListItem;


public class StudyUIMap {

    private static Map<String, List<UIListItem>> studyCategoryMap = new LinkedHashMap<String, List<UIListItem>>();

    public static List<UIListItem> get(String category) {

        if (TextUtils.isEmpty(category)) {
            Set<String> keys = studyCategoryMap.keySet();
            List<UIListItem> lists = new ArrayList<UIListItem>();
            UIListItem item = null;
            for (String key : keys) {
                item = new UIListItem();
                item.setTitle(key);
                lists.add(item);
            }
            return lists;

        } else {
            return studyCategoryMap.get(category);
        }
    }

    static {

        List<UIListItem> uis = new ArrayList<UIListItem>();
        UIListItem item = new UIListItem();
        item.setClazz(ToolbarActivity.class);
        item.setTitle("Toolbar");
        uis.add(item);
        
         item = new UIListItem();
        item.setClazz(TranslucentSystemBarActivity.class);
        item.setTitle("TranslucentSystemBar");
        uis.add(item);
        studyCategoryMap.put("ui", uis);

        uis = new ArrayList<>();
        item = new UIListItem();
        item.setClazz(CompassActivity.class);
        item.setTitle("Compass");
        uis.add(item);
      
        item = new UIListItem();
        item.setClazz(StepActivity.class);
        item.setTitle("Step");
        uis.add(item);
        studyCategoryMap.put("sensor", uis);
        
        
        uis = new ArrayList<>();
        item = new UIListItem();
        item.setClazz(SimpleBounceAnimatorActivity.class);
        item.setTitle("SimpleBounceAnimator");
        uis.add(item);
        studyCategoryMap.put("anim", uis);

        uis = new ArrayList<>();
        item = new UIListItem();
        item.setClazz(BitmapActivity.class);
        item.setTitle("BitmapActivity");
        uis.add(item);
        
        item = new UIListItem();
        item.setClazz(RoundBitmapActivity.class);
        item.setTitle("RoundBitmapActivity");
        uis.add(item);
        
        item = new UIListItem();
        item.setClazz(PorterDuffBitmapActivity.class);
        item.setTitle("PorterDuffBitmapActivity");
        uis.add(item);
        studyCategoryMap.put("bitmap", uis);

        uis = new ArrayList<>();
        
        item = new UIListItem();
        item.setClazz(ValueIncrementActivity.class);
        item.setTitle("ValueIncrementActivity");
        uis.add(item);
        
        item = new UIListItem();
        item.setClazz(ShineTextActivity.class);
        item.setTitle("ShineTextActivity");
        uis.add(item);
        
        item = new UIListItem();
        item.setClazz(DynamicPointTextActivity.class);
        item.setTitle("DynamicPointTextActivity");
        uis.add(item);
        
        item = new UIListItem();
        item.setClazz(CircleBarActivity.class);
        item.setTitle("CircleBar");
        uis.add(item);
        studyCategoryMap.put("customerView", uis);



        uis = new ArrayList<>();
        item = new UIListItem();
        item.setClazz(RecyclerViewMainActivity.class);
        item.setTitle("normalRecyclerview");
        uis.add(item);

        studyCategoryMap.put("recyclerview", uis);



        uis = new ArrayList<>();
        item = new UIListItem();
        item.setClazz(SnackbarActivity.class);
        item.setTitle("Snackbar");
        uis.add(item);

        item = new UIListItem();
        item.setClazz(FloatingButtonActivity.class);
        item.setTitle("FloatingActionButton");
        uis.add(item);

        item = new UIListItem();
        item.setClazz(EntryAlwaysActivity.class);
        item.setTitle("EntryAlwaysActivity");
        uis.add(item);

        item = new UIListItem();
        item.setClazz(ExitUntilActivity.class);
        item.setTitle("ExitUntilActivity");
        uis.add(item);

        studyCategoryMap.put("SupportLib", uis);
    }
}
