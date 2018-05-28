package com.mango.mobile.paper.modules.webView.X5Web;

/**
 * Created by Administrator on 2018/3/29 0029.
 */

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Message;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.mango.lib_common.utils.RequestCode;
import com.tencent.smtt.export.external.interfaces.IX5WebChromeClient;
import com.tencent.smtt.export.external.interfaces.JsPromptResult;
import com.tencent.smtt.export.external.interfaces.JsResult;
import com.tencent.smtt.sdk.ValueCallback;
import com.tencent.smtt.sdk.WebChromeClient;
import com.tencent.smtt.sdk.WebSettings;
import com.tencent.smtt.sdk.WebView;

public class X5WebView extends WebView {

    public ValueCallback<Uri> mUploadMessage;
    public ValueCallback<Uri[]> mUploadCallbackAboveL;

    private WebChromeClient chromeClient = new WebChromeClient() {

        @Override
        public void onProgressChanged(WebView webView, int newProgress) {
            curProgress = newProgress;
            if (mProgressChange != null) {
                mProgressChange.onChange(curProgress);
            }
            super.onProgressChanged(webView, curProgress);
        }

        @Override
        public void onReceivedTitle(WebView view, String title) {
            super.onReceivedTitle(view, title);
           // Logger.d("标题变更 " + title);
            if (mTitleChange != null) {
                mTitleChange.onChange(view.getTitle());
            }

            String url = X5WebView.this.getUrl();
            if (!TextUtils.isEmpty(url) && mUrlChange != null) {
                mUrlChange.onChange(url);
            }

        }

        @Override
        public boolean onJsConfirm(WebView arg0, String arg1, String arg2, JsResult arg3) {
            return super.onJsConfirm(arg0, arg1, arg2, arg3);
        }

        View myVideoView;
        View myNormalView;
        IX5WebChromeClient.CustomViewCallback callback;

        ///////////////////////////////////////////////////////////
        //

        /**
         * 全屏播放配置
         */
        @Override
        public void onShowCustomView(View view, IX5WebChromeClient.CustomViewCallback customViewCallback) {
            FrameLayout normalView = (FrameLayout) X5WebView.this.getParent();
            ViewGroup viewGroup = (ViewGroup) normalView.getParent();
            viewGroup.removeView(normalView);
            viewGroup.addView(view);
            myVideoView = view;
            myNormalView = normalView;
            callback = customViewCallback;
            if (VideoFullScreenListener != null) {
                VideoFullScreenListener.intoFullScreen();
            }
        }

        @Override
        public void onHideCustomView() {
            if (callback != null) {
                callback.onCustomViewHidden();
                callback = null;
            }
            if (myVideoView != null) {
                ViewGroup viewGroup = (ViewGroup) myVideoView.getParent();
                viewGroup.removeView(myVideoView);
                viewGroup.addView(myNormalView);
            }
            if (VideoFullScreenListener != null) {
                VideoFullScreenListener.exitFullScreen();
            }
        }

        // For Android 3.0+
        public void openFileChooser(ValueCallback<Uri> uploadMsg) {
            if (mUploadMessage != null) {
                mUploadMessage.onReceiveValue(null);
            }
            mUploadMessage = uploadMsg;
            Intent i = new Intent(Intent.ACTION_GET_CONTENT);
            i.addCategory(Intent.CATEGORY_OPENABLE);
            i.setType("image/*");
            ((Activity) (X5WebView.this.getContext())).startActivityForResult(Intent.createChooser(i, "选择文件来源"), RequestCode.WEB_FILE_CHOOSER);
        }

        // For Android 3.0+
        public void openFileChooser(ValueCallback uploadMsg, String acceptType) {
            if (mUploadMessage != null) {
                mUploadMessage.onReceiveValue(null);
            }
            mUploadMessage = uploadMsg;
            Intent i = new Intent(Intent.ACTION_GET_CONTENT);
            i.addCategory(Intent.CATEGORY_OPENABLE);
            String type = TextUtils.isEmpty(acceptType) ? "image/*" : acceptType;
            i.setType(type);
            ((Activity) (X5WebView.this.getContext())).startActivityForResult(Intent.createChooser(i, "选择文件来源"), RequestCode.WEB_FILE_CHOOSER);
        }

        // For Android 4.1
        public void openFileChooser(ValueCallback<Uri> uploadMsg, String acceptType, String capture) {
            if (mUploadMessage != null) {
                mUploadMessage.onReceiveValue(null);
            }
            mUploadMessage = uploadMsg;
            Intent i = new Intent(Intent.ACTION_GET_CONTENT);
            i.addCategory(Intent.CATEGORY_OPENABLE);
            String type = TextUtils.isEmpty(acceptType) ? "image/*" : acceptType;
            i.setType(type);
            ((Activity) (X5WebView.this.getContext())).startActivityForResult(Intent.createChooser(i, "选择文件来源"), RequestCode.WEB_FILE_CHOOSER);
        }

        //Android 5.0+
        @Override
        @SuppressLint("NewApi")
        public boolean onShowFileChooser(WebView webView, ValueCallback<Uri[]> filePathCallback, FileChooserParams fileChooserParams) {
            if (mUploadMessage != null) {
                mUploadMessage.onReceiveValue(null);
            }
            mUploadCallbackAboveL = filePathCallback;
            Intent i = new Intent(Intent.ACTION_GET_CONTENT);
            i.addCategory(Intent.CATEGORY_OPENABLE);
            if (fileChooserParams != null && fileChooserParams.getAcceptTypes() != null && fileChooserParams.getAcceptTypes().length > 0) {
                if (!TextUtils.isEmpty(fileChooserParams.getAcceptTypes()[0])) {
                    i.setType(fileChooserParams.getAcceptTypes()[0]);
                } else {
                    i.setType("image/*");
                }
            } else {
                i.setType("image/*");
            }
            ((Activity) (X5WebView.this.getContext())).startActivityForResult(Intent.createChooser(i, "选择文件来源"), RequestCode.WEB_FILE_CHOOSER);
            return true;
        }


        /**
         * webview 的窗口转移
         */
        @Override
        public boolean onCreateWindow(WebView arg0, boolean arg1, boolean arg2, Message msg) {
            return true;
        }

        @Override
        public boolean onJsAlert(WebView arg0, String arg1, String arg2, JsResult arg3) {
            return super.onJsAlert(null, "www.baidu.com", "aa", arg3);
        }

        /**
         * 对应js 的通知弹框 ，可以用来实现js 和 android之间的通信
         */
        @Override
        public boolean onJsPrompt(WebView arg0, String arg1, String arg2, String arg3, JsPromptResult arg4) {
            return super.onJsPrompt(arg0, arg1, arg2, arg3, arg4);
        }

    };

