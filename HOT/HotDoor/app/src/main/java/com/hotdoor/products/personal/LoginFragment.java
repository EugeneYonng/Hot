package com.hotdoor.products.personal;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.gc.materialdesign.views.ButtonRectangle;
import com.gc.materialdesign.views.CheckBox;
import com.hotdoor.products.main.R;


/**
 * Created by Yip on 2015/8/10.
 */
public class LoginFragment extends Fragment implements View.OnClickListener{

    EditText mAccount;
    EditText mPassword;
    CheckBox mCbRemember;
    CheckBox mCbAutoLogin;
    ButtonRectangle mBtnLogin;
    ButtonRectangle mBtnRegister;

    PersonalFragment personalFragment;
    ProtocolFragment protocolFragment;

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
        mBtnLogin = (ButtonRectangle) view.findViewById(R.id.btn_personal_login);
        mBtnRegister = (ButtonRectangle) view.findViewById(R.id.btn_personal_register);

        mBtnLogin.setOnClickListener(this);
        mBtnRegister.setOnClickListener(this);

        Log.i("login", "" + getFragmentManager().findFragmentByTag("loginFragment"));
        Log.i("personal", "" + getFragmentManager().findFragmentByTag("personalFragment"));
        Log.i("collect", "" + getFragmentManager().findFragmentByTag("collectFragment"));
    }


    @Override
    public void onClick(View v) {
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        personalFragment = new PersonalFragment();
        protocolFragment = new ProtocolFragment();

        switch (v.getId()) {
            case R.id.btn_personal_login:
                transaction.remove(this);
//                transaction.replace(R.id.fl_personal_main, personalFragment, "personalFragment");
                transaction.add(R.id.fl_personal_main, personalFragment, "personalFragment");
                transaction.addToBackStack(null);
                transaction.commit();
                break;
            case R.id.btn_personal_register:
                transaction.replace(R.id.fl_personal_main, protocolFragment, "protocolFragment");
                transaction.addToBackStack(null);
                transaction.commit();
                break;
            default:
                break;
        }
    }
}
