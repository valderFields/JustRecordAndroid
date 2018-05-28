package com.mango.mobile.paper.modules.searchHistory;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.mango.mobile.paper.R;

import java.util.ArrayList;
import java.util.List;


import butterknife.BindView;
import butterknife.ButterKnife;
import co.lujun.androidtagview.TagContainerLayout;

public class SearchActivity extends AppCompatActivity {

    @BindView(R.id.tag_search)
    TagContainerLayout tagContainer;
    @BindView(R.id.rv_history)
    RecyclerView rvHistory;


    private String[] tags = {"南方航空","獐子岛","塞班岛","崇明岛","海南岛","台湾岛","夏威夷岛","中华人民共护国","护照","尼罗岛"};
    private List list = new ArrayList();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_serach);
        ButterKnife.bind(this);

        init();
    }

    private void init() {
        list.add("南方航空");
        list.add("獐子岛");
        list.add("塞班岛");
        list.add("崇明岛");
        list.add("海南岛");
        list.add("台湾岛");
        list.add("夏威夷岛");
        list.add("中华人民共护国");
        list.add("护照");
        list.add("尼罗岛");
        tagContainer.setTags(list);


        rvHistory.setLayoutManager(new LinearLayoutManager(this));
        rvHistory.setAdapter(new SearchHistoryAdapter(list));
    }
}
