package com.example.mac.myapplication.ui;


import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.graphics.drawable.Drawable;
import android.media.ExifInterface;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.droid.CityChooseActivity;
import com.example.mac.myapplication.R;
import com.example.mac.myapplication.helper.FragmentHelper;
import com.example.mac.myapplication.ui.activity.BaseActivity;
import com.example.mac.myapplication.ui.fragment.BaseFragment;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import butterknife.Bind;
import butterknife.ButterKnife;


/**
 * A simple {@link Fragment} subclass.
 */
public class UserEditFragment extends BaseFragment implements View.OnClickListener, EditNameFragment.UpdateTextListener {
    private static final int REQUEST_PICK_PHOTO = 1;
    private static final int REQUEST_CAPTURE_PHOTO = 2;
    private static final int REQUEST_CUT_PHOTO = 3;
    private static final int REQUEST_CITY_CHOOSE = 4;
    private static final String IMAGE_FILE_NAME = "photo";
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
    TextView editSave;
    @Bind(R.id.back)
    ImageView back;
    @Bind(R.id.user_edit_fragment)
    FrameLayout userEditFragment;

    private TextView editTakePhoto;
    private TextView editChoosePhoto;

    private Toolbar toolbar;
    private Fragment fragment;
    private String filePath;
    private String nickName;
    private String birthday;
    private String gender;
    private String city;


    protected void initViews() {
//        toolbar = BaseActivity.getToolbar();
//        toolbar.setTitle("个人资料");
        editBirthday.setOnClickListener(this);
        editCity.setOnClickListener(this);
        editGender.setOnClickListener(this);
        editHead.setOnClickListener(this);
        editNickname.setOnClickListener(this);
        editSave.setOnClickListener(this);
        back.setOnClickListener(this);

        if (!TextUtils.isEmpty(birthday)) {
            editBirthday.setText(birthday);
        }
        if (!TextUtils.isEmpty(gender)) {
            editGender.setText(gender);
        }
        if (!TextUtils.isEmpty(city)) {
            editCity.setText(city);
        }

        if (!TextUtils.isEmpty(nickName)) {
            editNickname.setText(nickName);
        }

    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_user_edit;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    @Override
    public void onClick(View v) {
        //super.onClick(v);

        switch (v.getId()) {
            case R.id.edit_birthday:
                Calendar calendar = Calendar.getInstance();
                new DatePickerDialog(getContext(), new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                        birthday = year + "年" + monthOfYear + "月" + dayOfMonth + "日";
                        editBirthday.setText(birthday);
                    }
                }, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH)).show();
                break;
            case R.id.edit_city:
                Intent cityChoose = new Intent(getContext(), CityChooseActivity.class);
                startActivityForResult(cityChoose, REQUEST_CITY_CHOOSE);

                break;
            case R.id.edit_gender:
                final String[] items = {"男", "女"};

