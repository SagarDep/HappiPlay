package com.example.mac.myapplication.ui;


import android.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.alibaba.fastjson.JSON;
import com.example.mac.myapplication.R;
import com.example.mac.myapplication.model.MyPhotoSimpleModel;
import com.example.mac.myapplication.ui.adapter.CardAdapter;
import com.example.mac.myapplication.ui.adapter.MyPhotoAdapter;
import com.example.mac.myapplication.ui.fragment.BaseFragment;
import com.example.mac.myapplication.ui.fragment.MeFragment;
import com.example.mac.myapplication.utils.LogUtils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;


/**
 * A simple {@link Fragment} subclass.
 */
public class PagerFragmentAll extends BaseFragment implements CardAdapter.OnRecyclerViewClickListener {

    @Bind(R.id.recycler_view)
    RecyclerView recyclerView;

    private List<MyPhotoSimpleModel> mDataList = new ArrayList<>();
    private MyPhotoAdapter adapter;

    public PagerFragmentAll() {}

    @Override
    protected void initViews() {
        adapter = new MyPhotoAdapter(getContext(),mDataList,this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 2));
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_pager_all;
    }

    @Override
    protected void initData() {
        super.initData();
    }

    public void refreshData(List<MyPhotoSimpleModel> photos) {
        this.mDataList.clear();
        this.mDataList.addAll(photos);

        LogUtils.e("----", this.mDataList.toString());

        adapter.notifyDataSetChanged();
    }


    @Override
    public void onItemClick(View view, int position) {

    }

    @Override
    public void onItemLongClick(View view, int position) {

    }
}
