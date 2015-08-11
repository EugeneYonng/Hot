package com.hotdoor.products.personal;

import android.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hotdoor.products.main.R;

/**
 * Created by Yip on 2015/8/10.
 */
public class CollectFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.personal_fragment_personal, container, false);
        init(view);
        return view;
    }

    private void init(View view) {
        Log.i("login", "" + getFragmentManager().findFragmentByTag("loginFragment"));
        Log.i("personal", "" + getFragmentManager().findFragmentByTag("personalFragment"));
        Log.i("collect", "" + getFragmentManager().findFragmentByTag("collectFragment"));
    }
}
