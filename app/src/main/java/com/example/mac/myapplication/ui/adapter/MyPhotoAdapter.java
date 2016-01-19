package com.example.mac.myapplication.ui.adapter;


import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.mac.myapplication.R;
import com.example.mac.myapplication.model.MyPhotoSimpleModel;

import java.util.List;

/**
 * Created by happi on 16/1/15.
 */
public class MyPhotoAdapter extends RecyclerView.Adapter<MyPhotoItemHolder> {

    private Context mContext;
    private LayoutInflater mInflater;
    private CardAdapter.OnRecyclerViewClickListener recyclerViewClick;
    private List<MyPhotoSimpleModel> mData;

    public MyPhotoAdapter(Context mContext, List<MyPhotoSimpleModel> data, CardAdapter.OnRecyclerViewClickListener recyclerViewClick) {
        this.mContext = mContext;
        this.mData = data;
        this.recyclerViewClick = recyclerViewClick;
        mInflater = LayoutInflater.from(mContext);
    }

    @Override
    public MyPhotoItemHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.adapter_myphoto, parent, false);
        MyPhotoItemHolder holder = new MyPhotoItemHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(MyPhotoItemHolder holder, final int position) {

        Glide.with(mContext).load(mData.get(position).getSimg()).into(holder.card_img);
        holder.like_amount.setText("" + mData.get(position).getLikes());

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
        return mData.size();
    }

}

class MyPhotoItemHolder extends RecyclerView.ViewHolder {
    public ImageView card_img;
    public ImageView like_it;
    public TextView like_amount;

    public MyPhotoItemHolder(View itemView) {
        super(itemView);
        card_img = (ImageView) itemView.findViewById(R.id.card_img);
        like_it = (ImageView) itemView.findViewById(R.id.like_it);
        like_amount = (TextView) itemView.findViewById(R.id.like_amount);
    }
}