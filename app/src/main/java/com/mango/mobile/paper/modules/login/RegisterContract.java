package com.mango.mobile.paper.modules.login;

import com.mango.lib_common.base.mvp.BaseModel;
import com.mango.lib_common.base.mvp.BasePresenter;
import com.mango.lib_common.base.mvp.BaseView;

/**
 * Created by Administrator on 2018/4/27 0027.
 */

public interface RegisterContract {
    interface View extends BaseView {
        void registerSuccess();

        void showMsg(String msg);

        void upDataView();
    }

    interface Model extends BaseModel {
        boolean register(String username, String password, String passcode);
    }

    abstract class Presenter extends BasePresenter<RegisterContract.Model, RegisterContract.View> {

        @Override
        public void onStart() {

        }

        public abstract void register(String phone,String passWord,String strand);

        public abstract void getCode(String phone);
    }
}
