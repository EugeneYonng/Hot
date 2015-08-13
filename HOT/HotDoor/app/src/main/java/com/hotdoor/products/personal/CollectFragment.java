package com.hotdoor.products.personal;

import android.app.Activity;
import android.app.Fragment;
import android.content.SharedPreferences;
import android.content.pm.ApplicationInfo;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.baoyz.swipemenulistview.SwipeMenu;
import com.baoyz.swipemenulistview.SwipeMenuCreator;
import com.baoyz.swipemenulistview.SwipeMenuItem;
import com.baoyz.swipemenulistview.SwipeMenuListView;
import com.gc.materialdesign.views.ButtonFlat;
import com.gc.materialdesign.widgets.Dialog;
import com.hotdoor.adapter.ProCollectAdapter;
import com.hotdoor.adapter.ProdAdapter;
import com.hotdoor.list.ProListItem;
import com.hotdoor.products.main.MainActivity;
import com.hotdoor.products.main.R;
import com.hotdoor.textview.MyTextView;
import com.romainpiel.shimmer.Shimmer;
import com.romainpiel.shimmer.ShimmerTextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Yip on 2015/8/10.
 */
public class CollectFragment extends Fragment implements View.OnClickListener {

    private static final int DELETEPRODUCT = 0;
    private static final int DELETEMETHOD = 1;
    private static final int UPDATE_PRODUCT = 0;
    private static final int UPDATE_METHOD = 1;

    private int[] mPagerEntity = new int[]{R.layout.product_fragment_list , R.layout.personal_collect_listview}; //第二个界面还没画
    ViewPager mViewPagerCollect;
    ShimmerTextView mTextProduct;
    ShimmerTextView mTextScheme;
    private ListView listCollect;
    private Shimmer shimmer;
    private ArrayList<ProListItem> listProItem;
    private SharedPreferences share;
    private SharedPreferences.Editor editor;
    private ProdAdapter proadapter;
    private ArrayList<String> arrayTitle;
    private ArrayList<String> arrayModle;

    @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.personal_fragment_collect, container, false);
        init(view);
        listProItem = new ArrayList<ProListItem>();
        new ReadXMLThread().start();  //读取xml
        //需要使用xml文件来存储收藏的东西；
        setPagerAdapter(mViewPagerCollect);
     //   initArrayList();
        return view;
    }

