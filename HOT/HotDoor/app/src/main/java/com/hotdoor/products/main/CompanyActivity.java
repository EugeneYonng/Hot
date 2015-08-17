package com.hotdoor.products.main;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;

import com.hotdoor.adapter.CmpAdapter;
import com.hotdoor.list.CmpListItem;
import com.hotdoor.products.company.CompanyFragment;
import com.hotdoor.products.method.MethodFragment;
import com.hotdoor.textview.MyTextView;

import java.util.ArrayList;

/**
 * Created by Yip on 2015/8/3.
 */
public class CompanyActivity extends Activity implements View.OnClickListener {


    ImageView mCmpLeftImg;
    ImageView cmpLeftIcon;
    public static MyTextView textTitle;
    Fragment cmpFragment;
    public static String[] titleBuffer=new String[] {"公司简介",""};



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.company);
        init();

        if (savedInstanceState == null)
            setDefaultFragment();
    }

    private void setDefaultFragment() {
        FragmentManager fm = getFragmentManager();
        FragmentTransaction transaction = fm.beginTransaction();
        cmpFragment = new CompanyFragment();
        transaction.add(R.id.fl_company_main, cmpFragment, "companyFragment");
        transaction.commit();
    }

    private void init() {
        mCmpLeftImg = (ImageView) findViewById(R.id.ic_company_left);
        cmpLeftIcon = (ImageView) findViewById(R.id.iv_company_left_icon);
        textTitle = (MyTextView) findViewById(R.id.text_main);
        mCmpLeftImg.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_company_left_icon:
            case R.id.ic_company_left:
                popBackFragment();
                break;
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
            transaction.add(R.id.fl_company_main, to, tag).commit();
        } else {
            if (!to.isAdded()) {
                transaction.hide(from).add(R.id.fl_company_main, to, tag).commit();
            } else {
                transaction.hide(from).show(to).commit();
            }
        }
    }

    @Override
    public void onBackPressed() {
        Log.d("onBackPressed", "back");
        popBackFragment();
    }

    private void popBackFragment() {
        if (getFragmentManager().findFragmentByTag("showFragment") != null) {
            getFragmentManager().popBackStack();
            textTitle.setText(titleBuffer[0]);

        }else{
            Intent intent = new Intent(MainActivity.ACTION_MAINACTIVITY);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            this.startActivity(intent);
            this.finish();
        }

    }
}
