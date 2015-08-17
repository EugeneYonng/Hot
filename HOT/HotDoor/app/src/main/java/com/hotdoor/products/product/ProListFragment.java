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
    private String[] listTitle;
    private String[] listText;
    private int[] listImageResource;
    private String titleString;
    private int showID;

    @Override
    public View onCreateView(LayoutInflater inflater , ViewGroup container, Bundle saveInstanceState) {
        View view = inflater.inflate(R.layout.product_fragment_list, container, false);
        initList(showID);
        init(view);
        return view;
    }

    public void setShowID(int id) {
        this.showID = id;
    }

    private void initList(int index) {
        arrayListPro = new ArrayList<ProListItem>();
        switch (index) {
            case ProductListString.CLOUDCOMPUTER:
//                mItemString = ProductListString.cloudComputer;
                listTitle = getActivity().getResources().getStringArray(R.array.cloudComputerListTitle);
                listText = getActivity().getResources().getStringArray(R.array.cloudComputerListText);
                listImageResource = ProductListString.CLOUD_COMPUTER_RESOURCE;
                break;
            case ProductListString.CLOUDMANAGER:
//                mItemString = ProductListString.cloudManager;
                listTitle = getActivity().getResources().getStringArray(R.array.cloudManagerListTitle);
                listText = getActivity().getResources().getStringArray(R.array.cloudManagerListText);
                listImageResource = ProductListString.CLOUD_MANAGER_RESOURCE;
                break;
            case ProductListString.CLOUDSAFE:
//                mItemString = ProductListString.cloudSafe;
                listTitle = getActivity().getResources().getStringArray(R.array.cloudSafeListTitle);
                listText = getActivity().getResources().getStringArray(R.array.cloudSafeListText);
                listImageResource = ProductListString.CLOUD_SAFE_RESOURCE;
                break;
            case ProductListString.CLOUDSAVE:
//                mItemString = ProductListString.cloudSave;
                listTitle = getActivity().getResources().getStringArray(R.array.cloudSaveListTitle);
                listText = getActivity().getResources().getStringArray(R.array.cloudSaveListText);
                listImageResource = ProductListString.CLOUD_SAVE_RESOURCE;
                break;
            case ProductListString.CLOUDSPEED:
//                mItemString = ProductListString.cloudSpeed;
                listTitle = getActivity().getResources().getStringArray(R.array.cloudSpeedListTitle);
                listText = getActivity().getResources().getStringArray(R.array.cloudSpeedListText);
                listImageResource = ProductListString.CLOUD_SPEED_RESOURCE;
                break;
            case ProductListString.SMARTCLOUDSERVER:
//                mItemString = ProductListString.smartCloudServer;
                listTitle = getActivity().getResources().getStringArray(R.array.smartCloudServerListTitle);
                listText = getActivity().getResources().getStringArray(R.array.smartCloudServerListText);
                listImageResource = ProductListString.CLOUD_SERVER_RESOURCE;
                break;
        }

        addStringItem();

    }

    public String getTitleString() {
        return this.titleString;
    }

    public void setTitleString(String title) {
        this.titleString = title;
    }

    private void addStringItem() {
        for(int i=0 ;i<listTitle.length; i++) {
            ProListItem item = new ProListItem();

            item.setImageResource(listImageResource[i]);  //设置图片资源
            item.setTitle(listTitle[i]);    //设置标题
            item.setProductModel(listText[i]);  //设置产品型号

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
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {     //list监听事件

                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.setCustomAnimations(R.anim.fragment_slide_in_right, R.anim.fragment_slide_out_left
                        , R.anim.fragment_slide_in_right, R.anim.fragment_slide_out_left);

                String title = listTitle[position].split(":")[1];
                textProTitle.setText(title);    //设置show的标题
                ProductActivity.titleBuffer[2] = listTitle[position];  //存储选中的产品

                ProdShowFragment showFragment = new ProdShowFragment();

                switch (position) {
                    case 0:
                           //要传入需要显示的数组和图片。

                        break;
                    case 1:


                        break;
                    case 2:


                        break;
                    case 3:


                        break;
                    case 4:

                        break;
                }
                showFragment.setListimageResource(listImageResource[position]);
                showFragment.setListTextString(listText[position]);
                showFragment.setProductID(ProductListString.PRODUCT_ID.indexOf(title));
                transaction.replace(R.id.fl_product, showFragment, "showfragment");
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });
    }
}
