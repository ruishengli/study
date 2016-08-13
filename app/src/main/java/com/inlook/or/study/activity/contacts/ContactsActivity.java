package com.inlook.or.study.activity.contacts;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AlphabetIndexer;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.inlook.or.study.R;
import com.inlook.or.study.activity.BaseAppCompatActivity;
import com.inlook.or.study.activity.contacts.model.Contacts;
import com.inlook.or.study.utils.Logs;

import java.util.ArrayList;
import java.util.List;

public class ContactsActivity extends BaseAppCompatActivity {

    private final String TAG = ContactsActivity.class.getSimpleName();
    /**
     * 分组的布局
     */
    private LinearLayout titleLayout;

    /**
     * 分组上显示的字母
     */
    private TextView title;

    /**
     * 联系人ListView
     */
    private ListView contactsListView;

    /**
     * 联系人列表适配器
     */
    private ContactsAdapter mAdapter;
    private AlphabetIndexer mIndex;
    private String alphabet = "#ABCDEFGHIJKLMNOPQRSTUVWXYZ";

    private List<Contacts> mContacts = new ArrayList<>();
    /**
     * 上次第一个可见元素，用于滚动时记录标识。
     */
    private int lastFirstVisibleItem = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        initView();
        setUpContactsListView();

    }

    @Override
    public int getLayoutResId() {
        return R.layout.activity_contacts;
    }


    private void initView() {
        mAdapter = new ContactsAdapter(this, R.layout.contacts_list_item, mContacts);
        titleLayout = (LinearLayout) findViewById(R.id.title_layout);
        title = (TextView) findViewById(R.id.title);
        contactsListView = (ListView) findViewById(R.id.contacts_list_view);
    }


    private void getContacts() {

        Uri uri = ContactsContract.CommonDataKinds.Phone.CONTENT_URI;

            Cursor cursor = getContentResolver().query(uri,
                    new String[] { "display_name", "sort_key" }, null, null, "sort_key");
            if (cursor.moveToFirst()) {
                do {
                    String name = cursor.getString(0);
                    String sortKey = getSortKey(cursor.getString(1));
                    Contacts contact = new Contacts();
                    contact.setName(name);
                    contact.setSortKey(sortKey);
                    mContacts.add(contact);
                } while (cursor.moveToNext());
            }
            startManagingCursor(cursor);
            mIndex = new AlphabetIndexer(cursor,1,alphabet);
    }

    private String getSortKey(String sortKeyString) {
        String key = sortKeyString.substring(0, 1).toUpperCase();
        if (key.matches("[A-Z]")) {
            return key;
        }
        return "#";
    }

    private void setUpContactsListView() {
        getContacts();

        if(mContacts.size() >0) {
            mAdapter.setIndex(mIndex);
            contactsListView.setAdapter(mAdapter);

            contactsListView.setOnScrollListener(new AbsListView.OnScrollListener() {
                @Override
                public void onScrollStateChanged(AbsListView view, int scrollState) {

                }

                @Override
                public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
                    int section = mIndex.getSectionForPosition(firstVisibleItem);
                    int nextSecPosition = mIndex.getPositionForSection(section + 1);

                    Logs.d(TAG,"section " + section + " nextSecPosition " + nextSecPosition + " firstVisibleItem " + firstVisibleItem);
                    if (firstVisibleItem != lastFirstVisibleItem) {
                        ViewGroup.MarginLayoutParams params = (ViewGroup.MarginLayoutParams) titleLayout.getLayoutParams();
                        params.topMargin = 0;
                        titleLayout.setLayoutParams(params);
                        title.setText(String.valueOf(alphabet.charAt(section)));
                    }

                    if (nextSecPosition == firstVisibleItem + 1) {
                        View childView = view.getChildAt(0);
                        if (childView != null) {
                            int titleHeight = titleLayout.getHeight();
                            int bottom = childView.getBottom();
                            ViewGroup.MarginLayoutParams params = (ViewGroup.MarginLayoutParams) titleLayout
                                    .getLayoutParams();
                            if (bottom < titleHeight) {
                                float pushedDistance = bottom - titleHeight;
                                params.topMargin = (int) pushedDistance;
                                titleLayout.setLayoutParams(params);
                            } else {
                                if (params.topMargin != 0) {
                                    params.topMargin = 0;
                                    titleLayout.setLayoutParams(params);
                                }
                            }
                        }
                    }
                    lastFirstVisibleItem = firstVisibleItem;
                }
            });
        }
    }
}
