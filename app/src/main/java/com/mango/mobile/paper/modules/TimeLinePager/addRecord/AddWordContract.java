package com.mango.mobile.paper.modules.TimeLinePager.addRecord;

import com.mango.base.mvp.BaseModel;
import com.mango.base.mvp.BasePresenter;
import com.mango.base.mvp.BaseView;
import com.mango.mobile.paper.modules.mainPager.MainPagerContract;

/**
 * Created by Administrator on 2018/5/23 0023.
 */

public interface AddWordContract {
    interface View extends BaseView {
        void upDate();

    }

    interface Model extends BaseModel {

    }

    abstract class Presenter extends BasePresenter<Model,View> {

    }
}
