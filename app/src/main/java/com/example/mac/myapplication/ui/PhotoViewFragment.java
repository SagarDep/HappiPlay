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
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.mac.myapplication.R;

import java.util.List;

import uk.co.senab.photoview.PhotoView;
import uk.co.senab.photoview.PhotoViewAttacher;

/**
 * A simple {@link Fragment} subclass.
 */
public class PhotoViewFragment extends Fragment {

    private ViewPager mViewPager;
    public List<Integer> imgIds;
    private Context mContext;
    private static ActionBar bar;
    private static android.support.v7.widget.Toolbar toolbar;
    private static LinearLayout photoViewBottom;

    public PhotoViewFragment() {
        // Required empty public constructor
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
//        bar=((AppCompatActivity) getActivity()).getSupportActionBar();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_photo_view, container, false);
//        initToolbar();
        initViewPager(view);
        return view;
    }

    private void initToolbar() {
//        toolbar = BaseActivity.getToolbar();
//        toolbar.setBackgroundColor(Color.TRANSPARENT);
//        toolbar.setVisibility(toolbar.getVisibility() == View.VISIBLE ? View.INVISIBLE : View.VISIBLE);

    }

    private void initViewPager(View view) {
//        toolbar = (Toolbar) view.findViewById(R.id.tool_bar);
//        AppCompatActivity activity = (AppCompatActivity)getActivity();
//        activity.setSupportActionBar(toolbar);
//        toolbar.setBackgroundColor(Color.parseColor("#00FFFFFF"));

        mContext = getActivity();
        Bundle userBundle = getArguments();
        imgIds = userBundle.getIntegerArrayList("data");
        int currentPosition = userBundle.getInt("position");
        mViewPager = (ViewPager) view.findViewById(R.id.photo_mode_viewpager);
        mViewPager.setAdapter(new PhotoModePagerAdapter(imgIds, mContext));
        mViewPager.setCurrentItem(currentPosition);

        photoViewBottom= (LinearLayout) view.findViewById(R.id.photo_mode_bottom);
    }

    static class PhotoModePagerAdapter extends PagerAdapter {

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
            return view == object;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            PhotoView photo = new PhotoView(mContext);
            photo.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    return false;
                }
            });
            photo.setOnPhotoTapListener(new PhotoViewAttacher.OnPhotoTapListener() {
                @Override
                public void onPhotoTap(View view, float x, float y) {
                    toolbar.setVisibility(toolbar.getVisibility() == View.VISIBLE ? View.INVISIBLE : View.VISIBLE);
                    photoViewBottom.setVisibility(photoViewBottom.getVisibility()==View.VISIBLE?View.INVISIBLE:View.VISIBLE);
                }
            });

            Glide.with(mContext).load(imgIds.get(position)).into(photo);
            container.addView(photo);
            return photo;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }
    }

}
