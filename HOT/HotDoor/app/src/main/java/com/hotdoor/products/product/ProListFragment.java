package com.hotdoor.products.product;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.hotdoor.adapter.ProdAdapter;
import com.hotdoor.list.ProListItem;
import com.hotdoor.list.ProductListString;
import com.hotdoor.products.main.ProductActivity;
import com.hotdoor.products.main.R;
import com.hotdoor.textview.MyTextView;

import java.util.ArrayList;

/**
 * Created by Administrator on 2015/8/12.
 */
public class ProListFragment extends Fragment {

    private ListView listProduct;
    private ArrayList<ProListItem> arrayListPro;
    private MyTextView textProTitle;
    private String[][] mItemString;
    private String titleString;

    public ProListFragment(int index) {
        initList(index);
    }

    @Override
    public View onCreateView(LayoutInflater inflater , ViewGroup container, Bundle saveInstanceState) {
        View view = inflater.inflate(R.layout.product_fragment_list, container, false);
        init(view);
        return view;
    }

    private void initList(int index) {
        arrayListPro = new ArrayList<ProListItem>();
        switch (index) {
            case ProductListString.CLOUDCOMPUTER:
                mItemString = ProductListString.cloudComputer;
                break;
            case ProductListString.CLOUDMANAGER:
                mItemString = ProductListString.cloudManager;
                break;
            case ProductListString.CLOUDSAFE:
                mItemString = ProductListString.cloudSafe;
                break;
            case ProductListString.CLOUDSAVE:
                mItemString = ProductListString.cloudSave;
                break;
            case ProductListString.CLOUDSPEED:
                mItemString = ProductListString.cloudSpeed;
                break;
            case ProductListString.SMARTCLOUDSERVER:
                mItemString = ProductListString.smartCloudServer;
                break;
        }

        addStringItem(mItemString);

    }

    public String getTitleString() {
        return this.titleString;
    }

    public void setTitleString(String title) {
        this.titleString = title;
    }

    private void addStringItem(String[][] string) {
        for(int i=0 ;i<string.length; i++) {
            ProListItem item = new ProListItem();
            item.setImageResource(R.drawable.testimage);  //设置图片资源
            item.setTitle(string[i][0]);    //设置标题
            item.setProductModel(string[i][1]);  //设置产品型号
            arrayListPro.add(item);
        }
    }

    public void setTitleText(MyTextView text) {
        this.textProTitle = text;
    }

    private void init(View view) {
        listProduct = (ListView) view.findViewById(R.id.lv_product_list);
        listProduct.setAdapter(new ProdAdapter(getActivity(),arrayListPro));

        listProduct.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.setCustomAnimations(R.anim.fragment_slide_in_right, R.anim.fragment_slide_out_left
                        , R.anim.fragment_slide_in_right, R.anim.fragment_slide_out_left);

                textProTitle.setText(mItemString[position][1]);
                ProductActivity.titleBuffer[2] = mItemString[position][1];  //设置show的标题
                ProdShowFragment showFragment = null;
                switch (position) {
                    case 0:
                        showFragment = new ProdShowFragment();   //要传入需要显示的数组和图片。

                        break;
                    case 1:
                        showFragment = new ProdShowFragment();

                        break;
                    case 2:
                        showFragment = new ProdShowFragment();

                        break;
                    case 3:
                        showFragment = new ProdShowFragment();

                        break;
                    case 4:
                        showFragment = new ProdShowFragment();
                        break;
                }
                showFragment.setProductID(ProductListString.PRODUCT_ID.indexOf(mItemString[position][1]));
                showFragment.setProductTitle(mItemString[position][0]);
                transaction.replace(R.id.fl_product, showFragment, "showfragment");
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });
    }
}
