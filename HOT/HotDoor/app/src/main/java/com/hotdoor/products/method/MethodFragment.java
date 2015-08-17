package com.hotdoor.products.method;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.hotdoor.list.ProductListString;
import com.hotdoor.products.main.MainActivity;
import com.hotdoor.products.main.MethodActivity;
import com.hotdoor.products.main.R;
import com.hotdoor.regularHexagon.RegularHexagonView;
import com.hotdoor.textview.MyTextView;

/**
 * Created by Administrator on 2015/8/15.
 */
public class MethodFragment extends Fragment{

    private RelativeLayout mSmartCmp;
    private RelativeLayout mSmartCampus;
    private RegularHexagonView mSecurityInfo;
    private RegularHexagonView mRuralInfo;
    private RegularHexagonView mMoveWork;
    private RegularHexagonView mHotelInfo;
    private RegularHexagonView mFinanceInfo;
    private RegularHexagonView mLibInfo;
    private RegularHexagonView mIndustryInfo;

    private FragmentTransaction transaction;
    private MyTextView textTitle;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.method_fragment_list, container, false);
        transaction = getFragmentManager().beginTransaction();
        init(view);
        return view;
    }

    private void init(View view) {
        mSmartCampus = (RelativeLayout) view.findViewById(R.id.rl_smart_campus);
        mSmartCmp = (RelativeLayout) view.findViewById(R.id.rl_smart_company);

        mSecurityInfo = (RegularHexagonView) view.findViewById(R.id.rhv_security_informatization);
        mRuralInfo = (RegularHexagonView) view.findViewById(R.id.rhv_rural_informatization);
        mMoveWork = (RegularHexagonView) view.findViewById(R.id.rhv_move_working);
        mHotelInfo = (RegularHexagonView) view.findViewById(R.id.rhv_hotel_informatization);
        mFinanceInfo = (RegularHexagonView) view.findViewById(R.id.rhv_financial_informatization);
        mLibInfo = (RegularHexagonView) view.findViewById(R.id.rhv_library_cloud);
        mIndustryInfo = (RegularHexagonView) view.findViewById(R.id.rhv_industry_informatization);
        
        MyListener myListener = new MyListener();

        mSmartCampus.setOnClickListener(myListener);
        mSmartCmp.setOnClickListener(myListener);
        mSecurityInfo.setOnClickListener(myListener);
        mRuralInfo.setOnClickListener(myListener);
        mMoveWork.setOnClickListener(myListener);
        mHotelInfo.setOnClickListener(myListener);
        mFinanceInfo.setOnClickListener(myListener);
        mLibInfo.setOnClickListener(myListener);
        mIndustryInfo.setOnClickListener(myListener);

    }

    public void setTitleText(MyTextView title) {
        Log.d("setTitleText","");
        this.textTitle = title;
    }

    private class MyListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            MethodShowFragment methshowFragment = new MethodShowFragment();
            switch (v.getId()) {
                case R.id.rl_smart_campus:
                    //设置MethodShowFragMent需要显示的内容
                    Log.d("aaaaa","智慧校园");
                    MethodActivity.titleBuffer[1] = "智慧校园";
                    methshowFragment.setMethodShowID(ProductListString.SMART_CAMPUS);
                    Log.d("bbbbbbb","智慧校园");
                    break;
                case R.id.rl_smart_company:
                    MethodActivity.titleBuffer[1] = "智慧企业";
                    methshowFragment.setMethodShowID(ProductListString.SMART_COMPANY);
                    break;
                case R.id.rhv_financial_informatization:
                    MethodActivity.titleBuffer[1] = "金融信息化";
                    methshowFragment.setMethodShowID(ProductListString.FINANCIAL_INFORMATIZATION);
                    break;
                case R.id.rhv_hotel_informatization:
                    MethodActivity.titleBuffer[1] = "酒店信息化";
                    methshowFragment.setMethodShowID(ProductListString.HOTEL_INFORMATIZATION);
                    break;
                case R.id.rhv_industry_informatization:
                    MethodActivity.titleBuffer[1] = "工业信息化";
                    methshowFragment.setMethodShowID(ProductListString.INDUSTRY_INFORMATIZATION);
                    break;
                case R.id.rhv_security_informatization:
                    MethodActivity.titleBuffer[1] = "信息安全";
                    methshowFragment.setMethodShowID(ProductListString.SECURITY_INFORMATIZATION);
                    break;
                case R.id.rhv_library_cloud:
                    MethodActivity.titleBuffer[1] = "云图书馆";
                    methshowFragment.setMethodShowID(ProductListString.LIBRARY_CLOUD);
                    break;
                case R.id.rhv_move_working:
                    MethodActivity.titleBuffer[1] = "移动办公";
                    methshowFragment.setMethodShowID(ProductListString.MOVE_WORKING);
                    break;
                case R.id.rhv_rural_informatization:
                    MethodActivity.titleBuffer[1] = "农村信息化";
                    methshowFragment.setMethodShowID(ProductListString.RURAL_INFORMATIZATION);
                    break;
            }
            if( MethodFragment.this.textTitle == null){
                Log.d("aaaa","textTitle is null");
            }

//            MethodFragment.this.textTitle.setText(MethodActivity.titleBuffer[1]);   //切换标题
            MethodActivity.methodTitle.setText(MethodActivity.titleBuffer[1]);
            transaction.replace(R.id.fl_method, methshowFragment, "methodShowFragment");
            transaction.addToBackStack(null);
            transaction.commit();
        }
    }

}
