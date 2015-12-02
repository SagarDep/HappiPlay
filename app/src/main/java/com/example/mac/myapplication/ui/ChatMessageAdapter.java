package com.example.mac.myapplication.ui;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.mac.myapplication.R;
import com.example.mac.myapplication.bean.ChatMessage;

import java.util.List;
import java.util.Random;

/**
 * Created by yons on 15/12/2.
 */
public class ChatMessageAdapter extends RecyclerView.Adapter<ChatViewHolder> {

    public static final int CHAT_MESSAGE_TYPE_RECEIVED = 0;
    public static final int CHAT_MESSAGE_TYPE_SENT = 1;

    private Context mContext;
    private LayoutInflater mInflater;
    private List<ChatMessage> chatMessages;
    private CardAdapter.OnRecyclerViewClickListener recyclerViewClick;

    public ChatMessageAdapter(Context mContext, List<ChatMessage> chatMessages, CardAdapter.OnRecyclerViewClickListener onRecyclerViewClickListener) {
        this.mContext = mContext;
        this.mInflater = LayoutInflater.from(mContext);
        this.chatMessages = chatMessages;
        this.recyclerViewClick = onRecyclerViewClickListener;
    }

    @Override
    public int getItemViewType(int position) {
        ChatMessage message = chatMessages.get(position);
        ChatMessage.CHAT_VIEW_TYPE type = message.getViewType();
        if (type == ChatMessage.CHAT_VIEW_TYPE.CHAT_RECEVIED) {
            return CHAT_MESSAGE_TYPE_RECEIVED;
        } else if (type == ChatMessage.CHAT_VIEW_TYPE.CHAT_SENT) {
            return CHAT_MESSAGE_TYPE_SENT;
        }
        return 0;
    }


    @Override
    public ChatViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view=null;
        switch (viewType) {
            case CHAT_MESSAGE_TYPE_RECEIVED:
                view = mInflater.inflate(R.layout.chat_item_received, parent,false);
                break;
            case CHAT_MESSAGE_TYPE_SENT:
                view = mInflater.inflate(R.layout.chat_item_sent, parent,false);
                break;
        }
        ChatViewHolder holder = new ChatViewHolder(view);

        return holder;
    }

    @Override
    public void onBindViewHolder(ChatViewHolder holder, int position) {
        ChatMessage message = chatMessages.get(position);

        String[] timeStamps = message.getTimeStamp().split(":");
        int hour = Integer.valueOf(timeStamps[0]);
        StringBuilder timeStamp=new StringBuilder();
        if (hour > 17) {
            timeStamp.append("晚上");
        } else if (hour >= 0 && hour <= 6) {
            timeStamp.append("凌晨");
        } else if (hour > 11 && hour <= 17) {
            timeStamp.append("下午");
        } else {
            timeStamp.append("上午");
        }
        timeStamp.append(message.getTimeStamp());

        holder.chat_timestamp.setText(timeStamp.toString());
        holder.chat_content.setText(message.getChatContent());
        if (new Random().nextBoolean()){
            holder.chat_timestamp.setVisibility(View.VISIBLE);
        }else {
            holder.chat_timestamp.setVisibility(View.GONE);
        }

    }

    @Override
    public int getItemCount() {
        return chatMessages.size();
    }
}

class ChatViewHolder extends RecyclerView.ViewHolder {

    public TextView chat_content;
    public TextView chat_timestamp;

    public ImageView chat_head;

    public ChatViewHolder(View itemView) {
        super(itemView);
        chat_content = (TextView) itemView.findViewById(R.id.chat_content);
        chat_timestamp = (TextView) itemView.findViewById(R.id.chat_timestamp);
        chat_head = (ImageView) itemView.findViewById(R.id.chat_head);
    }
}