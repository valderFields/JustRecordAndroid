package com.mango.viewBySelf;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.mango.lib_common.R;


/**
 * Created by Administrator on 2018/4/11 0011.
 */

public class TopBar extends LinearLayout {
    private Context context;
    private TextViewCustomTF tvTitle;
    private TextViewCustomTF tvBack;
    private TextViewCustomTF tvRight;
    private LinearLayout llBack;
    private ImageView ivRightIcon;
    private OnTopClickListener listener;
    public TopBar(Context context) {
        this(context,null);
    }

    public TopBar(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public TopBar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context = context;
        initView();
        initEvent();
    }

    private void initView() {
        View.inflate(context, R.layout.view_topbar, this);
        tvTitle = findViewById(R.id.tv_title);
        tvBack = findViewById(R.id.tv_back);
        tvRight = findViewById(R.id.tv_right);
        llBack = findViewById(R.id.ll_back);
        ivRightIcon = findViewById(R.id.iv_right_icon);
    }

    public void setTitle(String title){
        tvTitle.setText(title);
    }

    private void initEvent() {
        llBack.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                //context.finish();
                if (listener != null){
                    listener.onLeftClick();
                }
            }
        });

        tvRight.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener != null){
                    listener.onRightClick();
                }
            }
        });

        ivRightIcon.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener != null){
                    listener.onRightClick();
                }
            }
        });


    }

    public void setRightTextVisibility(){
        tvRight.setVisibility(VISIBLE);
    }

    public void setRightIconVisibility(){
        ivRightIcon.setVisibility(VISIBLE);
    }

    public void setRightIcon(Drawable rightIcon){
        ivRightIcon.setBackground(rightIcon);
    }

    public interface OnTopClickListener{
        void onLeftClick();

        void onRightClick();
    }

    public void setOnTopClickListener(OnTopClickListener l){
        this.listener = l;
    }

}
