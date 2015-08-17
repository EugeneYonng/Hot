package com.hotdoor.products.product;

import android.app.Activity;
import android.app.Fragment;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.hotdoor.list.ProductListString;
import com.hotdoor.products.main.MainActivity;
import com.hotdoor.products.main.ProductActivity;
import com.hotdoor.products.main.R;
import com.hotdoor.textview.MyTextView;
import com.ms.square.android.expandabletextview.ExpandableTextView;
import com.romainpiel.shimmer.Shimmer;
import com.romainpiel.shimmer.ShimmerTextView;

import java.util.ArrayList;

/**
 * Created by Administrator on 2015/8/12.
 */
public class ProdShowFragment extends Fragment{

    private static final int SETCOLLECT = 0;
    private static final int SETNOTCOLLECT = 1;
    private static final int STATE_COLLECT = 0;
    private static final int STATE_CANCLE = 1;
    private static final int STATE_LOGOUT = 2;
    private static final int STATE_ADD = 3;
    private static final int STATE_DELETE = 4;

    private ShimmerTextView textShowFirst;
    private ShimmerTextView textShowSecond;
    private ShimmerTextView textShowThird;
    private ShimmerTextView textShowFourth;
    private ExpandableTextView etvShowText;
    private ExpandableTextView etvFuntionText;
    private ExpandableTextView etvStandardText;
    private TextView productTextTitle1;
    private TextView productTextTitle2;
    private TextView productTextTitle3;
    private Shimmer shimmer;
    private ImageView imageShow;
    private collectListener myListener;

    private int listimageResource;
    private String listTextString;
    private int productID;

    private SharedPreferences share;
    private SharedPreferences.Editor editor;
    private ArrayList<String> listTitle;
    private ArrayList<String> listText;
    private ArrayList<Integer> listResource;


//    public ProdShowFragment(String[] title ,int imagRes) {
//        this.textTitle = title;
//        this.imageResource = imagRes;
//    }

    @Override
    public View onCreateView(LayoutInflater inflater , ViewGroup container, Bundle saveInstanceState) {
        View view = inflater.inflate(R.layout.product_fragment_show, container, false);
        init(view);
        return view;
    }

    private void init(View view) {
        textShowFirst = (ShimmerTextView) view.findViewById(R.id.stv_product_show_1);
        textShowSecond = (ShimmerTextView) view.findViewById(R.id.stv_product_show_2);
        textShowThird = (ShimmerTextView) view.findViewById(R.id.stv_product_show_3);
        textShowFourth = (ShimmerTextView) view.findViewById(R.id.stv_product_show_4);
        imageShow = (ImageView) view.findViewById(R.id.iv_product_show_icon);

        etvShowText = (ExpandableTextView) view.findViewById(R.id.expend_text_product_show);
        etvFuntionText = (ExpandableTextView) view.findViewById(R.id.expend_text_product_function);
        etvStandardText = (ExpandableTextView) view.findViewById(R.id.expend_text_product_standard);

        productTextTitle1 = (TextView) view.findViewById(R.id.tv_product_title_1);
        productTextTitle2 = (TextView) view.findViewById(R.id.tv_product_title_2);
        productTextTitle3 = (TextView) view.findViewById(R.id.tv_product_title_3);

        productTextTitle1.setText("产品介绍");
        productTextTitle2.setText("功能特点");
        productTextTitle3.setText("技术规格");

        setProductIntroduceText(productID);   //设置文字简介内容

        myListener = new collectListener();
        ProductActivity.imagProCollect.setVisibility(View.VISIBLE);  //显示收藏按钮s
        ProductActivity.imagProCollect.setOnClickListener(myListener);

//        productCollect = new ArrayList<String>();  //创建对应的数组
//        arrayTitle = new ArrayList<String>();
        listResource = new ArrayList<Integer>();
        listText = new ArrayList<String>();
        listTitle = new ArrayList<String>();

        new readXMLThread().start();
//        imageShow.setBackgroundResource(this.imageResource);
        setShimmer();

    }

    public void setListimageResource(int resource) {
        this.listimageResource = resource;
    }

    public void setListTextString (String text) {
        this.listTextString = text;
    }

