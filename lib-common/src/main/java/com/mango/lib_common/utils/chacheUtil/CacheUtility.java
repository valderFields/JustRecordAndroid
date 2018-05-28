package com.mango.lib_common.utils.chacheUtil;

import android.content.Context;
import android.content.SharedPreferences;

import com.mango.lib_common.base.BaseApplication;

/**
 * Created by zhou on 2018/3/25.
 */

public class CacheUtility {

    /**
     * 用户行为
     */

    public static final String USER_GUIDE = "user_guide";

    public static final String USER_MESSAGE = "user_message";


    /**
     * 不清空的
     */
    public static final String GlobalLocalConfig = "GlobalLocalConfig";

    SharedPreferences mPreferences;

    public CacheUtility(Context context, String fileKey) {
        mPreferences = context.getSharedPreferences(fileKey, Context.MODE_PRIVATE);
    }

    public static CacheUtility create(Context context, String fileKey) {
        return new CacheUtility(context, fileKey);
    }

    public void saveCache(String valueKey, String value) {
        SharedPreferences.Editor editor = mPreferences.edit();
        editor.putString(valueKey, value);
        editor.commit();
    }

    public void saveCache(String valueKey, Integer value) {
        SharedPreferences.Editor editor = mPreferences.edit();
        editor.putInt(valueKey, value);
        editor.commit();
    }

    public void saveCache(String valueKey, Long value) {
        SharedPreferences.Editor editor = mPreferences.edit();
        editor.putLong(valueKey, value);
        editor.commit();
    }

    public void saveCache(String valueKey, Float value) {
        SharedPreferences.Editor editor = mPreferences.edit();
        editor.putFloat(valueKey, value);
        editor.commit();
    }

    public void saveCache(String valueKey, boolean value) {
        SharedPreferences.Editor editor = mPreferences.edit();
        editor.putBoolean(valueKey, value);
        editor.commit();
    }

    public String readStringCache(String valueKey) {
        return mPreferences.getString(valueKey, "");
    }

    public Integer readIntCache(String valueKey) {
        return mPreferences.getInt(valueKey, 0);
    }

    public Integer readIntCache(String valueKey, int defaultValue) {
        return mPreferences.getInt(valueKey, defaultValue);
    }

    public Float readFloatCache(String valueKey) {
        return mPreferences.getFloat(valueKey, 0);
    }

    public Long readLongCache(String valueKey) {
        return mPreferences.getLong(valueKey, 0);
    }

    public boolean readBooleanCache(String valueKey) {
        return mPreferences.getBoolean(valueKey, false);
    }

    public static String readStringCache(String fileKey, String valueKey) {
        CacheUtility cacheUtility = new CacheUtility(BaseApplication.getContext(), fileKey);
        return cacheUtility.readStringCache(valueKey);
    }

    public static Integer readIntCache(String fileKey, String valueKey) {
        CacheUtility cacheUtility = new CacheUtility(BaseApplication.getContext(), fileKey);
        return cacheUtility.readIntCache(valueKey);
    }

    public static Integer readIntCache(String fileKey, String valueKey, int defaultValue) {
        CacheUtility cacheUtility = new CacheUtility(BaseApplication.getContext(), fileKey);
        return cacheUtility.readIntCache(valueKey, defaultValue);
    }

    public static Float readFloatCache(String fileKey, String valueKey) {
        CacheUtility cacheUtility = new CacheUtility(BaseApplication.getContext(), fileKey);
        return cacheUtility.readFloatCache(valueKey);
    }

    public static Long readLongCache(String fileKey, String valueKey) {
        CacheUtility cacheUtility = new CacheUtility(BaseApplication.getContext(), fileKey);
        return cacheUtility.readLongCache(valueKey);
    }

    public static boolean readBooleanCache(String fileKey, String valueKey) {
        CacheUtility cacheUtility = new CacheUtility(BaseApplication.getContext(), fileKey);
        return cacheUtility.readBooleanCache(valueKey);
    }

    public void removeCache(String valueKey) {
        SharedPreferences.Editor editor = mPreferences.edit();
        editor.remove(valueKey);
        editor.commit();
    }

    public void clearCache() {
        SharedPreferences.Editor editor = mPreferences.edit();
        editor.clear();
        editor.commit();
    }

}
