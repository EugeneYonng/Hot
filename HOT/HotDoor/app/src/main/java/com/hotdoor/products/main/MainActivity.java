package com.hotdoor.products.main;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.RippleDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.andexert.library.RippleView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Timer;
import java.util.TimerTask;

import java.util.logging.LogRecord;

import me.relex.circleindicator.CircleIndicator;


public class MainActivity extends Activity{
    public static final String ACTION_MAINACTIVITY="com.hotdoor.products.main.MainActivity";
    public static final String ACTION_COMPANY = "com.hotdoor.products.main.CompanyActivity";
    public static final String ACTION_PRODUCT = "com.hotdoor.products.main.ProductActivity";
    public static final String ACTION_METHOD = "com.hotdoor.products.main.MethodActivity";
    public static final String ACTION_PERSONAL = "com.hotdoor.products.main.PersonalActivity";
    public static final String ACTION_SERVICE = "com.hotdoor.products.main.ServiceActivity";

    private ViewPager mainPager;
    private RippleView companyRipp;
    private RippleView productRipp;
    private RippleView methodRipp;
    private RippleView personRipp;
    private RippleView serverRipp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        mainPager = (ViewPager)findViewById(R.id.vp_main);
        companyRipp = (RippleView)findViewById(R.id.rv_company);
        productRipp = (RippleView)findViewById(R.id.rv_product);
        methodRipp = (RippleView)findViewById(R.id.rv_method);
        personRipp = (RippleView)findViewById(R.id.rv_person);
        serverRipp = (RippleView)findViewById(R.id.rv_server);

        mainPager.setAdapter(new PagerAdapter() {
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
                TextView text = new TextView(MainActivity.this);
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

        CircleIndicator indicator = (CircleIndicator)findViewById(R.id.ci_main);
        indicator.setViewPager(mainPager);
        companyRipp.setOnRippleCompleteListener(new myListener());
        productRipp.setOnRippleCompleteListener(new myListener());
        methodRipp.setOnRippleCompleteListener(new myListener());
        personRipp.setOnRippleCompleteListener(new myListener());
        serverRipp.setOnRippleCompleteListener(new myListener());

        new Timer().schedule(new myTimeTask(),0,4000);
    }

    private class myTimeTask extends TimerTask{

        @Override
        public void run(){
            myhandler.sendEmptyMessage(0);
        }
    }

    Handler myhandler = new Handler() {
        private int index=0;
        @Override
        public void handleMessage(Message msg){
            if(index > 2)
                index = 0;
            mainPager.setCurrentItem(index);
            index++;
        }
    };

    private class myListener implements RippleView.OnRippleCompleteListener {
        @Override
        public void onComplete(RippleView v){
            switch(v.getId()){
                case R.id.rv_company:
                    startIntent(ACTION_COMPANY);
                    break;
                case R.id.rv_product:
                    startIntent(ACTION_PRODUCT);
                    break;
                case R.id.rv_method:
                    startIntent(ACTION_METHOD);
                    break;
                case R.id.rv_person:
                    startIntent(ACTION_PERSONAL);
                    break;
                case R.id.rv_server:
                    startIntent(ACTION_SERVICE);
                    break;

            }
        }
    }

    private void startIntent(String action) {
        Intent intent = new Intent();
        intent.setAction(action);
        this.startActivity(intent);
    }
}
