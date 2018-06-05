package com.mango.mobile.paper.modules.TimeLinePager.addRecord;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.mango.mobile.paper.R;
import com.mango.utils.CalendarUtil;
import com.mango.viewBySelf.TextViewCustomTF;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Administrator on 2018/6/4 0004.
 */

public class AddRecordDialog extends Dialog {

    @BindView(R.id.rl_record)
    RelativeLayout rlRecord;
    @BindView(R.id.iv_cancel)
    ImageView ivCancel;
    @BindView(R.id.tv_day_of_month)
    TextViewCustomTF tvDayOfMonth;
    @BindView(R.id.tv_day_of_week)
    TextViewCustomTF tvDayOfWeek;
    @BindView(R.id.tv_year_month)
    TextViewCustomTF tvYearMonth;
    @BindView(R.id.iv_word)
    ImageView ivWord;


    private Context context;

    public AddRecordDialog(@NonNull Context context) {
        super(context);
        this.context = context;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        View view = View.inflate(context, R.layout.activity_add_record, null);
        setContentView(view);
        ButterKnife.bind(this, view);
        getWindow().setBackgroundDrawable(new ColorDrawable(0xdeffffff));
        getWindow().setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.MATCH_PARENT);
        initView();
    }

    private void initView() {

        if (CalendarUtil.getCurrentDay()<10){
            tvDayOfMonth.setText("0"+CalendarUtil.getCurrentDay());
        }
        tvDayOfWeek.setText(CalendarUtil.getCurrentDayOfWeekString());
        tvYearMonth.setText(CalendarUtil.getCurrentYear() + "/" + CalendarUtil.getCurrentMonth());

    }

    @OnClick({R.id.iv_cancel,R.id.iv_word})
    void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_cancel:
                dismiss();
            case R.id.iv_word:
                context.startActivity(new Intent(context,AddWordActivity.class));
                break;
        }
    }
}
