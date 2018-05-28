package com.mango.mobile.paper.modules.setting;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.mango.base.BaseActivity;
import com.mango.viewBySelf.TopBar;
import com.mango.mobile.paper.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SecurityActivity extends BaseActivity {

    @BindView(R.id.topbar)
    TopBar topBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_security);
        ButterKnife.bind(this);

        init();
    }

    private void init(){
        topBar.setTitle("账户安全");
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

    @OnClick({R.id.ib_change_ps})
    void onClick(View view){
        switch (view.getId()){
            case R.id.ib_change_ps:
                startActivity(new Intent(this,ChangePasswordActivity.class));
                break;
        }
    }
}
