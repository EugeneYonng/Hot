package com.hotdoor.products.main;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.LinearLayout;

/**
 * Created by Yip on 2015/8/3.
 */
public class ServiceActivity extends Activity {

    private LinearLayout servicePolicy;
    private LinearLayout serviceSell;
    private LinearLayout faultDiagnosis;
    private LinearLayout onlineSupport;
    private ImageView serviceLeft;
    private ImageView serviceLeftIcon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.service);
        init();
    }

    private void init() {
        serviceLeft = (ImageView) findViewById(R.id.iv_service_left);
        serviceLeftIcon = (ImageView) findViewById(R.id.iv_product_left_icon);
        servicePolicy = (LinearLayout) findViewById(R.id.ll_service_policy);
        serviceSell = (LinearLayout) findViewById(R.id.ll_service_sell);
        faultDiagnosis = (LinearLayout) findViewById(R.id.ll_fault_diagnosis);
        onlineSupport = (LinearLayout) findViewById(R.id.ll_online_support);


    }

//    private class myClickListener implements View.OnClickListener {
//        @Override
//        public
//    }
}
