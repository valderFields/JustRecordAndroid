package com.mango.base;


import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mango.base.mvp.BaseModel;
import com.mango.base.mvp.BasePresenter;
import com.mango.base.mvp.BaseView;
import com.mango.base.mvp.TUtil;


/**
 * Created by DN on 2018/03/12.
 */

public abstract class BaseMvpFragment<T extends BasePresenter, E extends BaseModel> extends Fragment {
    public T mPresenter;
    public E mModel;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate( getLayoutResID(), null);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mPresenter = TUtil.getT(this, 0);
        mModel = TUtil.getT(this, 1);
        if (this instanceof BaseView) mPresenter.setVM(this, mModel);

        init();
    }

    /**
     * 获得Layout文件id
     *
     * @return
     */
    protected abstract int getLayoutResID();


    protected abstract void init();
}