package com.mango.mobile.paper.modules.mainPager.adapter;


import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.mango.mobile.paper.modules.mainPager.ProjectIntroduceFragment;
import com.mango.mobile.paper.modules.mainPager.WarningFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2018/5/16 0016.
 */

public class ProjectPagerAdapter extends FragmentPagerAdapter {
    private String[] tableTitle = new String[]{"项目介绍", "风险公示"};
    private Context mContext;
    private List<Fragment> mFragmentTab;
    private ProjectIntroduceFragment fg1;
    private WarningFragment fg2;

    public ProjectPagerAdapter(FragmentManager fm, Context context) {
        super(fm);
        mContext = context;
        initFragmentTab();
    }

    @Override
    public Fragment getItem(int position) {
        return mFragmentTab.get(position);
    }

    @Override
    public int getCount() {
        return tableTitle.length;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return tableTitle[position];
    }

    private void initFragmentTab() {
        fg1 = new ProjectIntroduceFragment();
        fg2 = new WarningFragment();

        mFragmentTab = new ArrayList<>();
        mFragmentTab.add(fg1);
        mFragmentTab.add(fg2);
    }
}