    @SuppressLint("SetJavaScriptEnabled")
    public X5WebView(Context arg0, AttributeSet arg1) {
        super(arg0, arg1);
        this.setWebViewClientExtension(new X5WebViewEventHandler(this));// 配置X5webview的事件处理
        this.setWebChromeClient(chromeClient);
        initWebViewSettings();
        this.getView().setClickable(true);
        this.getView().setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return false;
            }
        });
    }

    private void initWebViewSettings() {
        WebSettings webSetting = this.getSettings();
        webSetting.setJavaScriptEnabled(true);
        webSetting.setJavaScriptCanOpenWindowsAutomatically(true);
        webSetting.setAllowFileAccess(true);
        webSetting.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.NARROW_COLUMNS);
        webSetting.setSupportZoom(true);
        webSetting.setBuiltInZoomControls(true);
        webSetting.setUseWideViewPort(true);
        webSetting.setSupportMultipleWindows(true);
        webSetting.setLoadWithOverviewMode(true);
        webSetting.setAppCacheEnabled(true);
        webSetting.setDatabaseEnabled(true);
        webSetting.setDomStorageEnabled(true);
        webSetting.setGeolocationEnabled(true);
        webSetting.setAppCacheMaxSize(Long.MAX_VALUE);
        webSetting.setPluginState(WebSettings.PluginState.ON_DEMAND);
        webSetting.setRenderPriority(WebSettings.RenderPriority.HIGH);
        webSetting.setCacheMode(WebSettings.LOAD_NO_CACHE);
    }

    public X5WebView(Context arg0) {
        this(arg0, null);
        setBackgroundColor(0xffffffff);
    }

    // TBS: Do not use @Override to avoid false calls
    public boolean tbs_dispatchTouchEvent(MotionEvent ev, View view) {
        boolean r = super.super_dispatchTouchEvent(ev);
        return r;
    }

    // TBS: Do not use @Override to avoid false calls
    public boolean tbs_onInterceptTouchEvent(MotionEvent ev, View view) {
        boolean r = super.super_onInterceptTouchEvent(ev);
        return r;
    }

    protected void tbs_onScrollChanged(int l, int t, int oldl, int oldt, View view) {
        super_onScrollChanged(l, t, oldl, oldt);
    }

    protected void tbs_onOverScrolled(int scrollX, int scrollY, boolean clampedX, boolean clampedY, View view) {
        super_onOverScrolled(scrollX, scrollY, clampedX, clampedY);
    }

    protected void tbs_computeScroll(View view) {
        super_computeScroll();
    }

    protected boolean tbs_overScrollBy(int deltaX, int deltaY, int scrollX, int scrollY, int scrollRangeX,
                                       int scrollRangeY, int maxOverScrollX, int maxOverScrollY, boolean isTouchEvent, View view) {
        return super_overScrollBy(deltaX, deltaY, scrollX, scrollY, scrollRangeX, scrollRangeY, maxOverScrollX,
                maxOverScrollY, isTouchEvent);
    }


    protected boolean tbs_onTouchEvent(MotionEvent event, View view) {
        return super_onTouchEvent(event);
    }

    public interface onTitleChange {
        void onChange(String aTitle);
    }

    public interface onProgressChange {
        void onChange(int progress);
    }


    public interface onUrlChange {
        void onChange(String mChangeUrl);
    }

    public interface onVideoFullScreen {
        void intoFullScreen();

        void exitFullScreen();
    }

    private onUrlChange mUrlChange;

    private onTitleChange mTitleChange;

    private onProgressChange mProgressChange;

    private onVideoFullScreen VideoFullScreenListener;

    public void setOnUrlChangeListener(onUrlChange aLsner) {
        mUrlChange = aLsner;
    }

    public void setOnTitleChangeListener(onTitleChange aLsner) {
        mTitleChange = aLsner;
    }

    public void setOnProgressChange(onProgressChange aLsner) {
        mProgressChange = aLsner;
    }

    public void setOnVideoFullScreenListener(onVideoFullScreen listener) {
        this.VideoFullScreenListener = listener;
    }

    private int curProgress;


}
