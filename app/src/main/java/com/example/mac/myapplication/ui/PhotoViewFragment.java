package com.example.mac.myapplication.ui;


import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.MenuCompat;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.mac.myapplication.R;

import java.util.List;

import uk.co.senab.photoview.PhotoView;

/**
 * A simple {@link Fragment} subclass.
 */
public class PhotoViewFragment extends Fragment {

    private ViewPager mViewPager;
    public List<Integer> imgIds;
    private Context mContext;
    private static ActionBar bar;
    private android.support.v7.widget.Toolbar toolbar;

    public PhotoViewFragment() {
        // Required empty public constructor
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        bar=((AppCompatActivity) getActivity()).getSupportActionBar();
        if (bar!=null){
//            bar.hide();
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_photo_view, container, false);
        initViewPager(view);
        return view;
    }

    private void initViewPager(View view) {
//        toolbar = (Toolbar) view.findViewById(R.id.tool_bar);
//        AppCompatActivity activity = (AppCompatActivity)getActivity();
//        activity.setSupportActionBar(toolbar);
//        toolbar.setBackgroundColor(Color.parseColor("#00FFFFFF"));

        mContext = getActivity();
        Bundle userBundle =getArguments();
        imgIds=userBundle.getIntegerArrayList("data");
        int currentPosition=userBundle.getInt("position");
        mViewPager = (ViewPager) view.findViewById(R.id.photo_mode_viewpager);
        mViewPager.setAdapter(new PhotoModePagerAdapter(imgIds, mContext));
        mViewPager.setCurrentItem(currentPosition);
    }
static class PhotoModePagerAdapter extends PagerAdapter{

    private List<Integer> imgIds;
    private Context mContext;

    public PhotoModePagerAdapter(List<Integer> imgIds, Context mContext) {
        this.imgIds = imgIds;
        this.mContext = mContext;
    }

    @Override

    public int getCount() {
        return imgIds.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view ==object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        PhotoView photoView=new PhotoView(mContext);
        photoView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                return false;
            }
        });

        Glide.with(mContext).load(imgIds.get(position)).into(photoView);
        container.addView(photoView);
        return photoView;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View)object);
    }
}

}
