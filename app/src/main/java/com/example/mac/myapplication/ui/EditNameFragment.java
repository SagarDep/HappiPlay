package com.example.mac.myapplication.ui;


import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.mac.myapplication.R;
import com.example.mac.myapplication.helper.FragmentHelper;
import com.example.mac.myapplication.ui.fragment.BaseFragment;

import butterknife.Bind;
import butterknife.ButterKnife;


/**
 * A simple {@link Fragment} subclass.
 */
public class EditNameFragment extends BaseFragment implements View.OnClickListener {

    UpdateTextListener updateTextListener;
    @Bind(R.id.save_nickname)
    Button saveNickname;
    @Bind(R.id.et_nickname)
    EditText etNickname;
    private String nickName;

    private InputMethodManager imm;


    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        imm = (InputMethodManager) saveNickname.getContext().
                getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.showSoftInput(etNickname, InputMethodManager.SHOW_IMPLICIT);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.save_nickname:
                nickName = etNickname.getText().toString();
                if (TextUtils.isEmpty(nickName)){
                    Toast.makeText(getContext(), "给自己起个名字吧~", Toast.LENGTH_SHORT).show();
                }else {
                    updateTextListener.sendText(nickName);
                    imm.hideSoftInputFromWindow(rootView.getWindowToken(), 0);
                    FragmentHelper.manager.popBackStack();
                }
                break;
        }
    }

    public interface UpdateTextListener {
        void sendText(String text);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            updateTextListener = (UpdateTextListener) (context);
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString() + "must implement UpdateText");
        }

    }

    protected void initViews() {
        saveNickname.setOnClickListener(this);
        etNickname.setFocusable(true);
        etNickname.setFocusableInTouchMode(true);
        etNickname.requestFocus();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_edit_name;
    }


}
