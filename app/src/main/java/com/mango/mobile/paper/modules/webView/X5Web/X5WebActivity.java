package com.mango.mobile.paper.modules.webView.X5Web;

import android.content.ClipData;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.database.Cursor;
import android.graphics.PixelFormat;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.provider.MediaStore;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.mango.mobile.paper.R;
import com.mango.mobile.paper.common.events.GoBackToFinishEvent;
import com.mango.lib_common.event.EventCenter;
import com.mango.lib_common.utils.Helper;
import com.mango.lib_common.utils.RequestCode;
import com.orhanobut.logger.Logger;
import com.tencent.smtt.sdk.CookieManager;
import com.tencent.smtt.sdk.CookieSyncManager;
import com.tencent.smtt.sdk.DownloadListener;
import com.tencent.smtt.sdk.WebSettings;
import com.tencent.smtt.sdk.WebView;
import com.tencent.smtt.sdk.WebViewClient;


import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import static android.os.Build.MODEL;

/**
 * Created by Administrator on 2018/3/29 0029.
 */

public abstract class X5WebActivity extends AppCompatActivity implements JavaScriptPwBridge.JsBridgeListener {

    private FrameLayout mViewParent;
    private ProgressBar progressBar;
    private X5WebView mWebView;
    private String homeUrl;
    public View back_icon, share_icon;
    public TextView title, back,close;

    public String needSpecialCookieUrl = "";
    public int isSetClick = -1;
    public boolean isGoBack = false;
    public boolean isfinish = false;
    public boolean isFirstClick = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        EventCenter.register(this);

        homeUrl = getHomePagerUrl();

        try {
            getWindow().setFormat(PixelFormat.TRANSLUCENT);

            if (Integer.parseInt(Build.VERSION.SDK) >= 11) {
                getWindow().setFlags(
                        WindowManager.LayoutParams.FLAG_HARDWARE_ACCELERATED,
                        WindowManager.LayoutParams.FLAG_HARDWARE_ACCELERATED);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        setContentView(R.layout.x5webview_activity);
        initView();
        initX5WebView();
        checkLoginCookie(homeUrl);
    }

    public abstract String getHomePagerUrl();

    public abstract String getPagerKey();


    private void initView() {
        mViewParent = (FrameLayout) findViewById(R.id.webView_layout);
        progressBar = (ProgressBar) findViewById(R.id.x5web_progressBar);
        title = (TextView) findViewById(R.id.webview_aty_title);
        title.setText(getDefaultTitleName());
        share_icon = findViewById(R.id.webview_aty_shuaxin_icon);
        share_icon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        back_icon = findViewById(R.id.webview_aty_btn_back_icon);
        back = (TextView) findViewById(R.id.webview_aty_fanhui);
        back_icon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
      //  back.setText(BackName());
        close = (TextView) findViewById(R.id.webview_aty_tuichu);
        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                close();
            }
        });
    }

    public WebViewClient createWebViewClient() {

        WebViewClient client = new WebViewClient() {
            /**
             * 防止加载网页时调起系统浏览器
             */
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                Log.i("SpecialUrl", url);
                if(back_icon != null && back != null){
                    back_icon.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            onBackPressed();
                        }
                    });
                    back.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            onBackPressed();
                        }
                    });
                }
                if (url.startsWith("tel:")) {
                    Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                    startActivity(intent);
                    return true;
                }
 //               else if (url.contains(finalKey)) {
