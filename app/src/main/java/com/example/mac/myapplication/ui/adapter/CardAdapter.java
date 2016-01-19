package com.example.mac.myapplication.ui.adapter;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.Resource;
import com.example.mac.myapplication.R;
import com.example.mac.myapplication.model.PlazaSimpleModel;

import java.util.List;

/**
 * Created by yons on 15/9/24.
 */
public class CardAdapter extends RecyclerView.Adapter<CardViewHolder> {

    private Context mContext;
    private LayoutInflater mInflater;
    private List<PlazaSimpleModel> mData;
    private OnRecyclerViewClickListener recyclerViewClick;

    public interface OnRecyclerViewClickListener {
        void onItemClick(View view, int position);

        void onItemLongClick(View view, int position);
    }

    public CardAdapter(Context mContext, List<PlazaSimpleModel> mData, OnRecyclerViewClickListener onRecyclerViewClickListener) {
        this.mContext = mContext;
        this.mInflater = LayoutInflater.from(mContext);
        this.mData = mData;
        this.recyclerViewClick = onRecyclerViewClickListener;
    }

    @Override
    public CardViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.nearby_card, parent, false);
        CardViewHolder holder = new CardViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(CardViewHolder holder, final int position) {
        holder.user_name.setText(mData.get(position).getNick());
        holder.user_city.setText(mData.get(position).getArea());
        holder.user_age.setText(mData.get(position).getAge());
        holder.user_photo_num.setText(mData.get(position).getPhotos());
        holder.like_amount.setText("" + mData.get(position).getLikes());

        Glide.with(mContext).load(mData.get(position).getAvatar()).into(holder.user_head);
        Glide.with(mContext).load(mData.get(position).getPhoto()).fitCenter().into(holder.card_img);

        if (mData.get(position).getSex().equals("M")) {
            holder.user_age_layout.setBackgroundColor(mContext.getResources().getColor(R.color.blue_light));
            holder.age_img.setImageResource(R.drawable.sex_male);
        } else {
            holder.user_age_layout.setBackgroundColor(mContext.getResources().getColor(R.color.pink_light));
            holder.age_img.setImageResource(R.drawable.sex_female);
        }



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
            holder.user_head.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(mContext, "head" + position, Toast.LENGTH_SHORT).show();
                }
            });
            holder.like_it.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(mContext, "test" + position, Toast.LENGTH_SHORT).show();
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
    public TextView like_amount;

    public ImageView user_head;
    public ImageView card_img;
    public ImageView like_it;

    public LinearLayout user_age_layout;
    public ImageView age_img;

    public CardViewHolder(View itemView) {
        super(itemView);
        user_name = (TextView) itemView.findViewById(R.id.user_name);
        user_age = (TextView) itemView.findViewById(R.id.user_age);
        user_city = (TextView) itemView.findViewById(R.id.user_city);
        user_photo_num = (TextView) itemView.findViewById(R.id.photo_num);
        card_img = (ImageView) itemView.findViewById(R.id.card_img);
        user_head = (ImageView) itemView.findViewById(R.id.user_head);
        like_it = (ImageView) itemView.findViewById(R.id.like_it);
        like_amount = (TextView) itemView.findViewById(R.id.like_amount);
        user_age_layout = (LinearLayout) itemView.findViewById(R.id.user_age_layout);
        age_img = (ImageView) itemView.findViewById(R.id.age_img);
    }
}