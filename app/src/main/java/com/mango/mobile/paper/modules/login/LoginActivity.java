package com.mango.mobile.paper.modules.login;


import android.app.Fragment;
import android.os.Bundle;

import com.mango.base.BaseActivity;
import com.mango.viewBySelf.TopBar;
import com.mango.mobile.paper.R;

import butterknife.ButterKnife;

public class LoginActivity extends BaseActivity {

    public static final int TYPE_LOFIN = 0;
    public static final int TYPE_REGISTER = 1;

    private TopBar topBar;
    public LoginFragment fgLogin;
    public RegisterFragment fgRegister;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);

        initView();
        initEvent();
    }

    private void initView() {
        topBar = findViewById(R.id.top_bar);
        topBar.setTitle("登录");
        fgLogin = new LoginFragment();
        fgRegister = new RegisterFragment();


        fgLogin = new LoginFragment();
        showFragment(fgLogin,TYPE_LOFIN);
    }

    private void initEvent() {
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

    public void showFragment(Fragment fragment,int type) {
        getFragmentManager().beginTransaction().replace(R.id.fl_container, fragment).commit();
        getFragmentManager().beginTransaction().show(fragment);

        switch (type){
            case TYPE_LOFIN:
                topBar.setTitle("登录");
                break;
            case TYPE_REGISTER:
                topBar.setTitle("注册");
                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        fgLogin = null;
        fgRegister = null;
    }
}
