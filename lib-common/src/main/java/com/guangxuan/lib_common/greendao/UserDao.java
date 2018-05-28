package com.guangxuan.lib_common.greendao;

import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.Property;
import org.greenrobot.greendao.internal.DaoConfig;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.database.DatabaseStatement;

import com.mango.lib_common.entity.User;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.
/** 
 * DAO for table "USER".
*/
public class UserDao extends AbstractDao<User, Long> {

    public static final String TABLENAME = "USER";

    /**
     * Properties of entity User.<br/>
     * Can be used for QueryBuilder and for referencing column names.
     */
    public static class Properties {
        public final static Property Id = new Property(0, Long.class, "id", true, "_id");
        public final static Property User_id = new Property(1, int.class, "user_id", false, "USER_ID");
        public final static Property Portrait = new Property(2, String.class, "portrait", false, "PORTRAIT");
        public final static Property Nick = new Property(3, String.class, "nick", false, "NICK");
        public final static Property Sex = new Property(4, String.class, "sex", false, "SEX");
        public final static Property Phone = new Property(5, String.class, "phone", false, "PHONE");
        public final static Property Email = new Property(6, String.class, "email", false, "EMAIL");
        public final static Property Address = new Property(7, String.class, "address", false, "ADDRESS");
        public final static Property IsLogin = new Property(8, int.class, "isLogin", false, "IS_LOGIN");
    }


    public UserDao(DaoConfig config) {
        super(config);
    }
    
    public UserDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
    }

    /** Creates the underlying database table. */
    public static void createTable(Database db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "\"USER\" (" + //
                "\"_id\" INTEGER PRIMARY KEY AUTOINCREMENT ," + // 0: id
                "\"USER_ID\" INTEGER NOT NULL ," + // 1: user_id
                "\"PORTRAIT\" TEXT," + // 2: portrait
                "\"NICK\" TEXT NOT NULL ," + // 3: nick
                "\"SEX\" TEXT," + // 4: sex
                "\"PHONE\" TEXT NOT NULL ," + // 5: phone
                "\"EMAIL\" TEXT," + // 6: email
                "\"ADDRESS\" TEXT," + // 7: address
                "\"IS_LOGIN\" INTEGER NOT NULL );"); // 8: isLogin
    }

    /** Drops the underlying database table. */
    public static void dropTable(Database db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "\"USER\"";
        db.execSQL(sql);
    }

    @Override
    protected final void bindValues(DatabaseStatement stmt, User entity) {
        stmt.clearBindings();
 
        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }
        stmt.bindLong(2, entity.getUser_id());
 
        String portrait = entity.getPortrait();
        if (portrait != null) {
            stmt.bindString(3, portrait);
        }
        stmt.bindString(4, entity.getNick());
 
        String sex = entity.getSex();
        if (sex != null) {
            stmt.bindString(5, sex);
        }
        stmt.bindString(6, entity.getPhone());
 
        String email = entity.getEmail();
        if (email != null) {
            stmt.bindString(7, email);
        }
 
        String address = entity.getAddress();
        if (address != null) {
            stmt.bindString(8, address);
        }
        stmt.bindLong(9, entity.getIsLogin());
    }

    @Override
    protected final void bindValues(SQLiteStatement stmt, User entity) {
        stmt.clearBindings();
 
        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }
        stmt.bindLong(2, entity.getUser_id());
 
        String portrait = entity.getPortrait();
        if (portrait != null) {
            stmt.bindString(3, portrait);
        }
        stmt.bindString(4, entity.getNick());
 
        String sex = entity.getSex();
        if (sex != null) {
            stmt.bindString(5, sex);
        }
        stmt.bindString(6, entity.getPhone());
 
        String email = entity.getEmail();
        if (email != null) {
            stmt.bindString(7, email);
        }
 
        String address = entity.getAddress();
        if (address != null) {
            stmt.bindString(8, address);
        }
        stmt.bindLong(9, entity.getIsLogin());
    }

    @Override
    public Long readKey(Cursor cursor, int offset) {
        return cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0);
    }    

    @Override
    public User readEntity(Cursor cursor, int offset) {
        User entity = new User( //
            cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0), // id
            cursor.getInt(offset + 1), // user_id
            cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2), // portrait
            cursor.getString(offset + 3), // nick
            cursor.isNull(offset + 4) ? null : cursor.getString(offset + 4), // sex
            cursor.getString(offset + 5), // phone
            cursor.isNull(offset + 6) ? null : cursor.getString(offset + 6), // email
            cursor.isNull(offset + 7) ? null : cursor.getString(offset + 7), // address
            cursor.getInt(offset + 8) // isLogin
        );
        return entity;
    }
     
    @Override
    public void readEntity(Cursor cursor, User entity, int offset) {
        entity.setId(cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0));
        entity.setUser_id(cursor.getInt(offset + 1));
        entity.setPortrait(cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2));
        entity.setNick(cursor.getString(offset + 3));
        entity.setSex(cursor.isNull(offset + 4) ? null : cursor.getString(offset + 4));
        entity.setPhone(cursor.getString(offset + 5));
        entity.setEmail(cursor.isNull(offset + 6) ? null : cursor.getString(offset + 6));
        entity.setAddress(cursor.isNull(offset + 7) ? null : cursor.getString(offset + 7));
        entity.setIsLogin(cursor.getInt(offset + 8));
     }
    
    @Override
    protected final Long updateKeyAfterInsert(User entity, long rowId) {
        entity.setId(rowId);
        return rowId;
    }
    
    @Override
    public Long getKey(User entity) {
        if(entity != null) {
            return entity.getId();
        } else {
            return null;
        }
    }

    @Override
    public boolean hasKey(User entity) {
        return entity.getId() != null;
    }

    @Override
    protected final boolean isEntityUpdateable() {
        return true;
    }
    
}
