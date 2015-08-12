package com.hotdoor.products.personal;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.gc.materialdesign.views.CheckBox;
import com.hotdoor.products.main.R;
import com.hotdoor.textview.MyTextView;

/**
 * Created by Yip on 2015/8/10.
 */
public class ProtocolFragment extends Fragment implements View.OnClickListener {

    CheckBox mCbProtocol;
    MyTextView mBtnProtocol;

    FillInfoFragment fillInfoFragment;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.personal_fragment_register_step_first, container, false);
        init(v);
        return v;
    }

    private void init(View view) {
        mCbProtocol = (CheckBox) view.findViewById(R.id.cb_personal_protocol);
        mBtnProtocol = (MyTextView) view.findViewById(R.id.btn_personal_protocol_next_step);

        mBtnProtocol.setOnClickListener(this);

        int num = getActivity().getFragmentManager().getBackStackEntryCount();
        Log.i("Fragment", "Fragment回退栈数量：" + num);
        Log.i("this", "" + this);
        for (int i = 0; i < num; i++) {
            Log.i("Fragment", "" + getActivity().getFragmentManager().getBackStackEntryAt(i).getId());
        }
        Log.i("分割线", "-----------------------------------------");
    }

    @Override
    public void onClick(View v) {
        fillInfoFragment = new FillInfoFragment();

        FragmentTransaction transaction = getActivity().getFragmentManager().beginTransaction();
//        transaction.setCustomAnimations(R.anim.fragment_slide_in_right, R.anim.fragment_slide_out_left
//                , R.anim.fragment_slide_in_right, R.anim.fragment_slide_out_left);

        if (mCbProtocol.isCheck() == true) {
            transaction.hide(this).add(R.id.fl_personal_main, fillInfoFragment);
            transaction.addToBackStack(null);
            transaction.commit();
        }
    }
}