//                    if (isfirstClick) {
//                        isfirstClick = false;
//                        Intent intent = new Intent(X5WebActivity.this, PayWebView.class);
//                        intent.putExtra(ServerPushMessageAction.Server_Licai_Url_Tag, url);
//                        startActivity(intent);
//                    }
//                    return true;
 //               }
                else if (!url.startsWith("http:") && !url.startsWith("https:")) {
                    try {
                        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                        startActivity(intent);
                    } catch (Exception e) {
//                        view.loadUrl(url);
                    }
                    return true;
                } else if (needSpecialCookieUrl.equals(url)){
                    Log.i("SpecialUrl", "LOAD URL");
                    view.loadUrl(url);
                    return true;
                }else {
                    checkLoginCookie(url);
                    view.loadUrl(url);
                    return true;
                }
            }

            @Override
            public void onPageFinished(WebView webView, String s) {
                super.onPageFinished(webView, s);
                loadWebPlatformJS();
            }
        };
        return client;
    }

    public void onUrlChanged(String url) {

    }

    private void initX5WebView() {
        mWebView = new X5WebView(this);
        mWebView.setWebViewClient(createWebViewClient());
        mWebView.setOnUrlChangeListener(new X5WebView.onUrlChange() {
            @Override
            public void onChange(String mChangeUrl) {
                onUrlChanged(mChangeUrl);
            }
        });
        mViewParent.addView(mWebView, new FrameLayout.LayoutParams(
                FrameLayout.LayoutParams.FILL_PARENT,
                FrameLayout.LayoutParams.FILL_PARENT));
        mWebView.setDownloadListener(new DownloadListener() {
            @Override
            public void onDownloadStart(String s, String s1, String s2, String s3, long l) {
                if (TextUtils.isEmpty(s)) return;
                try {
                    Intent intent = new Intent();
                    intent.setAction("android.intent.action.VIEW");
                    Uri content_url = Uri.parse(s);
                    intent.setData(content_url);
                    startActivity(intent);
                } catch (Exception e) {
                    Helper.showToast("下载失败");
                }
            }
        });

        WebSettings webSetting = mWebView.getSettings();
        webSetting.setDefaultTextEncodingName("utf-8");
        webSetting.setAllowFileAccess(true);
        webSetting.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.NARROW_COLUMNS);
        webSetting.setSupportZoom(true);
        webSetting.setBuiltInZoomControls(true);
        webSetting.setUseWideViewPort(true);
        webSetting.setSupportMultipleWindows(false);
        webSetting.setLoadWithOverviewMode(true);
        webSetting.setAppCacheEnabled(true);
        webSetting.setDatabaseEnabled(true);
        webSetting.setDomStorageEnabled(true);
        webSetting.setJavaScriptEnabled(true);
        webSetting.setGeolocationEnabled(true);
        webSetting.setAppCacheMaxSize(Long.MAX_VALUE);
        webSetting.setAppCachePath(this.getDir("appcache", 0).getPath());
        webSetting.setDatabasePath(this.getDir("databases", 0).getPath());
        webSetting.setGeolocationDatabasePath(this.getDir("geolocation", 0).getPath());
        webSetting.setPluginState(WebSettings.PluginState.ON_DEMAND);
        webSetting.setRenderPriority(WebSettings.RenderPriority.HIGH);
        mWebView.loadUrl(homeUrl);

        mWebView.setOnProgressChange(new X5WebView.onProgressChange() {
            @Override
            public void onChange(final int progress) {
                if (isSetClick == 100 && progress < 100){
                    try{
                        back_icon.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                onBackPressed();
                            }
                        });
                        back.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                onBackPressed();
                            }
                        });
                    }catch (Exception e){

                    }
                    isSetClick = progress;
                }

                if (progress == 100){
                    isSetClick = progress;
                }
                progressBar.setProgress(progress);
                if (progress == 100) {
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            if (progress == 100) {
                                progressBar.setVisibility(View.GONE);
                            }
                        }
                    }, 2000);
                } else {
                    if (progressBar.getVisibility() == View.GONE)
                        progressBar.setVisibility(View.VISIBLE);
                }
            }
        });
        JavaScriptPwBridge javaScriptPwBridge = new JavaScriptPwBridge(this);
        javaScriptPwBridge.setJsBridgeListener(this);
        mWebView.addJavascriptInterface(javaScriptPwBridge, "pwBrigde");

        try {
            mWebView.removeJavascriptInterface("searchBoxJavaBridge_");
            mWebView.removeJavascriptInterface("accessibility");
            mWebView.removeJavascriptInterface("accessibilityTraversal");
        } catch (Exception e) {
            Logger.e("debug", "removeJavascriptInterface Error");
        }

        mWebView.setOnVideoFullScreenListener(new X5WebView.onVideoFullScreen() {
            @Override
            public void intoFullScreen() {
                if (getRequestedOrientation() != ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE) {
                    setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
                }
            }

            @Override
            public void exitFullScreen() {
                if (getRequestedOrientation() != ActivityInfo.SCREEN_ORIENTATION_PORTRAIT) {
                    setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
                }
            }
        });
        mWebView.setOnTitleChangeListener(new X5WebView.onTitleChange() {
            @Override
            public void onChange(String aTitle) {
                title.setText(aTitle);
            }
        });
    }

    public String getDefaultTitleName() {
        return "";
    }

    private void syncCookie(String token, String curUrl) {
        String url = HostWhite.isWhiteHostPath(curUrl);
        if (TextUtils.isEmpty(url)) return;
        try {
            CookieSyncManager.createInstance(this);
            CookieManager cookieManager = CookieManager.getInstance();
            cookieManager.setAcceptCookie(true);

            cookieManager.setCookie(url, "domain=" + url);
            cookieManager.setCookie(url, "path=/");
            cookieManager.setCookie(url, "platform=Android"+ ";domain=" + url + ";path=/");
            cookieManager.setCookie(url, "os_version=" + Build.VERSION.RELEASE+ ";domain=" + url + ";path=/");
            cookieManager.setCookie(url, "app_client_version=" + Helper.getVersion(this)+ ";domain=" + url + ";path=/");
            cookieManager.setCookie(url, "hardware=" + MODEL+ ";domain=" + url + ";path=/");
            cookieManager.setCookie(url, "device_key=" + Helper.getDeviceId(this)+ ";domain=" + url + ";path=/");
            cookieManager.setCookie(url, "access_token=" + token+ ";domain=" + url + ";path=/");
            cookieManager.setCookie(url, "app_version=" + Helper.getVersion(this)+ ";domain=" + url + ";path=/");
           // cookieManager.setCookie(url, "is_new_user=" + HomeActivity.getIsFirstTime(this)+ ";domain=" + url + ";path=/");
            cookieManager.setCookie(url, "app_name=kdjz" + ";domain=" + url + ";path=/");


            if (Build.VERSION.SDK_INT < 21) {
                CookieSyncManager.getInstance().sync();
            } else {
                CookieManager.getInstance().flush();
            }
        } catch (Exception e) {

        }
    }

    public String getCurPageUrl() {
        return mWebView.getUrl();
    }

    public String getCurTitle() {
        return mWebView.getTitle();
    }

    public void reloadWeb() {
        mWebView.reload();
    }

    public boolean canGoBack() {
        return mWebView.canGoBack();
    }

    public void webGoBack() {
        mWebView.goBack();
    }

    public void loadUrl(String url) {
        mWebView.loadUrl(url);
    }

    public void clearHistory() {
        mWebView.clearHistory();
    }

    private void cleanCookie() {
        CookieManager cookieManager = CookieManager.getInstance();
        cookieManager.removeSessionCookie();
    }

    private void checkLoginCookie(String curUrl) {
        CookieSyncManager.createInstance(this);
        CookieManager cookieManager = CookieManager.getInstance();
        cookieManager.setAcceptCookie(true);

//        if (DataCenter.getUser().getIsLogin() == 1) {
//            Log.i("test","====token");
//            syncCookie(DataCenter.getToken().getAccessToken(), curUrl);
//        } else {
//            Log.i("test","====notoken");
//            syncCookie(Token.DEFAULT_ACCESS_TOKEN, curUrl);
//        }
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent aItn) {
        switch (requestCode) {
//            case RequestCode.LOGIN_PAGE: {
//                if (aItn == null) break;
//                syncCookie(DataCenter.getToken().getAccessToken(), mWebView.getUrl());
//
//                mWebView.reload();
//                break;
//            }

            case RequestCode.WEB_FILE_CHOOSER: {
                //file chooser result from html <input> label
                if (mWebView.mUploadCallbackAboveL != null) {
                    onActivityResultAboveL(aItn);
                } else if (mWebView.mUploadMessage != null) {
                    String path = "";
                    long fileLength;
                    if (aItn != null) {
                        Log.i("chooser", "intent is" + aItn.toString());
                        Log.i("chooser", "bundle is" + aItn.getDataString());
                        path = aItn.getDataString();
                    }
                    fileLength = getFileLength(Uri.parse(path));
                    Log.i("chooser", "bundle length " + fileLength);
                    mWebView.mUploadMessage.onReceiveValue(Uri.parse(path));
                    mWebView.mUploadMessage = null;
                }
            }
        }
    }


    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    private void onActivityResultAboveL(Intent data) {
        if (mWebView.mUploadCallbackAboveL == null) {
            return;
        }
        long fileLength;
        Uri[] results = null;
        if (data != null) {
            String dataString = data.getDataString();
            ClipData clipData = data.getClipData();
            Log.i("chooser", "intent is" + data.toString());
            Log.i("chooser", "bundle is" + data.getDataString());

            if (clipData != null) {
                results = new Uri[clipData.getItemCount()];
                for (int i = 0; i < clipData.getItemCount(); i++) {
                    ClipData.Item item = clipData.getItemAt(i);
                    results[i] = item.getUri();
                }
            }
            if (dataString != null)
                results = new Uri[]{Uri.parse(dataString)};
        }
        if (results != null && results.length >= 1) {
            fileLength = getFileLength(results[0]);
            mWebView.mUploadCallbackAboveL.onReceiveValue(results);
        } else {
            mWebView.mUploadCallbackAboveL.onReceiveValue(null);
        }
        mWebView.mUploadCallbackAboveL = null;
        return;
    }

    private long getFileLength(Uri uri) {
        long fileLength = Helper.getFileLength(getRealPathFromURI(uri));
        return fileLength;
    }

    public String getRealPathFromURI(Uri contentUri) {
        String res = null;
        String[] proj = {MediaStore.Images.Media.DATA};
        Cursor cursor = getContentResolver().query(contentUri, proj, null, null, null);
        if (cursor != null) {
            if (cursor.moveToFirst()) {
                int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
                res = cursor.getString(column_index);
            }
            cursor.close();
        }
        return res;
    }

    @Override
    protected void onDestroy() {
        ViewGroup view = (ViewGroup) getWindow().getDecorView();
        view.removeAllViews();
        mWebView.setVisibility(View.GONE);
        if (mWebView != null) {
            mWebView.destroy();
        }
        EventCenter.unRegister(this);
        super.onDestroy();
    }

    @Override
    public void onBackPressed() {
        if (canGoBack()) {
            webGoBack();
            return;
        }
        super.onBackPressed();
    }

    @Override
    public void close() {
        this.finish();
    }




