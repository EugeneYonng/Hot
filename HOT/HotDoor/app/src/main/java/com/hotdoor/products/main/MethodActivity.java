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
import android.widget.RelativeLayout;

import com.hotdoor.products.method.MethodFragment;
import com.hotdoor.products.product.ProductFragment;
import com.hotdoor.regularHexagon.RegularHexagonView;
import com.hotdoor.textview.MyTextView;

/**
 * Created by Yip on 2015/8/3.
 */
public class MethodActivity extends Activity {

    public static String[] titleBuffer = new String[]{"解决方案",""};
    public static ImageView imagMethodCollect;

    private ImageView methodLeft;
    private ImageView methodIcon;
    private MyTextView methodTitle;
    private MethodFragment fragMethodList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.method);
        setDefaultFragment();
        init();
    }

    private void setDefaultFragment() {
        FragmentManager fm = getFragmentManager();
        FragmentTransaction transaction = fm.beginTransaction();
        fragMethodList = new MethodFragment();
        transaction.add(R.id.fl_method, fragMethodList, "methodFragment");
        transaction.commit();
    }

    private void init() {
        methodLeft = (ImageView) findViewById(R.id.iv_method_left);
        methodIcon = (ImageView) findViewById(R.id.iv_method_icon_left);
        methodTitle = (MyTextView) findViewById(R.id.tv_method_title);
        imagMethodCollect = (ImageView) findViewById(R.id.iv_method_collect_button);

        methodLeft.setOnClickListener(new myClickListener());
        methodIcon.setOnClickListener(new myClickListener());

    }

    private class myClickListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            switch(v.getId()) {
                case R.id.iv_product_left:
                case R.id.iv_product_left_icon:
                    popBackFragment();
                    break;
            }
        }
    }

    @Override
    public void onBackPressed() {
        Log.d("onBackPressed", "back");
        popBackFragment();
    }

    private void popBackFragment() {
        if (getFragmentManager().findFragmentByTag("methodShowFragment") != null) {
            getFragmentManager().popBackStack();
            methodTitle.setText(titleBuffer[0]);
            imagMethodCollect.setVisibility(View.INVISIBLE);
            imagMethodCollect.setOnClickListener(null);
        }else{
            Intent intent = new Intent(MainActivity.ACTION_MAINACTIVITY);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            this.startActivity(intent);
            this.finish();
        }

//        if (getFragmentManager().findFragmentByTag("showfragment") != null) {   //从showfragment退回
//            getFragmentManager().popBackStack();
//            textProdTitle.setText(titleBuffer[1]);
//            imagMethodCollect.setVisibility(View.INVISIBLE);
//            imagMethodCollect.setOnClickListener(null);
//        }else if (getFragmentManager().findFragmentByTag("FragProList") != null) {   //从FragProList退回
//            getFragmentManager().popBackStack();
//            textProdTitle.setText(titleBuffer[0]);
//            imagMethodCollect.setVisibility(View.INVISIBLE);
//            imagMethodCollect.setOnClickListener(null);
//        } else {   //从ProductFragmnet退回
//            Intent intent = new Intent(MainActivity.ACTION_MAINACTIVITY);
//            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//            this.startActivity(intent);
//            this.finish();
//        }
    }



}
