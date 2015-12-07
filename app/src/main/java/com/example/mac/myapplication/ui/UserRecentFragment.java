package com.example.mac.myapplication.ui;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.mac.myapplication.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class UserRecentFragment extends BaseFragment implements CardAdapter.OnRecyclerViewClickListener {
    @Bind(R.id.recycler_view)
    RecyclerView recyclerView;
    @Bind(R.id.go_top)
    ImageView goTop;
    private List<String> mDataList;
    private CardAdapter adapter;

    protected void initViews() {
        initRecyclerView();

        goTop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                recyclerView.scrollToPosition(0);
                goTop.setVisibility(View.GONE);
            }
        });
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_recycler_withtop;
    }

    private void initRecyclerView() {
        mDataList = new ArrayList<>();
        for (int i = 0; i < 17; i++) {
            mDataList.add("" + i);
        }
        adapter = new CardAdapter(getContext(), mDataList, this);
        recyclerView.setAdapter(adapter);
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                int position = recyclerView.computeVerticalScrollOffset();
                goTop.setVisibility(position > 2500 ? View.VISIBLE : View.GONE);
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);

            }
        });
        recyclerView.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));
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
