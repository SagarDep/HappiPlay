package com.example.mac.myapplication.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
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

public class ChatActivity extends AppCompatActivity implements CardAdapter.OnRecyclerViewClickListener {

    @Bind(R.id.recycler_view)
    RecyclerView recyclerView;
    private List<ChatMessage> chatMessages;
    private ChatMessage message;
    private ChatMessageAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_chat);
        ButterKnife.bind(this);
        initData();
    }

    protected void initData() {
        chatMessages = new ArrayList<>();
        for (int i = 0; i < 15; i++) {
            if (i % 2 == 0) {
                message = new ChatMessage("name" + i, "chatContentReceived>>>> ", getTimestamp(), ChatMessage.CHAT_VIEW_TYPE.CHAT_RECEVIED);
            } else {
                message = new ChatMessage("name" + i, "chatSent>>>><<<<<< " + i, getTimestamp(), ChatMessage.CHAT_VIEW_TYPE.CHAT_SENT);
            }
            chatMessages.add(message);
        }
        adapter = new ChatMessageAdapter(this, chatMessages, this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override
    public void onItemClick(View view, int position) {

    }

    @Override
    public void onItemLongClick(View view, int position) {

    }
    public static String getTimestamp() {
        String dateFormat = "hh:mm";
        SimpleDateFormat sdf = new SimpleDateFormat(dateFormat, Locale.getDefault());
        return sdf.format(new Date());
    }
}
