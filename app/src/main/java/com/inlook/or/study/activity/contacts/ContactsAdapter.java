package com.inlook.or.study.activity.contacts;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.SectionIndexer;
import android.widget.TextView;

import com.inlook.or.study.R;
import com.inlook.or.study.activity.contacts.model.Contacts;

import java.util.List;

/**
 * ContactsAdapter
 *
 * @author or
 * @since 2016/8/4.
 */
public class ContactsAdapter extends ArrayAdapter<Contacts> {

    private int resource;
    private SectionIndexer mIndex;

    public ContactsAdapter(Context context, int resource, List<Contacts> objects) {
        super(context, resource, objects);
        this.resource = resource;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LinearLayout linearLayout;
        Contacts contacts = getItem(position);

        if(convertView == null) {
            linearLayout = (LinearLayout) LayoutInflater.from(getContext()).inflate(this.resource,null);
        } else {
            linearLayout = (LinearLayout) convertView;
        }

        TextView name = (TextView) linearLayout.findViewById(R.id.name);
        LinearLayout sortKeyLayout = (LinearLayout) linearLayout.findViewById(R.id.sort_key_layout);
        TextView sortKey = (TextView) linearLayout.findViewById(R.id.sort_key);

       int section = mIndex.getSectionForPosition(position);
        if(position == mIndex.getPositionForSection(section)) {
            sortKey.setText(contacts.getSortKey());
            sortKeyLayout.setVisibility(View.VISIBLE);
        } else {
            sortKeyLayout.setVisibility(View.GONE);
        }
        name.setText(contacts.getName());
        return linearLayout;
    }

    public void setIndex(SectionIndexer index) {
        mIndex = index;
    }
}
