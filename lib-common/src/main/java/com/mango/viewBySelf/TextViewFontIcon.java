package com.mango.viewBySelf;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Typeface;
import android.os.Build;
import android.util.AttributeSet;
import android.widget.TextView;

/**
 * Created by Administrator on 2018/3/27 0027.
 */

public class TextViewFontIcon extends TextView  {
    public TextViewFontIcon(Context context) {
        super(context);
        init();
    }

    public TextViewFontIcon(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public TextViewFontIcon(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public TextViewFontIcon(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init();
    }

    public void init() {
        try {
            setTypeface(Typeface.createFromAsset(getContext().getAssets(), "fonts/iconfont.ttf"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void updateIcon() {
        setTypeface(Typeface.createFromAsset(getContext().getAssets(), "fonts/iconfont.ttf"));
        setText(getText().toString());
    }
    }
