package com.mango.utils;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;

import com.mango.utils.chacheUtil.CacheElementKey;
import com.mango.utils.chacheUtil.CacheUtility;

import java.util.UUID;

/**
 * Created by Administrator on 2018/3/27 0027.
 */

public class DeviceUuidUtil {
    private static String uuid = "";
    public static String getUuid(Context aCtx){
        if(TextUtils.isEmpty(uuid)){
            CacheUtility cacheUtil = new CacheUtility(aCtx,CacheUtility.GlobalLocalConfig);
            uuid = cacheUtil.readStringCache(CacheElementKey.DEVICE_UUID);
            if( TextUtils.isEmpty(uuid) ){
                uuid = UUID.randomUUID().toString();
                cacheUtil.saveCache(CacheElementKey.DEVICE_UUID,uuid);
                Log.d("deviceUuidUtil","create uuid:"+uuid);
            }else {
                Log.d("deviceUuidUtil","read uuid:"+uuid);
            }
        }
        return uuid;
    }
}
