package com.mango.mobile.paper.mainui;


import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.mango.lib_common.base.BaseMvpFragment;
import com.mango.mobile.paper.R;
import com.mango.mobile.paper.modules.projectPager.ProjectPagerContract;
import com.mango.mobile.paper.modules.projectPager.ProjectPagerPresenter;
import com.mango.mobile.paper.modules.projectPager.SearchActivity;
import com.mango.mobile.paper.modules.projectPager.adapter.ProjectPagerAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


/**
 * Created by DN on 2018/03/12.
 */

public class ProjectMvpFragment extends BaseMvpFragment<ProjectPagerPresenter,ProjectPagerContract.Model> implements ProjectPagerContract.View {
    @BindView(R.id.rv_project)
    RecyclerView rvProject;
    @BindView(R.id.iv_message)
    ImageView ivSearch;

    private String TAG = "zhou";
    private ProjectPagerAdapter adapter;
    private List<String> list = new ArrayList<>();

    @Override
    public int getLayoutResID() {
        return R.layout.fragment_search;
    }

    @Override
    protected void init() {
        ButterKnife.bind(this, getActivity());

        list.add("项目1");
        list.add("项目1");
        list.add("项目1");
        list.add("项目1");
        list.add("项目1");
        list.add("项目1");
        list.add("项目1");
        list.add("项目1");

        rvProject.setLayoutManager(new LinearLayoutManager(getActivity()));
        adapter = new ProjectPagerAdapter(getActivity(),list);
        rvProject.setAdapter(adapter);

    }

    @OnClick({R.id.iv_message})
    void onClick(View view){
        switch (view.getId()){
            case R.id.iv_message:
                startActivity(new Intent(getActivity(), SearchActivity.class));
                break;
        }
    }


    @Override
    public void upDate() {

    }
}