    private void setProductIntroduceText(int id) {
        switch (id) {
            case ProductListString.GXY_EDU12:
                Log.d("Product",ProductListString.PRODUCT_ID.get(ProductListString.GXY_EDU12));   //在此处设置产品介绍文字
                break;
            case ProductListString.GXY_EDU26:
                Log.d("Product",ProductListString.PRODUCT_ID.get(ProductListString.GXY_EDU26));
                break;
            case ProductListString.GXY_ENT12:
                Log.d("Product",ProductListString.PRODUCT_ID.get(ProductListString.GXY_ENT12));
                break;
            case ProductListString.GXY_ENT26:
                Log.d("Product",ProductListString.PRODUCT_ID.get(ProductListString.GXY_ENT26));
                break;
            case ProductListString.GXY_AEDU26:
                Log.d("Product",ProductListString.PRODUCT_ID.get(ProductListString.GXY_AEDU26));
                break;
            case ProductListString.STD26:
                Log.d("Product",ProductListString.PRODUCT_ID.get(ProductListString.STD26));
                break;
            case ProductListString.GXY_ENH26:
                Log.d("Product",ProductListString.PRODUCT_ID.get(ProductListString.GXY_ENH26));
                break;
            case ProductListString.GXY_ADV26:
                Log.d("Product",ProductListString.PRODUCT_ID.get(ProductListString.GXY_ADV26));
                break;
            case ProductListString.GXY_ADV14B:
                Log.d("Product",ProductListString.PRODUCT_ID.get(ProductListString.GXY_ADV14B));
                break;
            case ProductListString.GXY_EXS26:
                Log.d("Product",ProductListString.PRODUCT_ID.get(ProductListString.GXY_EXS26));
                break;
            case ProductListString.GXY_ERS26:
                Log.d("Product",ProductListString.PRODUCT_ID.get(ProductListString.GXY_ERS26));
                break;
            case ProductListString.GXY_DTP12:
                Log.d("Product",ProductListString.PRODUCT_ID.get(ProductListString.GXY_DTP12));
                break;
            case ProductListString.GXY_DTP26:
                Log.d("Product",ProductListString.PRODUCT_ID.get(ProductListString.GXY_DTP26));
                break;
            case ProductListString.GXY_OA12:
                Log.d("Product",ProductListString.PRODUCT_ID.get(ProductListString.GXY_OA12));
                break;
            case ProductListString.GXY_OA26:
                Log.d("Product",ProductListString.PRODUCT_ID.get(ProductListString.GXY_OA26));
                break;
            case ProductListString.GXY_C3:
                Log.d("Product",ProductListString.PRODUCT_ID.get(ProductListString.GXY_C3));
                break;
            case ProductListString.GXY_37:
                Log.d("Product", ProductListString.PRODUCT_ID.get(ProductListString.GXY_37));
                break;
        }

        textShowFirst.setText("云化");
        textShowSecond.setText("全面提速");
        textShowThird.setText("利用现有资源");
        textShowFourth.setText("打造高性价比的云化机房");
        etvShowText.setText(getString(R.string.GXY_EDU12_show));
        etvFuntionText.setText(getString(R.string.GXY_EDU12_funtion));
        etvStandardText.setText(getString(R.string.GXY_EDU12_Standard));
    }

    public void setProductID(int id){
        this.productID = id;
    }

    class readXMLThread extends Thread {
        @Override
        public void run() {   //读取xml文件，设置收藏按钮为红色或者白色

            if (MainActivity.isLogin) {   //登陆状态
                Log.d("readXMLThread", "Login");
                readProductXML();
            }else{
                myshowHandler.sendEmptyMessage(ProdShowFragment.SETNOTCOLLECT);
                myListener.setState(STATE_LOGOUT);
            }

            showCurrentCollect();
        }

        private void readProductXML() {
            share = getActivity().getSharedPreferences(MainActivity.account+"product", Activity.MODE_PRIVATE);
            int count = share.getInt("count",0);  //读取收藏数

            if (count != 0) {  //收藏数不为0
                Log.d("readProductXML","not zero");
                String buffer;

                for (int i=0 ; i<count ; i++) {
                    listTitle.add(share.getString(""+i+"title",""));
                    listText.add(share.getString("" + i + "text", ""));  //读取标题
                    listResource.add(new Integer(share.getInt(""+i+"resource",0)));
                    Log.d("readProductXML", share.getString("" + i + "title", ""));

                }

                if (listTitle.contains(ProductActivity.titleBuffer[2])) {   //检查是否有收藏,
                    myshowHandler.sendEmptyMessage(ProdShowFragment.SETCOLLECT);  //按钮为红色
                    myListener.setState(STATE_COLLECT);
                }else{  //没有找到,没有收藏
                    Log.d("readProductXML","not find collect");
                    myshowHandler.sendEmptyMessage(ProdShowFragment.SETNOTCOLLECT);  //按钮为白色
                    myListener.setState(STATE_CANCLE);

                }

            }else {  //收藏数为0
                Log.d("readProductXML","is zero");
                myshowHandler.sendEmptyMessage(ProdShowFragment.SETNOTCOLLECT);  //按钮为白色
                myListener.setState(STATE_CANCLE);
            }

        }
    }

