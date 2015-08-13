package com.hotdoor.products.main;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.andexert.library.RippleView;
import com.hotdoor.products.personal.LoginFragment;
import com.hotdoor.products.product.ProductFragment;
import com.hotdoor.textview.MyTextView;

import java.util.Timer;
import java.util.TimerTask;

import java.util.logging.LogRecord;

import me.relex.circleindicator.CircleIndicator;

/**
 * Created by Yip on 2015/8/3.
 */
public class ProductActivity extends Activity {

    public static String[] titleBuffer = new String[]{"产品中心","",""};
    public static ImageView imagProCollect;

    private ImageView imagProductLeft;
    private ImageView imagProdLeftIcon;
    private MyTextView textProdTitle;
    private ProductFragment fragProduct;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.product);
        init();
        setDefaultFragment();
    }

    private void setDefaultFragment() {
        FragmentManager fm = getFragmentManager();
        FragmentTransaction transaction = fm.beginTransaction();
        fragProduct = new ProductFragment();
        fragProduct.setTitleText(textProdTitle);
        transaction.add(R.id.fl_product, fragProduct, "productFragment");
        transaction.commit();
    }


    private void init() {

        imagProdLeftIcon = (ImageView) findViewById(R.id.iv_product_left_icon);
        imagProductLeft = (ImageView) findViewById(R.id.iv_product_left);
        textProdTitle = (MyTextView) findViewById(R.id.mtv_product_title);
        imagProCollect = (ImageView) findViewById(R.id.iv_product_collect_button);

        imagProductLeft.setOnClickListener(new myClickListener());
        imagProdLeftIcon.setOnClickListener(new myClickListener());

    }

    @Override
    public void onBackPressed() {
        Log.d("onBackPressed","back");
        popBackFragment();
       // super.onBackPressed();
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

    private void popBackFragment() {
        if (getFragmentManager().findFragmentByTag("showfragment") != null) {   //从showfragment退回
            getFragmentManager().popBackStack();
            textProdTitle.setText(titleBuffer[1]);
            imagProCollect.setVisibility(View.INVISIBLE);
            imagProCollect.setOnClickListener(null);
        }else if (getFragmentManager().findFragmentByTag("FragProList") != null) {   //从FragProList退回
            getFragmentManager().popBackStack();
            textProdTitle.setText(titleBuffer[0]);
            imagProCollect.setVisibility(View.INVISIBLE);
            imagProCollect.setOnClickListener(null);
        } else {   //从ProductFragmnet退回
            Intent intent = new Intent(MainActivity.ACTION_MAINACTIVITY);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            this.startActivity(intent);
            this.finish();
        }
    }

}
