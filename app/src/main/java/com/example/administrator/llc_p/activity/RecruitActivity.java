package com.example.administrator.llc_p.activity;

import android.os.Bundle;
import android.widget.RelativeLayout;

import com.example.administrator.llc_p.R;

public class RecruitActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recruit);
        RelativeLayout status = findViewById(R.id.status);
        setStatusBarHeight(status);
    }
}
