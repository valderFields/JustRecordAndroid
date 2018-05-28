package com.mango.mobile.paper.common.recyclerAdapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;


/**
 * Created by DN on 2018/03/12.
 */

public abstract class MyBaseQuickAdapter<T, K extends BaseViewHolder> extends BaseQuickAdapter<T,K> {
    public MyBaseQuickAdapter(int id, List<T> data) {
        super(id, data);
    }
}


