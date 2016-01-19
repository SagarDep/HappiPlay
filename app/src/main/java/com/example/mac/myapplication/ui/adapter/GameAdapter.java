package com.example.mac.myapplication.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.mac.myapplication.R;
import com.example.mac.myapplication.model.AppSimpleModel;

import java.util.List;

/**
 * Created by yons on 15/10/21.
 */
public class GameAdapter extends RecyclerView.Adapter<GameItemHolder>{

    private Context mContext;
    private LayoutInflater mInflater;
    private CardAdapter.OnRecyclerViewClickListener recyclerViewClick;
    private List<AppSimpleModel> mData;

    public GameAdapter(Context mContext, List<AppSimpleModel> data,CardAdapter.OnRecyclerViewClickListener recyclerViewClick) {
        this.mContext = mContext;
        this.mData = data;
        this.recyclerViewClick = recyclerViewClick;
        mInflater = LayoutInflater.from(mContext);
    }

    @Override
    public GameItemHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view=mInflater.inflate(R.layout.game_play_item,parent, false);
        GameItemHolder holder = new GameItemHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(GameItemHolder holder,final int position) {

        Glide.with(mContext).load(mData.get(position).getIco()).into(holder.game_icon);
        holder.game_name.setText(mData.get(position).getName());
        holder.game_views.setText(mData.get(position).getViews() + "次下载");

        if (mData.get(position).getHot() == 1) {
            holder.game_state.setVisibility(View.VISIBLE);
            holder.game_state.setImageResource(R.drawable.hot_icon);
        } else if (mData.get(position).getNew() == 1) {
            holder.game_state.setVisibility(View.VISIBLE);
            holder.game_state.setImageResource(R.drawable.new_icon);
        } else {
            holder.game_state.setVisibility(View.GONE);
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
    public ImageView game_state;
    public TextView game_views;
    public TextView game_isDow;

    public GameItemHolder(View itemView) {
        super(itemView);
        game_icon = (ImageView) itemView.findViewById(R.id.game_icon);
        game_name = (TextView) itemView.findViewById(R.id.game_name);
        game_state = (ImageView) itemView.findViewById(R.id.game_state);
        game_views = (TextView) itemView.findViewById(R.id.game_views);
        game_isDow = (TextView) itemView.findViewById(R.id.game_isDow);
    }
}