package com.mango.viewBySelf;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.mango.lib_common.R;
import com.mango.utils.Helper;

/**
 * Created by Administrator on 2018/5/3 0003.
 */

public class ItemBar extends LinearLayout {

    private Context context;
    private ImageView ivLeftImg;
    private TextViewCustomTF tvLeftText;
    private TextViewCustomTF tvRightText;
    private ImageView ivRightIcon;
    private ImageView ivRightImg;

    private OnItemBarClickListener listener;

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    public ItemBar(Context context) {
        this(context,null);
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    public ItemBar(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    public ItemBar(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context = context;

        initView(attrs);
        initEvent();
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    private void initView(@Nullable AttributeSet attrs) {
        View.inflate(context, R.layout.view_itembar, this);
        ivLeftImg = findViewById(R.id.iv_left_img);
        tvLeftText = findViewById(R.id.tv_left_text);
        tvRightText = findViewById(R.id.tv_right_text);
        ivRightIcon = findViewById(R.id.iv_right_icon);
        ivRightImg = findViewById(R.id.iv_right_img);

        TypedArray ta = getContext().obtainStyledAttributes(attrs, R.styleable.ItemBar);
        int leftImg = ta.getResourceId(R.styleable.ItemBar_left_img,0);
        String leftText = ta.getString(R.styleable.ItemBar_left_text);
        String rightText = ta.getString(R.styleable.ItemBar_right_text);
        int leftImgVisible = ta.getInt(R.styleable.ItemBar_left_img_visible,VISIBLE);

        int rightIcon = ta.getResourceId(R.styleable.ItemBar_right_icon,0);
        int rightImg = ta.getResourceId(R.styleable.ItemBar_right_img,R.drawable.to_right);



        tvLeftText.setText(leftText);
        tvRightText.setText(rightText);

        if (leftImg != 0){
            ivLeftImg.setBackground(context.getResources().getDrawable(leftImg));
        }

        if (rightIcon != 0){
            ivRightIcon.setBackground(context.getResources().getDrawable(rightIcon));
        }

        if (leftImgVisible == 0){
            ivLeftImg.setVisibility(VISIBLE);
        }else if (leftImgVisible == 1){
            ivLeftImg.setVisibility(INVISIBLE);
        }else if (leftImgVisible == 2){
            ivLeftImg.setVisibility(GONE);

            RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.FILL_PARENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
            lp.setMargins(Helper.dip2px(16),0, 0, 0);
           // tvLeftText.setGravity(Gravity.CENTER_VERTICAL);
            lp.addRule(RelativeLayout.CENTER_VERTICAL);
            tvLeftText.setLayoutParams(lp);
        }


        if (rightImg != R.drawable.to_right){
            ivRightImg.setBackground(context.getResources().getDrawable(rightImg));
        }

    }

    private void initEvent(){
        ivRightImg.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener != null){
                    listener.onRightImageClick(ivRightImg);
                }

            }
        });
    }


    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    public void setRightImage(int drawableId){
        ivRightImg.setBackground(context.getResources().getDrawable(drawableId));
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    public void setRightIcon(int drawableId){
        ivRightIcon.setBackground(context.getResources().getDrawable(drawableId));
    }

    public void setRightIcon(Drawable drawable){
        ivRightIcon.setBackground(drawable);
    }

    public void setRightText(String s){
        tvRightText.setText(s);
    }

    public interface OnItemBarClickListener{
       // void onLeftClick();

        void onRightImageClick(ImageView ivRightImg);
    }

    public void setOnItemBarClickListener(OnItemBarClickListener l){
        this.listener = l;
    }
}
