package com.example.mac.myapplication.ui;


import android.content.Context;
import android.content.Intent;
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
import android.widget.ImageView;
import android.widget.TextView;

import com.example.mac.myapplication.R;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class UserFragment extends android.support.v4.app.Fragment {
    private Context mContext;

    private ArrayList<Integer> imgIds;
    private ViewPager pager;

    public UserFragment() {
        // Required empty public constructor
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

        setHasOptionsMenu(true);
        initUserAlbum(view);

        return view;
    }

    private void initUserAlbum(final View v) {
        pager = (ViewPager) v.findViewById(R.id.user_viewpager);

        imgIds = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            int resId = mContext.getResources().getIdentifier("img_" + i, "drawable", mContext.getPackageName());
            if (resId != 0) {
                ImageView img = new ImageView(mContext);
                img.setImageResource(resId);
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
                Intent intent = new Intent(mContext, UserEditActivity.class);
                int requestCode = 1;
                startActivityForResult(intent, requestCode);
        }
        return super.onOptionsItemSelected(item);
    }

    class UserAlbumAdapter extends PagerAdapter {

        @Override
        public Object instantiateItem(ViewGroup container, final int position) {
            ImageView img = new ImageView(mContext);
            img.setImageResource(imgIds.get(position));
            img.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                    FragmentTransaction transaction = fragmentManager.beginTransaction();
                    android.support.v4.app.Fragment fragment = new PhotoViewFragment();
                    Bundle bundle=new Bundle();
                    bundle.putInt("position",position);
                    bundle.putIntegerArrayList("data", imgIds);
                    fragment.setArguments(bundle);
//                    transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_CLOSE);
                    transaction.setCustomAnimations(
                            0,R.anim.abc_fade_out,0,R.anim.abc_fade_out);
                    transaction.replace(R.id.fragment, fragment);
                    transaction.addToBackStack("photo mode");
                    transaction.commit();
                }
            });
            container.addView(img);
            return img;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View)object);
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


