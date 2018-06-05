package com.mango.mobile.paper.mainui;

import android.app.Dialog;
import android.app.Fragment;
import android.graphics.Bitmap;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.mango.mobile.paper.modules.TimeLinePager.addRecord.AddRecordDialog;
import com.mango.viewBySelf.TextViewCustomTF;
import com.mango.mobile.paper.R;
import com.umeng.message.PushAgent;

import java.io.ByteArrayOutputStream;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class MainActivity extends AppCompatActivity {

    @BindView(R.id.ll_home)
    LinearLayout llHome;
    @BindView(R.id.iv_home)
    ImageView ivHome;
    @BindView(R.id.tv_home)
    TextViewCustomTF tvHome;

    @BindView(R.id.ll_project)
    LinearLayout llProject;
    @BindView(R.id.iv_project)
    ImageView ivProject;
    @BindView(R.id.tv_project)
    TextViewCustomTF tvProject;

    @BindView(R.id.ll_message)
    LinearLayout llMessage;
    @BindView(R.id.iv_message)
    ImageView ivMessage;
    @BindView(R.id.tv_mes)
    TextViewCustomTF tvMessage;

    @BindView(R.id.ll_add)
    LinearLayout llAdd;

    @BindView(R.id.ll_info)
    LinearLayout llInfo;
    @BindView(R.id.iv_info)
    ImageView ivInfo;
    @BindView(R.id.tv_info)
    TextViewCustomTF tvInfo;


    @BindView(R.id.fl_main_container)
    FrameLayout vpMain;


    private MainPagerMvpFragment mainPagerMvpFragment;  //第一个fragment
    private ProjectMvpFragment projectMvpFragment;   //第二个fragment
    private TimeLineMvpFragment timeLineMvpFragment; //时光轴
    private MyInfoMvpFragment myInfoMvpFragment;  // //第三个fragment

    private int currentPager = 2;
    private byte[] bitmapByte;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();
        ButterKnife.bind(this);
        init();
    }

    private void init() {
        PushAgent.getInstance(this).onAppStart();
        mainPagerMvpFragment = new MainPagerMvpFragment();
        projectMvpFragment = new ProjectMvpFragment();
        timeLineMvpFragment = new TimeLineMvpFragment();
        myInfoMvpFragment = new MyInfoMvpFragment();

        showFragment(timeLineMvpFragment);
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        cutScreen();
    }

    private void cutScreen() {
        View bgView = getWindow().getDecorView();
        bgView.setDrawingCacheEnabled(true);
        bgView.buildDrawingCache(true);
        /**
         * 获取当前窗口快照，相当于截屏
         */
        Bitmap bmp = bgView.getDrawingCache();
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bmp.compress(Bitmap.CompressFormat.PNG, 100, baos);
        bitmapByte = baos.toByteArray();
    }

    public void showFragment(Fragment fragment) {
        getFragmentManager().beginTransaction().replace(R.id.fl_main_container, fragment).commit();
        getFragmentManager().beginTransaction().show(fragment);
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @OnClick({R.id.ll_home, R.id.ll_project, R.id.ll_add, R.id.ll_message, R.id.ll_info})
    void onClick(View view) {
        switch (view.getId()) {
            case R.id.ll_home:
                currentPager = 0;
                showFragment(mainPagerMvpFragment);
                setBannerColor(0);
                break;
            case R.id.ll_project:
                currentPager = 1;
                showFragment(projectMvpFragment);
                setBannerColor(1);
                break;
            case R.id.ll_add:
                if (currentPager == 2) {
                    addRecord();
                } else {
                    showFragment(timeLineMvpFragment);
                    setBannerColor(2);
                }
                currentPager = 2;
                break;
            case R.id.ll_message:
                currentPager = 3;
                setBannerColor(3);
                break;
            case R.id.ll_info:
                currentPager = 4;
                showFragment(myInfoMvpFragment);
                setBannerColor(4);
                break;
            default:
                break;
        }

    }

    private void addRecord() {
        Dialog dialog = new AddRecordDialog(this);
        dialog.show();
        Log.d("zhou",System.currentTimeMillis()+"");
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    private void setBannerColor(int num) {
        switch (num) {
            case 0:
                ivHome.setBackground(getResources().getDrawable(R.drawable.home_1));
                ivProject.setBackground(getResources().getDrawable(R.drawable.project_2));
                // ivMessage.setBackground(getResources().getDrawable(R.drawable.message_2));
                ivInfo.setBackground(getResources().getDrawable(R.drawable.info_2));

                tvHome.setTextColor(getResources().getColor(R.color.banner_text_red));
                tvProject.setTextColor(getResources().getColor(R.color.banner_text_grey));
                // tvMessage.setTextColor(getResources().getColor(R.color.banner_text_grey));
                tvInfo.setTextColor(getResources().getColor(R.color.banner_text_grey));
                break;
            case 1:
                ivHome.setBackground(getResources().getDrawable(R.drawable.home_2));
                ivProject.setBackground(getResources().getDrawable(R.drawable.project_1));
                //  ivMessage.setBackground(getResources().getDrawable(R.drawable.message_2));
                ivInfo.setBackground(getResources().getDrawable(R.drawable.info_2));

                tvHome.setTextColor(getResources().getColor(R.color.banner_text_grey));
                tvProject.setTextColor(getResources().getColor(R.color.banner_text_red));
                //  tvMessage.setTextColor(getResources().getColor(R.color.banner_text_grey));
                tvInfo.setTextColor(getResources().getColor(R.color.banner_text_grey));
                break;
            case 2:
                ivHome.setBackground(getResources().getDrawable(R.drawable.home_2));
                ivProject.setBackground(getResources().getDrawable(R.drawable.project_2));
                //  ivMessage.setBackground(getResources().getDrawable(R.drawable.message_1));
                ivInfo.setBackground(getResources().getDrawable(R.drawable.info_2));

                tvHome.setTextColor(getResources().getColor(R.color.banner_text_grey));
                tvProject.setTextColor(getResources().getColor(R.color.banner_text_grey));
                //   tvMessage.setTextColor(getResources().getColor(R.color.banner_text_red));
                tvInfo.setTextColor(getResources().getColor(R.color.banner_text_grey));
                break;
            case 3:
                ivHome.setBackground(getResources().getDrawable(R.drawable.home_2));
                ivProject.setBackground(getResources().getDrawable(R.drawable.project_2));
                // ivMessage.setBackground(getResources().getDrawable(R.drawable.message_2));
                ivInfo.setBackground(getResources().getDrawable(R.drawable.info_1));

                tvHome.setTextColor(getResources().getColor(R.color.banner_text_grey));
                tvProject.setTextColor(getResources().getColor(R.color.banner_text_grey));
                //   tvMessage.setTextColor(getResources().getColor(R.color.banner_text_grey));
                tvInfo.setTextColor(getResources().getColor(R.color.banner_text_red));
                break;
            case 4:
                ivHome.setBackground(getResources().getDrawable(R.drawable.home_2));
                ivProject.setBackground(getResources().getDrawable(R.drawable.project_2));
                // ivMessage.setBackground(getResources().getDrawable(R.drawable.message_2));
                ivInfo.setBackground(getResources().getDrawable(R.drawable.info_1));

                tvHome.setTextColor(getResources().getColor(R.color.banner_text_grey));
                tvProject.setTextColor(getResources().getColor(R.color.banner_text_grey));
                //   tvMessage.setTextColor(getResources().getColor(R.color.banner_text_grey));
                tvInfo.setTextColor(getResources().getColor(R.color.banner_text_red));
                break;

        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mainPagerMvpFragment = null;
        projectMvpFragment = null;
        timeLineMvpFragment = null;
        myInfoMvpFragment = null;
    }


}
