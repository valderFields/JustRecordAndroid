package com.mango.mobile.paper.modules.mainPager.adapter;

import android.content.Context;

import com.chad.library.adapter.base.BaseViewHolder;
import com.mango.mobile.paper.R;
import com.mango.mobile.paper.common.recyclerAdapter.MyBaseQuickAdapter;

import java.util.List;

/**
 * Created by Administrator on 2018/4/3 0003.
 */

public class MainPagerAdapter extends MyBaseQuickAdapter<String,BaseViewHolder>{


    public MainPagerAdapter(Context context, List<String> data) {
        super(R.layout.item_main, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, String item) {

    }
}
