package com.mango.mobile.paper.modules.projectPager.adapter;

import android.content.Context;

import com.chad.library.adapter.base.BaseViewHolder;
import com.mango.mobile.paper.R;
import com.mango.mobile.paper.common.recyclerAdapter.MyBaseQuickAdapter;

import java.util.List;

/**
 * Created by Administrator on 2018/4/3 0003.
 */

public class SearchListAdapter extends MyBaseQuickAdapter<String,BaseViewHolder>{


    public SearchListAdapter(Context context, List<String> data) {
        super(R.layout.item_search, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, String item) {

    }
}
