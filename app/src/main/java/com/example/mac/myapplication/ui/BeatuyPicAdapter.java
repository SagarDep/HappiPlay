package com.example.mac.myapplication.ui;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.mac.myapplication.R;

import java.util.List;

/**
 * Created by yons on 15/12/4.
 */
public class BeatuyPicAdapter extends RecyclerView.Adapter<BeautyViewHolder> {

    private Context mContext;
    private LayoutInflater mInflater;
    private List<Integer> mData;
    private CardAdapter.OnRecyclerViewClickListener recyclerViewClick;

    public BeatuyPicAdapter(Context mContext, List<Integer> mData, CardAdapter.OnRecyclerViewClickListener onRecyclerViewClickListener) {
        this.mContext = mContext;
        this.mInflater = LayoutInflater.from(mContext);
        this.mData = mData;
        this.recyclerViewClick = onRecyclerViewClickListener;
    }

    @Override
    public BeautyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.grid_item, parent, false);
        BeautyViewHolder holder = new BeautyViewHolder(view);
//        holder.setIsRecyclable(true);
        return holder;
    }

    @Override
    public void onBindViewHolder(BeautyViewHolder holder, final int position) {

        ViewGroup.LayoutParams params = holder.grid_image.getLayoutParams();
        params.height = 500;
        holder.grid_image.setLayoutParams(params);
        Glide.with(mContext).load(mData.get(position)).centerCrop().into(holder.grid_image);

        if (recyclerViewClick != null) {
            holder.grid_image.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    recyclerViewClick.onItemClick(v, position);
                }
            });
            holder.grid_image.setOnLongClickListener(new View.OnLongClickListener() {
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

class BeautyViewHolder extends RecyclerView.ViewHolder {

    public ImageView grid_image;

    public BeautyViewHolder(View itemView) {
        super(itemView);
        grid_image = (ImageView) itemView.findViewById(R.id.grid_image);
    }
}