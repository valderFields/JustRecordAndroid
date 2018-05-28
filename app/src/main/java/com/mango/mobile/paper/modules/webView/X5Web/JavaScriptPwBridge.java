package com.mango.mobile.paper.modules.webView.X5Web;

import android.app.Activity;
import android.webkit.JavascriptInterface;

/**
 * Created by Administrator on 2018/3/29 0029.
 */

public class JavaScriptPwBridge {
    private Activity mContext;
    private JsBridgeListener jsBridgeListener;

    public JavaScriptPwBridge(Activity mContext) {
        this.mContext = mContext;
       // this.mHandler = new Handler(Looper.getMainLooper());
    }

    @JavascriptInterface
    public void close() {
        if (jsBridgeListener != null) {
            jsBridgeListener.close();
        }
    }


    public interface JsBridgeListener {
        void webShareDialog(String title, String desc, String link, String imgUrl);

        void onShareContentChanged(String title, String desc, String link, String imgUrl);

        void webShareTo(String channel, String callback);

        void directOpenLink(String link);

        void close();

        void redirectNativePage(String pageName);

        void getContact(String sign);
    }

    public void setJsBridgeListener(JsBridgeListener listener) {
        this.jsBridgeListener = listener;
    }
}
