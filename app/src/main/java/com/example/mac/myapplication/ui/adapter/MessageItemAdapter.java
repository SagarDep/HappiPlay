package com.example.mac.myapplication.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.mac.myapplication.R;

import java.util.List;
import java.util.Random;

/**
 * Created by yons on 15/11/30.
 */
public class MessageItemAdapter extends RecyclerView.Adapter<MessageViewHolder> {

    private Context mContext;
    private LayoutInflater mInflater;
    private List<String> mData;
    private CardAdapter.OnRecyclerViewClickListener recyclerViewClick;


    public MessageItemAdapter(Context mContext, List<String> mData, CardAdapter.OnRecyclerViewClickListener recyclerViewClick) {
        this.mContext = mContext;
        this.mInflater = LayoutInflater.from(mContext);
        this.mData = mData;
        this.recyclerViewClick = recyclerViewClick;
    }

    @Override
    public MessageViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.message_item, parent, false);
        MessageViewHolder holder = new MessageViewHolder(view);
//        holder.setIsRecyclable(true);
        return holder;
    }

    @Override
    public void onBindViewHolder(MessageViewHolder holder, final int position) {

        holder.message_num.setText(String.format("%d", position));

        Random random=new Random();
        if (random.nextBoolean()){
            holder.message_num.setVisibility(View.VISIBLE);
        }else {
            holder.message_num.setVisibility(View.GONE);
        }
        holder.message_content.setText(String.format("message content: %s", mData.get(position)));

        if (recyclerViewClick != null) {
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    recyclerViewClick.onItemClick(v, position);
                }
            });
            holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    recyclerViewClick.onItemLongClick(v, position);
                    return true;
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return mData.size();

    }
}

class MessageViewHolder extends RecyclerView.ViewHolder {

    public TextView message_from;
    public TextView message_content;
    public TextView message_time;
    public TextView message_num;
    public ImageView message_user_head;

    public MessageViewHolder(View itemView) {
        super(itemView);
        message_from = (TextView) itemView.findViewById(R.id.message_from);
        message_content = (TextView) itemView.findViewById(R.id.message_content);
        message_time = (TextView) itemView.findViewById(R.id.message_time);
        message_num = (TextView) itemView.findViewById(R.id.message_num);
        message_user_head = (ImageView) itemView.findViewById(R.id.message_user_head);
    }
}