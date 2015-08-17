package com.hotdoor.products.main;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.hotdoor.products.service.ServiceFragment;

/**
 * Created by Yip on 2015/8/3.
 */
public class ServiceActivity extends Activity implements View.OnClickListener {

    private ImageView serviceLeft;
    private ImageView serviceLeftIcon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.service);

        init();

        if (savedInstanceState == null)
            setDefaultFragment();
    }

    private void init() {
        serviceLeft = (ImageView) findViewById(R.id.iv_service_left);
        serviceLeftIcon = (ImageView) findViewById(R.id.iv_service_left_icon);

        serviceLeft.setOnClickListener(this);
        serviceLeftIcon.setOnClickListener(this);

    }

    private void setDefaultFragment() {
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        transaction.add(R.id.fl_service_main, new ServiceFragment(), "serviceFragment");
        transaction.commit();
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
            transaction.add(R.id.fl_service_main, to, tag).commit();
        } else {
            if (!to.isAdded()) {
                transaction.hide(from).add(R.id.fl_service_main, to, tag).commit();
            } else {
                transaction.hide(from).show(to).commit();
            }
        }
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.iv_service_left || v.getId() == R.id.iv_service_left_icon) {


            Intent intent = new Intent(MainActivity.ACTION_MAINACTIVITY);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            ServiceActivity.this.startActivity(intent);
            finish();
        }
    }

}