                new AlertDialog.Builder(getContext()).setTitle("选择性别")
                        .setItems(items, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                gender = items[which];
                                editGender.setText(gender);
                            }
                        }).show();
                break;
            case R.id.edit_head:
                editHeadPopWindow();
                break;
            case R.id.edit_nickname:
                startEditName();
                break;
            case R.id.edit_save:
                FragmentHelper.manager.popBackStack();
                break;
        }
    }

    private void editHeadPopWindow() {
        View popView = LayoutInflater.from(getContext()).inflate(
                R.layout.popup_edit_head, null);
        RelativeLayout back = (RelativeLayout) popView.findViewById(R.id.popup_back);

        final PopupWindow popupWindow = new PopupWindow(popView, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT, true);
        popupWindow.setTouchable(true);
        popupWindow.setOutsideTouchable(true);
        popupWindow.setAnimationStyle(R.style.PopupAnimation);
        popupWindow.showAtLocation(rootView, Gravity.BOTTOM, 0, 0);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popupWindow.dismiss();
            }
        });
        editChoosePhoto = (TextView) popView.findViewById(R.id.edit_choose_photo);
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
    }

    private void startEditName() {
        if (fragment == null) {
            fragment = new EditNameFragment();
        }
        FragmentHelper.replaceFragment(R.id.content, fragment, "edit_name");
    }


    private void pickPhoto() {
        Intent pick = new Intent(Intent.ACTION_PICK, null);
        pick.setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, "image/*");
        startActivityForResult(pick, REQUEST_PICK_PHOTO);
    }

    private void takePhoto() {
        Intent take = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        SimpleDateFormat format = new SimpleDateFormat("yyyy年MM月dd日" + "hh:mm:ss", Locale.getDefault());
        String date = format.format(new Date());
        //存储拍的照片
        File photo = new File(Environment.getExternalStorageDirectory(), date);
        take.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(photo));
        filePath = photo.getPath();
        startActivityForResult(take, REQUEST_CAPTURE_PHOTO);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        Uri uriData;
        switch (requestCode) {
            case REQUEST_PICK_PHOTO:
                if (data != null) {
                    uriData = data.getData();
                    cutPhoto(uriData);
                }
                break;
            case REQUEST_CAPTURE_PHOTO:
                if (filePath != null && resultCode == BaseActivity.RESULT_OK) {
                    Bitmap bitmap = BitmapFactory.decodeFile(filePath);
                    bitmap = turnPicture(bitmap, readPictureDegree(filePath));
                    Uri uri = Uri.parse(MediaStore.Images.Media.insertImage(getContext().getContentResolver(), bitmap, null, null));
                    cutPhoto(uri);
                }

                break;
            case REQUEST_CUT_PHOTO:

                if (data != null && resultCode == BaseActivity.RESULT_OK) {
                    uriData = data.getData();
                    uploadPhoto();
                    Drawable photo = null;
                    try {
                        photo = Drawable.createFromStream(getContext().getContentResolver().openInputStream(uriData), null);
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }
                    Drawable left;
                    left = ContextCompat.getDrawable(getContext(), R.drawable.user_photo_num);
                    editHead.setCompoundDrawablesWithIntrinsicBounds(left, null, photo, null);
//                    editHead.setCompoundDrawablePadding(5);
                }
                break;
            case REQUEST_CITY_CHOOSE:
                if (data != null) {
                    city = data.getStringExtra("city");
                    editCity.setText(city);
                }
                break;
        }

    }

    private void uploadPhoto() {

    }

    private void cutPhoto(Uri uri) {
        Intent crop = new Intent("com.android.camera.action.CROP");
        crop.setDataAndType(uri, "image/*");
        crop.putExtra("crop", true);
//      aspectX aspectY 是宽高的比例
        crop.putExtra("aspectX", 1);
        crop.putExtra("aspectY", 1);
//      outputX outputY 是裁剪图片宽高
        crop.putExtra("outputX", 512);
        crop.putExtra("outputY", 512);
        startActivityForResult(crop, REQUEST_CUT_PHOTO);
    }

    @Override
    public void sendText(String text) {
        nickName = text;
        Log.i("test",nickName);
        editNickname = (TextView) rootView.findViewById(R.id.edit_nickname);
        editNickname.setText(nickName);
    }

    public static Bitmap turnPicture(Bitmap img, int degree) {
        Matrix matrix = new Matrix();
        matrix.postRotate(+degree); /*翻转90度*/
        int width = img.getWidth();
        int height = img.getHeight();
        img = Bitmap.createBitmap(img, 0, 0, width, height, matrix, true);
        return img;
    }

    public static int readPictureDegree(String path) {
        int degree = 0;
        try {
            ExifInterface exifInterface = new ExifInterface(path);
            int orientation = exifInterface.getAttributeInt(ExifInterface.TAG_ORIENTATION, ExifInterface.ORIENTATION_NORMAL);
            switch (orientation) {
                case ExifInterface.ORIENTATION_ROTATE_90:
                    degree = 90;
                    break;
                case ExifInterface.ORIENTATION_ROTATE_180:
                    degree = 180;
                    break;
                case ExifInterface.ORIENTATION_ROTATE_270:
                    degree = 270;
                    break;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return degree;
    }
}
