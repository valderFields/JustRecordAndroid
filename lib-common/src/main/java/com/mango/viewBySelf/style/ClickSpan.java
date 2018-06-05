package com.mango.viewBySelf.style;

import android.text.TextPaint;
import android.text.style.ClickableSpan;
import android.view.View;

import com.mango.utils.Helper;

/**
 * Created by Administrator on 2018/6/5 0005.
 */

public class ClickSpan extends ClickableSpan {
    private String txt;

    public ClickSpan(String txt) {
        this.txt = txt;
    }

    @Override
    public void onClick(View widget) {
        String content = String.format("ClickSpan is clicked, and txt is %s ", txt);
        Helper.showToast(content);
    }

    @Override
    public void updateDrawState(TextPaint ds) { //根据自己的需求定制文本的样式
//        ds.setColor(ds.linkColor);
//        ds.setUnderlineText(false);
    }
}


