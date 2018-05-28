package com.mango.lib_common.utils;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;

/**
 * Created by Administrator on 2018/3/27 0027.
 */

public class VersionUtil {
    private static PackageInfo info;
    private static String version = "";
    private static int versionCode = 0;

    public static String getVersion(Context aCtx) {
        if (aCtx == null) return "";

        if( info != null ){
            version = info.versionName;
        }else {
            try {
                PackageManager manager = aCtx.getPackageManager();
                info = manager.getPackageInfo(aCtx.getPackageName(), 0);
                version = info.versionName;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return version;
    }

    public static int getVersionCode(Context aCtx){
        if( info != null ){
            versionCode = info.versionCode;
        }else {
            try {
                PackageManager manager = aCtx.getPackageManager();
                info = manager.getPackageInfo(aCtx.getPackageName(), 0);
                versionCode = info.versionCode;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return versionCode;
    }
}
