package com.hotdoor.products.personal;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.dd.CircularProgressButton;
import com.gc.materialdesign.views.CheckBox;
import com.hotdoor.products.main.R;
import com.hotdoor.textview.MyTextView;


/**
 * Created by Yip on 2015/8/10.
 */
public class LoginFragment extends Fragment implements View.OnClickListener{

    final static int UPDATEPROCESS = 0;

    EditText mAccount;
    EditText mPassword;
    CheckBox mCbRemember;
    CheckBox mCbAutoLogin;
    CircularProgressButton mBtnLogin;
    MyTextView mBtnRegister;

    FragmentTransaction transaction;

    PersonalFragment personalFragment;
    RegisterFragment registerFragment;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.personal_fragment_login, container, false);
        init(view);
        return view;
    }

    private void init(View view) {

        mAccount = (EditText) view.findViewById(R.id.et_personal_account);
        mPassword = (EditText) view.findViewById(R.id.et_personal_password);
        mCbRemember = (CheckBox) view.findViewById(R.id.cb_personal_remember);
        mCbAutoLogin = (CheckBox) view.findViewById(R.id.cb_personal_login_auto);
        mBtnLogin = (CircularProgressButton) view.findViewById(R.id.btn_personal_login);
        mBtnRegister = (MyTextView) view.findViewById(R.id.btn_personal_register);

        mBtnLogin.setOnClickListener(this);
        mBtnRegister.setOnClickListener(this);

        /**
         * set mBtnLogin's font
         */
        mBtnLogin.setTypeface(Typeface.createFromAsset(getActivity().getAssets(), "fonts/youyuan.ttf"));

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
                        transaction.replace(R.id.fl_personal_main, personalFragment);
                        transaction.commit();
                    }
                    break;
            }
        }
    };


    @Override
    public void onClick(View v) {
        transaction = getActivity().getFragmentManager().beginTransaction();
        transaction.setCustomAnimations(R.anim.fragment_slide_in_right, R.anim.fragment_slide_out_left
                , R.anim.fragment_slide_in_right, R.anim.fragment_slide_out_left);
        personalFragment = new PersonalFragment();
        registerFragment = new RegisterFragment();

        switch (v.getId()) {
            case R.id.btn_personal_login:
                mBtnLogin.setIndeterminateProgressMode(true);
                new loginThread().start();

                break;
            case R.id.btn_personal_register:
                transaction.replace(R.id.fl_personal_main, registerFragment, "protocolFragment");
                transaction.addToBackStack(null);
                transaction.commit();
                break;
            default:
                break;
        }
    }


}
