package com.android.songhang.voteprogressview;

import android.graphics.Rect;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.FrameLayout;
import com.vote.VotePresenter;
import com.vote.VoteProgressView;
import com.vote.VoteViewAction;

public class MainActivity extends AppCompatActivity implements VoteViewAction {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        VotePresenter votePresenter = new VotePresenter(this, this);
        ((FrameLayout) findViewById(R.id.content)).addView(votePresenter.getExpandView());
        votePresenter.show("");
    }

    @Override
    public void share() {

    }

    public class SpaceItemDecoration extends RecyclerView.ItemDecoration {

        private int space;

        public SpaceItemDecoration(int space) {
            this.space = space;
        }

        @Override
        public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {

            if (parent.getChildLayoutPosition(view) != 0)
                outRect.top = space;
        }
    }

    private class ViewHodler extends RecyclerView.ViewHolder {

        private VoteProgressView voteProgressView;

        public ViewHodler(View itemView) {
            super(itemView);
            voteProgressView = (VoteProgressView) itemView;
        }
    }
}
