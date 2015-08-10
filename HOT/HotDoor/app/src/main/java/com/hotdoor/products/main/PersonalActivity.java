package com.hotdoor.products.main;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.hotdoor.products.personal.LoginFragment;

/**
 * Created by Yip on 2015/8/3.
 */
public class PersonalActivity extends Activity implements View.OnClickListener {
    ImageView mPersonalLeftImg;
    ImageView mPersonalLeftIcon;

    LoginFragment loginFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.personal);

        init();

        setDefaultFragment();
    }

    private void init() {
        mPersonalLeftImg = (ImageView) findViewById(R.id.iv_personal_left);
        mPersonalLeftIcon = (ImageView) findViewById(R.id.iv_personal_icon_left);

        mPersonalLeftImg.setOnClickListener(this);
        mPersonalLeftIcon.setOnClickListener(this);
    }

    private void setDefaultFragment() {
        FragmentManager fm = getFragmentManager();
        FragmentTransaction transaction = fm.beginTransaction();
        loginFragment = new LoginFragment();
        transaction.add(R.id.fl_personal_main, loginFragment);
        transaction.commit();
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.iv_personal_left || v.getId() == R.id.iv_personal_icon_left) {
            Intent intent = new Intent(MainActivity.ACTION_MAINACTIVITY);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            this.startActivity(intent);
            this.finish();
        }
    }
}
