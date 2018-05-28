package com.mango.mobile.paper.mainui;


import android.content.Intent;

import com.mango.db.DbManager;
import com.mango.entity.User;
import com.mango.mobile.paper.R;
import com.mango.base.BaseMvpFragment;
import com.mango.mobile.paper.modules.login.LoginActivity;
import com.mango.mobile.paper.modules.myinfo.MyInfoActivity;
import com.mango.utils.Helper;
import com.mango.utils.RSAUtil;


import java.util.List;

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

//    @BindView(R.id.btn_login)
//    Button btnLogin;


    @Override
    public int getLayoutResID() {
        return R.layout.fragment_info;
    }

    @Override
    protected void init() {

        try {
            byte[] s = RSAUtil.encryptByPublicKey("我是加密数据", RSAUtil.PUBLIC_KEY);
            String str = RSAUtil.bytes2HexString(s);// 16进制字符串
            Helper.showToast(str);
        }catch (Exception e){

        }
        //ButterKnife.bind(this, getActivity());
//
//        btnLogin.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                startActivity(new Intent(getActivity(), LoginActivity.class));
//            }
//        });


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
