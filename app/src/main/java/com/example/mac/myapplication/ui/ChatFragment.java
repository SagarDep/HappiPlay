package com.example.mac.myapplication.ui;


import android.app.AlertDialog;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.DialogInterface;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.mac.myapplication.R;
import com.example.mac.myapplication.bean.ChatMessage;
import com.example.mac.myapplication.helper.UiHelper;
import com.example.mac.myapplication.ui.adapter.CardAdapter;
import com.example.mac.myapplication.ui.adapter.ChatMessageAdapter;
import com.example.mac.myapplication.ui.fragment.BaseFragment;

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
public class ChatFragment extends BaseFragment implements CardAdapter.OnRecyclerViewClickListener ,View.OnClickListener{


    @Bind(R.id.back)
    ImageView back;
    @Bind(R.id.add)
    ImageView add;
    @Bind(R.id.recycler_view)
    RecyclerView recyclerView;
    @Bind(R.id.chat_emoji)
    ImageView chatEmoji;
    @Bind(R.id.chat_send)
    TextView chatSend;
    @Bind(R.id.chat_to_send)
    EditText chatToSend;

    private List<ChatMessage> messages;
    private ChatMessage message;
    private ChatMessageAdapter adapter;
    private String toSend;


    @Override
    protected void initData() {
        messages = new ArrayList<>();
        for (int i = 0; i < 15; i++) {
            if (i % 2 == 0) {
                message = new ChatMessage("fromUser", "chatReceived>>>", getTimestamp(), ChatMessage.CHAT_VIEW_TYPE.CHAT_RECEVIED);
            } else {
                message = new ChatMessage("meUser", "chatSent>>> " + i, getTimestamp(), ChatMessage.CHAT_VIEW_TYPE.CHAT_SENT);
            }
            messages.add(message);
        }
        adapter = new ChatMessageAdapter(getContext(), messages, this);
        recyclerView.setAdapter(adapter);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        layoutManager.setStackFromEnd(true);
        recyclerView.setLayoutManager(layoutManager);
    }

    @Override
    protected void initViews() {
        back.setOnClickListener(this);
        add.setOnClickListener(this);
        back.setOnClickListener(this);
        chatSend.setOnClickListener(this);
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
    public void onItemLongClick(View view, final int position) {
        final String[] items = {"复制消息", "删除"};
        new AlertDialog.Builder(getContext())
                .setItems(items, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if (which == 0) {
                            String content = messages.get(position).getChatContent();
                            ClipboardManager manager = (ClipboardManager) getActivity().getSystemService(Context.CLIPBOARD_SERVICE);
                            ClipData clipData = ClipData.newPlainText("message", content);
                            manager.setPrimaryClip(clipData);
                            UiHelper.showToast(getContext(), "已复制");

                        } else if (which == 1) {
                            messages.remove(position);
                            adapter.notifyItemRemoved(position);
                            adapter.notifyItemRangeChanged(position, messages.size());
                        }
                    }
                }).show();
    }

    @Override
    public void onClick(View v) {
        //super.onClick(v);
        switch (v.getId()) {
            case R.id.chat_send:
                toSend = chatToSend.getText().toString();
                ChatMessage message = new ChatMessage("myUserName", toSend, getTimestamp(), ChatMessage.CHAT_VIEW_TYPE.CHAT_SENT);
                messages.add(message);
                adapter.notifyDataSetChanged();
                recyclerView.scrollToPosition(messages.size()-1);
                chatToSend.getText().clear();
        }
    }
}
