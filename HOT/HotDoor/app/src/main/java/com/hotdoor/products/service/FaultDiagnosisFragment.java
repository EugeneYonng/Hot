package com.hotdoor.products.service;

import android.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;

import com.andexert.expandablelayout.library.ExpandableLayoutItem;
import com.andexert.expandablelayout.library.ExpandableLayoutListView;
import com.hotdoor.adapter.ServiceAdapter;
import com.hotdoor.products.main.R;

/**
 * Created by Yip on 2015/8/18.
 */
public class FaultDiagnosisFragment extends Fragment implements ListView.OnItemClickListener {

    private static final String[] HEADER = {"保修条例", "权责声明", "售后服务", "维修收费"};
    private static final String[] CONTENT = {"content1", "content2", "content3", "content4"};

    ExpandableLayoutListView mPolicyContent;

    ServiceAdapter mAdapter;

    private static View mLastView;

    private static boolean isSelected;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.service_fragment_policy, container, false);

        init(view);

        return view;
    }

    private void init(View view) {
        mPolicyContent = (ExpandableLayoutListView) view.findViewById(R.id.elv_service_policy);

        mAdapter = new ServiceAdapter(getActivity(), HEADER, CONTENT);

        mPolicyContent.setAdapter(mAdapter);

        mPolicyContent.setOnItemClickListener(this);

    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        ExpandableLayoutItem ss = (ExpandableLayoutItem) view.findViewById(R.id.expandableLayout);
        Log.i("isOpened", "" + ss.isOpened());
        if (mLastView == null) {//第一次点击
            ImageView mImage = (ImageView) view.findViewById(R.id.iv_service_show_list_header);
            mImage.setImageResource(R.drawable.ic_expand_less_black_18dp);
            mLastView = view;
            isSelected = true;
        } else if ((view != mLastView) && (isSelected == true)) {
            ImageView mImage = (ImageView) view.findViewById(R.id.iv_service_show_list_header);
            mImage.setImageResource(R.drawable.ic_expand_less_black_18dp);
            ImageView mLastImage = (ImageView) mLastView.findViewById(R.id.iv_service_show_list_header);
            mLastImage.setImageResource(R.drawable.ic_expand_more_black_18dp);
            mLastView = view;
            isSelected = true;
        } else if ((view == mLastView) && (isSelected == true)) {
            ImageView mImage = (ImageView) view.findViewById(R.id.iv_service_show_list_header);
            mImage.setImageResource(R.drawable.ic_expand_more_black_18dp);
            isSelected = false;
        } else if ((view == mLastView) && (isSelected == false)) {
            ImageView mImage = (ImageView) view.findViewById(R.id.iv_service_show_list_header);
            mImage.setImageResource(R.drawable.ic_expand_less_black_18dp);
            isSelected = true;
        }
    }
}
