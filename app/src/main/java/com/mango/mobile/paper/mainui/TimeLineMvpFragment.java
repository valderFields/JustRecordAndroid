package com.mango.mobile.paper.mainui;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.LinearLayout;

import com.mango.lib_common.base.BaseMvpFragment;
import com.mango.mobile.paper.R;
import com.mango.mobile.paper.modules.TimeLinePager.TimeLineContract;
import com.mango.mobile.paper.modules.TimeLinePager.TimeLineModel;
import com.mango.mobile.paper.modules.TimeLinePager.TimeLinePresenter;
import com.mango.mobile.paper.modules.TimeLinePager.adapter.TimeLineAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2018/5/23 0023.
 */

public class TimeLineMvpFragment extends BaseMvpFragment<TimeLinePresenter,TimeLineModel> implements TimeLineContract.View {

    @BindView(R.id.rc_line)
    RecyclerView rcLine;

    private List<String> list = new ArrayList<>();
    private TimeLineAdapter adapter;
    @Override
    protected int getLayoutResID() {
        return R.layout.fragment_line;
    }

    @Override
    protected void init() {
        ButterKnife.bind(this,getActivity());

        list.add("这是第一条");
        list.add("这是第一条");
        list.add("这是第一条");
        list.add("这是第一条");
        list.add("这是第一条");

        rcLine.setLayoutManager(new LinearLayoutManager(getActivity()));
        adapter = new TimeLineAdapter(getActivity(),list);
        rcLine.setAdapter(adapter);


    }

    @Override
    public void upDate() {

    }

}
