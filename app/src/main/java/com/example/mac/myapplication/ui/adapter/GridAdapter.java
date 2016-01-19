package com.example.mac.myapplication.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ListAdapter;

import com.example.mac.myapplication.R;

/**
 * Created by yons on 15/9/21.
 */
public class GridAdapter extends BaseAdapter {

    private Context mContext;
    public GridAdapter(Context mContext) {
        this.mContext = mContext;
    }

    @Override
    public int getCount() {
        return imgIds.length;
    }

    @Override
    public Object getItem(int i) {
        return imgIds[i];
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ImageView imageView ;
        if (view==null){
            view=LayoutInflater.from(mContext).inflate(R.layout.grid_item, null);
            imageView= (ImageView) view.findViewById(R.id.grid_image);

            view.setTag(imageView);
//            card_img = new ImageView(mContext);
//            card_img.setLayoutParams(new GridView.LayoutParams(90,90));
//            card_img.setScaleType(ImageView.ScaleType.CENTER_CROP);
//            card_img.setPadding(10,10,10,10);
        }else{
            imageView = (ImageView)view.getTag();
        }
        ViewGroup.LayoutParams params = imageView.getLayoutParams();
        params.height = 95;
        imageView.setLayoutParams(params);
        imageView.setImageResource(imgIds[i]);
        return view;
    }

    private Integer[] imgIds={
            R.drawable.grid_img1,R.drawable.grid_img2,
            R.drawable.grid_img3,R.drawable.grid_img4,
            R.drawable.grid_img5
    };

}
