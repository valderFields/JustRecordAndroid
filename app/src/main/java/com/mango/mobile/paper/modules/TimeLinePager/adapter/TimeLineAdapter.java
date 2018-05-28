package com.mango.mobile.paper.modules.TimeLinePager.adapter;

import android.content.Context;
import android.widget.Switch;

import com.chad.library.adapter.base.BaseViewHolder;
import com.mango.mobile.paper.R;
import com.mango.mobile.paper.common.recyclerAdapter.MyBaseQuickAdapter;

import java.util.List;

/**
 * Created by Administrator on 2018/5/23 0023.
 */

public class TimeLineAdapter extends MyBaseQuickAdapter<String,BaseViewHolder> {
    private Context context;
    public TimeLineAdapter(Context context, List<String> data) {
        super(R.layout.item_line, data);
        this.context = context;
    }

    @Override
    protected void convert(BaseViewHolder helper, String item) {
        switch (helper.getPosition()){
            case 0:
                helper.setText(R.id.tv_item_title,"今天去北海玩");
                helper.setText(R.id.tv_item_content,"好开心，看到了鸭子，倒映着美丽的白塔，周围是绿树红墙,周围是绿树红墙");
                helper.setGone(R.id.iv_item_image,false);
                break;
            case 1:
                helper.setText(R.id.tv_item_title,"好无聊~~");
                helper.setText(R.id.tv_item_content,"干点什么好呢");
                break;
            case 2:
                helper.setText(R.id.tv_item_title,"一路向西去大理");
                helper.setText(R.id.tv_item_content,"玉龙雪山，苍山洱海，香格里拉，大理，双廊");
                helper.setGone(R.id.iv_item_image,false);
                break;
            case 3:
                break;
        }
    }
}
