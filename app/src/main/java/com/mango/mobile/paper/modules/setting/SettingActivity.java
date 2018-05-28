package com.mango.mobile.paper.modules.setting;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.view.View;
import android.widget.ImageView;

import com.mango.base.BaseActivity;
import com.mango.viewBySelf.ItemBar;
import com.mango.viewBySelf.TopBar;
import com.mango.mobile.paper.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SettingActivity extends BaseActivity {

    @BindView(R.id.topbar)
    TopBar topBar;
    @BindView(R.id.ib_message)
    ItemBar ibMessage;

    private boolean isOpen = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        ButterKnife.bind(this);

        init();
    }

    private void init(){
        topBar.setTitle("设置");
        topBar.setOnTopClickListener(new TopBar.OnTopClickListener() {
            @Override
            public void onLeftClick() {
                finish();
            }

            @Override
            public void onRightClick() {

            }
        });

        ibMessage.setOnItemBarClickListener(new ItemBar.OnItemBarClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onRightImageClick(ImageView ivRightImg) {
                if (isOpen){
                    ivRightImg.setBackground(getDrawable(R.drawable.message_close));
                    isOpen = false;
                }else {
                    ivRightImg.setBackground(getDrawable(R.drawable.message_open));
                    isOpen = true;
                }
            }
        });
    }

    @OnClick({R.id.ib_security,R.id.ib_message,R.id.ib_feedback,R.id.ib_question,R.id.ib_about,R.id.ib_contact,R.id.tv_logout})
    void onClick(View view){
        switch (view.getId()){
            case R.id.ib_security:
                startActivity(new Intent(this,SecurityActivity.class));
                break;
            case R.id.ib_message:

                break;
            case R.id.ib_feedback:
               startActivity(new Intent(this,FeedBackActivity.class));
                break;
            case R.id.ib_about:
                finish();
                break;
            case R.id.ib_contact  :
                finish();
                break;
            case R.id.tv_logout:
                finish();
                break;
        }
    }


}
