package com.example.mac.myapplication.ui;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.mac.myapplication.R;

import java.util.List;

/**
 * Created by yons on 15/10/21.
 */
public class GameAdapter extends RecyclerView.Adapter<GameItemHolder>{

    private Context mContext;
    private LayoutInflater mInflater;
    private CardAdapter.OnRecyclerViewClickListener recyclerViewClick;
    private List<String> mData;

    public GameAdapter(Context mContext, List<String> data,CardAdapter.OnRecyclerViewClickListener recyclerViewClick) {
        this.mContext = mContext;
        this.mData = data;
        this.recyclerViewClick = recyclerViewClick;
        mInflater = LayoutInflater.from(mContext);
    }

    @Override
    public GameItemHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view=mInflater.inflate(R.layout.game_play_item,parent, false);
        GameItemHolder holder = new GameItemHolder(view);
//        holder.setIsRecyclable(false);
        return holder;
    }

    @Override
    public void onBindViewHolder(GameItemHolder holder,final int position) {
//        holder.game_money.setText(mData.get(position));
        int resId = mContext.getResources().getIdentifier("img_" + position, "drawable", mContext.getPackageName());
        if (resId!=0){
            Glide.with(mContext).load(R.drawable.game_icon).into(holder.game_icon);
        }

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
class GameItemHolder extends RecyclerView.ViewHolder{
    public ImageView game_icon;
    public TextView game_name;
    public TextView game_money;

    public GameItemHolder(View itemView) {
        super(itemView);
        game_icon = (ImageView) itemView.findViewById(R.id.game_icon);
        game_name = (TextView) itemView.findViewById(R.id.game_name);
//        game_money = (TextView) itemView.findViewById(R.id.game_money);
    }
}