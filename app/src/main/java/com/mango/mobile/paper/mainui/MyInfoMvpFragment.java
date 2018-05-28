package com.mango.mobile.paper.mainui;


import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.mango.lib_common.db.DbManager;
import com.mango.lib_common.entity.User;
import com.mango.mobile.paper.R;
import com.mango.lib_common.base.BaseMvpFragment;
import com.mango.mobile.paper.modules.login.LoginActivity;
import com.mango.mobile.paper.modules.myinfo.MyInfoActivity;
import com.mango.mobile.paper.modules.setting.SettingActivity;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by DN on 2018/03/12.
 */

public class MyInfoMvpFragment extends BaseMvpFragment {
    //    @BindView(R.id.iv_glide)
//    ImageView ivGlide;
//    @BindView(R.id.btn_lode)
//    Button btnLoad;
    @BindView(R.id.iv_setting)
    ImageView ivSetting;
    @BindView(R.id.btn_login)
    Button btnLogin;
    @BindView(R.id.fl_info)
    FrameLayout flInfo;

    @Override
    public int getLayoutResID() {
        return R.layout.fragment_info;
    }

    @Override
    protected void init() {
        ButterKnife.bind(this, getActivity());
//        btnLoad.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                String url = "https://img.gcall.com/dca5/M00/10/8E/wKhoNlggetaENWylAAAAAAAAAAA457.jpg";
//                String url1 = "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1522404789932&di=98a05729a82eb34b3e197c315b13f083&imgtype=0&src=http%3A%2F%2Fimgsrc.baidu.com%2Fimgad%2Fpic%2Fitem%2Ff703738da9773912d40a27abf2198618377ae2c8.jpg";
//                Helper.showToast("下载");
//                Glide.with(getActivity())
//                        .load(url1)
//                        .into(ivGlide);
//            }
        //       });

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), LoginActivity.class));
            }
        });


    }

    @OnClick({R.id.iv_setting, R.id.fl_info})
    void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_setting:
                startActivity(new Intent(getActivity(), SettingActivity.class));
                break;
            case R.id.fl_info:
                isLogin();
                break;
        }
    }

    /**
     * 判断是否已经登录
     */
    private void isLogin() {
        Observable<List<User>> observable = Observable.create(new ObservableOnSubscribe<List<User>>() {
            @Override
            public void subscribe(ObservableEmitter<List<User>> e) throws Exception {
                // 进行数据库查询  （查询数据库是费时操作，放到其他线程）
                List<User> list = DbManager.getDaoSession(getActivity()).getUserDao().queryBuilder().build().list();
                e.onNext(list);
            }
        });

        Consumer<List<User>> consumer = new Consumer<List<User>>() {
            @Override
            public void accept(final List<User> list) throws Exception {
                if (list.size() == 1) {
                    if (list.get(0).getIsLogin() == 1) {
                        startActivity(new Intent(getActivity(), MyInfoActivity.class));
                    }
                } else {
                    startActivity(new Intent(getActivity(), LoginActivity.class));
                }
            }
        };

        observable.subscribeOn(Schedulers.io())  //被观察者执行的线程
                .observeOn(AndroidSchedulers.mainThread())  //观察者执行的线程
                .subscribe(consumer);


    }

}
