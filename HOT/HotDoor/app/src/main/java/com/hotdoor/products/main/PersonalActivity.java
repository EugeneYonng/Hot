package com.hotdoor.products.main;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.hotdoor.products.personal.LoginFragment;

/**
 * Created by Yip on 2015/8/3.
 */
public class PersonalActivity extends Activity implements View.OnClickListener {
    ImageView mPersonalLeftImg;
    ImageView mPersonalLeftIcon;
    public TextView mPersonalLeftTitle;

    LoginFragment loginFragment;

    public static Typeface mFonts;

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
        mPersonalLeftTitle = (TextView) findViewById(R.id.tv_personal_title);

        mPersonalLeftImg.setOnClickListener(this);
        mPersonalLeftIcon.setOnClickListener(this);

        /**
         * create font entity
         */
        mFonts = Typeface.createFromAsset(getAssets(), "fonts/youyuan.ttf");

        setFonts();
    }

    private void setFonts() {
        mPersonalLeftTitle.setTypeface(mFonts);
    }

    private void setDefaultFragment() {
        FragmentManager fm = getFragmentManager();
        FragmentTransaction transaction = fm.beginTransaction();
        loginFragment = new LoginFragment();
        transaction.add(R.id.fl_personal_main, loginFragment, "loginFragment");
        transaction.commit();
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.iv_personal_left || v.getId() == R.id.iv_personal_icon_left) {
            popBackFragment();
        }
    }

    private void popBackFragment() {
        if (getFragmentManager().findFragmentByTag("collectFragment") != null) {
            getFragmentManager().popBackStack();
        } else if (getFragmentManager().findFragmentByTag("registerEnterFragment") != null) {
            /**
             * 如果注册完成进入个人界面，那么此时退出将会退出activity
             */
            Intent intent = new Intent(MainActivity.ACTION_MAINACTIVITY);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            this.startActivity(intent);
            this.finish();
        } else if (getFragmentManager().findFragmentByTag("registerFragment") != null) {
            getFragmentManager().popBackStack();
        } else {
            Intent intent = new Intent(MainActivity.ACTION_MAINACTIVITY);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            this.startActivity(intent);
            this.finish();
        }
    }

    @Override
    public void onBackPressed() {
        popBackFragment();
    }
}
