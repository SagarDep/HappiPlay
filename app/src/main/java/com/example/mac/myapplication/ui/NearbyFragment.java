package com.example.mac.myapplication.ui;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.mac.myapplication.MainActivity;
import com.example.mac.myapplication.R;

import java.util.ArrayList;
import java.util.List;
import java.util.zip.Inflater;


/**
 * Created by yons on 15/9/22.
 */
public class NearbyFragment extends Fragment implements CardAdapter.OnRecyclerViewClickListener {

    private RecyclerView mRecyclerView;
    private List<String> mDataList;
    private CardAdapter adapter;
    private Context mContext;

    public NearbyFragment() {
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
        setHasOptionsMenu(true);
        View view = inflater.inflate(R.layout.fragment_nearby, container, false);
        initRecyclerView(view);
        return view;
    }

    private void initRecyclerView(View view) {
        mRecyclerView = (RecyclerView) view.findViewById(R.id.recycler_view);

        mDataList = new ArrayList<>();
        for (int i = 'A'; i < 'z'; i++) {
            mDataList.add(""+(char)i);
        }
        adapter=new CardAdapter(mContext, mDataList, this);
        mRecyclerView.setAdapter(adapter);
        mRecyclerView.setLayoutManager(new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL));
    }


    @Override
    public void onItemClick(View view, int position) {
        Toast.makeText(mContext, position + "clicked!" , Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onItemLongClick(View view, int position) {
        Toast.makeText(mContext, position + "long clicked!" , Toast.LENGTH_SHORT).show();
    }

    //菜单项的初始化
    @Override
    public void onPrepareOptionsMenu(Menu menu) {
        if (!MainActivity.isDrawerOpen){
            menu.findItem(R.id.action_camera).setVisible(true);
        }
        //比如打开菜单时隐藏search按钮
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
//        super.onCreateOptionsMenu(menu, inflater);
        menu.clear();
        menu.add(10,10,1,"test");
        getActivity().getMenuInflater().inflate(R.menu.menu_main, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_search) {
            Intent intent = new Intent();
            Uri uri = Uri.parse("http://www.baidu.com");
            intent.setAction(Intent.ACTION_VIEW)
                    .setData(uri);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }

}