package com.hotdoor.products.method;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hotdoor.products.main.R;

/**
 * Created by Administrator on 2015/8/15.
 */
public class MethodShowFragment extends Fragment{

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.method_fragment_list, container, false);
        init(view);
        return view;
    }

    private void init(View view) {
    }
}
