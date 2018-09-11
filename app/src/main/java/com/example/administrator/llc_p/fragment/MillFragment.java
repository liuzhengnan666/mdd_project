package com.example.administrator.llc_p.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.administrator.llc_p.R;

public class MillFragment extends Fragment {

    public MillFragment() {
    }

    public static MillFragment newInstance() {
        MillFragment fragment = new MillFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_mill, container, false);
    }
}
