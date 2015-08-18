package com.hotdoor.products.personal;

import android.app.Activity;
import android.app.Fragment;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.gc.materialdesign.views.ButtonFlat;
import com.gc.materialdesign.widgets.Dialog;
import com.hotdoor.adapter.ProdAdapter;
import com.hotdoor.list.ProListItem;
import com.hotdoor.list.ProductListString;
import com.hotdoor.products.main.MainActivity;
import com.hotdoor.products.main.PersonalActivity;
import com.hotdoor.products.main.R;
import com.romainpiel.shimmer.Shimmer;
import com.romainpiel.shimmer.ShimmerTextView;

import java.util.ArrayList;

/**
 * Created by Yip on 2015/8/10.
 */
public class No_Use_Now_CollectFragment extends Fragment implements View.OnClickListener {

    private static final int DELETEPRODUCT = 0;
    private static final int DELETEMETHOD = 1;
    private static final int UPDATE_PRODUCT = 0;
    private static final int UPDATE_METHOD = 1;
    public static final int METHOD_LISTVIEW_ID = 100;

    ViewPager mViewPagerCollect;
    ShimmerTextView mTextProduct;
    ShimmerTextView mTextScheme;
    private ListView listCollectProduct;
    private ListView listCollectMethod;
    private Shimmer shimmer;

    private int[] mPagerEntity = new int[]{R.layout.product_fragment_list , R.layout.product_fragment_list}; //第二个界面还没画
    private ArrayList<ProListItem> listProItem;
    private ArrayList<String> prodListTitle;
    private ArrayList<String> prodListText;
    private ArrayList<Integer> prodListResource;

    private ArrayList<ProListItem> listMtdItem;
    private ArrayList<Integer> mtdListID;

    private SharedPreferences share;
    private SharedPreferences.Editor editor;
    private ProdAdapter prodAdapter;
    private ProdAdapter mtdAdapter;

    PersonalActivity mActivity;

    public No_Use_Now_CollectFragment() {
        prodListTitle = new ArrayList<String>();
        prodListText = new ArrayList<String>();
        prodListResource = new ArrayList<Integer>();
        listProItem = new ArrayList<ProListItem>();
        listMtdItem = new ArrayList<ProListItem>();
        mtdListID = new ArrayList<Integer>();

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        new ReadXMLThread().start();
        View view = inflater.inflate(R.layout.a_no_use_now_personal_fragment_collect, container, false);
        init(view);

        setPagerAdapter(mViewPagerCollect);
        return view;
    }



    private void init(View view) {
        mActivity = (PersonalActivity) getActivity();

        mViewPagerCollect = (ViewPager) view.findViewById(R.id.vp_personal_collect);
        mTextProduct = (ShimmerTextView) view.findViewById(R.id.tv_personal_collect_tab_product);
        mTextScheme = (ShimmerTextView) view.findViewById(R.id.tv_personal_collect_tab_scheme);

        mTextProduct.setOnClickListener(this);
        mTextScheme.setOnClickListener(this);

        setShimmer();
        mActivity.mPersonalLeftTitle.setText("我的收藏");

        mViewPagerCollect.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                if (position == 0) {
                    resetShimmer(position);

                } else if (position == 1) {
                    resetShimmer(position);

                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        /**
         * set Fonts
         */
        setFonts();

    }

    private void resetShimmer(int position) {
        switch (position) {
            case 0:
                shimmer.cancel();   //
                mTextProduct.setBackgroundColor(0xf08bffc5);
                mTextProduct.setTextColor(0xff9478ff);

                mTextScheme.setTextColor(0xff6f6f6f);
                mTextScheme.setBackgroundColor(0x3a020201);
                shimmer.start(mTextProduct);
                break;
            case 1:
                shimmer.cancel();
                mTextScheme.setBackgroundColor(0xf08bffc5);
                mTextScheme.setTextColor(0xff9478ff);

                mTextProduct.setTextColor(0xff6f6f6f);
                mTextProduct.setBackgroundColor(0x3a020201);
                shimmer.start(mTextScheme);
                break;
        }
    }

    private void setShimmer() {
        mTextProduct.setReflectionColor(0xfffeef6b);
        mTextScheme.setReflectionColor(0xfffeef6b);
        shimmer = new Shimmer();
        shimmer.setDuration(3000).start(mTextProduct);
    }

    private void setFonts() {
        mTextProduct.setTypeface(mActivity.mFonts);
        mTextScheme.setTypeface(mActivity.mFonts);

    }

    private void setPagerAdapter(ViewPager viewPager) {
        viewPager.setAdapter(new PagerAdapter() {

            @Override
            public Object instantiateItem(ViewGroup container, int position) {
                View v = LayoutInflater.from(getActivity()).inflate(mPagerEntity[position], null);
                //设置适配器
                mItemLongClickListener longlistener = new mItemLongClickListener();
                switch (position) {
                    case 0:
                        listCollectProduct = (ListView) v.findViewById(R.id.lv_product_list);
                        prodAdapter = new ProdAdapter(getActivity(),listProItem);
                        listCollectProduct.setAdapter(prodAdapter);
//                        listCollectProduct.setOnItemClickListener();
                        listCollectProduct.setOnItemLongClickListener(longlistener);
                        break;
                    case 1:
                        listCollectMethod = (ListView) v.findViewById(R.id.lv_product_list);
                        listCollectMethod.setId(R.id.iv_main);
                        mtdAdapter = new ProdAdapter(getActivity(),listMtdItem);
                        listCollectMethod.setAdapter(mtdAdapter);

                        listCollectMethod.setOnItemLongClickListener(longlistener);
                        break;

                }

                container.addView(v,ViewGroup.LayoutParams.MATCH_PARENT,
                       ViewGroup.LayoutParams.MATCH_PARENT);

                Log.i("container", "" + v);
                return v;

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
                return view == object;
            }
            }
        );
    }

    private class mItemCliclListener implements AdapterView.OnItemClickListener{

        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        }
    }

