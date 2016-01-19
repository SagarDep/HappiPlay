package com.example.mac.myapplication.ui.fragment;


import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.bumptech.glide.Glide;
import com.example.mac.myapplication.R;
import com.example.mac.myapplication.helper.FragmentHelper;
import com.example.mac.myapplication.model.AppSimpleModel;
import com.example.mac.myapplication.model.MyPhotoSimpleModel;
import com.example.mac.myapplication.model.PlazaSimpleModel;
import com.example.mac.myapplication.ui.BeautyFragment;
import com.example.mac.myapplication.ui.MessageFragment;
import com.example.mac.myapplication.ui.adapter.MyFragmentPagerAdapter;
import com.example.mac.myapplication.ui.PagerFragmentAll;
import com.example.mac.myapplication.ui.PagerFragmentLike;
import com.example.mac.myapplication.ui.SettingFragment;
import com.example.mac.myapplication.utils.LogUtils;
import com.example.mac.myapplication.utils.MosApplication;
import com.example.mac.myapplication.view.CustomImageView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;

/**
 * A simple {@link Fragment} subclass.
 */
public class MeFragment extends BaseFragment implements View.OnClickListener {

    @Bind(R.id.tab_indicator)
    View tabIndicator;
    @Bind(R.id.indicator_all)
    ImageView indicatorAll;
    @Bind(R.id.indicator_like)
    ImageView indicatorLike;
    @Bind(R.id.message)
    ImageView message;
    @Bind(R.id.setting)
    ImageView setting;
    @Bind(R.id.upload_picture)
    Button uploadPicture;
    @Bind(R.id.browse_picture)
    Button browsePicture;
    @Bind(R.id.view_pager)
    ViewPager viewPager;

    @Bind(R.id.bar_title)TextView barTitle;
    @Bind(R.id.avatar)ImageView avatar;
    @Bind(R.id.fensi_text)TextView fensiText;
    @Bind(R.id.xihuan_text)TextView xihuanText;
    @Bind(R.id.fensi_layout)LinearLayout fensiLayout;
    @Bind(R.id.xihuan_layout)LinearLayout xihuanLayout;

    private ArrayList<Fragment> fragments = new ArrayList<>();
    private int indicatorWidth;

    private Fragment settingFragment;
    private Fragment beautyfragment;
    private MyFragmentPagerAdapter adapter;
    private PagerFragmentAll allFragment;
    private PagerFragmentLike likeFragment;

    @Override
    protected void initViews() {
        initViewPager();
        initIndicator(2);
        setting.setOnClickListener(this);
        message.setOnClickListener(this);
    }

    @Override
    protected void AlwaysInit() {
        FragmentHelper.showTab();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_me;
    }

    private void initIndicator(int indicatorNum) {
        Display display = getActivity().getWindow().getWindowManager().getDefaultDisplay();
        DisplayMetrics displayMetrics = new DisplayMetrics();
        display.getMetrics(displayMetrics);
        indicatorWidth = displayMetrics.widthPixels / indicatorNum;

        LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) tabIndicator.getLayoutParams();
        layoutParams.width = indicatorWidth;
        tabIndicator.setLayoutParams(layoutParams);

        indicatorAll.setOnClickListener(this);
        indicatorLike.setOnClickListener(this);
        uploadPicture.setOnClickListener(this);
        browsePicture.setOnClickListener(this);
        avatar.setOnClickListener(this);
        fensiLayout.setOnClickListener(this);
        xihuanLayout.setOnClickListener(this);
    }

    private void initViewPager() {
        allFragment = new PagerFragmentAll();
        likeFragment = new PagerFragmentLike();

        fragments.add(allFragment);
        fragments.add(likeFragment);
        if (adapter == null) {
            adapter = new MyFragmentPagerAdapter(getChildFragmentManager(), fragments);
        }

        viewPager.setAdapter(adapter);
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) tabIndicator.getLayoutParams();
                layoutParams.leftMargin = (int) ((position + positionOffset) * indicatorWidth);
                tabIndicator.setLayoutParams(layoutParams);
            }

            @Override
            public void onPageSelected(int position) {
                if (position == 0) {
                    indicatorAll.setImageResource(R.drawable.all_select);
                    indicatorLike.setImageResource(R.drawable.like_unselect);
                } else {
                    indicatorAll.setImageResource(R.drawable.all_unselect);
                    indicatorLike.setImageResource(R.drawable.like_select);
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });
    }

    @Override
    protected void initData() {
        super.initData();

        //如果是登录
        if (MosApplication.getInstance().authInfo.getLogin() == 1) {
            barTitle.setText(MosApplication.getInstance().authInfo.getUser().getUser_nick());
            Glide.with(getActivity()).load(MosApplication.getInstance().authInfo.getUser().getUser_avatar()).into(avatar);

            getNetClient().getUserHomeInfo(MosApplication.getInstance().authInfo.getSessionKey(), null);
        } else {

        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.indicator_all:
                viewPager.setCurrentItem(0);
                break;
            case R.id.indicator_like:
                viewPager.setCurrentItem(1);
                break;
            case R.id.setting:
                showToast("设置");
                //startSetting();
                break;
            case R.id.browse_picture:
                showToast("看美图");
                /*if (beautyfragment == null) {
                    beautyfragment = new BeautyFragment();
                }
                FragmentHelper.replaceFragment(R.id.content, beautyfragment, "beauty");*/
                break;
            case R.id.upload_picture:
                showToast("上传图片");
                break;
            case R.id.message:
                showToast("消息");
                /*if (FragmentHelper.messageFragment == null) {
                    FragmentHelper.messageFragment = new MessageFragment();
                }
                FragmentHelper.replaceFragment(R.id.content, FragmentHelper.messageFragment, "message");*/
                break;
            case R.id.avatar:
                showToast("头像");
                break;
            case R.id.fensi_layout:
                showToast("粉丝数");
                break;
            case R.id.xihuan_layout:
                showToast("喜欢的");
                break;
        }
    }


    /*private void startSetting() {
        if (settingFragment == null) {
            settingFragment = new SettingFragment();
        }
        FragmentHelper.replaceFragment(R.id.content, settingFragment, "setting");
        FragmentHelper.hideTab();
    }*/

    @Override
    protected void onHttpSuccess(String url, Object obj) {
        super.onHttpSuccess(url, obj);
        JSONObject object = (JSONObject)obj;

        try {
            fensiText.setText(object.getString("likeme"));
            xihuanText.setText(object.getString("melike"));

            JSONArray photosJsonArray = object.getJSONArray("photos");
            List<MyPhotoSimpleModel> photos = JSON.parseArray(photosJsonArray.toString(),MyPhotoSimpleModel.class);
            allFragment.refreshData(photos);

            JSONArray likesJsonArray = object.getJSONArray("likes");
            List<PlazaSimpleModel> likes = JSON.parseArray(likesJsonArray.toString(), PlazaSimpleModel.class);
            likeFragment.refreshData(likes);

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onHttpError(int result, String url, Object obj) {
        super.onHttpError(result, url, obj);
    }


}
