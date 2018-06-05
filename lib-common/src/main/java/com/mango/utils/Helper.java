package com.mango.utils;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.Resources;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;

import com.mango.utils.chacheUtil.CacheElementKey;
import com.mango.utils.chacheUtil.CacheUtility;
import com.orhanobut.logger.Logger;

import java.io.File;
import java.lang.reflect.Method;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;


/**
 * Created by zhou on 2018/3/25.
 */

public class Helper {

    public static Context context;
    private static Toast mToast;

    /**
     * 全局Toast
     *
     * @param str
     */
    public static void showToast(String str) {
        if (!isMainThread(Thread.currentThread().getName())) {
            Logger.e("不能在异步线程调用 ShowToast！");
            return;
        }
        if (context == null) return;
        if (TextUtils.isEmpty(str)) return;
        if (mToast == null) {
            mToast = Toast.makeText(context, str, Toast.LENGTH_SHORT);
        } else {
            mToast.setText(str);
        }
        mToast.show();
    }

    /**
     * 是否在主线程
     *
     * @param aThreadName
     * @return
     */
    public static boolean isMainThread(String aThreadName) {
        return aThreadName.equals("main");
    }

    /**
     * 获取当前版本号
     *
     * @return
     */
    public static String getVersion() {
        return getVersion(context);
    }

    public static String getVersion(Context aCtx) {
        if (aCtx == null) return "";
        return VersionUtil.getVersion(aCtx);
    }

    /**
     * 设备号
     */
    public static String getDeviceId(Context aCtx) {
        return DeviceUuidUtil.getUuid(aCtx);
    }

    /**
     * @param aCtx
     * @return
     */
    public static boolean getIsFirstTime(Context aCtx) {
        CacheUtility cacheUtil = new CacheUtility(aCtx, CacheUtility.GlobalLocalConfig);
        return cacheUtil.readBooleanCache(CacheElementKey.UOT_FIRST_TIME_APP);
    }


    /**
     * 检查网络是否可用
     *
     * @return
     */
    public static boolean isNetworkConnected() {
        if (context != null) {
            try {
                ConnectivityManager mConnectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
                NetworkInfo mNetworkInfo = mConnectivityManager.getActiveNetworkInfo();
                if (mNetworkInfo != null) {
                    return mNetworkInfo.isAvailable();
                }
            } catch (Exception e) {

            }
        }
        return false;
    }

    //判断文件大小
    public static long getFileLength(String path) {
        if (path == null || path.equals("")) return 0;
        return getFileLength(new File(path));
    }

    public static long getFileLength(File file) {
        if (file != null && file.exists() && file.isFile()) {
            return file.length();
        } else {
            return 0;
        }
    }

    public static int dip2px(Context context, float dipValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dipValue * scale + 0.5f);
    }

    public static int dip2px(float dipValue) {
        if (context == null) return 0;
        return dip2px(context, dipValue);
    }

    public static int px2dip(Context context, float pxValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }

    public static int px2dip(float pxValue) {
        if (context == null) return 0;
        return px2dip(context, pxValue);
    }

    /**
     * 校验手机号
     *
     * @param mobile
     * @return 校验通过返回true，否则返回false
     */
    public static boolean isMobile(String mobile) {

        /**
         * 判断字符串是否符合手机号码格式
         * 移动号段: 134,135,136,137,138,139,147,150,151,152,157,158,159,170,178,182,183,184,187,188
         * 联通号段: 130,131,132,145,155,156,170,171,175,176,185,186
         * 电信号段: 133,149,153,170,173,177,180,181,189
         * @param str
         * @return 待检测的字符串
         */
        String telRegex = "^((13[0-9])|(14[5,7,9])|(15[^4])|(18[0-9])|(17[0,1,3,5,6,7,8]))\\d{8}$";// "[1]"代表第1位为数字1，"[358]"代表第二位可以为3、5、8中的一个，"\\d{9}"代表后面是可以是0～9的数字，有9位。
        if (TextUtils.isEmpty(mobile))
            return false;
        else
            return mobile.matches(telRegex);
    }


    public static boolean isChinaPhoneLegal(String str)
            throws PatternSyntaxException {
        String regExp = "^((13[0-9])|(15[^4])|(166)|(17[0-8])|(18[0-9])|(19[8-9])|(147,145))\\d{8}$";
        Pattern p = Pattern.compile(regExp);
        Matcher m = p.matcher(str);
        return m.matches();
    }


    public static boolean isLegalPassword(String password) {
        boolean isDigit = false;//定义一个boolean值，用来表示是否包含数字
        boolean isLetter = false;//定义一个boolean值，用来表示是否包含字母
        if (password.length() >= 6 && password.length() <= 16) {
            for(int i=0 ; i<password.length();i++){
                if(Character.isDigit(password.charAt(i))){   //用char包装类中的判断数字的方法判断每一个字符
                    isDigit = true;
                }
                if(Character.isLetter(password.charAt(i))){  //用char包装类中的判断字母的方法判断每一个字符
                    isLetter = true;
                }
            }

        }
        String regex = "^[a-zA-Z0-9]+$";
        boolean isRight = isDigit && isLetter&&password.matches(regex);
        return isRight;

    }

    /**
     * 隐藏软键盘
     * @param v
     */
    public static void HideKeyboard(View v) {
        if (v == null) return;
        InputMethodManager imm = (InputMethodManager) v.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
        if (imm.isActive()) {
            imm.hideSoftInputFromWindow(v.getApplicationWindowToken(), 0);
        }
    }




}
