package com.hotdoor.products.main;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.hotdoor.regularHexagon.RegularHexagonView;
import com.hotdoor.textview.MyTextView;

/**
 * Created by Yip on 2015/8/3.
 */
public class MethodActivity extends Activity {

    private ImageView methodLeft;
    private ImageView methodIcon;
    private MyTextView methodTitle;
    private RelativeLayout mSmartCmp;
    private RelativeLayout mSmartCampus;
    private RegularHexagonView mSecurityInfo;
    private RegularHexagonView mRuralInfo;
    private RegularHexagonView mMoveWork;
    private RegularHexagonView mHotelInfo;
    private RegularHexagonView mFinanceInfo;
    private RegularHexagonView mLibInfo;
    private RegularHexagonView mIndustryInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.method);

        init();
    }

    private void init() {
        methodLeft = (ImageView) findViewById(R.id.iv_method_left);
        methodIcon = (ImageView) findViewById(R.id.iv_method_icon_left);
        methodTitle = (MyTextView) findViewById(R.id.tv_method_title);
        mSmartCampus = (RelativeLayout) findViewById(R.id.rl_smart_campus);
        mSmartCmp = (RelativeLayout) findViewById(R.id.rl_smart_company);

        mSecurityInfo = (RegularHexagonView) findViewById(R.id.rhv_security_informatization);
        mRuralInfo = (RegularHexagonView) findViewById(R.id.rhv_rural_informatization);
        mMoveWork = (RegularHexagonView) findViewById(R.id.rhv_move_working);
        mHotelInfo = (RegularHexagonView) findViewById(R.id.rhv_hotel_informatization);
        mFinanceInfo = (RegularHexagonView) findViewById(R.id.rhv_financial_informatization);
        mLibInfo = (RegularHexagonView) findViewById(R.id.rhv_library_cloud);
        mIndustryInfo = (RegularHexagonView) findViewById(R.id.rhv_industry_informatization);

        methodLeft.setOnClickListener(new myListener());
        methodTitle.setOnClickListener(new myListener());
        methodIcon.setOnClickListener(new myListener());

        mSecurityInfo.setOnClickListener(new myListener());
        mRuralInfo.setOnClickListener(new myListener());
        mMoveWork.setOnClickListener(new myListener());
        mHotelInfo.setOnClickListener(new myListener());
        mFinanceInfo.setOnClickListener(new myListener());
        mLibInfo.setOnClickListener(new myListener());
        mIndustryInfo.setOnClickListener(new myListener());
    }

    private class myListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.iv_method_left:
                case R.id.iv_method_icon_left:
                case R.id.tv_method_title:
                    Intent intent = new Intent(MainActivity.ACTION_MAINACTIVITY);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    MethodActivity.this.startActivity(intent);
                    finish();
                    break;
                case R.id.rl_smart_campus:
                    break;
                case R.id.rl_smart_company:
                    break;
                case R.id.rhv_financial_informatization:
                    break;
                case R.id.rhv_hotel_informatization:
                    break;
                case R.id.rhv_industry_informatization:
                    break;
                case R.id.rhv_security_informatization:
                    break;
                case R.id.rhv_library_cloud:
                    break;
                case R.id.rhv_move_working:
                    break;
                case R.id.rhv_rural_informatization:
                    break;

            }
        }
    }

}
