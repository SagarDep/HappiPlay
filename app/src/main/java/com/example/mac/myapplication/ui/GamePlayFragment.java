package com.example.mac.myapplication.ui;


import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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
public class GamePlayFragment extends android.support.v4.app.Fragment implements CardAdapter.OnRecyclerViewClickListener {
    @Bind(R.id.recycler_view)
    RecyclerView mRecyclerView;
    @Bind(R.id.segment_game)
    SegmentControl segmentGame;
    private Context mContext;
    private GameAdapter adapter;
    private List<String> mDataList;

    public GamePlayFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mContext = getActivity();
        View view = inflater.inflate(R.layout.fragment_game_play, container, false);
        ButterKnife.bind(this, view);
        initRecyclerView(view);
        initSegment();
        return view;
    }

    private void initSegment() {
        segmentGame.setOnSegmentControlClickListener(new SegmentControl.OnSegmentControlClickListener() {
            @Override
            public void onSegmentControlClick(int index) {
                if (index==0){

                }else if (index ==1){

                }
            }
        });
    }

    private void initRecyclerView(View view) {
        initData();
        adapter = new GameAdapter(mContext, mDataList, this);
        mRecyclerView.setAdapter(adapter);
        mRecyclerView.addItemDecoration(new DividerGridItemDecoration(mContext));
        mRecyclerView.setLayoutManager(new GridLayoutManager(mContext, 3));
    }

    private void initData() {
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
        Toast.makeText(mContext, position + "clicked!", Toast.LENGTH_SHORT).show();

    }

    @Override
    public void onItemLongClick(View view, int position) {
        Toast.makeText(mContext, position + "long clicked!", Toast.LENGTH_SHORT).show();
    }
}
