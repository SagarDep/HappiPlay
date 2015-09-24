package com.example.mac.myapplication.ui;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.mac.myapplication.R;

import java.util.List;

/**
 * Created by yons on 15/9/24.
 */
public class CardAdapter extends RecyclerView.Adapter<CardViewHolder> {

    private Context mContext;
    private LayoutInflater mInflater;
    private List<String> mData;
    private OnRecyclerViewClickListener listener;

    public interface OnRecyclerViewClickListener {
        void onItemClick(View view, int position);

        void onItemLongClick(View view, int position);
    }

    public CardAdapter(Context mContext, List<String> mData, OnRecyclerViewClickListener listener) {
        this.mContext = mContext;
        this.mInflater = LayoutInflater.from(mContext);
        this.mData = mData;
        this.listener = listener;
    }

    @Override
    public CardViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.nearby_card, parent, false);
        CardViewHolder holder = new CardViewHolder(view);
        holder.setIsRecyclable(true);
        return holder;
    }

    @Override
    public void onBindViewHolder(CardViewHolder holder, final int position) {
        holder.user_name.setText(mData.get(position));

        int resId = mContext.getResources().getIdentifier("img_" + position, "drawable", mContext.getPackageName());
        if (resId != 0) {
            holder.card_img.setImageResource(resId);
            holder.user_head.setImageResource(resId);
        }

        if (listener != null) {
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onItemClick(v, position);
                }
            });
            holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    listener.onItemLongClick(v, position);
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

class CardViewHolder extends RecyclerView.ViewHolder {

    public TextView user_name;
    public TextView user_age;
    public TextView user_city;
    public TextView user_photo_num;

    public ImageView user_head;
    public ImageView card_img;

    public CardViewHolder(View itemView) {
        super(itemView);
        user_name = (TextView) itemView.findViewById(R.id.user_name);
        user_age = (TextView) itemView.findViewById(R.id.user_age);
        user_city = (TextView) itemView.findViewById(R.id.user_city);
        user_photo_num = (TextView) itemView.findViewById(R.id.photo_num);
        card_img = (ImageView) itemView.findViewById(R.id.card_img);
        user_head = (ImageView) itemView.findViewById(R.id.user_head);
    }
}