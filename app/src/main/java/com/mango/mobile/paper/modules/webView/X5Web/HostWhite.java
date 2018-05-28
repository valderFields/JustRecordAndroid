package com.mango.mobile.paper.modules.webView.X5Web;

import android.text.TextUtils;

import org.json.JSONArray;

/**
 * Created by icezers on 16/9/14.
 */
public class HostWhite {


    private static final String Test_Articl = "m.test.ikoudai.cc";

    private static final String Test_Articl2 = "m-ikoudai-test.qeeniao.com";

    private static final String Test_Api = "dev.qeeniao.com";

    private static final String Test_Licai1 = "wap.test.qeeniao.com";

    private static final String Test_Licai2 = "licai-dev.qeeniao.com";
    private static final String Articl = "m.ikoudai.cc";
    private static final String Api = "api.qeeniao.com";
    private static final String Licai = "m.kdjz.qeeniao.com";
    private static final String JIEBEITEST = "jiebei-test.qeeniao.com";
    private static final String JIEBEI = "jiebei.qeeniao.com";
    private static final String LOAN_TEST = "loan-test.qeeniao.com";
    private static final String LOAN = "loan.qeeniao.com";
    private static final String whiteHostArray[] = {Test_Api, Test_Articl, Test_Articl2, Test_Licai1, Test_Licai2, Articl, Api, Licai, JIEBEITEST, JIEBEI, LOAN, LOAN_TEST};

    public static String isWhiteHostPath(String url) {
        String host[] = getOnlineWhiteHost();
        if (host == null || host.length == 0) {
            host = whiteHostArray;
        }
        if (TextUtils.isEmpty(url)) {
            return null;
        }
        for (int i = 0; i < host.length; i++) {
            if (url.contains(host[i])) {
                return host[i];
            }
        }
        return null;
    }

    public static boolean isWhiteHost(String url) {
        return !TextUtils.isEmpty(isWhiteHostPath(url));
    }

    public static String[] getOnlineWhiteHost() {
        String host[] = null;
        //String params = OnlineParamsUtil.getInstance().getParam().white_host;
        String params = "";
        if (!TextUtils.isEmpty(params)) {
            try {
                JSONArray array = new JSONArray(params);
                host = new String[array.length()];
                for (int i = 0; i < array.length(); i++) {
                    host[i] = array.getString(i);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return host;
    }
}
