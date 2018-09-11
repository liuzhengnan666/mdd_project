package com.example.administrator.llc_p.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.example.administrator.llc_p.R;
import com.example.administrator.llc_p.activity.DealRecordActivity;
import com.example.administrator.llc_p.activity.EarningsActivity;
import com.example.administrator.llc_p.activity.HangRecordActivity;
import com.example.administrator.llc_p.activity.RecruitActivity;
import com.example.administrator.llc_p.activity.SettingActivity;
import com.example.administrator.llc_p.activity.TransferActivity;

public class MyFragment extends Fragment implements View.OnClickListener{

    private RelativeLayout transfer,purchase_h,put_h,earnings,recruit,setting;

    public MyFragment() {
    }

    public static MyFragment newInstance() {
        MyFragment fragment = new MyFragment();
        return fragment;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View inflate = inflater.inflate(R.layout.fragment_my, container, false);
        transfer = inflate.findViewById(R.id.transfer);
        purchase_h = inflate.findViewById(R.id.purchase_h);
        put_h = inflate.findViewById(R.id.put_h);
        earnings = inflate.findViewById(R.id.earnings);
        recruit = inflate.findViewById(R.id.recruit);
        setting = inflate.findViewById(R.id.setting);
        transfer.setOnClickListener(this);
        purchase_h.setOnClickListener(this);
        put_h.setOnClickListener(this);
        earnings.setOnClickListener(this);
        recruit.setOnClickListener(this);
        setting.setOnClickListener(this);

        return inflate;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.transfer:
                Intent intent_transfer = new Intent(getActivity(), TransferActivity.class);
                startActivity(intent_transfer);
                break;
            case R.id.purchase_h:
                Intent intent_deal = new Intent(getActivity(), DealRecordActivity.class);
                startActivity(intent_deal);
                break;
            case R.id.put_h:
                Intent intent_hang = new Intent(getActivity(), HangRecordActivity.class);
                startActivity(intent_hang);
                break;
            case R.id.earnings:
                Intent intent_earnings = new Intent(getActivity(), EarningsActivity.class);
                startActivity(intent_earnings);
                break;
            case R.id.recruit:
                Intent intent_recruit = new Intent(getActivity(), RecruitActivity.class);
                startActivity(intent_recruit);
                break;
            case R.id.setting:
                Intent intent_setting = new Intent(getActivity(), SettingActivity.class);
                startActivity(intent_setting);
                break;
        }
    }
}
