package com.hotdoor.products.main;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import me.relex.circleindicator.CircleIndicator;


public class MainActivity extends Activity implements View.OnClickListener {
    public static final String ACTION_MAINACTIVITY="com.hotdoor.products.main.MainActivity";
    public static final String ACTION_COMPANY = "com.hotdoor.products.main.CompanyActivity";
    public static final String ACTION_PRODUCT = "com.hotdoor.products.main.ProductActivity";
    public static final String ACTION_METHOD = "com.hotdoor.products.main.MethodActivity";
    public static final String ACTION_PERSONAL = "com.hotdoor.products.main.PersonalActivity";
    public static final String ACTION_SERVICE = "com.hotdoor.products.main.ServiceActivity";

    private ViewPager start_pager;
    RelativeLayout rlCompany;
    RelativeLayout rlProduct;
    RelativeLayout rlMethod;
    RelativeLayout rlPersonal;
    RelativeLayout rlService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        init();
    }

    private void init() {
        /*initialize widgets*/
        rlCompany = (RelativeLayout) findViewById(R.id.rl_main_company);
        rlProduct = (RelativeLayout) findViewById(R.id.rl_main_product);
        rlMethod = (RelativeLayout) findViewById(R.id.rl_main_method);
        rlPersonal = (RelativeLayout) findViewById(R.id.rl_main_personal);
        rlService = (RelativeLayout) findViewById(R.id.rl_main_service);

        rlCompany.setOnClickListener(this);
        rlProduct.setOnClickListener(this);
        rlMethod.setOnClickListener(this);
        rlPersonal.setOnClickListener(this);
        rlService.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.rl_main_company:
                startIntent(ACTION_COMPANY);
                break;
            case R.id.rl_main_product:
                startIntent(ACTION_PRODUCT);
                break;
            case R.id.rl_main_method:
                startIntent(ACTION_METHOD);
                break;
            case R.id.rl_main_personal:
                startIntent(ACTION_PERSONAL);
                break;
            case R.id.rl_main_service:
                startIntent(ACTION_SERVICE);
                break;
            default:
                break;
        }
    }

    private void startIntent(String action) {
        Intent intent = new Intent();
        intent.setAction(action);
        this.startActivity(intent);
    }
}
