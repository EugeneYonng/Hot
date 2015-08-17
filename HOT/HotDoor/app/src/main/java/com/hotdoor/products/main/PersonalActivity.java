package com.hotdoor.products.main;

import android.app.Activity;
import android.app.Fragment;
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

        if (savedInstanceState == null)
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
        if (getFragmentManager().findFragmentByTag("submitedFragment") != null) {
            getFragmentManager().popBackStack();
        } else if (getFragmentManager().findFragmentByTag("collectFragment") != null) {
            getFragmentManager().popBackStack();
        } else if (getFragmentManager().findFragmentByTag("sellFragment") != null) {
            getFragmentManager().popBackStack();
        } else if (getFragmentManager().findFragmentByTag("registerEnterFragment") != null) {
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

    public void changeFragment(Fragment from, Fragment to, String tag, int flag, boolean pop, boolean replace) {
        if (from == null || to == null)
            return;

        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        if (flag == 0) {//alpha animation
            transaction.setCustomAnimations(R.anim.fragment_alpha_in, R.anim.fragment_alpha_out
                    , R.anim.fragment_alpha_in, R.anim.fragment_alpha_out);
        } else if (flag == 1) {//slide animation
            transaction.setCustomAnimations(R.anim.fragment_slide_in_right, R.anim.fragment_slide_out_left
                    , R.anim.fragment_slide_in_right, R.anim.fragment_slide_out_left);
        }

        if (pop == true)
            transaction.addToBackStack(null);

        if (replace == true) {
            transaction.remove(from);
            transaction.add(R.id.fl_personal_main, to, tag).commit();
        } else {
            if (!to.isAdded()) {
                transaction.hide(from).add(R.id.fl_personal_main, to, tag).commit();
            } else {
                transaction.hide(from).show(to).commit();
            }
        }
    }

    @Override
    public void onBackPressed() {
        popBackFragment();
    }
}
