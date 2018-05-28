package com.mango.mobile.paper.modules.login;

import android.os.CountDownTimer;
import android.view.View;
import android.widget.ImageView;

import com.mango.lib_common.base.BaseMvpFragment;
import com.mango.lib_common.utils.Helper;
import com.mango.lib_common.viewBySelf.EditTextCustomTF;
import com.mango.lib_common.viewBySelf.TextViewCustomTF;
import com.mango.mobile.paper.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Administrator on 2018/4/27 0027.
 */

public class RegisterFragment extends BaseMvpFragment<RegisterPresenter, RegisterModel> implements RegisterContract.View {


    @BindView(R.id.et_phone)
    EditTextCustomTF etPhone;
    @BindView(R.id.tv_code)
    TextViewCustomTF tvCode;
    @BindView(R.id.btn_code)
    TextViewCustomTF btnCode;
    @BindView(R.id.et_code)
    EditTextCustomTF etCode;
    @BindView(R.id.et_set_ps)
    EditTextCustomTF etSetPs;
    @BindView(R.id.et_re_set_ps)
    EditTextCustomTF etReSetPs;
    @BindView(R.id.tv_register)
    TextViewCustomTF tvRegister;
    @BindView(R.id.iv_choose)
    ImageView ivChoose;
    @BindView(R.id.iv_no_choose)
    ImageView ivNoChoose;


    private String phone = "";
    private TimeCount timeCount;

    @Override
    protected int getLayoutResID() {
        return R.layout.fragment_register;
    }

    @Override
    protected void init() {
        ButterKnife.bind(this, getActivity());

    }


    @Override
    public void registerSuccess() {

    }

    @Override
    public void showMsg(String msg) {

    }

    @OnClick({R.id.tv_register, R.id.btn_code, R.id.iv_choose, R.id.iv_no_choose})
    void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_register:
                register();
                break;
            case R.id.btn_code:
                getCode();
                break;
            case R.id.iv_choose:
                ivChoose.setVisibility(View.INVISIBLE);
                ivNoChoose.setVisibility(View.VISIBLE);
                break;
            case R.id.iv_no_choose:
                ivChoose.setVisibility(View.VISIBLE);
                ivNoChoose.setVisibility(View.INVISIBLE);
                break;

        }

    }

    private void register() {
        phone = etPhone.getText().toString().trim();
        String code = etCode.getText().toString().trim();
        String ps1 = etSetPs.getText().toString().trim();
        String ps2 = etReSetPs.getText().toString().trim();

        if (code.equals("")) {
            Helper.showToast("验证码不能为空");
            return;
        }

        if (code.equals("")) {
            Helper.showToast("密码不能为空");
            return;
        }

        if (code.equals("")) {
            Helper.showToast("确认密码不能为空");
            return;
        }

        if (!ps1.equals(ps2)) {
            Helper.showToast("两次输入密码不一致");
            return;
        }

        if (!Helper.isLegalPassword(ps2)){
            Helper.showToast("请设置密码(6-16位数字、字母组合)");
        }

        if (ivChoose.getVisibility() == View.GONE) {
            Helper.showToast("请阅读并勾选注册协议");
            return;
        }

        mPresenter.register(phone,ps1, code);
    }

    private void getCode() {
        if (btnCode.getText().toString().contains("s")){
            Helper.showToast("请"+btnCode.getText().toString()+"后重试");
            return;
        }
        phone = etPhone.getText().toString().trim();
        if (!phone.equals("")) {
            if (Helper.isMobile(phone)) {
                mPresenter.getCode(phone);
                timeCount = new TimeCount(60000, 1000);
                timeCount.start();
            } else {
                Helper.showToast("手机号格式错误");
                return;
            }
        } else {
            Helper.showToast("手机号不能为空");
            return;
        }

    }

    @Override
    public void upDataView() {
        timeCount.cancel();
        btnCode.setText("获取验证码");
    }

    class TimeCount extends CountDownTimer {
        private int count;

        public TimeCount(long millisInFuture, long countDownInterval) {
            super(millisInFuture, countDownInterval);
        }

        @Override
        public void onTick(long l) {
            count = (int) (l / 1000);
            btnCode.setText(count + "s");
        }

        @Override
        public void onFinish() {
            count = 0;
            btnCode.setText("获取验证码");
        }
    }

}
