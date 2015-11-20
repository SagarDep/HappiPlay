package com.example.mac.myapplication.ui;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.mac.myapplication.R;
import com.example.mac.myapplication.helper.FragmentHelper;

import butterknife.Bind;
import butterknife.ButterKnife;


/**
 * A simple {@link Fragment} subclass.
 */
public class EditNameFragment extends android.support.v4.app.Fragment implements View.OnClickListener {

    UpdateTextListener updateTextListener;
    @Bind(R.id.save_nickname)
    Button saveNickname;
    @Bind(R.id.et_nickname)
    EditText etNickname;
    private String nickName;

    private View rootView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        if (rootView ==null){
            rootView = inflater.inflate(R.layout.fragment_edit_name, container, false);
        }
        ButterKnife.bind(this, rootView);
        initViews();
        return rootView;
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
                updateTextListener.sendText(nickName);
                FragmentHelper.manager.popBackStack();
                break;
        }
    }

    public interface UpdateTextListener {
        void sendText(String text);
    }

    public EditNameFragment() {
        // Required empty public constructor
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



    private void initViews() {
        saveNickname.setOnClickListener(this);
        saveNickname.setText(nickName);
    }


}
