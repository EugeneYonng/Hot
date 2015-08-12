package com.hotdoor.products.personal;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;

import com.hotdoor.products.main.R;
import com.hotdoor.textview.MyTextView;

/**
 * Created by Yip on 2015/8/12.
 */
public class FillInfoFragment extends Fragment implements View.OnClickListener {

    EditText mAcoount;
    EditText mPassword;
    EditText mPasswordCertify;
    EditText mUsername;
    EditText mEmail;
    ImageView mImgPortraitIcon;
    MyTextView mBtnUploadPortrait;
    MyTextView mBtnNextStep;

    CertifyInfoFragment certifyInfoFragment;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.personal_fragment_register_step_second, container, false);
        init(v);
        return v;
    }

    private void init(View view) {
        mAcoount = (EditText) view.findViewById(R.id.et_personal_register_account);
        mPassword = (EditText) view.findViewById(R.id.et_personal_register_password);
        mPasswordCertify = (EditText) view.findViewById(R.id.et_personal_register_password_certify);
        mUsername = (EditText) view.findViewById(R.id.et_personal_register_username);
        mEmail = (EditText) view.findViewById(R.id.et_personal_register_email);
        mImgPortraitIcon = (ImageView) view.findViewById(R.id.iv_personal_register_portrait_icon);
        mBtnUploadPortrait = (MyTextView) view.findViewById(R.id.btn_personal_register_upload_portrait_icon);
        mBtnNextStep = (MyTextView) view.findViewById(R.id.btn_personal_register_info_next_step);

        mImgPortraitIcon.setOnClickListener(this);
        mBtnUploadPortrait.setOnClickListener(this);
        mBtnNextStep.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        certifyInfoFragment = new CertifyInfoFragment();

        FragmentTransaction transaction = getActivity().getFragmentManager().beginTransaction();
//        transaction.setCustomAnimations(R.anim.fragment_slide_in_right, R.anim.fragment_slide_out_left
//                , R.anim.fragment_slide_in_right, R.anim.fragment_slide_out_left);

        switch (v.getId()) {
            case R.id.iv_personal_register_portrait_icon:
                break;
            case R.id.btn_personal_register_upload_portrait_icon:
                break;
            case R.id.btn_personal_register_info_next_step:
                transaction.hide(this).add(R.id.fl_personal_main, certifyInfoFragment);
                transaction.addToBackStack(null);
                transaction.commit();

                break;
            default:
                break;
        }
    }
}
