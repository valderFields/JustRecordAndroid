package com.mango.mobile.paper.modules.mainPager.message;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.mango.lib_common.base.BaseActivity;
import com.mango.lib_common.viewBySelf.TopBar;
import com.mango.mobile.paper.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MessageActivity extends BaseActivity {

    @BindView(R.id.topbar)
    TopBar topBar;
    @BindView(R.id.rv_message)
    RecyclerView recyclerView;

    private List<String> list = new ArrayList<>();
    private MessageAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message);
        ButterKnife.bind(this);

        initView();
    }

    private void initView() {

        list.add("系统消息系统消息系统消息系统消息系统消息系统消息");
        list.add("系统消息系统消息系统消息系统消息系统消息系统消息");
        list.add("系统消息系统消息系统消息系统消息系统消息系统消息");
        list.add("系统消息系统消息系统消息系统消息系统消息系统消息");

        topBar.setTitle("消息");
        topBar.setOnTopClickListener(new TopBar.OnTopClickListener() {
            @Override
            public void onLeftClick() {
                finish();
            }

            @Override
            public void onRightClick() {

            }
        });

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new MessageAdapter(this,list);
        recyclerView.setAdapter(adapter);
    }


}