    private void showCurrentCollect() {
        for(int i=0;i<listTitle.size();i++) {
            Log.d("showCurrentCollect","title:"+i+listTitle.get(i) );
            Log.d("showCurrentCollect","text:"+i+listText.get(i));
            Log.d("showCurrentCollect","resource"+i+listResource.get(i));
        }
    }

    private class collectListener implements View.OnClickListener{

        private int state = STATE_LOGOUT;

        public void setState(int state) {
            this.state = state;
        }

        @Override
        public void onClick(View v) {
            Log.d("myListener","onClick");
            switch (state) {
                case STATE_CANCLE:  //点击收藏,添加信息，设置收藏按钮为红色,改变监听器状态
                    Log.d("onClick", "增加收藏");
                    new writeXMLThread(STATE_ADD).start();  //增加收藏
                    myshowHandler.sendEmptyMessage(SETCOLLECT);
                    this.state = STATE_COLLECT;
//                    showCurrentItem();
                    break;
                case STATE_COLLECT:   //点击取消，删除xml里面的信息，设置收藏按钮为白色,改变监听器状态
                    Log.d("onClick", "取消收藏");
                    new writeXMLThread(STATE_DELETE).start();  //取消收藏
                    myshowHandler.sendEmptyMessage(SETNOTCOLLECT);
                    this.state = STATE_CANCLE;
//                    showCurrentItem();
                    break;
                case STATE_LOGOUT:   //跳转到登陆界面Frgment
                    Log.d("onClick","LOGOUT");
                    break;
            }
        }
    }

//    private void showCurrentItem() {
//        for (int i=0 ; i<productCollect.size() ;i++) {
//            Log.d("showCurrentItem"+i,productCollect.get(i));
//            Log.d("showCurrentItem" + i, arrayTitle.get(i));
//        }
//    }

    class writeXMLThread extends Thread {  //点击收藏按钮后，写入xml文件。
        private int state;
        public writeXMLThread(int state) {
            this.state = state;
        }

        @Override
        public void run() {
            if (state == STATE_ADD) {
                addProductXML();
            }else if(state == STATE_DELETE) {
                deleteProductXML();
            }
            showCurrentCollect();
        }

        private void deleteProductXML() {
            share = getActivity().getSharedPreferences(MainActivity.account + "product", Activity.MODE_PRIVATE);
            editor = share.edit();
            Log.d("deleteProductXML",""+listTitle.size());

            listText.remove(listTitle.indexOf(ProductActivity.titleBuffer[2]));  //删除标题对应的内容
            listResource.remove(listTitle.indexOf(ProductActivity.titleBuffer[2]));  //删除标题对应的图片
            listTitle.remove(ProductActivity.titleBuffer[2]);  //删除辩题

            Log.d("deleteProductXML", "" + listTitle.size());
            editor.putInt("count", listTitle.size());
            for (int i=0 ;i<listTitle.size() ; i++) {    //重写xml文件
                editor.putString("" + i + "title", listTitle.get(i));
                editor.putString("" + i + "text", listText.get(i));
                editor.putInt("" + i + "resource", listResource.get(i).intValue());
            }
            editor.commit();
        }

        private void addProductXML() {
            share = getActivity().getSharedPreferences(MainActivity.account + "product", Activity.MODE_PRIVATE);
            editor = share.edit();
            listTitle.add(ProductActivity.titleBuffer[2]);
            listText.add(listTextString);
            listResource.add(listimageResource);

            editor.putInt("count", listTitle.size());
            editor.putString("" + (listTitle.size() - 1) + "title", ProductActivity.titleBuffer[2]);
            editor.putString(""+ (listTitle.size()-1)+"text", listTextString);
            editor.putInt(""+ (listTitle.size()-1)+"resource", listimageResource);
            editor.commit();
        }
    }



    Handler myshowHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case ProdShowFragment.SETCOLLECT:
                    ProductActivity.imagProCollect.setBackgroundColor(0xffff0000);  //设置红色背景
                    break;
                case ProdShowFragment.SETNOTCOLLECT:
                    ProductActivity.imagProCollect.setBackgroundColor(0xff0080FF);   //设置蓝色背景
                    break;
            }
        }

    };

    private void setShimmer() {
        textShowFirst.setReflectionColor(0xfffeef6b);
        textShowSecond.setReflectionColor(0xfffeef6b);
        textShowThird.setReflectionColor(0xfffeef6b);
        textShowFourth.setReflectionColor(0xfffeef6b);

        shimmer = new Shimmer();
        shimmer.setDuration(2000).start(textShowFirst);
        shimmer.setDuration(2000).start(textShowSecond);
        shimmer.setDuration(2000).start(textShowThird);
        shimmer.setDuration(2000).start(textShowFourth);
    }
}
