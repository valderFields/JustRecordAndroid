package com.mango.retrofitHttp;

import android.os.Build;
import android.webkit.WebSettings;

import com.mango.base.BaseApplication;
import com.mango.utils.Helper;

import java.io.IOException;

import okhttp3.FormBody;
import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

import static android.os.Build.MODEL;

/**
 * Created by DN on 2018/03/13.
 */

class ParamsInterceptor implements Interceptor {

    @Override
    public Response intercept(Interceptor.Chain chain) throws IOException {

        Request orgRequest = chain.request();

        Request.Builder newRequest = orgRequest.newBuilder();

        switch (orgRequest.method()) {
            case "GET":
                //手机信息
                String hardware = "";
                try {
                    Build bd = new Build();
                    hardware = MODEL.replaceAll(" ", "");
                } catch (Exception e) {
                    e.printStackTrace();
                }

                // 添加新的参数
                HttpUrl.Builder authorizedUrlBuilder = orgRequest.url()
                        .newBuilder()
                        .scheme(orgRequest.url().scheme())
                        .host(orgRequest.url().host())
                        .addQueryParameter("access_token", "")
                        .addQueryParameter("platform", "Android")
                        .addQueryParameter("app_client_version", Helper.getVersion())
                        .addQueryParameter("os_version", Build.VERSION.RELEASE)
                        .addQueryParameter("app_name", "znl")
                        .addQueryParameter("app_real_name", "znl")
                        .addQueryParameter("hardware", hardware)
                        .addQueryParameter("device_key", Helper.getDeviceId(BaseApplication.getContext()))
                        .addQueryParameter("is_new_user", String.valueOf(Helper.getIsFirstTime(BaseApplication.getContext())));

                newRequest.method(orgRequest.method(), orgRequest.body()).url(authorizedUrlBuilder.build()).build();
                break;
            case "POST":
                RequestBody body = orgRequest.body();
                RequestBody newBody = null;
                if (body != null && body instanceof FormBody) {
                    newBody = addParamsToFormBody((FormBody) body);
                } else {
                    newBody = body;
                }
                newRequest.url(orgRequest.url()).method(orgRequest.method(), newBody);
                break;
        }
        newRequest.addHeader("User-Agent", getUserAgent());
        return chain.proceed(newRequest.build());

    }


    /**
     * 为FormBody类型请求体添加参数
     *
     * @param body
     * @return
     */
    private FormBody addParamsToFormBody(FormBody body) {
        FormBody.Builder builder = new FormBody.Builder();

        //某些请求中已经带入了platform参数 不能重复覆盖
        boolean isHasPlatform = false;
        boolean isHasName = false;
        //添加原请求体
        for (int i = 0; i < body.size(); i++) {
            builder.addEncoded(body.encodedName(i), body.encodedValue(i));
            if (body.encodedName(i).equals("platform")) {
                isHasPlatform = true;
            }
        }
        for (int i = 0; i < body.size(); i++) {
            builder.addEncoded(body.encodedName(i), body.encodedValue(i));
            if (body.encodedName(i).equals("app_name")) {
                isHasName = true;
            }
        }
        if(!isHasName){
            builder.add("app_name", "kdjz");
        }
        if (!isHasPlatform) {
            builder.add("platform", "Android");

        }
        builder.add("access_token","");
        builder.add("app_client_version", Helper.getVersion());
        builder.add("os_version", Build.VERSION.RELEASE);
        try {
            builder.add("hardware", MODEL.replaceAll(" ", ""));
        } catch (Exception e) {
            e.printStackTrace();
        }
        builder.add("device_key", Helper.getDeviceId(BaseApplication.getContext()));
        builder.add("is_new_user", String.valueOf(Helper.getIsFirstTime(BaseApplication.getContext())));

        return builder.build();
    }

    private String getUserAgent() {
        String userAgent = "";
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            try {
                userAgent = WebSettings.getDefaultUserAgent(BaseApplication.getContext());
            } catch (Exception e) {
                userAgent = System.getProperty("http.agent");
            }
        } else {
            userAgent = System.getProperty("http.agent");
        }
        StringBuffer sb = new StringBuffer();
        for (int i = 0, length = userAgent.length(); i < length; i++) {
            char c = userAgent.charAt(i);
            if (c <= '\u001f' || c >= '\u007f') {
                sb.append(String.format("\\u%04x", (int) c));
            } else {
                sb.append(c);
            }
        }
        return sb.toString();
    }
}