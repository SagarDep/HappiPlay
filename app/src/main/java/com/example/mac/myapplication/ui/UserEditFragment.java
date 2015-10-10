package com.example.mac.myapplication.ui;


import android.app.AlertDialog;
import android.app.Fragment;
import android.app.Notification;
import android.content.ComponentCallbacks;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.mac.myapplication.R;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import butterknife.Bind;
import butterknife.ButterKnife;


/**
 * A simple {@link Fragment} subclass.
 */
public class UserEditFragment extends android.support.v4.app.Fragment implements View.OnClickListener {
    private static final int REQUEST_PICK_PHOTO = 1;
    private static final String IMAGE_FILE_NAME = "photo";
    private static final int REQUEST_CAPTURE_PHOTO = 2;
    private static final int REQUEST_CUT_PHOTO = 3;
    @Bind(R.id.edit_head)
    TextView editHead;
    @Bind(R.id.edit_nickname)
    TextView editNickname;
    @Bind(R.id.edit_gender)
    TextView editGender;
    @Bind(R.id.edit_birthday)
    TextView editBirthday;
    @Bind(R.id.edit_city)
    TextView editCity;
    @Bind(R.id.edit_save)
    Button editSave;
    private TextView editTakePhoto;
    private TextView editChoosePhoto;

    private Toolbar toolbar;
    private Context mContext;
    private View view;
    private View popView;
    private String filePath;

    public UserEditFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mContext = getActivity();
        view = inflater.inflate(R.layout.fragment_user_edit, container, false);

        ButterKnife.bind(this, view);
        initView();
        return view;
    }

    private void initView() {
        toolbar = BaseActivity.getToolbar();
        toolbar.setTitle("个人资料");

        editBirthday.setOnClickListener(this);
        editCity.setOnClickListener(this);
        editGender.setOnClickListener(this);
        editHead.setOnClickListener(this);
        editNickname.setOnClickListener(this);
        editSave.setOnClickListener(this);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.edit_birthday:
                break;
            case R.id.edit_city:
                break;
            case R.id.edit_gender:
                final String[] items = {"男", "女"};

                new AlertDialog.Builder(mContext).setTitle("选择性别")
                        .setItems(items, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                editGender.setText(items[which]);
                            }
                        }).show();
                break;
            case R.id.edit_head:
                View popView = LayoutInflater.from(mContext).inflate(
                        R.layout.popup_edit_head, null);
                RelativeLayout back= (RelativeLayout) popView.findViewById(R.id.popup_back);

                final PopupWindow popupWindow = new PopupWindow(popView, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT,true);
                popupWindow.setTouchable(true);
                popupWindow.setOutsideTouchable(true);
                popupWindow.setAnimationStyle(R.style.PopupAnimation);
                popupWindow.showAtLocation(view, Gravity.BOTTOM, 0, 0);
                back.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        popupWindow.dismiss();
                    }
                });
                editChoosePhoto= (TextView) popView.findViewById(R.id.edit_choose_photo);
                editTakePhoto = (TextView) popView.findViewById(R.id.edit_take_photo);
                editChoosePhoto.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        pickPhoto();
                        popupWindow.dismiss();
                    }
                });
                editTakePhoto.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        takePhoto();
                        popupWindow.dismiss();
                    }
                });

                break;
            case R.id.edit_nickname:
                break;
            case R.id.edit_save:
                break;

        }
    }

    private void pickPhoto() {
        Intent pick = new Intent(Intent.ACTION_PICK,null);
        pick.setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI,"image/*");
        startActivityForResult(pick, REQUEST_PICK_PHOTO);
    }
    private void takePhoto(){
        Intent take=new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        SimpleDateFormat format = new SimpleDateFormat("yyyy年MM月dd日"+"hh:mm:ss", Locale.getDefault());
        String date = format.format(new Date());
        //存储拍的照片
        File photo=new File(Environment.getExternalStorageDirectory(), date);
        take.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(photo));
        filePath = photo.getPath();
        Log.i("test",filePath+">>>>>");
        startActivityForResult(take, REQUEST_CAPTURE_PHOTO);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode){
            case REQUEST_PICK_PHOTO :
                cutPhoto(data.getData());
                break;
            case REQUEST_CAPTURE_PHOTO:
                File file=new File(filePath);
                cutPhoto(Uri.fromFile(file));
                break;
            case REQUEST_CUT_PHOTO:
                if (data!=null)
                    uploadPhoto();
                break;
        }

    }

    private void uploadPhoto() {

    }

    private void cutPhoto(Uri uri) {
        Intent crop=new Intent("com.android.camera.action.CROP");
        crop.setDataAndType(uri, "image/*");
        crop.putExtra("crop", true);
//         aspectX aspectY 是宽高的比例
        crop.putExtra("aspectX",1);
        crop.putExtra("aspectY",1);
// outputX outputY 是裁剪图片宽高
        crop.putExtra("outputX", 300);
        crop.putExtra("outputY", 300);
        startActivityForResult(crop, REQUEST_CUT_PHOTO);
    }
}
