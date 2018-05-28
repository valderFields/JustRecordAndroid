package com.mango.mobile.paper.modules.webView.X5Web;

import android.content.Intent;


/**
 * Created by Administrator on 2018/3/30 0030.
 */

public class CommonWebActivity extends X5WebActivity {
    private String url = "";

    @Override
    public String getHomePagerUrl() {
        Intent intent = getIntent();
        url = intent.getStringExtra("url");
        return url;
    }

    @Override
    public String getPagerKey() {
        return "返回";
    }
}
