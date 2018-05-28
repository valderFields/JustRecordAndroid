package com.mango.retrofitHttp;

/**
 * Created by DN on 2018/03/13.
 */

public class BaseCallModel<T> {


  //  public long server_time;


    /**
     * 返回码
     * @return
     */
    public int success;


    /**
     * 返回信息
     * @return
     */
    public String msg;

    /**
     * 主要数据
     */
    public T data;

  //  public String err_code;


    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }


    public int getSuccess() {
        return success;
    }

    public void setSuccess(int success) {
        this.success = success;
    }


//    public long getServer_time() {
//        return server_time;
//    }
//
//    public void setServer_time(long server_time) {
//        this.server_time = server_time;
//    }
//
//    public boolean isSuccess() {
//        return success;
//    }
//
//    public void setSuccess(boolean success) {
//        this.success = success;
//    }
//
//    public String getErr_code() {
//        return err_code;
//    }
//
//    public void setErr_code(String err_code) {
//        this.err_code = err_code;
//    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}