package com.hotdoor.products.main;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.andexert.library.RippleView;

import java.util.Timer;
import java.util.TimerTask;

import java.util.logging.LogRecord;

import me.relex.circleindicator.CircleIndicator;

/**
 * Created by Yip on 2015/8/3.
 */
public class ProductActivity extends Activity {

    private ViewPager pagerProduct;
    private CircleIndicator indiProduct;
    private RippleView rippCloudSpeed;
    private RippleView rippCloudSafe;
    private RippleView rippCloudSave;
    private RippleView rippCloudManager;
    private RippleView rippCloudServer;
    private RippleView rippCloudComputer;
    private ImageView imagProductLeft;
    private ImageView imagProdLeftIcon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.product);
        init();
        new Timer().schedule(new myTimeTask(),4000,4000);
    }

    private void init() {
        pagerProduct = (ViewPager) findViewById(R.id.vp_product_pager);
        indiProduct = (CircleIndicator) findViewById(R.id.ci_product_indicator);
        rippCloudSave = (RippleView) findViewById(R.id.rv_cloud_save);
        rippCloudComputer = (RippleView) findViewById(R.id.rv_cloud_computer);
        rippCloudManager = (RippleView) findViewById(R.id.rv_cloud_manager);
        rippCloudSafe = (RippleView) findViewById(R.id.rv_cloud_safe);
        rippCloudServer = (RippleView) findViewById(R.id.rv_smart_cloud_server);
        rippCloudSpeed = (RippleView) findViewById(R.id.rv_cloud_speed);
        imagProdLeftIcon = (ImageView) findViewById(R.id.iv_product_left_icon);
        imagProductLeft = (ImageView) findViewById(R.id.iv_product_left);

        rippCloudSpeed.setRippleDuration(200);
        rippCloudServer.setRippleDuration(200);
        rippCloudSafe.setRippleDuration(200);
        rippCloudManager.setRippleDuration(200);
        rippCloudComputer.setRippleDuration(200);
        rippCloudSave.setRippleDuration(200);

        rippCloudComputer.setOnRippleCompleteListener(new myRippListener());
        rippCloudManager.setOnRippleCompleteListener(new myRippListener());
        rippCloudServer.setOnRippleCompleteListener(new myRippListener());
        rippCloudSafe.setOnRippleCompleteListener(new myRippListener());
        rippCloudSpeed.setOnRippleCompleteListener(new myRippListener());
        rippCloudSave.setOnRippleCompleteListener(new myRippListener());
        imagProductLeft.setOnClickListener(new myClickListener());
        imagProdLeftIcon.setOnClickListener(new myClickListener());


        pagerProduct.setAdapter(new PagerAdapter() {
            @Override
            public int getCount() {
                return 3;
            }

            @Override
            public boolean isViewFromObject(View view, Object object) {
                return view == object;
            }

            @Override
            public void destroyItem(ViewGroup container, int position, Object obj) {
                container.removeView((View) obj);
            }

            @Override
            public Object instantiateItem(ViewGroup container, final int position) {
                TextView text = new TextView(ProductActivity.this);
                text.setGravity(Gravity.CENTER);
                text.setTextSize(30);
                text.setTextColor(Color.WHITE);
                text.setText("Page " + position);
                text.setPadding(30, 30, 30, 30);
                int bg = Color.rgb((int) Math.floor(Math.random() * 128) + 64,
                        (int) Math.floor(Math.random() * 128) + 64,
                        (int) Math.floor(Math.random() * 128) + 64);
                text.setBackgroundColor(bg);
                container.addView(text, ViewGroup.LayoutParams.MATCH_PARENT,
                        ViewGroup.LayoutParams.MATCH_PARENT);

                return text;
            }

        });

        indiProduct.setViewPager(pagerProduct);

    }

    private class myTimeTask extends TimerTask {
        @Override
        public void run() {
            myhandler.sendEmptyMessage(0);
        }
    }

    private class myClickListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            switch(v.getId()) {
                case R.id.iv_product_left:
                case R.id.iv_product_left_icon:
                    Intent intent = new Intent(MainActivity.ACTION_MAINACTIVITY);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(intent);
                    finish();
                    break;
            }
        }
    }

    Handler myhandler = new Handler() {
        private int index = 0;
       @Override
        public void handleMessage(Message msg) {
           if(index > 2)
               index = 0;
           pagerProduct.setCurrentItem(index);
           index++;

       }

    };

    private class myRippListener implements RippleView.OnRippleCompleteListener {
        @Override
        public void onComplete(RippleView v) {
            switch(v.getId()){
                case R.id.rv_cloud_computer:
                    break;
                case R.id.rv_cloud_manager:
                    break;
                case R.id.rv_cloud_safe:
                    break;
                case R.id.rv_cloud_save:
                    break;
                case R.id.rv_smart_cloud_server:
                    break;
                case R.id.rv_cloud_speed:
                    break;
            }
        }
    }
}