    private class mItemLongClickListener implements AdapterView.OnItemLongClickListener{
        private int position;
        @Override
        public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {

            switch (parent.getId()) {
                case R.id.lv_product_list:
                    Log.d("onItemLongClick","product");
                    this.position = position;
                    Log.d("position",""+position);
                    showDialog(0);
                    break;

                case R.id.iv_main:
                    Log.d("onItemLongClick","method");
                    this.position = position;
                    Log.d("position",""+position);
                    showDialog(1);
            }
            return false;
        }

        private void showDialog(int mode) {
            final Dialog dialog;
            if (mode == 0)
                dialog = new Dialog(getActivity(),"温馨提示", "确认删除该收藏产品?");
            else
                dialog = new Dialog(getActivity(),"温馨提示", "确认删除该收藏方案?");

            dialog.addCancelButton("取消");
            dialog.show();
            ButtonFlat buAccept = dialog.getButtonAccept();
            buAccept.setText("确认");
            dialog.getButtonCancel().setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dialog.dismiss();
                }
            });

            if (mode == 0)
                buAccept.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        new writeXMLThread(DELETEPRODUCT,position).start();
                        dialog.dismiss();
                    }
                });
            else
                buAccept.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        new writeXMLThread(DELETEMETHOD,position).start();
                        dialog.dismiss();
                    }
                });
        }

    }

    Handler myCollectHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case UPDATE_PRODUCT:
                    prodAdapter.notifyDataSetChanged();
                    break;
                case UPDATE_METHOD:
                    mtdAdapter.notifyDataSetChanged();
                    break;
            }
        }
    };


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_personal_collect_tab_product :
                mViewPagerCollect.setCurrentItem(0);
                break;

            case R.id.tv_personal_collect_tab_scheme:
                mViewPagerCollect.setCurrentItem(1);
                break;
        }
    }

    private class ReadXMLThread extends Thread {
        @Override
        public void run() {

            readProductCollect();
            readMethodCollect();
            //此处获取解决方案收藏
        }

        private void readMethodCollect() {
            share = getActivity().getSharedPreferences(MainActivity.account+"method", Activity.MODE_PRIVATE);

            for (int i=0; i<share.getInt("count",0); i++) {
                ProListItem item = new ProListItem();
                switch (share.getInt(""+i,0)) {
                    case ProductListString.SMART_CAMPUS:
                        setProListItem(item,getActivity().getResources().getStringArray(R.array.smartCampusList),ProductListString.METHOD_IMAGE_RESOURCE[ProductListString.SMART_CAMPUS]);
                        break;
                    case ProductListString.SMART_COMPANY:
                        setProListItem(item,getActivity().getResources().getStringArray(R.array.smartCompanyList),ProductListString.METHOD_IMAGE_RESOURCE[ProductListString.SMART_COMPANY]);
                        break;
                    case ProductListString.INDUSTRY_INFORMATIZATION:
                        setProListItem(item,getActivity().getResources().getStringArray(R.array.industryInformatizationList),ProductListString.METHOD_IMAGE_RESOURCE[ProductListString.INDUSTRY_INFORMATIZATION]);
                        break;
                    case ProductListString.SECURITY_INFORMATIZATION:
                        setProListItem(item,getActivity().getResources().getStringArray(R.array.securityInformatizationList),ProductListString.METHOD_IMAGE_RESOURCE[ProductListString.SECURITY_INFORMATIZATION]);
                        break;
                    case ProductListString.MOVE_WORKING:
                        setProListItem(item,getActivity().getResources().getStringArray(R.array.moveWorkingList),ProductListString.METHOD_IMAGE_RESOURCE[ProductListString.MOVE_WORKING]);
                        break;
                    case ProductListString.LIBRARY_CLOUD:
                        setProListItem(item,getActivity().getResources().getStringArray(R.array.libraryCloudList),ProductListString.METHOD_IMAGE_RESOURCE[ProductListString.LIBRARY_CLOUD]);
                        break;
                    case ProductListString.HOTEL_INFORMATIZATION:
                        setProListItem(item,getActivity().getResources().getStringArray(R.array.hotelInformatizationList),ProductListString.METHOD_IMAGE_RESOURCE[ProductListString.HOTEL_INFORMATIZATION]);
                        break;
                    case ProductListString.FINANCIAL_INFORMATIZATION:
                        setProListItem(item,getActivity().getResources().getStringArray(R.array.financialInformatizationList),ProductListString.METHOD_IMAGE_RESOURCE[ProductListString.FINANCIAL_INFORMATIZATION]);
                        break;
                    case ProductListString.RURAL_INFORMATIZATION:
                        setProListItem(item,getActivity().getResources().getStringArray(R.array.ruralInformatizationList),ProductListString.METHOD_IMAGE_RESOURCE[ProductListString.RURAL_INFORMATIZATION]);
                        break;
                }

                mtdListID.add(new Integer(share.getInt(""+i,0)));  //初始化链表
                listMtdItem.add(item);
            }
        }

        private void setProListItem(ProListItem item,String[] string,int resource) {
            item.setTitle(string[0]);
            item.setProductModel(string[1]);
            item.setImageResource(resource);
        }

        private void readProductCollect() {
            share = getActivity().getSharedPreferences(MainActivity.account+"product", Activity.MODE_PRIVATE);

            String title;
            String text;
            int resource;
            ProListItem item;

            for (int i=0 ; i<share.getInt("count",0) ;i++) {
                title = share.getString(""+i+"title","");
                text = share.getString(""+i+"text","");
                resource = share.getInt("" + i + "resource", 0);

                prodListTitle.add(title);
                prodListText.add(text);
                prodListResource.add(resource);

                item = new ProListItem();
                item.setImageResource(resource);
                item.setTitle(title);
                item.setProductModel(text);

                listProItem.add(item);
            }
        }

    }

    private class writeXMLThread extends Thread {

        private int mode;
        private int position;

        public writeXMLThread(int mode,int position) {
            this.mode = mode;
            this.position = position;
        }

        @Override
        public void run() {
            switch (mode) {
                case DELETEPRODUCT:   //删除收藏产品
                    Log.d("arrayTitle",""+prodListTitle.size());
                    prodListTitle.remove(position);   //删除标题、text、以及图片内容
                    prodListText.remove(position);
                    prodListResource.remove(position);

                    listProItem.remove(position);   //删除掉产品

                    myCollectHandler.sendEmptyMessage(UPDATE_PRODUCT); //刷新列表

                    share = getActivity().getSharedPreferences(MainActivity.account+"product", Activity.MODE_PRIVATE);
                    editor = share.edit();
                    editor.putInt("count", prodListTitle.size());
                    for (int i=0 ; i< prodListTitle.size() ;i++) {   //重写XML文件
                        editor.putString("" + i + "title", prodListTitle.get(i));
                        editor.putString("" + i + "text", prodListText.get(i));
                        editor.putInt(""+i+"resource", prodListResource.get(i).intValue());
                    }
                    editor.commit();
                    break;

                case DELETEMETHOD:
                    mtdListID.remove(position);
                    listMtdItem.remove(position);

                    myCollectHandler.sendEmptyMessage(UPDATE_METHOD);

                    share = getActivity().getSharedPreferences(MainActivity.account + "method", Activity.MODE_PRIVATE);
                    editor = share.edit();
                    editor.putInt("count", mtdListID.size());
                    for (int i=0 ;i<mtdListID.size() ; i++) {
                        editor.putInt(""+i,mtdListID.get(i).intValue());
                    }
                    editor.commit();
                    break;
            }
        }
    }

}
