package com.mango.lib_common.entity;

import android.os.Parcel;
import android.os.Parcelable;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.NotNull;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by Administrator on 2018/3/29 0029.
 * 对应服务器user_data表
 */

@Entity
public class User implements Parcelable {
    @Id(autoincrement = true)
    private Long id;

    /**
     * 用户ID
     */
    @NotNull
    private int user_id;

    /**
     * 用户头像
     */
    private String portrait;

    /**
     * 用户名
     */
    @NotNull
    private String nick;

    /**
     * 性别  （0:保密  1：男 2：女）
     */
    private String sex;

    /**
     * 手机号（即登录时的账号）
     */
    @NotNull
    private String phone;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 地址
     */
    private String address;

    /**
     * 是否登录 0：未登录  1：已登录
     */

    private int isLogin = 0;

    @Generated(hash = 1871008748)
    public User(Long id, int user_id, String portrait, @NotNull String nick,
            String sex, @NotNull String phone, String email, String address,
            int isLogin) {
        this.id = id;
        this.user_id = user_id;
        this.portrait = portrait;
        this.nick = nick;
        this.sex = sex;
        this.phone = phone;
        this.email = email;
        this.address = address;
        this.isLogin = isLogin;
    }

    @Generated(hash = 586692638)
    public User() {
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getUser_id() {
        return this.user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getPortrait() {
        return this.portrait;
    }

    public void setPortrait(String portrait) {
        this.portrait = portrait;
    }

    public String getNick() {
        return this.nick;
    }

    public void setNick(String nick) {
        this.nick = nick;
    }

    public String getSex() {
        return this.sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getPhone() {
        return this.phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return this.address;
    }

    public void setAddress(String adress) {
        this.address = adress;
    }

    public int getIsLogin() {
        return this.isLogin;
    }

    public void setIsLogin(int isLogin) {
        this.isLogin = isLogin;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(this.id);
        dest.writeInt(this.user_id);
        dest.writeString(this.portrait);
        dest.writeString(this.nick);
        dest.writeString(this.sex);
        dest.writeString(this.phone);
        dest.writeString(this.email);
        dest.writeString(this.address);
        dest.writeInt(this.isLogin);
    }

    protected User(Parcel in) {
        this.id = (Long) in.readValue(Long.class.getClassLoader());
        this.user_id = in.readInt();
        this.portrait = in.readString();
        this.nick = in.readString();
        this.sex = in.readString();
        this.phone = in.readString();
        this.email = in.readString();
        this.address = in.readString();
        this.isLogin = in.readInt();
    }

    public static final Parcelable.Creator<User> CREATOR = new Parcelable.Creator<User>() {
        @Override
        public User createFromParcel(Parcel source) {
            return new User(source);
        }

        @Override
        public User[] newArray(int size) {
            return new User[size];
        }
    };
}