package com.mango.mobile.paper.mainui;


import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.mango.lib_common.base.BaseMvpFragment;
import com.mango.lib_common.utils.GlideImageLoader;
import com.mango.mobile.paper.R;
import com.mango.mobile.paper.modules.mainPager.ProjectDetailsActivity;
import com.mango.mobile.paper.modules.mainPager.adapter.MainFooterAdapter;
import com.mango.mobile.paper.modules.mainPager.adapter.MainPagerAdapter;
import com.mango.mobile.paper.modules.mainPager.MainPagerContract;
import com.mango.mobile.paper.modules.mainPager.MainPagerModel;
import com.mango.mobile.paper.modules.mainPager.MainPagerPresenter;
import com.mango.mobile.paper.modules.mainPager.message.MessageActivity;
import com.sunfusheng.marqueeview.MarqueeView;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by DN on 2018/03/12.
 */

public class MainPagerMvpFragment extends BaseMvpFragment<MainPagerPresenter, MainPagerModel> implements MainPagerContract.View {

    @BindView(R.id.rv_main)
    RecyclerView rvMain;

    @BindView(R.id.iv_message)
    ImageView ivMessage;

    private Banner banner;
    private MarqueeView marqueeView;
    private ImageView ivNoticeMore;

    private RecyclerView rvFooter;


    //轮播图内容
    private List<String> imageTitle;
    private List<String> imageArray;

    private List<Integer> ima;


    private MainPagerAdapter mainAdapter;
    private MainFooterAdapter footerAdapter;
    private List<String> list = new ArrayList<>();

    @Override
    protected int getLayoutResID() {
        return R.layout.fragment_gan_huo;
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

        rvMain.setLayoutManager(new LinearLayoutManager(getActivity()));
        mainAdapter = new MainPagerAdapter(getActivity(), list);
        mainAdapter.addHeaderView(getHeader());
        mainAdapter.addFooterView(getFooter());
        rvMain.setAdapter(mainAdapter);
        mainAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                startActivity(new Intent(getActivity(), ProjectDetailsActivity.class));
            }
        });

        initHeader();

        ivMessage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), MessageActivity.class));
            }
        });


    }


    private View getHeader() {
        View header = LayoutInflater.from(getActivity()).inflate(R.layout.mainpager_head, null);
        banner = header.findViewById(R.id.banner);
        marqueeView = header.findViewById(R.id.marqueeView);
        ivNoticeMore = header.findViewById(R.id.iv_notice_more);

        return header;
    }

    private void initHeader() {

        //设置图片加载集合
        imageArray = new ArrayList<>();

        imageArray.add("http://img3.imgtn.bdimg.com/it/u=2758743658,581437775&fm=15&gp=0.jpg");
        imageArray.add("http://img3.imgtn.bdimg.com/it/u=2105877023,3759180926&fm=15&gp=0.jpg");
        imageArray.add("http://img2.imgtn.bdimg.com/it/u=1876814088,3589919070&fm=15&gp=0.jpg");
        imageArray.add("http://img2.imgtn.bdimg.com/it/u=1876814088,3589919070&fm=15&gp=0.jpg");


        ima = new ArrayList<>();
        ima.add(R.drawable.banner1);
        ima.add(R.drawable.banner2);
        ima.add(R.drawable.banner3);
        ima.add(R.drawable.banner4);
        //设置图片标题集合
        imageTitle = new ArrayList<>();
        imageTitle.add("aaaaaaaaa");
        imageTitle.add("bbbbbbbbb");
        imageTitle.add("ccccccccc");
        imageTitle.add("ddddddddd");

        //设置banner样式
        banner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR_TITLE_INSIDE);
        //设置图片加载器
        banner.setImageLoader(new GlideImageLoader());
        //设置图片集合
        banner.setImages(ima);
        //设置banner动画效果
        banner.setBannerAnimation(Transformer.Default);
        //设置标题集合（当banner样式有显示title时）
        banner.setBannerTitles(imageTitle);
        //设置轮播时间
        banner.setDelayTime(1500);
        //设置指示器位置（当banner模式中有指示器时）
        banner.setIndicatorGravity(BannerConfig.CENTER);
        //banner设置方法全部调用完毕时最后调用
        banner.start();

        marqueeView.startWithList(imageTitle);

    }

    public View getFooter() {
        View footer = LayoutInflater.from(getActivity()).inflate(R.layout.mainpager_foot, null);
        rvFooter = footer.findViewById(R.id.rv_footer);

        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        rvFooter.setLayoutManager(layoutManager);
        footerAdapter = new MainFooterAdapter(getActivity(), list);
        rvFooter.setAdapter(footerAdapter);

        return footer;
    }


    @Override
    public void upDate() {

    }


}
