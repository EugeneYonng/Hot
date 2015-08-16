package com.hotdoor.products.personal;

import android.app.Fragment;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.dd.CircularProgressButton;
import com.gc.materialdesign.views.CheckBox;
import com.hotdoor.products.main.PersonalActivity;
import com.hotdoor.products.main.R;


/**
 * Created by Yip on 2015/8/10.
 */
public class LoginFragment extends Fragment implements View.OnClickListener{

    final static int UPDATEPROCESS = 0;

    TextView mTextTitleWelcome;
    EditText mAccount;
    EditText mPassword;
    CheckBox mCbRemember;
    CheckBox mCbAutoLogin;
    CircularProgressButton mBtnLogin;
    TextView mBtnRegister;

    PersonalFragment personalFragment;
    RegisterFragment registerFragment;

    PersonalActivity mActivity;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.personal_fragment_login, container, false);
        init(view);
        return view;
    }

    private void init(View view) {
        mActivity = (PersonalActivity) getActivity();

        mTextTitleWelcome = (TextView) view.findViewById(R.id.tv_personal_title_welcome);
        mAccount = (EditText) view.findViewById(R.id.et_personal_account);
        mPassword = (EditText) view.findViewById(R.id.et_personal_password);
        mCbRemember = (CheckBox) view.findViewById(R.id.cb_personal_remember);
        mCbAutoLogin = (CheckBox) view.findViewById(R.id.cb_personal_login_auto);
        mBtnLogin = (CircularProgressButton) view.findViewById(R.id.btn_personal_login);
        mBtnRegister = (TextView) view.findViewById(R.id.btn_personal_register);

        mBtnLogin.setOnClickListener(this);
        mBtnRegister.setOnClickListener(this);

        mActivity.mPersonalLeftTitle.setText("登陆界面");

        /**
         * set mBtnLogin's font
         */
        setFonts();

    }

    private void setFonts() {
        mTextTitleWelcome.setTypeface(mActivity.mFonts);
        mAccount.setTypeface(mActivity.mFonts);
        mPassword.setTypeface(mActivity.mFonts);
        mBtnLogin.setTypeface(mActivity.mFonts);
        mBtnRegister.setTypeface(mActivity.mFonts);
    }

    private class loginThread extends Thread {
        private int index = 0;
        @Override
        public void run() {
            for(index = 0; index <= 100; index+=10) {
                Message msg = new Message();
                Bundle bundle = new Bundle();
                bundle.putInt("process", index);
                msg.setData(bundle);
                msg.what = LoginFragment.UPDATEPROCESS;
                myhandler.sendMessage(msg);
                try{
                    this.sleep(400,0);
                }catch(InterruptedException e){
                    e.printStackTrace();
                }

            }
        }
    }

    Handler myhandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case LoginFragment.UPDATEPROCESS:
                    mBtnLogin.setProgress(msg.getData().getInt("process"));
                    if(msg.getData().getInt("process") == 100) {
                        mActivity.changeFragment(LoginFragment.this, personalFragment
                                , "personalFragment", 1, false, false);
                    }
                    break;
            }
        }
    };


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_personal_login:
                personalFragment = new PersonalFragment();
                mBtnLogin.setIndeterminateProgressMode(true);
                new loginThread().start();
                break;
            case R.id.btn_personal_register:
                registerFragment = new RegisterFragment();
                mActivity.changeFragment(this, registerFragment, "registerFragment", 0, true, false);
                break;
            default:
                break;
        }
    }


}
