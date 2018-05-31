package com.mango.mobile.paper.modules.TimeLinePager;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.mango.base.BaseActivity;
import com.mango.mobile.paper.R;
import com.mango.utils.CalendarUtil;
import com.mango.viewBySelf.TextViewCustomTF;
import com.wonderkiln.blurkit.BlurKit;


import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AddRecordActivity extends BaseActivity {

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_record);
        ButterKnife.bind(this);

        initView();
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        if (hasFocus) {
            byte [] bis=getIntent().getByteArrayExtra("bitmap");
            Bitmap bitmap= BitmapFactory.decodeByteArray(bis, 0, bis.length);
            if (bitmap != null){
                BlurKit blurKit = BlurKit.getInstance();
                Bitmap blur = blurKit.blur(bitmap, 25);

                for (int i =0;i<4;i++){
                    blur = blurKit.blur(blur, 25);
                }
                rlRecord.setBackground(new BitmapDrawable(blur));
            }

        }
        Log.d("zhou",System.currentTimeMillis()+"");
    }

    private void initView() {
        tvDayOfMonth.setText(CalendarUtil.getCurrentDay()+"");
        tvDayOfWeek.setText(CalendarUtil.getCurrentDayOfWeekString());
        tvYearMonth.setText(CalendarUtil.getCurrentYear()+"/"+CalendarUtil.getCurrentMonth());

    }

    @OnClick({R.id.iv_cancel})
    void onClick(View view){
        switch (view.getId()){
            case R.id.iv_cancel:
                finish();
                break;
        }
    }
}
