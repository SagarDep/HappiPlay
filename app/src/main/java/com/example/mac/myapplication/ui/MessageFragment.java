package com.example.mac.myapplication.ui;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.mac.myapplication.R;
import com.example.mac.myapplication.helper.FragmentHelper;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class MessageFragment extends BaseFragment implements CardAdapter.OnRecyclerViewClickListener {
    @Bind(R.id.back)
    ImageView back;
    @Bind(R.id.add)
    ImageView add;
    @Bind(R.id.recycler_view)
    RecyclerView recyclerView;
    private List<String> mDataList;
    private MessageItemAdapter adapter;


    protected void initViews() {
        initRecyclerView();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_message;
    }

    @Override
    protected void AlwaysInit() {
        FragmentHelper.hideTab();

    }

    private void initRecyclerView() {
        mDataList = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            mDataList.add("" + i);
        }
        adapter = new MessageItemAdapter(getContext(), mDataList, this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    @Override
    public void onItemClick(View view, int position) {
        Toast.makeText(getContext(), position + "message clicked!", Toast.LENGTH_SHORT).show();

    }

    @Override
    public void onItemLongClick(View view, int position) {
        Toast.makeText(getContext(), position + "message long clicked!", Toast.LENGTH_SHORT).show();

    }
}
