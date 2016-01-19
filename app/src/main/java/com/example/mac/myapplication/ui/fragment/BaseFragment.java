package com.example.mac.myapplication.ui.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;


import com.example.mac.myapplication.net.NetCallBack;
import com.example.mac.myapplication.net.NetClient;


import org.json.JSONException;

import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public abstract class BaseFragment extends Fragment{
    protected View rootView;
//    private Fragment fragment;
//    private InputMethodManager imm;

    private NetClient netClient;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        if (rootView == null) {
            rootView = inflater.inflate(getLayoutId(), container, false);
            ButterKnife.bind(this, rootView);
            initViews();
            initData();
        }
        ButterKnife.bind(this, rootView);
        AlwaysInit();
        return rootView;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
    }


    protected void initData() {

    }

    protected void AlwaysInit() {

    }

    protected abstract void initViews();

    protected abstract int getLayoutId();


    /*@Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.back:
                FragmentHelper.manager.popBackStack();
                break;
            case R.id.message:
                FragmentHelper.removeFragmentByTag("me");
                if (FragmentHelper.messageFragment == null) {
                    FragmentHelper.messageFragment = new MessageFragment();
                }
                FragmentHelper.replaceFragment(R.id.content, FragmentHelper.messageFragment, "message");
                break;
        }
    }*/

    /**
     * ShowToast 对话框
     *
     * @param msg
     */
    public void showToast(String msg) {
        Toast.makeText(getContext(), msg, Toast.LENGTH_SHORT).show();
    }

    public NetClient getNetClient() {
        if (netClient == null) {
            netClient = new NetClient(new BaseNetCallback());
        }
        return netClient;
    }

    public class BaseNetCallback implements NetCallBack {
        @Override
        public void onHttpComplete(int result, String url, Object obj) {
            if (result == netClient.RESULT_OK) {
                onHttpSuccess(url, obj);
            } else {
                onHttpError(result,url,obj);
            }
        }
    }

    protected void onHttpSuccess(String url, Object obj){

    }
    protected void onHttpError(int result,String url,Object obj) {
        String desc = (String)obj;
        if (desc == null) {
            showToast("网络错误");
        } else {
            showToast(desc);
        }
    }

}
