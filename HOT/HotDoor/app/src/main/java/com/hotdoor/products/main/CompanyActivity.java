package com.hotdoor.products.main;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;

import com.hotdoor.adapter.CmpAdapter;
import com.hotdoor.list.CmpListItem;

import java.util.ArrayList;

/**
 * Created by Yip on 2015/8/3.
 */
public class CompanyActivity extends Activity implements View.OnClickListener {
    private final static String[] CMP_TEXT = {"公司介绍", "专利证书", "公司荣誉", "合作伙伴", "联系我们"};
    private final static int[] CMP_ICON = {R.drawable.testimage, R.drawable.testimage, R.drawable.testimage,
            R.drawable.testimage, R.drawable.testimage};

    ImageView mCmpLeftImg;
    ImageView mCmpLeftIcon;
    ListView listCompany;
    ArrayList<CmpListItem> mCmpList = new ArrayList<>();
    CmpAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.company);
        initList();
        init();
    }

    private void init() {
        /** Initialize widgets */
        mCmpLeftImg = (ImageView) findViewById(R.id.iv_company_left);
        mCmpLeftIcon = (ImageView) findViewById(R.id.iv_company_icon_left);
        listCompany = (ListView) findViewById(R.id.list_campany);

        mAdapter = new CmpAdapter(this, mCmpList);
        listCompany.setAdapter(mAdapter);

        mCmpLeftImg.setOnClickListener(this);
    }

    private void initList() {
        for (int i = 0; i < CMP_TEXT.length; i++) {
            CmpListItem mItem = new CmpListItem();
            mItem.setImageId(CMP_ICON[i]);
            mItem.setText(CMP_TEXT[i]);
            mCmpList.add(mItem);
        }
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.iv_company_left || v.getId() == R.id.iv_company_icon_left) {
            Intent intent = new Intent(MainActivity.ACTION_MAINACTIVITY);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            this.startActivity(intent);
            this.finish();
        }
    }
}
