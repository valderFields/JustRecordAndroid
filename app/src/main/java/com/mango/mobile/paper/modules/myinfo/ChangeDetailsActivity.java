package com.mango.mobile.paper.modules.myinfo;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import com.mango.lib_common.base.BaseActivity;
import com.mango.lib_common.utils.Helper;
import com.mango.lib_common.viewBySelf.EditTextCustomTF;
import com.mango.lib_common.viewBySelf.TextViewCustomTF;
import com.mango.lib_common.viewBySelf.TopBar;
import com.mango.mobile.paper.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ChangeDetailsActivity extends BaseActivity {

    @BindView(R.id.topbar)
    TopBar topBar;
    @BindView(R.id.et_nick)
    EditTextCustomTF etNick;
    @BindView(R.id.tv_confirm)
    TextViewCustomTF tvConfirm;

    @BindView(R.id.et_ps)
    EditTextCustomTF etPassword;
    @BindView(R.id.et_phone)
    EditTextCustomTF etPhone;
    @BindView(R.id.et_code)
    EditTextCustomTF etCode;

    @BindView(R.id.ll_change_name)
    LinearLayout llNick;
    @BindView(R.id.ll_phone)
    LinearLayout llPhone;

    private int type;
    private String nick;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_details);
        ButterKnife.bind(this);

        init();
    }

    private void init() {

        type = getIntent().getIntExtra("type", 0);

        topBar.setTitle("账户安全");
        switch (type) {
            case MyInfoActivity.TYPE_NICK:
                llNick.setVisibility(View.VISIBLE);
                nick = getIntent().getStringExtra("nick");
                topBar.setTitle("修改用户名");
                etNick.setHint(nick);
                break;
            case MyInfoActivity.TYPE_PHONE:
                llPhone.setVisibility(View.VISIBLE);
                topBar.setTitle("修改手机号");
                break;

            case MyInfoActivity.TYPE_EMAIL:
                llNick.setVisibility(View.VISIBLE);
                nick = getIntent().getStringExtra("email");
                topBar.setTitle("修改邮箱");
                etNick.setHint(nick);
                break;
        }


        topBar.setOnTopClickListener(new TopBar.OnTopClickListener() {
            @Override
            public void onLeftClick() {
                finish();
            }

            @Override
            public void onRightClick() {

            }
        });
    }


    @OnClick({R.id.tv_confirm})
    void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_confirm:
                confirm();
                break;
        }
    }

    private void confirm() {
        Intent result = new Intent();
        switch (type) {
            case MyInfoActivity.TYPE_NICK:
                result.putExtra("nick",etNick.getText().toString().trim());
                setResult(RESULT_OK,result);
                Helper.showToast("用户名修改成功");
                break;
            case MyInfoActivity.TYPE_PHONE:
                checkPhone();
                result.putExtra("phone",etPhone.getText().toString().trim());
                break;
            case MyInfoActivity.TYPE_EMAIL:
                result.putExtra("email",etNick.getText().toString().trim());
                setResult(RESULT_OK,result);
                Helper.showToast("用邮箱成功");
                break;
        }
        finish();
    }

    private void checkPhone(){
        if (etPassword.getText().toString().trim().equals("")){
            Helper.showToast("登录密码不能为空");
            return;
        }

        if (etPhone.getText().toString().trim().equals("")){
            Helper.showToast("新手机号不能为空");
            return;
        }

        if (etCode.getText().toString().trim().equals("")){
            Helper.showToast("验证码不能为空");
            return;
        }

        //TODO   换手机号请求
    }
}
