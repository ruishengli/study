package com.inlook.or.study.activity.supportlib;

import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.content.ContextCompat;
import android.view.View;

import com.inlook.or.study.R;
import com.inlook.or.study.activity.BaseAppCompatActivity;

public class SnackbarActivity extends BaseAppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public int getLayoutResId() {
        return R.layout.activity_snackbar;
    }

    public void onNormal(View view) {
        Snackbar.make(view,"normal",Snackbar.LENGTH_SHORT).show();
    }

    public void onAction(View view) {

        final Snackbar snackbar = Snackbar.make(view,"Action SnackBar" ,Snackbar.LENGTH_LONG);
        snackbar.setActionTextColor(ContextCompat.getColor(this,R.color.color_31c27c)).setAction("I Known", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                snackbar.dismiss();
            }
        });

        snackbar.show();
    }


    public void onCustomer(View view) {
        final Snackbar snackbar = Snackbar.make(view,"Customer SnackBar" ,Snackbar.LENGTH_LONG);
        View snackbarView = snackbar.getView();
        snackbarView.setBackgroundColor(ContextCompat.getColor(this,R.color.color_31c27c));
        snackbarView.setAlpha(0.8f);
        snackbar.show();
    }
}
