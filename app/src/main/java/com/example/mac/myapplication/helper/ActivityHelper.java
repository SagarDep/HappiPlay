package com.example.mac.myapplication.helper;

import android.content.Context;
import android.content.Intent;

/**
 * Created by yons on 15/9/17.
 */
public class ActivityHelper {
    public static void openActivity(Context context, Class newActivity){
        Intent intent = new Intent(context, newActivity);
        context.startActivity(intent);
//        intent.putExtra(SimpleBackActivity.BUNDLE_KEY_PAGE, page.getValue());
    }
}
