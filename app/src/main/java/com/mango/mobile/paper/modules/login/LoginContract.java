package com.mango.mobile.paper.modules.login;

import com.mango.lib_common.base.mvp.BaseModel;
import com.mango.lib_common.base.mvp.BasePresenter;
import com.mango.lib_common.base.mvp.BaseView;

/**
 * Created by Administrator on 2018/4/27 0027.
 */

public interface LoginContract {
    interface View extends BaseView {
        void loginSuccess();

        void showMsg(String msg);

        void upDataView();
    }

    interface Model extends BaseModel {
        boolean login(String username, String password, String passcode);
    }

    abstract class Presenter extends BasePresenter<Model, View> {

        @Override
        public void onStart() {

        }

        public abstract void login(String username, String password);
    }
}