//    @Subscribe
//    public void gobackFirst(GobackFirst event) {
//        Log.i("uuurl", "EVENT BUS");
//        isGoBack = false;
//        isfinish = true;
//        loadUrl(homeUrl);
//        new Handler().postDelayed(new Runnable() {
//            @Override
//            public void run() {
//                clearHistory();
//            }
//        }, 1500);
//    }


    @Subscribe(threadMode = ThreadMode.MAIN)
    public void gobacktofinish(GoBackToFinishEvent event) {
        Log.i("uuurl", "EVENT BUS");
        finish();
    }

//    @Subscribe
//    public void gobackForReload(GobackForReload event) {
//        Log.i("uuurl", "EVENT BUS");
//        reloadWeb();
//    }

//

    @Override
    protected void onResume() {
        super.onResume();
        if (canGoBack() && isGoBack) {
            Log.i("uuurl", "RESUME");
            //mWebView.goBack();
        }
        isFirstClick = true;
    }

    @Override
    public void webShareDialog(String title, String desc, String link, String imgUrl) {
    }

    @Override
    public void onShareContentChanged(String title, String desc, String link, String imgUrl) {

    }

    @Override
    public void webShareTo(final String channel, final String callback) {

    }

    @Override
    public void directOpenLink(String link) {

    }


    private String wholeJS;

    protected void loadWebPlatformJS() {
//        ThreadSqlOperateUtil.startThread(new ThreadAutoCall() {
//            @Override
//            public void doInThread() throws Exception {
//                if (wholeJS == null || wholeJS.equals("")) {
//                    URL url = null;
//                    InputStream in = null;
//                    try {
//                        url = new URL(UrlConfig.webview_platform());
//                        in = url.openStream();
//
//                        byte buff[] = new byte[1024];
//                        ByteArrayOutputStream fromFile = new ByteArrayOutputStream();
//                        FileOutputStream out = null;
//                        do {
//                            int numread = in.read(buff);
//                            if (numread <= 0) {
//                                break;
//                            }
//                            fromFile.write(buff, 0, numread);
//                        } while (true);
//                        wholeJS = fromFile.toString();
//                    } catch (Exception e) {
//                        e.printStackTrace();
//                    }
//                }
//            }
//
//            @Override
//            public void onThreadEndCall(Exception e) {
//                if (mWebView != null && wholeJS != null) {
//                    try {
//                        String key = getPagerKey();
//                        if (TextUtils.isEmpty(key)) {
//                            key = "other";
//                        }
//                        mWebView.loadUrl("javascript:" + wholeJS);
////						Logger.d("javascript--->" + wholeJS);
//                        mWebView.loadUrl("javascript:try{\nstat_webview('" + key + "','" + DataCenter.getToken().getAccessToken() + "','" + Helper.getDeviceId() + "','" + "Android')\n}catch(e){}");
//                        Logger.i("loadJs-->" + "javascript:stat_webview('" + key + "','" + DataCenter.getToken().getAccessToken() + "','" + Helper.getDeviceId() + "','" + "Android')");
//                    } catch (Exception ee) {
//
//                    }
//
//                }
//            }
//        });
    }

    @Override
    public void redirectNativePage(String pageName) {

    }

    public void RunJs(String s){
        mWebView.loadUrl(s);
    }

    @Override
    public void getContact(String sign) {

    }
}
