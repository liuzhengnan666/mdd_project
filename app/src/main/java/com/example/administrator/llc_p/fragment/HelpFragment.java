package com.example.administrator.llc_p.fragment;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.llc_p.R;

import org.w3c.dom.Text;

public class HelpFragment extends Fragment implements View.OnClickListener {

    private RelativeLayout about_freeze;
    private RelativeLayout about_mill;
    private RelativeLayout about_ncoordinate;
    private RelativeLayout about_production;
    private TextView explain_freeze;
    private TextView explain_mill;
    private TextView explain_ncoordinate;
    private TextView explain_production;
    private TextView connection_us;
    private Boolean isFreeze = false;
    private Boolean isMill = false;
    private Boolean isNcoordinate = false;
    private Boolean isProduction =false;
    private TextView tab_freeze;
    private TextView tab_mill;
    private TextView tab_production;
    private TextView tab_ncoordinate;
    private Dialog dialog;


    public HelpFragment() {
    }

    public static HelpFragment newInstance() {
        HelpFragment fragment = new HelpFragment();
        return fragment;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View help_view = inflater.inflate(R.layout.fragment_help, container, false);
        about_freeze = help_view.findViewById(R.id.about_freeze);
        about_mill = help_view.findViewById(R.id.about_mill);
        about_ncoordinate = help_view.findViewById(R.id.about_ncoordinate);
        about_production = help_view.findViewById(R.id.about_production);
        explain_freeze = help_view.findViewById(R.id.explain_freeze);
        explain_mill = help_view.findViewById(R.id.explain_mill);
        explain_ncoordinate = help_view.findViewById(R.id.explain_ncoordinate);
        explain_production = help_view.findViewById(R.id.explain_production);
        connection_us = help_view.findViewById(R.id.connection_us);
        tab_freeze = help_view.findViewById(R.id.tab_freeze);
        tab_mill = help_view.findViewById(R.id.tab_mill);
        tab_ncoordinate = help_view.findViewById(R.id.tab_ncoordinate);
        tab_production = help_view.findViewById(R.id.tab_production);
        about_freeze.setOnClickListener(this);
        about_mill.setOnClickListener(this);
        about_ncoordinate.setOnClickListener(this);
        about_production.setOnClickListener(this);
        connection_us.setOnClickListener(this);

        return help_view;
    }



    public  void  showDialog(){
        ButtonDialogFragment buttonDialogFragment = new ButtonDialogFragment();
        buttonDialogFragment.show("官方QQ：", "2666632957", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
            }
        }, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
            }
        }, getFragmentManager());
    }

    public  void showDialog2(){
        dialog = new Dialog(getActivity());
        LayoutInflater inflater = LayoutInflater.from(getActivity());
        View viewDialog = inflater.inflate(R.layout.dialog_qq, null);
        dialog.setContentView(viewDialog);
        dialog.show();
        TextView ok = viewDialog.findViewById(R.id.ok);
        ok.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.about_freeze:
                if (isFreeze){
                    explain_freeze.setVisibility(View.GONE);
                    tab_freeze.setBackgroundResource(R.mipmap.up);
                    isFreeze = !isFreeze;
                }else {
                    explain_freeze.setVisibility(View.VISIBLE);
                    tab_freeze.setBackgroundResource(R.mipmap.down);

                    isFreeze = !isFreeze;
                }
                break;
            case R.id.about_mill:
                if (isMill){
                    explain_mill.setVisibility(View.GONE);
                    tab_mill.setBackgroundResource(R.mipmap.up);
                    isMill = !isMill;
                }else {
                    explain_mill.setVisibility(View.VISIBLE);
                    tab_mill.setBackgroundResource(R.mipmap.down);
                    isMill = !isMill;
                }
                break;
            case R.id.about_ncoordinate:
                if (isNcoordinate){
                    explain_ncoordinate.setVisibility(View.GONE);
                    tab_ncoordinate.setBackgroundResource(R.mipmap.up);
                    isNcoordinate = !isNcoordinate;
                }else {
                    explain_ncoordinate.setVisibility(View.VISIBLE);
                    tab_ncoordinate.setBackgroundResource(R.mipmap.down);
                    isNcoordinate = !isNcoordinate;
                }
                break;
            case R.id.about_production:
                if (isProduction){
                    explain_production.setVisibility(View.GONE);
                    tab_production.setBackgroundResource(R.mipmap.up);
                    isProduction = !isProduction;
                }else {
                    explain_production.setVisibility(View.VISIBLE);
                    tab_production.setBackgroundResource(R.mipmap.down);
                    isProduction = !isProduction;
                }
                break;
            case R.id.connection_us:
                   // showDialog();
                showDialog2();
                break;
            case R.id.ok:
                dialog.dismiss();
                break;
        }
    }
}
