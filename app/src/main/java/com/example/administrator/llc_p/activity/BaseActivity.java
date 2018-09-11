package com.example.administrator.llc_p.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.example.administrator.llc_p.R;
import com.example.administrator.llc_p.adapter.MyBaseListViewAdapter;
import com.example.administrator.llc_p.adapter.ViewHolder;
import com.example.administrator.llc_p.bean.RegisterBean;
import com.example.administrator.llc_p.utils.StatusBarUtil;

import java.util.ArrayList;
import java.util.List;

public class BaseActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base);
        StatusBarUtil.transparencyBar(this);
    }

    @Override
    public void onClick(View view) {

    }

    public void setStatusBarHeight(RelativeLayout relativeLayout){
        View view = getLayoutInflater().inflate(R.layout.stauts, null);
        int statusBarHeight = getStatusBarHeight();
        ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, statusBarHeight);
        relativeLayout.addView(view,params);
    }

    public int getStatusBarHeight() {
        int result = 0;
        int resourceId = getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            result = getResources().getDimensionPixelSize(resourceId);
        }
        return result;
    }
}
