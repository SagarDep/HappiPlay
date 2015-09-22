package com.example.mac.myapplication.ui;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

import com.daimajia.slider.library.Animations.DescriptionAnimation;
import com.daimajia.slider.library.SliderLayout;
import com.daimajia.slider.library.SliderTypes.BaseSliderView;
import com.daimajia.slider.library.SliderTypes.TextSliderView;
import com.daimajia.slider.library.Tricks.ViewPagerEx;
import com.example.mac.myapplication.R;

import java.util.ArrayList;
import java.util.HashMap;


/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment implements ViewPagerEx.OnPageChangeListener, BaseSliderView.OnSliderClickListener, AdapterView.OnItemClickListener {

    private SliderLayout mSlider;
    private GridView mGridView;
    private Context mContext;
    private View view;

    public HomeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_home, container, false);
        mContext=getActivity();
        initSliderLayout();
        initGridView();
        return view;
    }

    private void initGridView() {
        mGridView = (GridView) view.findViewById(R.id.grid_view);
        mGridView.setAdapter(new GridAdapter(mContext));
        mGridView.setOnItemClickListener(this);
    }

    private void initSliderLayout() {
        mSlider = (SliderLayout)view.findViewById(R.id.slider);
        HashMap<String,Integer> file_maps=new HashMap<>();
        file_maps.put("Hannibal",R.drawable.banner_nine);
        file_maps.put("Big Bang Theory",R.drawable.banner_texas);
        file_maps.put("House of Cards",R.drawable.banner_baccarat);
        file_maps.put("Game of Thrones",R.drawable.banner_21dot);

        for (String name:file_maps.keySet()){
            TextSliderView textSliderView = new TextSliderView(mContext);
            textSliderView.description(name)
                    .image(file_maps.get(name))
                    .setScaleType(BaseSliderView.ScaleType.Fit)
                    .setOnSliderClickListener(this);
            //extra info
            Bundle bundle = new Bundle();
            bundle.putString("extra",name);
            textSliderView.bundle(bundle);
            mSlider.addSlider(textSliderView);
        }
        mSlider.setPresetTransformer(SliderLayout.Transformer.Default);//动画
        mSlider.setPresetIndicator(SliderLayout.PresetIndicators.Right_Bottom);
        mSlider.setCustomAnimation(new DescriptionAnimation());
        mSlider.setDuration(5000);
        mSlider.addOnPageChangeListener(this);
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {

    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    @Override
    public void onSliderClick(BaseSliderView slider) {
        //viewPager中图片的点击事件
        Toast.makeText(getActivity(),slider.getBundle().get("extra")+" pressed",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        Toast.makeText(mContext, i+" pressed",Toast.LENGTH_SHORT).show();
    }
}
