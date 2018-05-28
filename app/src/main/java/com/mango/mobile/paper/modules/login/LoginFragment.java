package com.mango.mobile.paper.modules.login;

import android.view.View;

import com.mango.lib_common.base.BaseApplication;
import com.mango.lib_common.base.BaseMvpFragment;
import com.mango.lib_common.utils.Helper;
import com.mango.lib_common.utils.chacheUtil.CacheElementKey;
import com.mango.lib_common.utils.chacheUtil.CacheUtility;
import com.mango.lib_common.viewBySelf.EditTextCustomTF;
import com.mango.lib_common.viewBySelf.TextViewCustomTF;
import com.mango.mobile.paper.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Administrator on 2018/4/27 0027.
 */

public class LoginFragment extends BaseMvpFragment<LoginPresenter, LoginModel> implements LoginContract.View {

    @BindView(R.id.et_phone)
    EditTextCustomTF etPhone;
    @BindView(R.id.et_ps)
    EditTextCustomTF etPs;
    @BindView(R.id.tv_login)
    TextViewCustomTF tvLogin;
    @BindView(R.id.tv_register)
    TextViewCustomTF tvRegister;
    @BindView(R.id.tv_forget)
    TextViewCustomTF tvForget;

    @Override
    protected int getLayoutResID() {
        return R.layout.fragment_login;
    }

    @Override
    protected void init() {
        ButterKnife.bind(this, getActivity());
    }

    @Override
    public void loginSuccess() {

    }

    @Override
    public void showMsg(String msg) {

    }

    @Override
    public void upDataView() {
        getActivity().finish();
    }

    @OnClick({R.id.tv_login,R.id.tv_register,R.id.tv_forget})
    void onClick(View view){
        switch (view.getId()){
            case R.id.tv_login:
                check();
                break;
            case R.id.tv_register:
                ((LoginActivity) getActivity()).showFragment( ((LoginActivity) getActivity()).fgRegister,LoginActivity.TYPE_REGISTER);
                break;
            case R.id.tv_forget:
                break;
        }
    }

    private void check(){

        String account = etPhone.getText().toString().trim();
        String password = etPs.getText().toString().trim();
        CacheUtility cacheUtility = new CacheUtility(BaseApplication.getContext(), CacheUtility.USER_MESSAGE);
        String token = cacheUtility.readStringCache(CacheElementKey.ACCESS_TOKEN);

        if (account.equals("")){
            Helper.showToast("手机号不能为空");
        }

        if (etPs.getText().toString().trim().equals("")){
            Helper.showToast("");
        }

        if (!Helper.isMobile(etPhone.getText().toString())) {
            Helper.showToast("手机号格式错误");
            return;
        }

        mPresenter.login(account,password);

    }


}
