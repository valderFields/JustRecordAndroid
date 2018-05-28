package com.mango.lib_common.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;


import com.mango.lib_common.greendao.DaoMaster;
import com.mango.lib_common.greendao.UserDao;

import org.greenrobot.greendao.database.Database;

/**
 * Created by Administrator on 2018/3/28 0028.
 */
public class MyOpenHelper extends DaoMaster.OpenHelper {

    public MyOpenHelper(Context context, String name, SQLiteDatabase.CursorFactory factory) {
        super(context, name, factory);
    }

    /**
     * 数据库升级
     * @param db
     * @param oldVersion
     * @param newVersion
     */
    @Override
    public void onUpgrade(Database db, int oldVersion, int newVersion) {
        //操作数据库的更新 有几个表升级都可以传入到下面
        MigrationHelper.getInstance().migrate(db,UserDao.class);
    }

}
