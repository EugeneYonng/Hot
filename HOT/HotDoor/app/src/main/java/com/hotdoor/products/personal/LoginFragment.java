package com.hotdoor.products.personal;

import android.app.Fragment;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.dd.CircularProgressButton;
import com.gc.materialdesign.views.CheckBox;
import com.hotdoor.products.main.PersonalActivity;
import com.hotdoor.products.main.R;
import com.weiwangcn.betterspinner.library.material.MaterialBetterSpinner;


/**
 * Created by Yip on 2015/8/10.
 */
public class LoginFragment extends Fragment implements View.OnClickListener, Spinner.OnItemClickListener {

    private final static int UPDATE_PROCESS = 0;
    private final static String[] SPINNER_STR = new String[] {"管理员", "申请人"};
    
    TextView mTextTitleWelcome;
    MaterialBetterSpinner mSpinner;
    EditText mAccount;
    EditText mPassword;
    CheckBox mCbRemember;
    CheckBox mCbAutoLogin;
    CircularProgressButton mBtnLogin;

    PersonalActivity mActivity;

    int authorityLevel;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.personal_fragment_login, container, false);
        init(view);
        return view;
    }

    private void init(View view) {
        mActivity = (PersonalActivity) getActivity();

        mTextTitleWelcome = (TextView) view.findViewById(R.id.tv_personal_title_welcome);
        mSpinner = (MaterialBetterSpinner) view.findViewById(R.id.spinner_personal_authority);
        mAccount = (EditText) view.findViewById(R.id.et_personal_account);
        mPassword = (EditText) view.findViewById(R.id.et_personal_password);
        mCbRemember = (CheckBox) view.findViewById(R.id.cb_personal_remember);
        mCbAutoLogin = (CheckBox) view.findViewById(R.id.cb_personal_login_auto);
        mBtnLogin = (CircularProgressButton) view.findViewById(R.id.btn_personal_login);

        mBtnLogin.setOnClickListener(this);

        ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<String>(getActivity().getBaseContext(),
                android.R.layout.simple_dropdown_item_1line, SPINNER_STR);
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
        mSpinner.setAdapter(spinnerAdapter);
        mSpinner.setOnItemClickListener(this);

        mActivity.mPersonalLeftTitle.setText("登陆界面");

        /**
         * set mBtnLogin's font
         */
        setFonts();

    }

    private void setFonts() {
        mTextTitleWelcome.setTypeface(mActivity.mFonts);
        mSpinner.setTypeface(mActivity.mFonts);
        mAccount.setTypeface(mActivity.mFonts);
        mPassword.setTypeface(mActivity.mFonts);
        mBtnLogin.setTypeface(mActivity.mFonts);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        if (position == 0) {
            authorityLevel = 0;
        } else if (position == 1) {
            authorityLevel = 1;
        }
    }

    private class loginThread extends Thread {
        private int index = 0;
        @Override
        public void run() {
            for(index = 0; index <= 100; index+=10) {
                /**
                 * 接口。
                 * 判断账号密码:
                 * 判断权限等级:authorityLevel = 1为administrator,authorityLevel = 2为proposer
                 */

                Message msg = new Message();
                Bundle bundle = new Bundle();
                bundle.putInt("process", index);
                msg.setData(bundle);
                msg.what = LoginFragment.UPDATE_PROCESS;
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
                case LoginFragment.UPDATE_PROCESS:
                    mBtnLogin.setProgress(msg.getData().getInt("process"));
                    if(msg.getData().getInt("process") == 100) {
                        if (authorityLevel == 0) {
                            mActivity.changeFragment(LoginFragment.this, new AdministratorFragment()
                                    , "administratorFragment", 1, false, false);
                        } else if (authorityLevel == 1) {
                            mActivity.changeFragment(LoginFragment.this, new ApplyFragment()
                                    , "applyFragment", 1, false, false);
                        }

                    }
                    break;
            }
        }
    };


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_personal_login:
                mBtnLogin.setIndeterminateProgressMode(true);
                new loginThread().start();
                break;
            default:
                break;
        }
    }


}
