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

import com.hotdoor.products.main.MainActivity;
import com.hotdoor.products.main.ProductActivity;
import com.hotdoor.products.main.R;
import com.hotdoor.textview.MyTextView;
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
    private String productTitle;
    private int imageResource;
    private Shimmer shimmer;
    private ImageView imageShow;
    private boolean isCollect;
    private SharedPreferences share;
    private SharedPreferences.Editor editor;
    private ArrayList<String> productCollect;
    private ArrayList<String> arrayTitle;
    private collectListener myListener;


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

        myListener = new collectListener();
        ProductActivity.imagProCollect.setVisibility(View.VISIBLE);  //显示收藏按钮s
        ProductActivity.imagProCollect.setOnClickListener(myListener);

        productCollect = new ArrayList<String>();  //创建对应的数组
        arrayTitle = new ArrayList<String>();

        new readXMLThread().start();
//        imageShow.setBackgroundResource(this.imageResource);
        setShimmer();

    }

    public void setProductTitle(String title) {
        this.productTitle = title;
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
        }

        private void readProductXML() {
            share = getActivity().getSharedPreferences(MainActivity.account+"product", Activity.MODE_PRIVATE);
            int count = share.getInt("count",0);

            if (count != 0) {  //收藏数不为0
                Log.d("readProductXML","not zero");
                String buffer;

                for (int i=0 ; i<count ; i++) {
                    buffer = share.getString(""+i+"model","");  //读取型号
                    arrayTitle.add(share.getString(""+i+"title",""));  //读取标题
                    Log.d("readProductXML",share.getString(""+i+"title",""));
                    Log.d("readProductXML",buffer);
                    if (buffer != null) {
                        productCollect.add(buffer);
                    }
                }

                if (productCollect.contains(ProductActivity.titleBuffer[2])) {   //检查是否有收藏,
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
                    Log.d("onClick","增加收藏");
                    new writeXMLThread(STATE_ADD).start();  //增加收藏
                    myshowHandler.sendEmptyMessage(SETCOLLECT);
                    this.state = STATE_COLLECT;
                    showCurrentItem();
                    break;
                case STATE_COLLECT:   //点击取消，删除xml里面的信息，设置收藏按钮为白色,改变监听器状态
                    Log.d("onClick","取消收藏");
                    new writeXMLThread(STATE_DELETE).start();  //取消收藏
                    myshowHandler.sendEmptyMessage(SETNOTCOLLECT);
                    this.state = STATE_CANCLE;
                    showCurrentItem();
                    break;
                case STATE_LOGOUT:   //跳转到登陆界面Frgment
                    Log.d("onClick","LOGOUT");
                    break;
            }
        }
    }

    private void showCurrentItem() {
        for (int i=0 ; i<productCollect.size() ;i++) {
            Log.d("showCurrentItem"+i,productCollect.get(i));
            Log.d("showCurrentItem" + i, arrayTitle.get(i));
        }
    }

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

        }

        private void deleteProductXML() {
            share = getActivity().getSharedPreferences(MainActivity.account + "product", Activity.MODE_PRIVATE);
            editor = share.edit();
            Log.d("deleteProductXML",""+productCollect.size());

            arrayTitle.remove(productCollect.indexOf(ProductActivity.titleBuffer[2]));
            productCollect.remove(ProductActivity.titleBuffer[2]);

            Log.d("deleteProductXML",""+productCollect.size());
            editor.putInt("count",productCollect.size());
            for (int i=0 ;i<productCollect.size() ; i++) {    //重写xml文件
                editor.putString(""+i+"model",productCollect.get(i));
                editor.putString(""+i+"title",arrayTitle.get(i));
                Log.d("deleteProductXML", "" + i + " :" + productCollect.get(i));
                Log.d("deleteProductXM", "" + i + " :" + arrayTitle.get(i));
            }
            editor.commit();
        }

        private void addProductXML() {
            share = getActivity().getSharedPreferences(MainActivity.account + "product", Activity.MODE_PRIVATE);
            editor = share.edit();
            productCollect.add(ProductActivity.titleBuffer[2]);
            arrayTitle.add(productTitle);
            editor.putInt("count", productCollect.size());
            editor.putString("" + (productCollect.size() - 1)+"model", ProductActivity.titleBuffer[2]);
            editor.putString(""+ (productCollect.size()-1)+"title", productTitle);
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
