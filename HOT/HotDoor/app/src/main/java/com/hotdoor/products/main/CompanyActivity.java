package com.hotdoor.products.main;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

/**
 * Created by Yip on 2015/8/3.
 */
public class CompanyActivity extends Activity {
    TextView view;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.company);

    }
}
