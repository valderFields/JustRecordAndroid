package com.mango.mobile.paper.modules.mainPager;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.akexorcist.roundcornerprogressbar.RoundCornerProgressBar;
import com.example.zhouwei.library.CustomPopWindow;
import com.mango.lib_common.base.BaseActivity;

import com.mango.lib_common.viewBySelf.TextViewCustomTF;
import com.mango.lib_common.viewBySelf.TopBar;
import com.mango.mobile.paper.R;
import com.mango.mobile.paper.common.utils.ShareUtils;
import com.mango.mobile.paper.modules.mainPager.adapter.ProjectPagerAdapter;
import com.umeng.socialize.UMShareAPI;
import com.umeng.socialize.bean.SHARE_MEDIA;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ProjectDetailsActivity extends BaseActivity {

    @BindView(R.id.topbar)
    TopBar topbar;
    @BindView(R.id.tv_project_title)
    TextViewCustomTF tvProjectTitle;
    @BindView(R.id.iv_project_image)
    ImageView ivProjectImage;
    @BindView(R.id.tv_last_time)
    TextViewCustomTF tvLastTime;
    @BindView(R.id.tv_project_type)
    TextViewCustomTF tvProjectType;
    @BindView(R.id.tv_income_type)
    TextViewCustomTF tvIncomeType;
    @BindView(R.id.tv_project_name)
    TextViewCustomTF tvProjectName;
    @BindView(R.id.progressbar)
    RoundCornerProgressBar progressbar;
    @BindView(R.id.iv_person)
    ImageView ivPerson;
    @BindView(R.id.iv_phone)
    ImageView ivPhone;
    @BindView(R.id.view_pager)
    ViewPager viewPager;
    @BindView(R.id.tab_layout)
    TabLayout tabLayout;
    @BindView(R.id.space1)
    View space1;
    @BindView(R.id.rl_layout)
    RelativeLayout rlLayout;
    @BindView(R.id.rl_project_head)
    RelativeLayout rlShareView;

    private ProjectPagerAdapter adapter;

    //分享弹窗
    private CustomPopWindow popShare;
    private View contentViewShare;
    private FrameLayout flWechat, flMoments, flQQ,flSina,flZone;
    private TextViewCustomTF tvCancel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_project_details);
        ButterKnife.bind(this);

        init();
    }

    private void init() {
        tvProjectTitle.setVisibility(View.GONE);
        space1.setVisibility(View.GONE);
        tvLastTime.setVisibility(View.VISIBLE);

        topbar.setTitle("项目详情");
        topbar.setRightIconVisibility();
        topbar.setRightIcon(getResources().getDrawable(R.drawable.share));
        topbar.setOnTopClickListener(new TopBar.OnTopClickListener() {
            @Override
            public void onLeftClick() {
                finish();
            }

            @Override
            public void onRightClick() {
                share();
            }
        });

        initIndicator();
    }

    private void share(){
        if (contentViewShare == null) {
            contentViewShare = LayoutInflater.from(this).inflate(R.layout.pop_share_menu, null);
            flWechat = contentViewShare.findViewById(R.id.fl_wechat);
            flMoments = contentViewShare.findViewById(R.id.fl_moments);
            flQQ = contentViewShare.findViewById(R.id.fl_qq);
            flSina = contentViewShare.findViewById(R.id.fl_sina);
            flZone = contentViewShare.findViewById(R.id.fl_zone);
            tvCancel = contentViewShare.findViewById(R.id.tv_cancel);
        }

        popShare = new CustomPopWindow.PopupWindowBuilder(this)
                .setView(contentViewShare)
                .size(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)
                .setOutsideTouchable(true)
                .enableBackgroundDark(false)
                .create()
                .showAtLocation(rlLayout, Gravity.BOTTOM, 0, 0);

        flWechat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                share(SHARE_MEDIA.WEIXIN);
            }
        });

        flMoments.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                share(SHARE_MEDIA.WEIXIN_CIRCLE);
            }
        });

        flQQ.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                share(SHARE_MEDIA.QQ);
            }
        });

        flSina.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                share(SHARE_MEDIA.SINA);
            }
        });

        flZone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                share(SHARE_MEDIA.QZONE);
            }
        });

        tvCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popShare.dissmiss();
            }
        });
    }

    private void share(SHARE_MEDIA platform) {
        ShareUtils.shareMixImage(platform, this, rlShareView);
    }

    private void initIndicator() {

        adapter = new ProjectPagerAdapter(getSupportFragmentManager(), this);
        viewPager.setAdapter(adapter);

        tabLayout.setTabMode(TabLayout.MODE_FIXED);
        tabLayout.setSelectedTabIndicatorColor(getResources().getColor(R.color.colorPrimaryDark));
        tabLayout.setTabTextColors(getResources().getColor(R.color.text_black), getResources().getColor(R.color.colorPrimaryDark));
        tabLayout.setupWithViewPager(viewPager);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        UMShareAPI.get(this).onActivityResult(requestCode, resultCode, data);
    }
}
