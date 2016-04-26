package com.android.songhang.voteprogressview;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.FrameLayout;
import com.vote.VotePresenter;
import com.vote.VoteViewAction;

public class MainActivity extends AppCompatActivity implements VoteViewAction {
    VotePresenter votePresenter;
    boolean isGrid;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        votePresenter = new VotePresenter(this, this);
        ((FrameLayout) findViewById(R.id.content)).addView(votePresenter.getExpandView());
        votePresenter.show("", isGrid = false);
    }

    @Override
    public void share() {

    }

    public void switchType(View view) {
        votePresenter.show("", isGrid = !isGrid);
    }
}
