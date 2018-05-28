package com.mango.retrofitHttp;


import com.mango.bean.Token;
import com.mango.entity.User;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by DN on 2018/03/13.
 */

public interface RetrofitApi {
//
//    @GET(UrlConfig.PULL_FEEDLIST_V2)
//    Observable<BaseCallModel<FeedDataList>> PullFeedListRequest(@Query("min_index_feed_id") Long min_index_feed_id, @Query("max_index_feed_id") Long max_index_feed_id, @Query("number") int number);

    @GET(UrlConfig.TEST)
    Observable<String> Test();

    /**
     * 获取验证码
     * @param phone
     * @return
     */
    @GET(UrlConfig.LOGIN_MESSAGE)
    Observable<BaseCallModel<String>> getCode(@Query("phone") String phone);

    /**
     * 注册
     * @param password
     * @param strand
     * @return
     */
    @GET(UrlConfig.LOGIN_REGISTER)
    Observable<BaseCallModel<Token>> register(@Query("phone") String phone,@Query("password") String password, @Query("strand") String strand);

    /**
     * 登陆
     * @param phone
     * @param password
     * @return
     */
    @GET(UrlConfig.LOGIN_INDEX)
    Observable<BaseCallModel<User>> Login(@Query("username") String phone, @Query("password") String password);

}
