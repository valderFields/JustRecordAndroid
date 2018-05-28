package com.mango.event;

import com.mango.base.BaseEvent;
import com.mango.utils.Helper;
import com.orhanobut.logger.Logger;

import org.greenrobot.eventbus.EventBus;

/**
 * Created by Administrator on 2018/3/27 0027.
 */

public class EventCenter {
    public static void post(BaseEvent event) {
        if (!Helper.isMainThread(Thread.currentThread().getName())) {
            Logger.e("EventCenter post not Main -> " + event.getClass().getSimpleName());
        }
        EventBus.getDefault().post(event);
    }

    public static void register(Object aObject) {
        EventBus.getDefault().register(aObject);
    }

    public static void unRegister(Object aObject) {
        EventBus.getDefault().unregister(aObject);
    }
}
