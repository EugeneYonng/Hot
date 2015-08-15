package com.hotdoor.products.method;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.hotdoor.products.main.MainActivity;
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
        this.textTitle = title;
    }

    private class MyListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            MethodShowFragment methshowFragment = new MethodShowFragment();
            switch (v.getId()) {
                case R.id.rl_smart_campus:
                    //设置MethodShowFragMent需要显示的内容
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

            transaction.replace(R.id.fl_method, methshowFragment, "methodShowFragment");
            transaction.addToBackStack(null);
            transaction.commit();
        }
    }

}
