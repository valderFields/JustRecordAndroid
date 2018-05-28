package com.mango.mobile.paper.modules.myinfo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.RelativeLayout;

import com.mango.base.BaseActivity;
import com.mango.viewBySelf.TopBar;
import com.mango.mobile.paper.R;
import com.mango.mobile.paper.modules.myinfo.adapter.CardAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CardActivity extends BaseActivity {

    @BindView(R.id.topbar)
    TopBar topBar;
    @BindView(R.id.rv_card)
    RecyclerView rvCard;

    private CardAdapter adapter;
    private List<String> list = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_card);
        ButterKnife.bind(this);

        initView();
    }

    private void initView() {
        topBar.setTitle("我的银行卡");
        topBar.setOnTopClickListener(new TopBar.OnTopClickListener() {
            @Override
            public void onLeftClick() {
                finish();
            }

            @Override
            public void onRightClick() {

            }
        });

        list.add("建设银行");
        list.add("工商银行");
        list.add("农业银行");
        list.add("招商银行");

        rvCard.setLayoutManager( new LinearLayoutManager(this));
        adapter = new CardAdapter( this,list);
        adapter.addFooterView(setFooter());
        rvCard.setAdapter(adapter);

    }

    private View setFooter(){
        View footer = View.inflate(this,R.layout.card_footer,null);
        RelativeLayout rlAdd = footer.findViewById(R.id.rl_add);
        rlAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(CardActivity.this,AddCardActivity.class));
            }
        });
        return footer;
    }
}
