package com.hotdoor.products.personal;

import android.app.Fragment;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.hotdoor.products.main.R;
import com.hotdoor.textview.MyTextView;

/**
 * Created by Yip on 2015/8/10.
 */
public class CollectFragment extends Fragment implements View.OnClickListener {
    private int[] mPagerEntity = new int[]{R.layout.service, R.layout.method};

    ViewPager mViewPagerCollect;
    MyTextView mTextProduct;
    MyTextView mTextScheme;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.personal_fragment_collect, container, false);
        init(view);

        setPagerAdapter(mViewPagerCollect);

        return view;
    }

    private void init(View view) {
        mViewPagerCollect = (ViewPager) view.findViewById(R.id.vp_personal_collect);
        mTextProduct = (MyTextView) view.findViewById(R.id.tv_personal_collect_tab_product);
        mTextScheme = (MyTextView) view.findViewById(R.id.tv_personal_collect_tab_scheme);

        mTextProduct.setOnClickListener(this);
        mTextScheme.setOnClickListener(this);

        mViewPagerCollect.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                if(position == 0) {
                    mTextProduct.setBackgroundColor(0x0080FF);
                    mTextScheme.setBackgroundColor(0x3a020201);
                }else if(position == 1) {
                    mTextProduct.setBackgroundColor(0x3a020201);
                    mTextScheme.setBackgroundColor(0x0080FF);
                }
            }
            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    private void setPagerAdapter(ViewPager viewPager) {
        viewPager.setAdapter(new PagerAdapter() {

            @Override
            public Object instantiateItem(ViewGroup container, int position) {
                View v = LayoutInflater.from(getActivity()).inflate(mPagerEntity[position], null);

                container.addView(v,ViewGroup.LayoutParams.MATCH_PARENT,
                       ViewGroup.LayoutParams.MATCH_PARENT);
                Log.i("container", "" + v);
                return v;
//                TextView text = new TextView(getActivity());
//                text.setGravity(Gravity.CENTER);
//                text.setTextSize(30);
//                text.setTextColor(Color.WHITE);
//                text.setText("Page " + position);
//                text.setPadding(30, 30, 30, 30);
//                int bg = Color.rgb((int) Math.floor(Math.random() * 128) + 64,
//                        (int) Math.floor(Math.random() * 128) + 64,
//                        (int) Math.floor(Math.random() * 128) + 64);
//                text.setBackgroundColor(bg);
//                container.addView(text, ViewGroup.LayoutParams.MATCH_PARENT,
//                        ViewGroup.LayoutParams.MATCH_PARENT);
//                return text;
            }

            @Override
            public void destroyItem(ViewGroup container, int position, Object obj) {
                container.removeView((View) obj);
            }


            @Override
            public int getCount() {
                return mPagerEntity.length;
            }

            @Override
            public boolean isViewFromObject(View view, Object object) {
                boolean xx = view == object;
                Log.i("isView", "" + xx);
                Log.i("view", "" + view);
                Log.i("object", "" + object);
                return view == object;
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_personal_collect_tab_product :
                mViewPagerCollect.setCurrentItem(0);
                mTextProduct.setBackgroundColor(0x0080FF);
                mTextScheme.setBackgroundColor(0x3a020201);

                break;
            case R.id.tv_personal_collect_tab_scheme:
                mViewPagerCollect.setCurrentItem(1);
                mTextProduct.setBackgroundColor(0x3a020201);
                mTextScheme.setBackgroundColor(0x0080FF);
                break;
        }
    }
}
