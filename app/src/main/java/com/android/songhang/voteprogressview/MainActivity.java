package com.android.songhang.voteprogressview;

import android.graphics.Color;
import android.graphics.Rect;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import com.vote.VoteProgressView;

import java.util.Random;

public class MainActivity extends AppCompatActivity {
    int[] color = {Color.RED, Color.BLACK, Color.BLUE, Color.GREEN, Color.YELLOW, Color.GRAY, Color.MAGENTA, Color.LTGRAY, Color.LTGRAY};
    Random random = new Random(9);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(com.android.songhang.voteprogressview.R.layout.activity_main);

        RecyclerView recyclerView = (RecyclerView) findViewById(com.android.songhang.voteprogressview.R.id.recycler_view);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.addItemDecoration(new SpaceItemDecoration(5));
        recyclerView.setAdapter(new RecyclerView.Adapter<ViewHodler>() {
            @Override
            public ViewHodler onCreateViewHolder(ViewGroup parent, int viewType) {
                View view = new VoteProgressView(MainActivity.this);
                view.setLayoutParams(new RecyclerView.LayoutParams(RecyclerView.LayoutParams.MATCH_PARENT, 80));
                Log.i("song", "****** 创建新的itemView ********");
                return new ViewHodler(view);
            }

            @Override
            public void onBindViewHolder(ViewHodler holder, int position) {
                Log.e("song", "positon: " + position);
                holder.voteProgressView.setProgressColor(color[random.nextInt(9)]);
                holder.voteProgressView.setCurProgress(random.nextInt(100));
                holder.voteProgressView.fireAnim();
            }

            @Override
            public int getItemCount() {
                return 100;
            }
        });

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
