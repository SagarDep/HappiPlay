package com.example.mac.myapplication.ui.fragment;


import android.app.Fragment;
import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.example.mac.myapplication.R;
import com.example.mac.myapplication.model.AppSimpleModel;
import com.example.mac.myapplication.ui.adapter.CardAdapter;
import com.example.mac.myapplication.ui.adapter.GameAdapter;
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
    private List<AppSimpleModel> mDataList = new ArrayList<>();


    protected void initViews() {
        adapter = new GameAdapter(getContext(), mDataList, this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 3));
        initSegment();

    }

    private void initSegment() {
        segmentGame.setSelectedIndex(0);
        segmentGame.setOnSegmentControlClickListener(new SegmentControl.OnSegmentControlClickListener() {
            @Override
            public void onSegmentControlClick(int index) {
                if (index == 0) {
                    getNetClient().getAppsList(0, 1);
                } else if (index == 1) {
                    getNetClient().getAppsList(1,1);
                }
            }
        });
    }

    @Override
    protected void initData() {
        super.initData();

        getNetClient().getAppsList(0,1);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_game_play;
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


    @Override
    protected void onHttpSuccess(String url, Object obj) {
        super.onHttpSuccess(url, obj);

        mDataList.clear();
        mDataList.addAll((List<AppSimpleModel>)obj);
        adapter.notifyDataSetChanged();

    }

    @Override
    protected void onHttpError(int result, String url, Object obj) {
        super.onHttpError(result, url, obj);
    }
}
