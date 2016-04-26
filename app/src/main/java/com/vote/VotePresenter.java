package com.vote;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;

import com.android.songhang.voteprogressview.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by songhang on 16/4/25.
 * 点播和支持投票Presenter
 */
public class VotePresenter {
    private String vid;
    private VoteViewAction viewAction;
    private Context context;
    private RecyclerView recyclerView;
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

    public void show(String vid) {
        this.vid = vid;
    }

    private List<VoteBean> getTestData() {
        List<VoteBean> list = new ArrayList<>();
        VoteBean voteBean1 = new VoteBean("张学友", 14);
        list.add(voteBean1);
        VoteBean voteBean2 = new VoteBean("刘德华", 10);
        list.add(voteBean2);
        VoteBean voteBean3 = new VoteBean("成龙", 20);
        list.add(voteBean3);
        VoteBean voteBean4 = new VoteBean("周润发", 30);
        list.add(voteBean4);
        VoteBean voteBean5 = new VoteBean("郭富城", 50);
        list.add(voteBean5);
        VoteBean voteBean6 = new VoteBean("周星驰", 60);
        list.add(voteBean6);
        return list;
    }

}
