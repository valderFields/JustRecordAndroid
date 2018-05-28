package com.mango.mobile.paper.modules.projectPager;

import android.os.Bundle;

import com.mango.base.BaseActivity;
import com.mango.utils.Helper;
import com.mango.viewBySelf.SearchView;
import com.mango.mobile.paper.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import co.lujun.androidtagview.TagContainerLayout;

public class SearchActivity extends BaseActivity {
    @BindView(R.id.tag_common)
    TagContainerLayout mTagCommon;
    @BindView(R.id.tag_history)
    TagContainerLayout mTagHistory;
    @BindView(R.id.search)
    SearchView searchView;

    private List<String> tags = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        ButterKnife.bind(this);
        initView();
    }

    private void initView() {

        searchView.setOnSearchEventListener(new SearchView.OnSearchEventListener() {
            @Override
            public void onLeftClick() {
                finish();
                Helper.HideKeyboard(searchView);
            }

            @Override
            public void onWordChange(String content) {

            }
        });
        tags.add("料理");
        tags.add("料理");
        tags.add("料理");
        tags.add("料理");
        tags.add("料理");
        tags.add("料理");
        tags.add("料理");
        tags.add("料理");

        mTagCommon.setTags(tags);
        mTagHistory.setTags(tags);


    }
}
