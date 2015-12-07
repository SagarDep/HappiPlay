package com.example.mac.myapplication.helper;

import android.content.Context;
import android.widget.Toast;

/**
 * Created by yons on 15/12/3.
 */
public class UiHelper {
    public static void showToast(Context context,String toast){
        Toast.makeText(context, toast, Toast.LENGTH_SHORT).show();
    }
}
