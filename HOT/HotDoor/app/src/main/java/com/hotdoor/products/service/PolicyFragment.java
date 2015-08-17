package com.hotdoor.products.service;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hotdoor.products.main.R;

/**
 * Created by Yip on 2015/8/16.
 */
public class PolicyFragment extends Fragment implements View.OnClickListener {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.service_fragment_policy, container, false);

        init(view);
        
        return view;
    }

    private void init(View view) {
    }

    @Override
    public void onClick(View v) {

    }
}
