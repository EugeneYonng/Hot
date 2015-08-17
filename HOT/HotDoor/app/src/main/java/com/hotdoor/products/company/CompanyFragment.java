package com.hotdoor.products.company;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.hotdoor.adapter.CmpAdapter;
import com.hotdoor.list.CmpListItem;
import com.hotdoor.products.main.CompanyActivity;
import com.hotdoor.products.main.R;

import java.sql.Time;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;


import me.relex.circleindicator.CircleIndicator;

/**
 * Created by Administrator on 2015/8/17.
 */
public class CompanyFragment extends Fragment {

    private final static String[] CMP_TEXT = {"公司介绍", "专利证书", "公司荣誉", "合作伙伴", "联系我们"};
    private final static int[] CMP_ICON = {R.drawable.testimage, R.drawable.testimage, R.drawable.testimage,
            R.drawable.testimage, R.drawable.testimage};
    private final static int UPDATE_VIEWPAGER = 0;

    private FragmentTransaction transaction;

    ListView listCompany;
    ArrayList<CmpListItem> mCmpList = new ArrayList<>();
    CmpAdapter mAdapter;

    private ViewPager mViewPager;
    private CompanyActivity mContext;
    private CircleIndicator mIndicator;
    private Timer mTimer;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.company_fragment_list, container, false);
        initList();
        mContext = (CompanyActivity)getActivity();
        transaction = getFragmentManager().beginTransaction();
        init(view);
        return view;
    }

    private void init(View v) {
        /** Initialize widgets */
        listCompany = (ListView) v.findViewById(R.id.list_campany);
        mViewPager = (ViewPager) v.findViewById(R.id.vp_company);
        mIndicator = (CircleIndicator) v.findViewById(R.id.ci_company);

        mViewPager.setAdapter(new PagerAdapter() {
            @Override
            public int getCount() {
                return 3;
            }

            @Override
            public boolean isViewFromObject(View view, Object object) {
                return view == object;
            }

            @Override
            public void destroyItem(ViewGroup container, int position, Object obj) {
                container.removeView((View) obj);
            }

            @Override
            public Object instantiateItem(ViewGroup container, final int position) {
                TextView text = new TextView(getActivity());
                text.setGravity(Gravity.CENTER);
                text.setTextSize(30);
                text.setTextColor(Color.WHITE);
                text.setText("Page " + position);
                text.setPadding(30, 30, 30, 30);
                int bg = Color.rgb((int) Math.floor(Math.random() * 128) + 64,
                        (int) Math.floor(Math.random() * 128) + 64,
                        (int) Math.floor(Math.random() * 128) + 64);
                text.setBackgroundColor(bg);
                container.addView(text, ViewGroup.LayoutParams.MATCH_PARENT,
                        ViewGroup.LayoutParams.MATCH_PARENT);

                return text;
            }

        });
        mIndicator.setViewPager(mViewPager);
        Log.d("mCmpList","Size"+mCmpList.size());
        mAdapter = new CmpAdapter(getActivity(), mCmpList);
        listCompany.setAdapter(mAdapter);

        listCompany.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                CompanyShowFragment showFragment = new CompanyShowFragment();
                switch (position) {
                    case 0:
                        Log.d("onItemClick", "公司简介");
                        showFragment.setLayoutID(CompanyShowFragment.COMPANY_INTRODUCT);
                        CompanyActivity.textTitle.setText("公司简介");
                        CompanyActivity.titleBuffer[1] = "公司简介";
                        break;
                    case 1:
                        Log.d("onItemClick", "专利证书");
                        showFragment.setLayoutID(CompanyShowFragment.OTHER_INTRODUCT);
                        CompanyActivity.textTitle.setText("专利证书");
                        CompanyActivity.titleBuffer[1] = "专利证书";
                        break;
                    case 2:
                        Log.d("onItemClick", "公司荣誉");
                        showFragment.setLayoutID(CompanyShowFragment.OTHER_INTRODUCT);
                        CompanyActivity.textTitle.setText("公司荣誉");
                        CompanyActivity.titleBuffer[1] = "公司荣誉";
                        break;
                    case 3:
                        Log.d("onItemClick", "合作伙伴");
                        showFragment.setLayoutID(CompanyShowFragment.OTHER_INTRODUCT);
                        CompanyActivity.textTitle.setText("合作伙伴");
                        CompanyActivity.titleBuffer[1] = "合作伙伴";
                        break;
                    case 4:
                        Log.d("onItemClick", "联系我们");
                        showFragment.setLayoutID(CompanyShowFragment.OTHER_INTRODUCT);
                        CompanyActivity.textTitle.setText("联系我们");
                        CompanyActivity.titleBuffer[1] = "联系我们";
                        break;
                }
                mTimer.cancel();
                mContext.changeFragment(CompanyFragment.this, showFragment, "showFragment", 1, true, false);

            }
        });

        mTimer = new Timer();
        mTimer.schedule(new MyTimeTask(), 4000, 4000);

    }

    private void initList() {
        for (int i = 0; i < CMP_TEXT.length; i++) {
            CmpListItem mItem = new CmpListItem();
            mItem.setImageId(CMP_ICON[i]);
            mItem.setText(CMP_TEXT[i]);
            mCmpList.add(mItem);
        }
    }

    private class MyTimeTask extends TimerTask {

        @Override
        public void run() {
            mCompanyHandler.sendEmptyMessage(UPDATE_VIEWPAGER);
       }
    }

    Handler mCompanyHandler = new Handler() {
        private int index=0;
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case UPDATE_VIEWPAGER:
                    if(index > 2)
                        index = 0;
                    mViewPager.setCurrentItem(index);
                    index++;
                    break;
            }
        }
    };

}
