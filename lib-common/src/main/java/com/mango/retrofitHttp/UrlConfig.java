package com.mango.retrofitHttp;

/**
 * Created by DN on 2018/03/13.
 */

public  class UrlConfig {


   public static final String TEST = "/app/ ";

   public static final String urlPrefix = "http://www.gxshine.com/app/index.php/Home/";

   //注册登录
    public static final String LOGIN_INDEX = "login/index";  //登录
    public static final String LOGIN_REGISTER = "login/register";  //注册
    public static final String LOGIN_MESSAGE = "login/message";  //验证码

   // public static final String LOGIN_MESSAGE ="http://www.gxshine.com/app/index.php/Home/login/message";

   public static String urlPrefix(){
       return urlPrefix;
   }

}
