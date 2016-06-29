package com.inlook.or.study.activity.recyclerview;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.inlook.or.study.R;

/**
 * desc RecyclerViewAdapter
 *
 * @author: or
 * @since: on 2016/6/29.
 */
public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.NormalViewHolder> {

    private LayoutInflater mLayoutInflater;
    private Context mContext;
    private String [] mTitle;
    private int [] mPic;

    public RecyclerViewAdapter(Context context,String[]title,int[] pic){
        mContext=context;
        mTitle=title;
        mPic=pic;
        mLayoutInflater=LayoutInflater.from(context);
    }

    @Override
    public NormalViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return  new NormalViewHolder (mLayoutInflater.inflate(R.layout.recylerview_item_view,parent,false));
    }

    @Override
    public void onBindViewHolder(NormalViewHolder holder, int position) {
        holder.mTextView.setText(mTitle[position]);
        holder.mImageView.setBackgroundResource(mPic[position]);
    }

    @Override
    public int getItemCount() {
        return mTitle == null ? 0 : mTitle.length;
    }


    public class NormalViewHolder extends RecyclerView.ViewHolder {

        CardView mCardView;
         ImageView mImageView;
         TextView mTextView;

        public NormalViewHolder(View itemView) {
            super(itemView);

            mCardView = (CardView) itemView.findViewById(R.id.cv_item);
            mImageView = (ImageView) itemView.findViewById(R.id.iv_pic);
            mTextView = (TextView) itemView.findViewById(R.id.tv_text);
        }
    }

}
