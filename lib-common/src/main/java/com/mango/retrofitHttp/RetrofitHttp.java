package com.mango.retrofitHttp;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;

import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

/**
 * Created by DN on 2018/03/13.
 */

public class RetrofitHttp {

    private static RetrofitApi api;

    private static final int DEFAULT_TIMEOUT = 30; //此处默认超时时间为30s

    public static RetrofitApi createApiInstance() {
        if (api == null) {
            OkHttpClient.Builder builder = new OkHttpClient.Builder();

            /**
             设置Log
             */
            HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
            loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
            builder.addInterceptor(loggingInterceptor);

            /**
             设置公共参数
             */
             builder.addInterceptor(new ParamsInterceptor());


            /**
             设置Retrofit
             */
            OkHttpClient okHttpClient = builder.connectTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS).build();

            Gson gson = new GsonBuilder().setLenient().create();

            api = new Retrofit.Builder()
                    .baseUrl(UrlConfig.urlPrefix())
                    //增加返回值为String的支持
                    .addConverterFactory(ScalarsConverterFactory.create())
                    //增加返回值为Gson的支持(以实体类返回)
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    //增加返回值为Observable<T>的支持
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .client(okHttpClient)
                    .build()
                    .create(RetrofitApi.class);
        }
        return api;
    }

}
