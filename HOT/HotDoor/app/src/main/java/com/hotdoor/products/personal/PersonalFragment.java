package com.hotdoor.products.personal;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.gc.materialdesign.views.ButtonRectangle;
import com.hotdoor.products.main.PersonalActivity;
import com.hotdoor.products.main.R;

/**
 * Created by Yip on 2015/8/10.
 */
public class PersonalFragment extends Fragment implements View.OnClickListener {

    ImageView mImgPersonalIcon;
    TextView mTextPersonalName;
    ButtonRectangle mBtnQuit;
    ButtonRectangle mBtnCollect;
    ButtonRectangle mBtnSell;

    CollectFragment collectFragment;
    SellFragment sellFragment;

    PersonalActivity mActivity;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.personal_fragment_personal, container, false);
        init(view);
        return view;
    }

    private void init(View view) {
        mActivity = (PersonalActivity) getActivity();

        mImgPersonalIcon = (ImageView) view.findViewById(R.id.iv_personal_personal_portrait_icon);
        mTextPersonalName = (TextView) view.findViewById(R.id.tv_personal_personal_name);
        mBtnQuit = (ButtonRectangle) view.findViewById(R.id.btn_personal_personal_quit);
        mBtnCollect = (ButtonRectangle) view.findViewById(R.id.btn_personal_personal_collect);
        mBtnSell = (ButtonRectangle) view.findViewById(R.id.btn_personal_personal_sell);

        mBtnQuit.setOnClickListener(this);
        mBtnCollect.setOnClickListener(this);
        mBtnSell.setOnClickListener(this);

        mActivity.mPersonalLeftTitle.setText("个人中心");

        /**
         * set Fonts
         */
        setFonts();

    }

    private void setFonts() {
        mTextPersonalName.setTypeface(mActivity.mFonts);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_personal_personal_collect:
                collectFragment = new CollectFragment();
                mActivity.changeFragment(this, collectFragment, "collectFragment", 1, true, false);
                break;
            case R.id.btn_personal_personal_sell:
                sellFragment = new SellFragment();
                mActivity.changeFragment(this, sellFragment, "sellFragment", 1, true, false);
                break;
            default:
                break;
        }
    }
}
