package com.example.administrator.llc_p.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;


import com.example.administrator.llc_p.R;

public class RegisterActivity extends BaseActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        RelativeLayout status = findViewById(R.id.status);
        setStatusBarHeight(status);

    }


}
