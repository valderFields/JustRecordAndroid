package com.mango.mobile.paper.modules.myinfo.adapter;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;

import com.chad.library.adapter.base.BaseViewHolder;
import com.mango.utils.Helper;
import com.mango.mobile.paper.R;
import com.mango.mobile.paper.common.recyclerAdapter.MyBaseQuickAdapter;

import java.util.List;

/**
 * Created by Administrator on 2018/5/10 0010.
 */

public class CardAdapter extends MyBaseQuickAdapter<String, BaseViewHolder> {

    private Context context;

    public CardAdapter(Context context,List<String> data) {
        super(R.layout.item_card, data);
        this.context = context;
    }

    @Override
    protected void convert(BaseViewHolder helper, String item) {
        GradientDrawable gd;
        int bankIcon = 0;
        switch (item) {
            case "浦发银行":
                gd = new GradientDrawable(GradientDrawable.Orientation.LEFT_RIGHT, new int[]{Color.parseColor("#054aa2"), Color.parseColor("#02439b")});
                bankIcon = R.drawable.bank_pufa;
                break;
            case "兴业银行":
                gd = new GradientDrawable(GradientDrawable.Orientation.LEFT_RIGHT, new int[]{Color.parseColor("#054aa2"), Color.parseColor("#02439b")});
                bankIcon = R.drawable.bank_xingye;
                break;
            case "建设银行":
                gd = new GradientDrawable(GradientDrawable.Orientation.LEFT_RIGHT, new int[]{Color.parseColor("#054aa2"), Color.parseColor("#02439b")});
                bankIcon = R.drawable.bank_jianshe;
                break;
            case "交通银行":
                gd = new GradientDrawable(GradientDrawable.Orientation.LEFT_RIGHT, new int[]{Color.parseColor("#054aa2"), Color.parseColor("#02439b")});
                bankIcon = R.drawable.bank_jiaotong;
                break;
            case "招商银行":
                gd = new GradientDrawable(GradientDrawable.Orientation.LEFT_RIGHT, new int[]{Color.parseColor("#fa5a5a"), Color.parseColor("#fa463d")});
                bankIcon = R.drawable.bank_zhaoshang;
                break;
            case "工商银行":
                gd = new GradientDrawable(GradientDrawable.Orientation.LEFT_RIGHT, new int[]{Color.parseColor("#fa5a5a"), Color.parseColor("#fa463d")});
                bankIcon = R.drawable.bank_gongshang;
                break;
            case "北京银行":
                gd = new GradientDrawable(GradientDrawable.Orientation.LEFT_RIGHT, new int[]{Color.parseColor("#fa5a5a"), Color.parseColor("#fa463d")});
                break;
            case "中信银行":
                gd = new GradientDrawable(GradientDrawable.Orientation.LEFT_RIGHT, new int[]{Color.parseColor("#fa5a5a"), Color.parseColor("#fa463d")});
                break;
            case "华夏银行":
                gd = new GradientDrawable(GradientDrawable.Orientation.LEFT_RIGHT, new int[]{Color.parseColor("#fa5a5a"), Color.parseColor("#fa463d")});
                bankIcon = R.drawable.bank_huaxia;
                break;
            case "邮政银行":
                gd = new GradientDrawable(GradientDrawable.Orientation.LEFT_RIGHT, new int[]{Color.parseColor("#04ae4b"), Color.parseColor("#0b9444")});
                break;
            case "民生银行":
                gd = new GradientDrawable(GradientDrawable.Orientation.LEFT_RIGHT, new int[]{Color.parseColor("#0c8e84"), Color.parseColor("#0c8e84")});
                bankIcon = R.drawable.bank_mingsheng;
                break;
            case "农业银行":
                gd = new GradientDrawable(GradientDrawable.Orientation.LEFT_RIGHT, new int[]{Color.parseColor("#02c4a9"), Color.parseColor("#02b29a")});
                bankIcon = R.drawable.bank_nongye;
                break;
            default:
                gd = new GradientDrawable(GradientDrawable.Orientation.LEFT_RIGHT, new int[]{Color.parseColor("#02b29a"), Color.parseColor("#02c4a9")});
                break;

        }

        gd.setCornerRadii(new float[] { Helper.dip2px(8),Helper.dip2px(8),Helper.dip2px(8),Helper.dip2px(8),Helper.dip2px(8),Helper.dip2px(8),Helper.dip2px(8),Helper.dip2px(8)});
        helper.setText(R.id.tv_card_name,item);
        helper.getView(R.id.iv_card_bg).setBackground(gd);

        if (bankIcon != 0){
            helper.setBackgroundRes(R.id.iv_icon,bankIcon);
        }
    }
}
