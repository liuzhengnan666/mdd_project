package com.example.administrator.llc_p.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.llc_p.R;
import com.example.administrator.llc_p.adapter.ListViewAdatper;
import com.scwang.smartrefresh.header.MaterialHeader;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.constant.SpinnerStyle;
import com.scwang.smartrefresh.layout.footer.BallPulseFooter;
import com.scwang.smartrefresh.layout.footer.ClassicsFooter;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.ArrayList;
import java.util.List;

public class DealFragment extends Fragment implements View.OnClickListener{

    private TextView buy;
    private TextView sale;
    private ListView listview;
    private RelativeLayout item_title;
    private SmartRefreshLayout refreshLayout;
    private List<String> strings = new ArrayList();
    private List<String> list = new ArrayList<>();
    private ListViewAdatper myAdapter;
    private ListViewAdatper adatper;
    int count = 0;

    public DealFragment() {
    }

    public static DealFragment newInstance() {
        DealFragment fragment = new DealFragment();
        return fragment;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View inflate = inflater.inflate(R.layout.fragment_deal, container, false);
        listview = inflate.findViewById(R.id.listview);
        item_title = inflate.findViewById(R.id.item_title);
        refreshLayout = inflate.findViewById(R.id.refreshLayout);
        //关闭下拉刷新
        refreshLayout.setEnableRefresh(false);
        initData();
        loasMore();
        return inflate;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //刷新监听
        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                //refreshlayout.finishRefresh(2000/*,false*/);//传入false表示刷新失败


            }
        });
    }

    //上啦加载
    public void  loasMore(){
        refreshLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(RefreshLayout refreshlayout) {
                if (count==5){
                    refreshlayout.finishLoadMoreWithNoMoreData();
                }else {
                    count++;
                    strings.add("我是刷新后得数据买入");
                    myAdapter.notifyDataSetChanged();
                    refreshlayout.finishLoadMore(2000/*,false*/);//传入false表示加载失败

                }
            }
        });
    }

    public void initData() {
        View list_head = View.inflate(getActivity(), R.layout.list_head, null);
        listview.addHeaderView(list_head);
        buy = list_head.findViewById(R.id.buy);
        sale = list_head.findViewById(R.id.sale);
        buy.setOnClickListener(this);
        sale.setOnClickListener(this);
        listview.addHeaderView(View.inflate(getActivity(), R.layout.stick_action, null));
        listview.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView absListView, int i) {

            }

            @Override
            public void onScroll(AbsListView absListView, int i, int i1, int i2) {
                if (i>=1){
                    item_title.setVisibility(View.VISIBLE);
                }else {
                    item_title.setVisibility(View.GONE);

                }
            }
        });
        initAdapterBuy();

    }
    public void initAdapterBuy(){
        strings.clear();
        count=0;
        refreshLayout.setNoMoreData(false);
        for (int i = 0; i < 30; i++) {
            strings.add("买入" + i);
        }
        myAdapter = new ListViewAdatper(getActivity(),strings);
        listview.setAdapter(myAdapter);
        myAdapter.notifyDataSetChanged();
    }
    public void initAdapterSale(){
        list.clear();
        count=0;
        refreshLayout.setNoMoreData(false);
        for (int i = 0; i < 30; i++) {
            list.add("卖出" + i);
        }
        adatper = new ListViewAdatper(getActivity(),list);
        listview.setAdapter(adatper);
        adatper.notifyDataSetChanged();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.buy:
                refreshSelection(Selection.BUY);
                break;
            case R.id.sale:
                refreshSelection(Selection.SALE);
                break;
        }
    }

    public void refreshSelection(Selection selection){
        switch (selection){
            case BUY:
                buy.setBackgroundResource(R.drawable.shape_button_semicircle_r);
                sale.setBackgroundResource(R.drawable.shape_button_semicircle);
                initAdapterBuy();
                break;
            case SALE:
                buy.setBackgroundResource(R.drawable.shape_button_semicircle_r_o);
                sale.setBackgroundResource(R.drawable.shape_button_semicircle_o);
                initAdapterSale();
                break;
        }
    }

    private enum Selection
    {
        BUY,SALE
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

    }
}
