package com.example.mac.myapplication.ui.fragment;


import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.mac.myapplication.R;
import com.example.mac.myapplication.model.PlazaModel;
import com.example.mac.myapplication.model.PlazaSimpleModel;
import com.example.mac.myapplication.ui.adapter.CardAdapter;
import com.example.mac.myapplication.ui.fragment.BaseFragment;
import com.example.mac.myapplication.utils.LogUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;

/**
 * A simple {@link Fragment} subclass.
 */
public class UserRecentFragment extends BaseFragment implements CardAdapter.OnRecyclerViewClickListener {
    @Bind(R.id.recycler_view)
    RecyclerView recyclerView;
    @Bind(R.id.go_top)
    ImageView goTop;
    private List<PlazaSimpleModel> mDataList = new ArrayList<PlazaSimpleModel>();
    private CardAdapter adapter;

    int type = 0;//类别类型 0：最近更新，1：猜你喜欢，2：在线用户，3用户推荐

    public UserRecentFragment(int type) {
        this.type = type;
    }

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
    protected void initData() {
        super.initData();
        getNetClient().getPlazaList(1,20,type);

    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_recycler_withtop;
    }

    private void initRecyclerView() {

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



    @Override
    protected void onHttpSuccess(String url, Object obj) {
        super.onHttpSuccess(url, obj);
        PlazaModel model = (PlazaModel)obj;
        mDataList.clear();
        mDataList.addAll(model.getSnsFind());
        adapter.notifyDataSetChanged();
    }

    @Override
    protected void onHttpError(int result, String url, Object obj) {
        super.onHttpError(result, url, obj);
    }
}
