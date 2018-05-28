package com.mango.mobile.paper.modules.mainPager.message;

import android.content.Context;

import com.chad.library.adapter.base.BaseViewHolder;
import com.mango.mobile.paper.R;
import com.mango.mobile.paper.common.recyclerAdapter.MyBaseQuickAdapter;

import java.util.List;

/**
 * Created by Administrator on 2018/5/11 0011.
 */

public class MessageAdapter extends MyBaseQuickAdapter<String, BaseViewHolder> {

    private Context context;

    public MessageAdapter(Context context, List<String> data) {
        super(R.layout.item_message, data);
        this.context = context;
    }

    @Override
    protected void convert(BaseViewHolder helper, String item) {
        helper.setText(R.id.tv_message, item);
    }
}
