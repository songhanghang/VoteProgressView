package com.vote;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.android.songhang.voteprogressview.R;

import java.util.List;

/**
 * Created by songhang on 16/4/26.
 */
public class VoteAdapter extends RecyclerView.Adapter<VoteAdapter.VoteViewHolder> {
    private boolean isGrid;
    private List<VoteBean> list;

    public VoteAdapter(boolean isGrid, List<VoteBean> voteBeanList) {
        this.isGrid = isGrid;
        this.list = voteBeanList;
    }

    @Override
    public VoteViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (isGrid) {
            View gridView = LayoutInflater.from(parent.getContext()).inflate(R.layout.vote_gird_item_layout, parent, false);
            return new VoteViewHolder(gridView);
        } else {
            View listView = LayoutInflater.from(parent.getContext()).inflate(R.layout.vote_list_item_layout, parent, false);
            return new VoteViewHolder(listView);
        }
    }

    @Override
    public void onBindViewHolder(VoteViewHolder holder, int position) {
//        VoteBean voteBean = list.get(position);
//        holder.progressView.setProgressColor(voteBean.color);
//        holder.progressView.setProgress(voteBean.progress);
//        holder.progressView.fireAnim();
//        holder.titleView.setText(voteBean.title);
    }

    @Override
    public int getItemCount() {
        return list == null ? 0 : list.size();
    }

    public class VoteViewHolder extends RecyclerView.ViewHolder {
//        private VoteProgressView progressView;
//        private TextView titleView;
//        private TextView labelView;

        public VoteViewHolder(View itemView) {
            super(itemView);
//            progressView = (VoteProgressView) itemView.findViewById(R.id.progress);
//            titleView = (TextView) itemView.findViewById(R.id.title);
//            labelView = (TextView) itemView.findViewById(R.id.label);
        }
    }
}
