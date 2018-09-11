package com.example.administrator.llc_p.activity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.MenuItem;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.administrator.llc_p.R;
import com.example.administrator.llc_p.fragment.DealFragment;
import com.example.administrator.llc_p.fragment.HelpFragment;
import com.example.administrator.llc_p.fragment.MillFragment;
import com.example.administrator.llc_p.fragment.MoreFragment;
import com.example.administrator.llc_p.fragment.MyFragment;
import com.example.administrator.llc_p.fragment.ShopFragment;
import com.example.administrator.llc_p.utils.BottomNavigationViewHelper;


public class MainActivity extends BaseActivity implements BottomNavigationView.OnNavigationItemSelectedListener{


    BottomNavigationView bottomNavigationView;

    DealFragment dealFragment;
    HelpFragment helpFragment;
    MillFragment millFragment;
    MoreFragment myFragment;
    ShopFragment shopFragment;
    private Fragment fragment_now = null;
    private TextView title_text;
    private RelativeLayout status_main;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bottomNavigationView = findViewById(R.id.bottomNavigationView);
        title_text = findViewById(R.id.title_text);
        status_main = findViewById(R.id.status_main);
        setStatusBarHeight(status_main);
        inint();
    }

    @SuppressLint("NewApi")
    private void inint() {
        bottomNavigationView.setOnNavigationItemSelectedListener(this);//设置 NavigationItemSelected 事件监听
        BottomNavigationViewHelper.disableShiftMode(bottomNavigationView);//改变 BottomNavigationView 默认的效果
        //选中第一个item,对应第一个fragment
        bottomNavigationView.setSelectedItemId(R.id.item_deal);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
    //NavigationItemSelected 事件监听
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        changePageFragment(item.getItemId());
        return true;
    }
    /**
     * 当点击导航栏时改变fragment
     *
     */
    public void changePageFragment(int id) {
        switch (id) {
            case R.id.item_shop:
                if (shopFragment == null) {
                    shopFragment = shopFragment.newInstance();
                }
                switchFragment(fragment_now, shopFragment);
                title_text.setText("算力商城");
                break;
            case R.id.item_mill:
                if (millFragment == null) {
                    millFragment = millFragment.newInstance();
                }
                switchFragment(fragment_now, millFragment);
                title_text.setText("我的矿机");
                break;
            case R.id.item_deal:
                if (dealFragment == null) { //减少new fragmnet,避免不必要的内存消耗
                    dealFragment = DealFragment.newInstance();
                }
                switchFragment(fragment_now, dealFragment);
                title_text.setText("交易市场");
                break;
            case R.id.item_help:
                if (helpFragment == null) {
                    helpFragment = helpFragment.newInstance();
                }
                switchFragment(fragment_now, helpFragment);
                title_text.setText("帮助中心");

                break;
            case R.id.item_my:
                if (myFragment == null) {
                    myFragment = myFragment.newInstance();
                }
                switchFragment(fragment_now, myFragment);
                title_text.setText("个人中心");

                break;

        }
    }
    /**
     * 隐藏显示fragment
     *
     * @param from 需要隐藏的fragment
     * @param to   需要显示的fragment
     */
    public void switchFragment(Fragment from, Fragment to) {
        if (to == null)
            return;
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        if (!to.isAdded()) {
            if (from == null) {
                transaction.add(R.id.fl_fragment, to).show(to).commit();
            } else {
                // 隐藏当前的fragment，add下一个fragment到Activity中
                transaction.hide(from).add(R.id.fl_fragment, to).commitAllowingStateLoss();
            }
        } else {
            // 隐藏当前的fragment，显示下一个
            transaction.hide(from).show(to).commit();
        }
        fragment_now = to;
    }
}