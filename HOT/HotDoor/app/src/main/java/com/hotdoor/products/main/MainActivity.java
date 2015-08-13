package com.hotdoor.products.main;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.andexert.library.RippleView;
import com.romainpiel.shimmer.Shimmer;
import com.romainpiel.shimmer.ShimmerTextView;

import java.util.Timer;
import java.util.TimerTask;

import me.relex.circleindicator.CircleIndicator;


public class MainActivity extends Activity {
    public static final String ACTION_MAINACTIVITY = "com.hotdoor.products.main.MainActivity";
    public static final String ACTION_COMPANY = "com.hotdoor.products.main.CompanyActivity";
    public static final String ACTION_PRODUCT = "com.hotdoor.products.main.ProductActivity";
    public static final String ACTION_METHOD = "com.hotdoor.products.main.MethodActivity";
    public static final String ACTION_PERSONAL = "com.hotdoor.products.main.PersonalActivity";
    public static final String ACTION_SERVICE = "com.hotdoor.products.main.ServiceActivity";

    public static boolean isLogin = true;    //登陆状态
    public static String account = "testaccount3" ;

    private ViewPager pagerMain;
    private RippleView rippCompany;
    private RippleView rippProduct;
    private RippleView rippMethod;
    private RippleView rippPersonal;
    private RippleView rippServer;
    private RippleView rippConnect;
    private ShimmerTextView stvTitle;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        init();

        pagerMain.setAdapter(new PagerAdapter() {
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
        indicator.setViewPager(pagerMain);

        rippCompany.setOnRippleCompleteListener(new myListener());
        rippProduct.setOnRippleCompleteListener(new myListener());
        rippMethod.setOnRippleCompleteListener(new myListener());
        rippPersonal.setOnRippleCompleteListener(new myListener());
        rippServer.setOnRippleCompleteListener(new myListener());

        new Timer().schedule(new myTimeTask(), 0, 4000);
    }

    private void init() {
        pagerMain = (ViewPager) findViewById(R.id.vp_main);
        rippCompany = (RippleView) findViewById(R.id.rv_company);
        rippProduct = (RippleView) findViewById(R.id.rv_product);
        rippMethod = (RippleView) findViewById(R.id.rv_method);
        rippPersonal = (RippleView) findViewById(R.id.rv_person);
        rippServer = (RippleView) findViewById(R.id.rv_server);
        rippConnect = (RippleView) findViewById(R.id.rv_connect);
        stvTitle = (ShimmerTextView) findViewById(R.id.text_main);

        rippCompany.setRippleDuration(100);
        rippMethod.setRippleDuration(100);
        rippPersonal.setRippleDuration(100);
        rippProduct.setRippleDuration(100);
        rippServer.setRippleDuration(100);
        rippConnect.setRippleDuration(100);

        stvTitle.setReflectionColor(0xffffa23c);
        stvTitle.setTypeface(Typeface.createFromAsset(this.getAssets(), "fonts/youyuan.ttf"));
        new Shimmer().setDuration(2000)
                .setStartDelay(2000)
                .setDirection(Shimmer.ANIMATION_DIRECTION_LTR)
                .start(stvTitle);
    }

    private class myTimeTask extends TimerTask {

        @Override
        public void run() {
            mHandler.sendEmptyMessage(0);
        }
    }

    Handler mHandler = new Handler() {
        private int index=0;
        @Override
        public void handleMessage(Message msg) {
            if(index > 2)
                index = 0;
            pagerMain.setCurrentItem(index);
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
                case R.id.rv_connect:
                    Log.d("RippleListener", "connect us");
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
