package com.example.mac.myapplication.ui;


import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;

import com.example.mac.myapplication.R;
import com.example.mac.myapplication.bean.ChatMessage;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import butterknife.Bind;
import butterknife.ButterKnife;


/**
 * A simple {@link Fragment} subclass.
 */
public class ChatFragment extends BaseFragment implements CardAdapter.OnRecyclerViewClickListener {


    @Bind(R.id.back)
    ImageView back;
    @Bind(R.id.add)
    ImageView add;
    @Bind(R.id.recycler_view)
    RecyclerView recyclerView;

    private List<ChatMessage> chatMessages;
    private ChatMessage message;
    private ChatMessageAdapter adapter;


    @Override
    protected void initData() {
        chatMessages = new ArrayList<>();
        for (int i = 0; i < 15; i++) {
            if (i % 2 == 0) {
                message = new ChatMessage("name" + i, "chatContentReceived>>>> ", getTimestamp(), ChatMessage.CHAT_VIEW_TYPE.CHAT_RECEVIED);
            } else {
                message = new ChatMessage("name" + i, "chatSent>>>><<<<<< "+i, getTimestamp(), ChatMessage.CHAT_VIEW_TYPE.CHAT_SENT);
            }
            chatMessages.add(message);
        }
        adapter = new ChatMessageAdapter(getContext(), chatMessages, this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
    }

    @Override
    protected void initViews() {
        back.setOnClickListener(this);
        add.setOnClickListener(this);
        back.setOnClickListener(this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_chat;
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    public static String getTimestamp() {
        String dateFormat = "hh:mm";
        SimpleDateFormat sdf = new SimpleDateFormat(dateFormat, Locale.getDefault());
//            if(hour > 17) {
//                dateFormat = "晚上 hh:mm";
//            } else if(hour >= 0 && hour <= 6) {
//                dateFormat = "凌晨 hh:mm";
//            } else if(hour > 11 && hour <= 17) {
//                dateFormat = "下午 hh:mm";
//            } else {
//                dateFormat = "上午 hh:mm";
//            }
        return sdf.format(new Date());
    }

    @Override
    public void onItemClick(View view, int position) {

    }

    @Override
    public void onItemLongClick(View view, int position) {

    }
}
