package com.hotdoor.products.personal;

import android.app.Fragment;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import com.hotdoor.adapter.PersonalSellItemAdapter;
import com.hotdoor.products.main.PersonalActivity;
import com.hotdoor.products.main.R;
import com.rengwuxian.materialedittext.MaterialEditText;
import com.romainpiel.shimmer.Shimmer;
import com.romainpiel.shimmer.ShimmerTextView;

/**
 * Created by Yip on 2015/8/18.
 */
public class ApplyFragment extends Fragment implements View.OnClickListener {
    private final static String[] HISTORY_PRO = {"项目1", "项目2", "项目3", "项目4", "项目5"};

    private static final int[] PAGER_ENTITY = new int[] {R.layout.personal_sell_submit_new, R.layout.personal_sell_submit_history};

    ShimmerTextView mTabSubmitNew;
    ShimmerTextView mTabSubmitHistory;
    ViewPager mViewPager;

    private static MaterialEditText mProName;
    private static MaterialEditText mProPrice;
    private static MaterialEditText mProTime;
    private static MaterialEditText mProMessage;
    private static TextView mBtnSubmit;

    private static ListView mListHistory;
    PersonalSellItemAdapter mAdapter;
    String[] mHistoryItem;

    private Shimmer mShimmer;

    PersonalActivity mActivity;
    SubmitedFragment submitedFragment;

    int mFlagSubmit = 0;
    int mFlagHistory = 0;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.personal_fragment_sell, container, false);

        init(view);

        return view;
    }

    private void init(View view) {
        mActivity = (PersonalActivity) getActivity();

        mFlagSubmit = 0;
        mFlagHistory = 0;

        mTabSubmitNew = (ShimmerTextView) view.findViewById(R.id.tv_personal_sell_tab_submit);
        mTabSubmitHistory = (ShimmerTextView) view.findViewById(R.id.tv_personal_sell_tab_history);
        mViewPager = (ViewPager) view.findViewById(R.id.vp_personal_sell);

        mActivity.mPersonalLeftTitle.setText("销售报备");

        mTabSubmitNew.setOnClickListener(this);
        mTabSubmitHistory.setOnClickListener(this);

        mTabSubmitNew.setReflectionColor(0xfffeef6b);
        mTabSubmitHistory.setReflectionColor(0xfffeef6b);

        mShimmer = new Shimmer();
        mShimmer.setDuration(3000).start(mTabSubmitNew);

        setViewPagerAdapter(mViewPager);
        setViewPagerListener(mViewPager);

        /**
         * set Fonts
         */
        setFont();

    }

    private void setFont() {
        mTabSubmitNew.setTypeface(mActivity.mFonts);
        mTabSubmitHistory.setTypeface(mActivity.mFonts);
    }

    private void setViewPagerAdapter(ViewPager viewPager) {
        viewPager.setAdapter(new PagerAdapter() {
            @Override
            public Object instantiateItem(ViewGroup container, int position) {
                View view = LayoutInflater.from(getActivity()).inflate(PAGER_ENTITY[position], null);
                if (position == 0 && mFlagSubmit == 0) {
                    initDataNewSubmit(view);
                    mFlagSubmit = 1;
                } else if (position == 1 && mFlagHistory == 0) {
                    initDataHistorySubmit(view);
                    mFlagHistory = 1;
                }
                container.addView(view);
                return view;
            }

            @Override
            public int getCount() {
                return 2;
            }

            @Override
            public boolean isViewFromObject(View view, Object object) {
                return view == object;
            }
        });
    }

    private void initDataNewSubmit(View view) {
        mProName = (MaterialEditText) view.findViewById(R.id.et_personal_sell_submit_project_name);
        mProPrice = (MaterialEditText) view.findViewById(R.id.et_personal_sell_submit_project_price);
        mProTime = (MaterialEditText) view.findViewById(R.id.et_personal_sell_submit_project_time);
        mProMessage = (MaterialEditText) view.findViewById(R.id.et_personal_sell_submit_project_message);
        mBtnSubmit = (TextView) view.findViewById(R.id.btn_personal_sell_submit_confirm);

        mProName.setTypeface(mActivity.mFonts);
        mProPrice.setTypeface(mActivity.mFonts);
        mProTime.setTypeface(mActivity.mFonts);
        mProMessage.setTypeface(mActivity.mFonts);
        mBtnSubmit.setTypeface(mActivity.mFonts);

        mBtnSubmit.setOnClickListener(ApplyFragment.this);

    }

    private void initDataHistorySubmit(View view) {
        mListHistory = (ListView) view.findViewById(R.id.lv_personal_sell_history);
        /**
         * 接口。得到mHistoryItem的具体项目
         */
        mHistoryItem = HISTORY_PRO;
        mAdapter = new PersonalSellItemAdapter(getActivity(), mHistoryItem);
        mListHistory.setAdapter(mAdapter);
    }

    private void setShimmerNew() {
        mShimmer.cancel();
        mTabSubmitHistory.setBackgroundColor(0x3a020201);
        mTabSubmitHistory.setTextColor(0xff6f6f6f);

        mTabSubmitNew.setBackgroundColor(0xf08bffc5);
        mTabSubmitNew.setTextColor(0xff9478ff);
        mShimmer.start(mTabSubmitNew);
    }

    private void setShimmerHistory() {
        mShimmer.cancel();
        mTabSubmitNew.setBackgroundColor(0x3a020201);
        mTabSubmitNew.setTextColor(0xff6f6f6f);

        mTabSubmitHistory.setBackgroundColor(0xf08bffc5);
        mTabSubmitHistory.setTextColor(0xff9478ff);
        mShimmer.start(mTabSubmitHistory);
    }

    private void setViewPagerListener(ViewPager viewPager) {
        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageSelected(int position) {
                if (position == 0) {
                    setShimmerNew();
                } else if (position == 1) {
                    setShimmerHistory();
                }
            }

            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_personal_sell_tab_submit:
                mViewPager.setCurrentItem(0);
                setShimmerNew();
                break;
            case R.id.tv_personal_sell_tab_history:
                mViewPager.setCurrentItem(1);
                setShimmerHistory();
                break;
            case R.id.btn_personal_sell_submit_confirm:
                /**
                 * 接口。加入如何存储数据的过程
                 */
                submitedFragment = new SubmitedFragment();
                mActivity.changeFragment(this, submitedFragment, "submitedFragment", 1, true, true);
        }
    }
}
