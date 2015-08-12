package com.hotdoor.products.personal;

import android.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hotdoor.products.main.R;
import com.hotdoor.textview.MyTextView;

/**
 * Created by Yip on 2015/8/12.
 */
public class CertifyInfoFragment extends Fragment implements View.OnClickListener {

    MyTextView mBtnComplete;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.personal_fragment_register_step_third, container, false);

        init(v);

        return v;
    }

    private void init(View view) {

        mBtnComplete = (MyTextView) view.findViewById(R.id.btn_personal_register_complete);

        mBtnComplete.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {

    }
}
