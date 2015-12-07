package com.example.mac.myapplication.ui;


import android.app.Fragment;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.example.mac.myapplication.R;
import com.example.mac.myapplication.custom.DividerGridItemDecoration;
import com.sevenheaven.segmentcontrol.SegmentControl;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class BoxFragment extends BaseFragment implements CardAdapter.OnRecyclerViewClickListener {
    @Bind(R.id.recycler_view)
    RecyclerView recyclerView;
    @Bind(R.id.segment_game)
    SegmentControl segmentGame;
    private Context mContext;
    private GameAdapter adapter;
    private List<String> mDataList;


    private void initSegment() {
        segmentGame.setOnSegmentControlClickListener(new SegmentControl.OnSegmentControlClickListener() {
            @Override
            public void onSegmentControlClick(int index) {
                if (index == 0) {

                } else if (index == 1) {

                }
            }
        });
    }

    protected void initViews() {
        adapter = new GameAdapter(getContext(), mDataList, this);
        recyclerView.setAdapter(adapter);
        recyclerView.addItemDecoration(new DividerGridItemDecoration(getContext()));
        recyclerView.addItemDecoration(new RecyclerView.ItemDecoration() {
            @Override
            public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
                super.getItemOffsets(outRect, view, parent, state);
            }

            @Override
            public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
                super.onDraw(c, parent, state);
            }
        });
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 3));
        initSegment();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_game_play;
    }

    protected void initData() {
        mDataList = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            mDataList.add("" + i);
        }
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    @Override
    public void onItemClick(View view, int position) {
        Toast.makeText(getContext(), position + "clicked!", Toast.LENGTH_SHORT).show();

    }

    @Override
    public void onItemLongClick(View view, int position) {
        Toast.makeText(getContext(), position + "long clicked!", Toast.LENGTH_SHORT).show();
    }
}
