package com.mango.mobile.paper.modules.projectPager;

import com.mango.lib_common.base.mvp.BaseModel;
import com.mango.lib_common.base.mvp.BasePresenter;
import com.mango.lib_common.base.mvp.BaseView;

/**
 * Created by Administrator on 2018/4/3 0003.
 */

public interface ProjectPagerContract {
    interface View extends BaseView{
        void upDate();

    }

    interface Model extends BaseModel{

        void upDate();

    }

    abstract class Presenter extends BasePresenter<Model,View>{

    }
}
