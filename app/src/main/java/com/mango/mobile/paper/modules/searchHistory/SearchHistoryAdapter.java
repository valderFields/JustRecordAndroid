package com.mango.mobile.paper.modules.searchHistory;


import com.chad.library.adapter.base.BaseViewHolder;
import com.mango.mobile.paper.R;
import com.mango.mobile.paper.common.recyclerAdapter.MyBaseQuickAdapter;

import java.util.List;

/**
 * Created by DN on 2018/03/13.
 */

public class SearchHistoryAdapter extends MyBaseQuickAdapter<String, BaseViewHolder> {
    public SearchHistoryAdapter(List<String> data) {
        super(R.layout.history_item, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, String item) {
        helper.setText(R.id.tv_history, item);
    }


}





