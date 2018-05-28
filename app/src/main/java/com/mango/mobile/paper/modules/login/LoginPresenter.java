package com.mango.mobile.paper.modules.login;


import com.mango.lib_common.entity.User;
import com.mango.lib_common.retrofitHttp.BaseCallModel;
import com.mango.lib_common.retrofitHttp.RetrofitHttp;
import com.mango.lib_common.utils.Helper;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Administrator on 2018/4/27 0027.
 */

public class LoginPresenter extends LoginContract.Presenter {
    private static final String TAG = "LoginPresenter--";
    @Override
    public void login(String username, String password ) {
        RetrofitHttp.createApiInstance().Login(username,password)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<BaseCallModel<User>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(BaseCallModel<User> value) {
                        Helper.showToast(value.getMsg());
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
