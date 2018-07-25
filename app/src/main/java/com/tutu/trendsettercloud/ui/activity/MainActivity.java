package com.tutu.trendsettercloud.ui.activity;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;

import com.tutu.trendsettercloud.R;
import com.tutu.trendsettercloud.adapter.BottomnavigationViewPagerAdapter;
import com.tutu.trendsettercloud.base.BaseActivity;
import com.tutu.trendsettercloud.ui.fragment.HomeFragment;
import com.tutu.trendsettercloud.ui.fragment.MillFragment;
import com.tutu.trendsettercloud.ui.fragment.DownloadFragment;
import com.tutu.trendsettercloud.ui.fragment.MineFragment;
import com.tutu.trendsettercloud.utils.BottomNavigationViewHelper;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends BaseActivity implements BottomNavigationView.OnNavigationItemSelectedListener,ViewPager.OnPageChangeListener,ViewPager.OnTouchListener{


    @BindView(R.id.viewpager)
    ViewPager viewpager;
    @BindView(R.id.bottomNavigationView)
    BottomNavigationView bottomNavigationView;

    HomeFragment homeFragment;
    MillFragment millFragment;
    DownloadFragment downloadFragment;
    MineFragment mineFragment;

    //    private Fragment fragment_now = null;
    BottomnavigationViewPagerAdapter pagerAdapter;
    List<Fragment> fragments;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initUI() {
        super.initUI();
        initView();
    }

    private void initView() {
        fragments = new ArrayList<>();
        homeFragment = HomeFragment.newInstance();
        millFragment = MillFragment.newInstance();
        downloadFragment = DownloadFragment.newInstance();
        mineFragment = MineFragment.newInstance();
        if(!fragments.contains(homeFragment)){
            fragments.add(homeFragment);
        }
        if(!fragments.contains(millFragment)){
            fragments.add(millFragment);
        }
        if(!fragments.contains(downloadFragment)){
            fragments.add(downloadFragment);
        }
        if(!fragments.contains(mineFragment)){
            fragments.add(mineFragment);
        }

        bottomNavigationView.setOnNavigationItemSelectedListener(this);//设置 NavigationItemSelected 事件监听
        BottomNavigationViewHelper.disableShiftMode(bottomNavigationView);//改变 BottomNavigationView 默认的效果
        //选中第一个item,对应第一个fragment
        bottomNavigationView.setSelectedItemId(R.id.item_1);

        pagerAdapter = new BottomnavigationViewPagerAdapter(getSupportFragmentManager(),fragments);
        viewpager.setAdapter(pagerAdapter);
        viewpager.addOnPageChangeListener(this);

        // 如果想禁止滑动，可以把下面的代码取消注释
//        viewPager.setOnTouchListener(this);
    }

    //NavigationItemSelected 事件监听
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
//        changePageFragment(item.getItemId());
        switch (item.getItemId()){
            case  R.id.item_1:
                viewpager.setCurrentItem(0);
                break;
            case  R.id.item_2:
                viewpager.setCurrentItem(1);
                break;
            case  R.id.item_3:
                viewpager.setCurrentItem(2);
                break;
            case  R.id.item_4:
                viewpager.setCurrentItem(3);
                break;
        }
        return true;
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        bottomNavigationView.getMenu().getItem(position).setChecked(true);
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        return true;
    }
}
