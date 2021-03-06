package com.vote;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Rect;
import android.support.annotation.NonNull;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.android.songhang.voteprogressview.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by songhang on 16/4/25.
 * 点播和支持投票Presenter
 */
public class VotePresenter implements View.OnClickListener{
    private static final int GRID_SPAN_COUNT = 2;
    private String vid;
    private VoteViewAction viewAction;
    private Context context;
    private RecyclerView recyclerView;
    private TextView headerView;
    private View footerView;
    private TextView footerCountView;
    public VotePresenter(@NonNull Context context, @NonNull VoteViewAction viewAction) {
        if (viewAction == null) {
            throw new RuntimeException("VoteViewAction must not null");
        }
        if (context == null) {
            throw new RuntimeException("context must not null");
        }
        this.context = context;
        this.viewAction = viewAction;
    }
    /**
     * 得到投票展开内容视图
     * @return
     */
    public View getExpandView() {
        if (recyclerView == null) {
            recyclerView = (RecyclerView) LayoutInflater.from(context).inflate(R.layout.vote_content_layout, null);
            headerView = (TextView) LayoutInflater.from(context).inflate(R.layout.vote_content_header_layout, null);
            footerView = LayoutInflater.from(context).inflate(R.layout.vote_content_footer_layout, null);

            headerView.setLayoutParams(new RecyclerView.LayoutParams(RecyclerView.LayoutParams.MATCH_PARENT, RecyclerView.LayoutParams.WRAP_CONTENT));
            footerView.setLayoutParams(new RecyclerView.LayoutParams(RecyclerView.LayoutParams.MATCH_PARENT, RecyclerView.LayoutParams.WRAP_CONTENT));

            footerCountView = (TextView) footerView.findViewById(R.id.people_count);
            //share click
            footerView.findViewById(R.id.share).setOnClickListener(this);
        }
        return recyclerView;
    }

    /**
     * 得到投票悬浮引导视图
     * @return
     */
    public View getFloatingView() {
        return null;
    }

    public void show(String vid, boolean b) {
        this.vid = vid;
        setVoteAdapter(b);
    }

    private void setVoteAdapter(boolean isGrid) {
        if (recyclerView == null) {
            return;
        }
        VoteAdapter voteAdapter = new VoteAdapter(isGrid, getTestData());
        RecyclerView.LayoutManager layoutManager;
        if (isGrid) {
            layoutManager = new GridLayoutManager(context, GRID_SPAN_COUNT);
        } else {
            layoutManager = new LinearLayoutManager(context);
        }
        recyclerView.setLayoutManager(layoutManager);
        //set header and footer view
        SmartRecyclerAdapter smartRecyclerAdapter = new SmartRecyclerAdapter(voteAdapter, layoutManager);
        smartRecyclerAdapter.setHeaderView(headerView);
        smartRecyclerAdapter.setFooterView(footerView);

        recyclerView.setAdapter(smartRecyclerAdapter);
    }

    private List<VoteBean> getTestData() {
        List<VoteBean> list = new ArrayList<>();
        VoteBean voteBean1 = new VoteBean("张学友", 87, Color.RED);
        list.add(voteBean1);
        VoteBean voteBean2 = new VoteBean("刘德华", 90, Color.GRAY);
        list.add(voteBean2);
        VoteBean voteBean3 = new VoteBean("成龙", 80, Color.BLUE);
        list.add(voteBean3);
        VoteBean voteBean4 = new VoteBean("周润发", 90, Color.MAGENTA);
        list.add(voteBean4);
        VoteBean voteBean5 = new VoteBean("郭富城", 80, Color.GREEN);
        list.add(voteBean5);
        VoteBean voteBean6 = new VoteBean("周星驰", 60, Color.YELLOW);
        list.add(voteBean6);
        return list;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.share:
                viewAction.share();
                break;
        }
    }
}
