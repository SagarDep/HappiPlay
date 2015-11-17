package com.example.mac.myapplication.ui;


import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.mac.myapplication.R;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class UserFragment extends android.support.v4.app.Fragment {
    private Context mContext;

    private ArrayList<Integer> imgIds;
    private ViewPager pager;
    private android.support.v7.widget.Toolbar toolbar;
    private FrameLayout frameLayout;


    public UserFragment() {
        // Required empty public constructor
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mContext = getActivity();
        View view = inflater.inflate(R.layout.fragment_user, container, false);
        initToolbar();
        initUserAlbum(view);

        return view;
    }

    private void initToolbar() {
        setHasOptionsMenu(true);
        toolbar = BaseActivity.getToolbar();
        toolbar.setVisibility(View.VISIBLE);
        toolbar.setTitle("我");
        toolbar.setBackgroundResource(R.color.app_style);

    }

    private void initUserAlbum(final View v) {
        pager = (ViewPager) v.findViewById(R.id.user_viewpager);
        frameLayout = (FrameLayout) v.findViewById(R.id.content);


        imgIds = new ArrayList<>();
        for (int i = 0; i < 15; i++) {
            int resId = mContext.getResources().getIdentifier("img_" + i, "drawable", mContext.getPackageName());
            if (resId != 0) {
                ImageView img = new ImageView(mContext);
//                img.setImageResource(resId);
                Glide.with(mContext).load(resId).into(img);
                img.setScaleType(ImageView.ScaleType.CENTER_CROP);
                imgIds.add(resId);
            } else {
                return;
            }
        }
        pager.setAdapter(new UserAlbumAdapter());
        pager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                TextView currentPhotoNum = (TextView) v.findViewById(R.id.current_photo_num);
                currentPhotoNum.setText(String.valueOf(position + 1));
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

    }

    @Override
    public void onPrepareOptionsMenu(Menu menu) {
        menu.findItem(R.id.action_edit_user).setVisible(true);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        getActivity().getMenuInflater().inflate(R.menu.menu_main, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_edit_user:
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                FragmentTransaction transaction = fragmentManager.beginTransaction();
                android.support.v4.app.Fragment fragment = new UserEditFragment();
                transaction.setCustomAnimations(
                        R.anim.abc_grow_fade_in_from_bottom, R.anim.abc_fade_out,
                        R.anim.abc_fade_in, R.anim.abc_shrink_fade_out_from_bottom);
                transaction.replace(R.id.content, fragment,"user_edit_fragment");
                transaction.addToBackStack("user_fragment");
                transaction.commit();

        }
        return super.onOptionsItemSelected(item);
    }

    class UserAlbumAdapter extends PagerAdapter {

        @Override
        public Object instantiateItem(ViewGroup container, final int position) {
            ImageView img = new ImageView(mContext);
            Glide.with(mContext).load(imgIds.get(position)).into(img);
            img.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                    FragmentTransaction transaction = fragmentManager.beginTransaction();
                    android.support.v4.app.Fragment fragment = new PhotoViewFragment();
                    Bundle bundle = new Bundle();
                    bundle.putInt("position", position);
                    bundle.putIntegerArrayList("data", imgIds);
                    fragment.setArguments(bundle);
//                    transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
                    transaction.setCustomAnimations(
                            R.anim.abc_fade_in, R.anim.abc_fade_out,
                            R.anim.abc_fade_in, R.anim.abc_fade_out);
                    transaction.replace(R.id.content, fragment);
                    transaction.addToBackStack("photo mode");
                    transaction.commit();
                    //进入PhotoMode时，上方会有白色闪一下，未找到完美解决办法
                    frameLayout.setBackgroundColor(Color.BLACK);
                }
            });
            container.addView(img);
            return img;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }

        @Override
        public int getCount() {
            return imgIds.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

    }
}


