package com.hotdoor.products.service;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.hotdoor.products.main.R;
import com.hotdoor.products.main.ServiceActivity;

/**
 * Created by Yip on 2015/8/16.
 */
public class ServiceFragment extends Fragment implements View.OnClickListener {

    private LinearLayout servicePolicy;
    private LinearLayout serviceSell;
    private LinearLayout faultDiagnosis;
    private LinearLayout onlineSupport;

    ServiceActivity mActivity;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.service_fragment_main, container, false);

        init(view);

        return view;
    }

    private void init(View view) {
        mActivity = (ServiceActivity) getActivity();

        servicePolicy = (LinearLayout) view.findViewById(R.id.ll_service_policy);
        serviceSell = (LinearLayout) view.findViewById(R.id.ll_service_sell);
        faultDiagnosis = (LinearLayout) view.findViewById(R.id.ll_fault_diagnosis);
        onlineSupport = (LinearLayout) view.findViewById(R.id.ll_online_support);

        servicePolicy.setOnClickListener(this);
        serviceSell.setOnClickListener(this);
        faultDiagnosis.setOnClickListener(this);
        onlineSupport.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ll_service_policy:
                mActivity.changeFragment(this, new PolicyFragment(), "policyFragment", 1, true, true);
                break;
            case R.id.ll_service_sell:
                mActivity.changeFragment(this, new AfterSellFragment(), "afterSellFragment", 1, true, true);
                break;
            case R.id.ll_fault_diagnosis:
                mActivity.changeFragment(this, new FaultDiagnosisFragment(), "faultDiagnosisFragment", 1, true, true);
                break;
            case R.id.ll_online_support:
                mActivity.changeFragment(this, new OnlineSupportFragment(), "onlineSupportFragment", 1, true, true);
                break;
            default:
                break;
        }
    }
}
