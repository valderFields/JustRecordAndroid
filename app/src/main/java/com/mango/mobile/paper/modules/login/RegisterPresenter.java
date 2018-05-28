package com.mango.mobile.paper.modules.login;

import com.mango.lib_common.base.BaseApplication;
import com.mango.lib_common.bean.Token;
import com.mango.lib_common.retrofitHttp.BaseCallModel;
import com.mango.lib_common.retrofitHttp.RetrofitHttp;
import com.mango.lib_common.utils.Helper;
import com.mango.lib_common.utils.chacheUtil.CacheElementKey;
import com.mango.lib_common.utils.chacheUtil.CacheUtility;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Administrator on 2018/4/27 0027.
 */

public class RegisterPresenter extends RegisterContract.Presenter {

    private static final String TAG = "RegisterPresenter--";

    @Override
    public void register(String phone,String passWord, String strand) {
        RetrofitHttp.createApiInstance().register(phone,passWord, strand)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<BaseCallModel<Token>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(BaseCallModel<Token> value) {
                        if (value.getSuccess() == 100001){
                            CacheUtility cacheUtility = new CacheUtility(BaseApplication.getContext(), CacheUtility.USER_MESSAGE);
                            cacheUtility.saveCache(CacheElementKey.ACCESS_TOKEN, value.getData().getToken());
                        }
                        Helper.showToast(value.getMsg());
                    }

                    @Override
                    public void onError(Throwable e) {
                        Helper.showToast(TAG + e.getMessage()+"|||"+ e.getLocalizedMessage()  +"|||"+  e.toString());
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    @Override
    public void getCode(String phone) {
        RetrofitHttp.createApiInstance().getCode(phone)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<BaseCallModel<String>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(BaseCallModel<String> value) {
                        if (value.getSuccess() == 100001) {
                            Helper.showToast("验证码已发送，请注意查收");
                        } else {
                            Helper.showToast("获取验证码错误：" +  value.getMsg());
                        }
                        mView.upDataView();
                    }

                    @Override
                    public void onError(Throwable e) {
                        Helper.showToast(TAG + e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
