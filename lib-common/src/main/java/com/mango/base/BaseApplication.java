package com.mango.base;

import android.content.Context;
import android.support.multidex.MultiDexApplication;

import com.mango.utils.Helper;

/**
 * Created by zhou on 2018/3/25.
 */

public class BaseApplication extends MultiDexApplication {
    private static Context mContext;

    @Override
    public void onCreate() {
        super.onCreate();
        mContext = getApplicationContext();
        Helper.context = mContext;
    }


    /**
     * 获取application的context
     * @return
     */
    public static Context getContext(){
        return  mContext;
    }
}
