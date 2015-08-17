package com.hotdoor.products.company;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.andexert.expandablelayout.library.ExpandableLayoutListView;
import com.hotdoor.adapter.CmpOthersAdapter;
import com.hotdoor.list.CmpIntroductItem;
import com.hotdoor.list.ProductListString;
import com.hotdoor.products.main.R;
import com.ms.square.android.expandabletextview.ExpandableTextView;

import java.util.ArrayList;

/**
 * Created by Administrator on 2015/8/17.
 */
public class CompanyShowFragment extends Fragment {

    public static final int COMPANY_INTRODUCT = 0;
    public static final int OTHER_INTRODUCT = 1;
    private int layoutID = COMPANY_INTRODUCT;
    private int showID;

    private ExpandableTextView textTechnology;
    private ExpandableTextView textProduct;
    private ExpandableTextView textMethod;
    private ExpandableLayoutListView mListView;
    private CmpOthersAdapter mAdapter;
    private ArrayList<CmpIntroductItem> listItem;

    String[] buffer;
    String[][] result;
    int[][] imageResource;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view;
        if( layoutID == COMPANY_INTRODUCT) {
            view = inflater.inflate(R.layout.company_introduction, container, false);
            initCompany(view);
        }else{
            view = inflater.inflate(R.layout.company_introduct_others, container, false);
            listItem = new ArrayList<CmpIntroductItem>();
            initList();
            initOthers(view);
        }
        return view;
    }

    private void initList() {
        switch(showID) {
            case ProductListString.PATENT:
            case ProductListString.PARTNER:
            case ProductListString.HONOUR:
            case ProductListString.CONNECT:
                buffer = getActivity().getResources().getStringArray(R.array.patent);
                result = getListString(buffer);
                imageResource = ProductListString.PATENT_IMAGE_RESOURCE;
                break;
        }

        for(int i=0;i<result.length ;i++){
            CmpIntroductItem item = new CmpIntroductItem();
            item.setHeaderTitle(result[i][0]);
            item.setHeaderText(result[i][1]);
            item.setContentText(result[i][2]);

            item.setHeaderIconResource(imageResource[i][0]);
            item.setContentIcon1Resource(imageResource[i][1]);
            item.setContentIcon2Resource(imageResource[i][2]);

            listItem.add(item);
        }
    }

    private String[][] getListString(String[] array) {
        String[][] result = new String[array.length][3];
        for( int i= 0; i<array.length; i++) {
            result[i] = array[i].split("#");
        }
        return result;
    }

    private void initOthers(View v) {
        mListView = (ExpandableLayoutListView) v.findViewById(R.id.ellv_company_introduct_others);
        mAdapter = new CmpOthersAdapter(getActivity(),listItem);
        mListView.setAdapter(mAdapter);
    }

    private void initCompany(View v) {
        textTechnology = (ExpandableTextView) v.findViewById(R.id.etv_company_introduct_technology);
        textMethod = (ExpandableTextView) v.findViewById(R.id.etv_company_introduct_method);
        textProduct = (ExpandableTextView) v.findViewById(R.id.etv_company_introduct_product);

        textProduct.setText(getString(R.string.companyProduct));
        textMethod.setText(getString(R.string.companyMethod));
        textTechnology.setText(getString(R.string.companyTechnology));
    }

    public void setLayoutID(int id) {
        this.layoutID = id;
    }

    public void setShowID(int id) {
        this.showID = id;
    }

}
