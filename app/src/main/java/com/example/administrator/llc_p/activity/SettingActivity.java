package com.example.administrator.llc_p.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;

import com.example.administrator.llc_p.R;

public class SettingActivity extends BaseActivity {

    private RelativeLayout authentication;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        RelativeLayout status = findViewById(R.id.status);
        setStatusBarHeight(status);
        authentication = findViewById(R.id.authentication);
        authentication.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        super.onClick(view);
        switch (view.getId()){
            case R.id.authentication:
                startActivity(new Intent(this,AuthenticationActivity.class));
                break;
        }
    }
}
