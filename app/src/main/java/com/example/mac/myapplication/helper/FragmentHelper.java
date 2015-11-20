package com.example.mac.myapplication.helper;

import android.app.Activity;
import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.View;

import com.example.mac.myapplication.MainActivity;
import com.example.mac.myapplication.R;
import com.example.mac.myapplication.ui.MeFragment;
import com.example.mac.myapplication.ui.SettingFragment;
import com.example.mac.myapplication.ui.UserEditFragment;

/**
 * Created by yons on 15/11/18.
 */
public class FragmentHelper {

    private FragmentActivity activity;

    public FragmentHelper(FragmentActivity activity) {
        this.activity = activity;
        manager = activity.getSupportFragmentManager();
    }

    public static FragmentManager manager;

    public static void hideTab() {
        MainActivity.getTabhost().setVisibility(View.GONE);
    }

    public static void showTab() {
        MainActivity.getTabhost().setVisibility(View.VISIBLE);
    }

    public static <T extends Fragment> void replaceFragment(
            int frameLayoutId, Class<T> fragmentClass) {
        replaceFragment(frameLayoutId, fragmentClass, "");
    }


    public static <T extends Fragment> void replaceFragment(
            int frameLayoutId, Class<T> fragmentClass, String tag) {

        FragmentTransaction transaction = manager.beginTransaction();
        transaction.setCustomAnimations(
                R.anim.abc_grow_fade_in_from_bottom, R.anim.abc_fade_out,
                R.anim.abc_fade_in, R.anim.abc_shrink_fade_out_from_bottom);
        Fragment fragment = manager.findFragmentByTag(tag);
        T t = null;
        if (fragment == null) {
            try {
                t = fragmentClass.newInstance();
            } catch (Fragment.InstantiationException
                    | IllegalAccessException | java.lang.InstantiationException e) {
                e.printStackTrace();
            }
        } else {
            t = (T) fragment;
            Log.i("test", tag + " fragment found");
        }
        transaction.replace(frameLayoutId, t, tag);
        transaction.addToBackStack("");
        transaction.commit();
    }

    public static  void replaceFragment(int frameLayoutId, Fragment fragment, String tag) {

        FragmentTransaction transaction = manager.beginTransaction();
        transaction.setCustomAnimations(
                R.anim.abc_grow_fade_in_from_bottom, R.anim.abc_fade_out,
                R.anim.abc_fade_in, R.anim.abc_shrink_fade_out_from_bottom);
        transaction.replace(frameLayoutId, fragment, tag);
        transaction.addToBackStack("");
        transaction.commit();
    }

    public static <T extends Fragment> void showNewFragment(int frameLayoutId, Class<T> fragmentClass, String tag) {

        FragmentTransaction transaction = manager.beginTransaction();
        T fragment = null;
        try {
            fragment = fragmentClass.newInstance();
        } catch (Fragment.InstantiationException
                | IllegalAccessException | java.lang.InstantiationException e) {
            e.printStackTrace();
        }
        transaction.setCustomAnimations(
                R.anim.abc_grow_fade_in_from_bottom, R.anim.abc_fade_out,
                R.anim.abc_fade_in, R.anim.abc_shrink_fade_out_from_bottom);
        transaction.add(frameLayoutId, fragment, tag);
//        transaction.addToBackStack("");
        transaction.commit();
    }

    public static <T extends Fragment> void showFragment(int frameLayoutId, Class<T> fragmentClass, String tag) {

        FragmentTransaction transaction = manager.beginTransaction();
        T fragment = null;
        try {
            fragment = fragmentClass.newInstance();
        } catch (Fragment.InstantiationException
                | IllegalAccessException | java.lang.InstantiationException e) {
            e.printStackTrace();
        }
        transaction.setCustomAnimations(
                R.anim.abc_grow_fade_in_from_bottom, R.anim.abc_fade_out,
                R.anim.abc_fade_in, R.anim.abc_shrink_fade_out_from_bottom);
        transaction.add(frameLayoutId, fragment, tag);
        transaction.commit();
    }


    public static void showFragmentByTag(Fragment oldFragment, String tag) {
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.setCustomAnimations(
                R.anim.abc_grow_fade_in_from_bottom, R.anim.abc_fade_out,
                R.anim.abc_fade_in, R.anim.abc_shrink_fade_out_from_bottom);
        Fragment fragment = manager.findFragmentByTag(tag);
        if (fragment != null) {
            transaction.hide(oldFragment);
            transaction.show(fragment);
        }
        transaction.commit();
    }

    public static void hideFragment(Fragment fragment) {
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.setCustomAnimations(
                R.anim.abc_grow_fade_in_from_bottom, R.anim.abc_fade_in,
                R.anim.abc_fade_out, R.anim.abc_shrink_fade_out_from_bottom);
        transaction.hide(fragment);
        transaction.commit();
    }

    public static void showFragment() {
        FragmentTransaction transaction = manager.beginTransaction();
        Fragment setting = manager.findFragmentByTag("setting");
        Fragment user_edit = manager.findFragmentByTag("user_edit");

        Fragment[] fragments = {setting, user_edit};

        for (int i = 0; i < manager.getFragments().size(); i++) {
            if (manager.getFragments().contains(fragments[i])) {
                transaction.hide(fragments[i]);
            }
        }
        transaction.show(setting);
        transaction.commit();
    }
}
