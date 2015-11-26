package com.example.mac.myapplication.ui;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.mac.myapplication.R;

import java.util.List;

/**
 * Created by yons on 15/9/24.
 */
public class CardAdapter extends RecyclerView.Adapter<CardViewHolder> {

    private Context mContext;
    private LayoutInflater mInflater;
    private List<String> mData;
    private OnRecyclerViewClickListener recyclerViewClick;

    public interface OnRecyclerViewClickListener {
        void onItemClick(View view, int position);

        void onItemLongClick(View view, int position);
    }

    public CardAdapter(Context mContext, List<String> mData, OnRecyclerViewClickListener onRecyclerViewClickListener) {
        this.mContext = mContext;
        this.mInflater = LayoutInflater.from(mContext);
        this.mData = mData;
        this.recyclerViewClick = onRecyclerViewClickListener;
    }

    @Override
    public CardViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.nearby_card, parent, false);
        CardViewHolder holder = new CardViewHolder(view);
//        holder.setIsRecyclable(true);
        return holder;
    }

    @Override
    public void onBindViewHolder(CardViewHolder holder, final int position) {
        View view = mInflater.inflate(R.layout.fragment_user_recent, null);
        ImageView goTop = (ImageView) view.findViewById(R.id.go_top);
        goTop.setVisibility(position > 10 ? View.VISIBLE : View.GONE);

        holder.user_name.setText(mData.get(position));

        int resId = mContext.getResources().getIdentifier("img_" + position, "drawable", mContext.getPackageName());
        if (resId != 0) {
//            holder.card_img.setScaleType(ImageView.ScaleType.FIT_CENTER);
            Glide.with(mContext).load(resId).fitCenter().into(holder.card_img);
            Glide.with(mContext).load(resId).into(holder.user_head);
        } else {
            return;
        }

        holder.like_it.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(mContext, "test" + position, Toast.LENGTH_SHORT).show();
            }
        });
        if (recyclerViewClick != null) {
            holder.card_img.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    recyclerViewClick.onItemClick(v, position);
                }
            });
            holder.card_img.setOnLongClickListener(new View.OnLongClickListener() {
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
        //实际上这里的size就已经确定了item的数量。如果图片不够，好像会自动填充之前的图片
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
    public ImageView like_it;

    public CardViewHolder(View itemView) {
        super(itemView);
        user_name = (TextView) itemView.findViewById(R.id.user_name);
        user_age = (TextView) itemView.findViewById(R.id.user_age);
        user_city = (TextView) itemView.findViewById(R.id.user_city);
        user_photo_num = (TextView) itemView.findViewById(R.id.photo_num);
        card_img = (ImageView) itemView.findViewById(R.id.card_img);
        user_head = (ImageView) itemView.findViewById(R.id.user_head);
        like_it = (ImageView) itemView.findViewById(R.id.like_it);
    }
}