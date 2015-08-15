package com.hotdoor.products.personal;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.hotdoor.products.main.PersonalActivity;
import com.hotdoor.products.main.R;
import com.rengwuxian.materialedittext.MaterialEditText;

/**
 * Created by Yip on 2015/8/13.
 */
public class RegisterFragment extends Fragment implements View.OnClickListener {

    MaterialEditText mUsername;
    MaterialEditText mRealName;
    MaterialEditText mEmail;
    MaterialEditText mPhone;
    MaterialEditText mPassword;
    TextView mBtnNext;

    PersonalActivity mActivity;

    PersonalFragment personalFragment;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.personal_fragment_register, container, false);
        init(view);
        return view;
    }

    private void init(View view) {
        mActivity = (PersonalActivity) getActivity();

        mUsername = (MaterialEditText) view.findViewById(R.id.et_personal_register_username);
        mRealName = (MaterialEditText) view.findViewById(R.id.et_personal_register_name);
        mEmail = (MaterialEditText) view.findViewById(R.id.et_personal_register_email);
        mPhone = (MaterialEditText) view.findViewById(R.id.et_personal_register_mobile);
        mPassword = (MaterialEditText) view.findViewById(R.id.et_personal_register_password);
        mBtnNext = (TextView) view.findViewById(R.id.btn_personal_register_next);

        mBtnNext.setOnClickListener(this);

        mActivity.mPersonalLeftTitle.setText("一键注册");

        /**
         * set Fonts
         */
        setFonts();
    }

    private void setFonts() {
        mUsername.setTypeface(mActivity.mFonts);
        mRealName.setTypeface(mActivity.mFonts);
        mEmail.setTypeface(mActivity.mFonts);
        mPhone.setTypeface(mActivity.mFonts);
        mPassword.setTypeface(mActivity.mFonts);
        mBtnNext.setTypeface(mActivity.mFonts);
    }

    @Override
    public void onClick(View v) {
        personalFragment = new PersonalFragment();

        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        transaction.setCustomAnimations(R.anim.fragment_slide_in_right, R.anim.fragment_slide_out_left
                , R.anim.fragment_slide_in_right, R.anim.fragment_slide_out_left);
        transaction.remove(this);
        transaction.add(R.id.fl_personal_main, personalFragment, "registerEnterFragment");
        transaction.commit();
    }
}
