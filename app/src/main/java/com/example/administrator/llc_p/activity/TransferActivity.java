package com.example.administrator.llc_p.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;

import com.example.administrator.llc_p.R;

public class TransferActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transfer);
        RelativeLayout status_Transfer = findViewById(R.id.status);
        setStatusBarHeight(status_Transfer);
    }
}
