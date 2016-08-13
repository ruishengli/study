package com.inlook.or.study.activity.blurredview;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.inlook.or.study.R;

/**
 * WeatherAdapter
 *
 * @author or
 * @since 2016/8/11.
 */
public class WeatherAdapter extends RecyclerView.Adapter {

    private final int ITEM_COUNT = 10;
    private final int TYPE_HEADER = 0;
    private final int TYPE_ITEM = 1;

    private Context mContext;

    public WeatherAdapter(Context context) {
        mContext = context;
    }
    @Override
    public int getItemViewType(int position) {
        if(position == 0) {
            return TYPE_HEADER;
        }
        return TYPE_ITEM;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if(viewType == TYPE_HEADER) {
            return new HeaderViewHolder(LayoutInflater.from(mContext).inflate(R.layout.weather_view_header,parent,false));
        }
        return new ItemViewHolder(LayoutInflater.from(mContext).inflate(R.layout.weather_view_item,parent,false));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

    }

    public class HeaderViewHolder extends RecyclerView.ViewHolder {

        public HeaderViewHolder(View itemView) {
            super(itemView);
        }
    }

    public class ItemViewHolder extends RecyclerView.ViewHolder {
        public ItemViewHolder(View itemView) {
            super(itemView);
        }
    }


    @Override
    public int getItemCount() {
        return ITEM_COUNT;
    }
}
