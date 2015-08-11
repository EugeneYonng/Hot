package com.hotdoor.products.personal;

import android.app.Fragment;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

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

//        setPagerAdapter(mViewPagerCollect);

        return view;
    }

    private void init(View view) {
        mViewPagerCollect = (ViewPager) view.findViewById(R.id.vp_personal_collect);
        mTextProduct = (MyTextView) view.findViewById(R.id.tv_personal_collect_tab_product);
        mTextScheme = (MyTextView) view.findViewById(R.id.tv_personal_collect_tab_scheme);

        mTextProduct.setOnClickListener(this);
        mTextScheme.setOnClickListener(this);

    }
//
//    private void setPagerAdapter(ViewPager viewPager) {
//        viewPager.setAdapter(new PagerAdapter() {
//
//            @Override
//            public Object instantiateItem(ViewGroup container, int position) {
//                View v = LayoutInflater.from(getActivity()).inflate(mPagerEntity[position], null);
//
//                container.addView(v);
//                Log.i("container", "" + v);
//                return v;
//            }
//
//            @Override
//            public int getCount() {
//                return mPagerEntity.length;
//            }
//
//            @Override
//            public boolean isViewFromObject(View view, Object object) {
//                boolean xx = view == object;
//                Log.i("isView", "" + xx);
//                Log.i("view", "" + view);
//                Log.i("object", "" + object);
//                return view == object;
//            }
//        });
//    }

    @Override
    public void onClick(View v) {

    }
}
