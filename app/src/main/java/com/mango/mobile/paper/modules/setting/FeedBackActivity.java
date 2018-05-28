package com.mango.mobile.paper.modules.setting;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;

import com.mango.base.BaseActivity;
import com.mango.viewBySelf.EditTextCustomTF;
import com.mango.viewBySelf.TextViewCustomTF;
import com.mango.viewBySelf.TopBar;
import com.mango.mobile.paper.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class FeedBackActivity extends BaseActivity {

    @BindView(R.id.topbar)
    TopBar topBar;
    @BindView(R.id.et_feedback)
    EditTextCustomTF etFeedback;
    @BindView(R.id.tv_hint)
    TextViewCustomTF tvHint;
    @BindView(R.id.tv_confirm)
    TextViewCustomTF tvConfirm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feed_back);
        ButterKnife.bind(this);

        init();
    }

    private void init() {
        topBar.setTitle("意见反馈");
        topBar.setOnTopClickListener(new TopBar.OnTopClickListener() {
            @Override
            public void onLeftClick() {
                finish();
            }

            @Override
            public void onRightClick() {

            }
        });

        etFeedback.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                tvHint.setText("反馈内容需大于4个字，您还可以输入" + (500 - s.length()) + "字");
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }
}
