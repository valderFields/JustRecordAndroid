package com.mango.base;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.WindowManager;

import com.mango.base.mvp.BaseModel;
import com.mango.base.mvp.BasePresenter;
import com.mango.base.mvp.BaseView;
import com.mango.base.mvp.TUtil;


public abstract class BaseMvpActivity<T extends BasePresenter, E extends BaseModel> extends AppCompatActivity {
    public T mPresenter;
    public E mModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        init();
    }

    protected void init() {


        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
        setContentView(getLayoutResID());
        mPresenter = TUtil.getT(this, 0);
        mModel = TUtil.getT(this, 1);
        if (this instanceof BaseView) mPresenter.setVM(this, mModel);

        initView();


    }

    /**
     * 获得Layout文件id
     *
     * @return
     */
    protected abstract int getLayoutResID();


    protected abstract void initView();


    }

