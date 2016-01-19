package com.example.mac.myapplication.ui;


import android.content.DialogInterface;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.mac.myapplication.R;
import com.example.mac.myapplication.helper.FragmentHelper;
import com.example.mac.myapplication.ui.adapter.CardAdapter;
import com.example.mac.myapplication.ui.adapter.MessageItemAdapter;
import com.example.mac.myapplication.ui.fragment.BaseFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class MessageFragment extends BaseFragment implements CardAdapter.OnRecyclerViewClickListener, View.OnClickListener {
    @Bind(R.id.back)
    ImageView back;
    @Bind(R.id.add)
    ImageView add;
    @Bind(R.id.recycler_view)
    RecyclerView recyclerView;
    private List<String> mDataList;
    private MessageItemAdapter adapter;
    private Fragment chatFragment;


    protected void initViews() {
        initRecyclerView();
        back.setOnClickListener(this);
        add.setOnClickListener(this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_message;
    }

    @Override
    protected void AlwaysInit() {
        FragmentHelper.hideTab();
//        adapter.notifyDataSetChanged();

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
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    @Override
    public void onItemClick(View view, int position) {
        TextView messageNum= (TextView) view.findViewById(R.id.message_num);
        messageNum.setVisibility(View.GONE);
        if (chatFragment==null){
            chatFragment = new ChatFragment();
        }
        FragmentHelper.replaceFragment(R.id.content,chatFragment,"chat");

    }

    @Override
    public void onItemLongClick(View view, final int position) {
        final String[] items = {"标记未读", "删除消息"};
        new android.app.AlertDialog.Builder(getContext())
                .setItems(items, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if (which == 1) {
                            mDataList.remove(position);
                            adapter.notifyItemRemoved(position);
                            adapter.notifyItemRangeChanged(position, mDataList.size());
                        }
                    }
                }).show();

    }

    @Override
    public void onClick(View v) {
        //super.onClick(v);
        switch (v.getId()) {

        }
    }
}
