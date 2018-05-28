package com.mango.mobile.paper.modules.myinfo;

import android.os.Bundle;
import android.view.View;

import com.mango.base.BaseActivity;
import com.mango.viewBySelf.EditTextCustomTF;
import com.mango.viewBySelf.TextViewCustomTF;
import com.mango.viewBySelf.TopBar;
import com.mango.mobile.paper.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class IdentityActivity extends BaseActivity {

    @BindView(R.id.topbar)
    TopBar topBar;
    @BindView(R.id.et_person_name)
    EditTextCustomTF etName;
    @BindView(R.id.et_person_num)
    EditTextCustomTF etPersonNum;
    @BindView(R.id.tv_confirm)
    TextViewCustomTF tvConfirm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_identity);

        ButterKnife.bind(this);
        init();
    }

    private void init(){
        topBar.setTitle("实名认证");
        topBar.setOnTopClickListener(new TopBar.OnTopClickListener() {
            @Override
            public void onLeftClick() {
                finish();
            }

            @Override
            public void onRightClick() {

            }
        });

        tvConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });


    }
}
