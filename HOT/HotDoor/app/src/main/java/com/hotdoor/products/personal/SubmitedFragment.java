package com.hotdoor.products.personal;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.hotdoor.products.main.PersonalActivity;
import com.hotdoor.products.main.R;

/**
 * Created by Yip on 2015/8/15.
 */
public class SubmitedFragment extends Fragment {
    TextView mTextProName;
    TextView mTextPriceScheme;
    TextView mTextPriceReal;
    TextView mTextTimeScheme;
    TextView mTextTimeReal;
    TextView mTextTimeSubmited;
    TextView mTextProcess;
    TextView mTextMessage;

    PersonalActivity mActivity;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.personal_fragment_sell_submited, container, false);

        init(view);
        
        return view;
    }

    private void init(View view) {
        mActivity = (PersonalActivity) getActivity();

        mTextProName = (TextView) view.findViewById(R.id.tv_personal_sell_submited_project_name_content);
        mTextPriceScheme = (TextView) view.findViewById(R.id.tv_personal_sell_submited_project_price_content);
        mTextPriceReal = (TextView) view.findViewById(R.id.tv_personal_sell_submited_project_price_real_content);
        mTextTimeScheme = (TextView) view.findViewById(R.id.tv_personal_sell_submited_project_time_content);
        mTextTimeReal = (TextView) view.findViewById(R.id.tv_personal_sell_submited_project_time_real_content);
        mTextTimeSubmited = (TextView) view.findViewById(R.id.tv_personal_sell_submited_project_submit_time_content);
        mTextProcess = (TextView) view.findViewById(R.id.tv_personal_sell_submited_project_process_content);
        mTextMessage = (TextView) view.findViewById(R.id.tv_personal_sell_submited_project_message_content);

        /**
         * set mBtnLogin's font
         */
        setFonts();

        /**
         * 接口。得到显示的内容
         */
        getTextDisplay();

    }

    private void setFonts() {
        mTextProName.setTypeface(mActivity.mFonts);
        mTextPriceScheme.setTypeface(mActivity.mFonts);
        mTextPriceReal.setTypeface(mActivity.mFonts);
        mTextTimeScheme.setTypeface(mActivity.mFonts);
        mTextTimeReal.setTypeface(mActivity.mFonts);
        mTextTimeSubmited.setTypeface(mActivity.mFonts);
        mTextProcess.setTypeface(mActivity.mFonts);
        mTextMessage.setTypeface(mActivity.mFonts);
    }

    private void getTextDisplay() {
    }

}