//    private void initArrayList() {
//
//        ProListItem item = new ProListItem();
//        item.setImageResource(R.drawable.testimage);
//        item.setTitle("教育型");
//        item.setProductModel("GXY-EDU16");
//        listProItem.add(item);
//    }

    private void init(View view) {
        mViewPagerCollect = (ViewPager) view.findViewById(R.id.vp_personal_collect);
        mTextProduct = (ShimmerTextView) view.findViewById(R.id.tv_personal_collect_tab_product);
        mTextScheme = (ShimmerTextView) view.findViewById(R.id.tv_personal_collect_tab_scheme);

        mTextProduct.setOnClickListener(this);
        mTextScheme.setOnClickListener(this);

        mTextProduct.setReflectionColor(0xfffeef6b);
        mTextScheme.setReflectionColor(0xfffeef6b);

        shimmer = new Shimmer();
        shimmer.setDuration(3000).start(mTextProduct);

        arrayTitle = new ArrayList<String>();
        arrayModle = new ArrayList<String>();

        mViewPagerCollect.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                if(position == 0) {
                    shimmer.cancel();
                    mTextProduct.setBackgroundColor(0xf08bffc5);
                    mTextProduct.setTextColor(0xff9478ff);

                    mTextScheme.setTextColor(0xff6f6f6f);
                    mTextScheme.setBackgroundColor(0x3a020201);
                    shimmer.start(mTextProduct);

                }else if(position == 1) {
                    shimmer.cancel();
                    mTextScheme.setBackgroundColor(0xf08bffc5);
                    mTextScheme.setTextColor(0xff9478ff);

                    mTextProduct.setTextColor(0xff6f6f6f);
                    mTextProduct.setBackgroundColor(0x3a020201);
                    shimmer.start(mTextScheme);
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
                //设置适配器
                mItemLongClickListener longlistener = new mItemLongClickListener();
                switch (position) {
                    case 0:
                        listCollect = (ListView) v.findViewById(R.id.lv_product_list);
                        proadapter = new ProdAdapter(getActivity(),listProItem);
                        listCollect.setAdapter(proadapter);
//                        listCollect.setOnItemClickListener();
                        listCollect.setOnItemLongClickListener(longlistener);
                        break;
                    case 1:
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
                    showDialog();
                    break;
            }
            return false;
        }

        private void showDialog() {
            final Dialog dialog = new Dialog(getActivity(),"温馨提示", "确认删除该收藏产品?");
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
            buAccept.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    new writeXMLThread(DELETEPRODUCT,position).start();
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
                    proadapter.notifyDataSetChanged();
                    break;
                case UPDATE_METHOD:
                    break;
            }
        }
    };


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_personal_collect_tab_product :
                mViewPagerCollect.setCurrentItem(0);
                shimmer.cancel();
                mTextProduct.setBackgroundColor(0xf08bffc5);
                mTextProduct.setTextColor(0xff9478ff);

                mTextScheme.setTextColor(0xff6f6f6f);
                mTextScheme.setBackgroundColor(0x3a020201);
                shimmer.start(mTextProduct);

                break;
            case R.id.tv_personal_collect_tab_scheme:
                mViewPagerCollect.setCurrentItem(1);
                shimmer.cancel();
                mTextScheme.setBackgroundColor(0xf08bffc5);
                mTextScheme.setTextColor(0xff9478ff);

                mTextProduct.setTextColor(0xff6f6f6f);
                mTextProduct.setBackgroundColor(0x3a020201);
                shimmer.start(mTextScheme);
                break;
        }
    }

    private class ReadXMLThread extends Thread {
        @Override
        public void run() {

            share = getActivity().getSharedPreferences(MainActivity.account+"product", Activity.MODE_PRIVATE);
            for (int i=0 ; i<share.getInt("count",0) ;i++) {

                addTitleItem(i);
                addModleItem(i);

                ProListItem item = new ProListItem();
                item.setImageResource(R.drawable.testimage);
                item.setTitle(arrayTitle.get(i));
                item.setProductModel(arrayModle.get(i));
                listProItem.add(item);
            }
            //此处获取解决方案收藏
        }

        private void addTitleItem(int i) {
            arrayTitle.add(share.getString(""+i+"title",""));
        }

        private void addModleItem(int i) {
            arrayModle.add(share.getString(""+i+"model",""));
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
                    Log.d("arrayTitle",""+arrayTitle.size());
                    Log.d("arrayTitle",""+arrayModle.size());
                    arrayTitle.remove(position);
                    arrayModle.remove(position);
                    listProItem.remove(position);   //删除掉产品
                    myCollectHandler.sendEmptyMessage(UPDATE_PRODUCT); //刷新列表

                    share = getActivity().getSharedPreferences(MainActivity.account+"product", Activity.MODE_PRIVATE);
                    editor = share.edit();
                    editor.putInt("count",arrayModle.size());
                    for (int i=0 ; i< arrayModle.size() ;i++) {   //重写XML文件
                        editor.putString("" + i + "model", arrayModle.get(i));
                        editor.putString(""+i+"title",arrayTitle.get(i));
                        Log.d("deleteProductXML", "" + i + " :" + arrayModle.get(i));
                        Log.d("deleteProductXM", "" + i + " :" + arrayTitle.get(i));
                    }
                    editor.commit();
                    break;
                case DELETEMETHOD:

                    break;
            }
        }
    }

}
