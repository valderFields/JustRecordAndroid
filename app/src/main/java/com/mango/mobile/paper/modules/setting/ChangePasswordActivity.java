package com.mango.mobile.paper.modules.setting;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.mango.lib_common.base.BaseActivity;
import com.mango.lib_common.utils.Helper;
import com.mango.lib_common.viewBySelf.EditTextCustomTF;
import com.mango.lib_common.viewBySelf.TextViewCustomTF;
import com.mango.lib_common.viewBySelf.TopBar;
import com.mango.mobile.paper.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ChangePasswordActivity extends BaseActivity {

    @BindView(R.id.topbar)
    TopBar topBar;
    @BindView(R.id.et_ps_1)
    EditTextCustomTF etPs1;
    @BindView(R.id.et_ps_2)
    EditTextCustomTF etPs2;
    @BindView(R.id.et_ps_3)
    EditTextCustomTF etPs3;
    @BindView(R.id.rl_1)
    RelativeLayout rl1;
    @BindView(R.id.rl_2)
    RelativeLayout rl2;
    @BindView(R.id.rl_3)
    RelativeLayout rl3;
    @BindView(R.id.iv_eye_1)
    ImageView ivEye_1;
    @BindView(R.id.iv_eye_2)
    ImageView ivEye_2;
    @BindView(R.id.iv_eye_3)
    ImageView ivEye_3;

    @BindView(R.id.tv_rule)
    TextViewCustomTF tvRule;
    @BindView(R.id.tv_confirm)
    TextViewCustomTF tvConfirm;

    private int current = 1; //1:输入原密码  2：输入新密码

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_password);
        ButterKnife.bind(this);

        initView();
    }


    private void initView() {
        topBar.setTitle("修改密码");
        topBar.setOnTopClickListener(new TopBar.OnTopClickListener() {
            @Override
            public void onLeftClick() {
                if (current == 1) {
                    finish();
                } else if (current == 2) {
                    rl1.setVisibility(View.VISIBLE);
                    rl2.setVisibility(View.GONE);
                    rl3.setVisibility(View.GONE);
                    tvRule.setVisibility(View.GONE);
                    current = 1;
                }

            }

            @Override
            public void onRightClick() {

            }
        });
    }

    @OnClick({R.id.tv_confirm,R.id.iv_eye_1,R.id.iv_eye_2,R.id.iv_eye_3})
    void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_confirm:
                check();
                break;
        }
    }


    private void check() {
        if (current == 1) {
            //TODO  老密码正确
            current = 2;
            rl1.setVisibility(View.GONE);
            rl2.setVisibility(View.VISIBLE);
            rl3.setVisibility(View.VISIBLE);
            tvRule.setVisibility(View.VISIBLE);

        } else if (current == 2) {

            if (!etPs2.getText().toString().trim().equals(etPs3.getText().toString().trim())){
                Helper.showToast("两次输入的密码不一致");
                return;
            }

            if (Helper.isLegalPassword(etPs2.getText().toString().trim())){
                finish();
                Helper.showToast("密码修改成功");
            }else {
                Helper.showToast("请设置密码(6-16位数字、字母组合)");
            }

        }
    }
}
