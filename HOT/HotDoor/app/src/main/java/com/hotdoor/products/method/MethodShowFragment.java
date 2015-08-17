package com.hotdoor.products.method;

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
import android.widget.ExpandableListAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.andexert.expandablelayout.library.ExpandableLayout;
import com.andexert.expandablelayout.library.ExpandableLayoutListView;
import com.hotdoor.adapter.MethodShowAdapter;
import com.hotdoor.list.MtdListItem;
import com.hotdoor.list.ProductListString;
import com.hotdoor.products.main.MainActivity;
import com.hotdoor.products.main.MethodActivity;
import com.hotdoor.products.main.ProductActivity;
import com.hotdoor.products.main.R;
import com.ms.square.android.expandabletextview.ExpandableTextView;
import com.romainpiel.shimmer.Shimmer;
import com.romainpiel.shimmer.ShimmerTextView;

import java.util.ArrayList;


/**
 * Created by Administrator on 2015/8/15.
 */
public class MethodShowFragment extends Fragment{

    private static final int SETCOLLECT = 0;
    private static final int SETNOTCOLLECT = 1;
    private static final int STATE_COLLECT = 0;
    private static final int STATE_CANCLE = 1;
    private static final int STATE_LOGOUT = 2;
    private static final int STATE_ADD = 3;
    private static final int STATE_DELETE = 4;
    private static final int SET_TITLE_TEXT = 5;
    private static final int ADD_CONTENT_ITEM = 6;

    private ShimmerTextView firstTitle;
    private ShimmerTextView secondTitle;
    private ShimmerTextView thirdTitle;
    private ShimmerTextView forthTitle;
    private ImageView imagMethodShow;
    private LinearLayout layoutMethodContent;
    private Shimmer shimmer;

    private int showID;
    private ArrayList<Integer> collectMtdID;   //方案收藏ID
    private CollectListener myListener;

    private SharedPreferences share;
    private SharedPreferences.Editor editor;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.method_show_layout, container, false);
        init(view);
        return view;
    }

    private void init(View view) {
        firstTitle = (ShimmerTextView) view.findViewById(R.id.stv_method_show_1);
        secondTitle = (ShimmerTextView) view.findViewById(R.id.stv_method_show_2);
        thirdTitle = (ShimmerTextView) view.findViewById(R.id.stv_method_show_3);
        forthTitle = (ShimmerTextView) view.findViewById(R.id.stv_method_show_4);
        imagMethodShow = (ImageView) view.findViewById(R.id.iv_method_show_icon);
        layoutMethodContent = (LinearLayout) view.findViewById(R.id.ll_method_show_content);

        new getContentThread(showID).start();
        myListener = new CollectListener();
        MethodActivity.imagMethodCollect.setVisibility(View.VISIBLE);  //显示收藏按钮s
        MethodActivity.imagMethodCollect.setOnClickListener(myListener);

        collectMtdID = new ArrayList<Integer>();
        new ReadXMLThread().start();

        setShimmer();
    }

    private void setShimmer() {
        firstTitle.setReflectionColor(0xfffeef6b);
        secondTitle.setReflectionColor(0xfffeef6b);
        thirdTitle.setReflectionColor(0xfffeef6b);
        forthTitle.setReflectionColor(0xfffeef6b);

        shimmer = new Shimmer();

        shimmer.setDuration(2000).start(firstTitle);
        shimmer.setDuration(2000).start(secondTitle);
        shimmer.setDuration(2000).start(thirdTitle);
        shimmer.setDuration(2000).start(forthTitle);
    }

    public void setMethodShowID(int id) {
        this.showID = id;
    }


    private class CollectListener implements View.OnClickListener {  //收藏按钮的监听器
        private int state = STATE_LOGOUT;

        public void setState(int state) {
            this.state = state;
        }

        @Override
        public void onClick(View v) {
            switch (state){
                case STATE_LOGOUT:  //跳转到登陆界面Fragment
                    Log.d("onClick","LOGOUT");
                    break;
                case STATE_COLLECT:  //点击取消，删除xml里面的信息，设置收藏按钮为白色,改变监听器状态
                    Log.d("onClick","取消收藏");
                    new WriteXMLThread(STATE_DELETE).start();  //取消收藏
                    myshowHandler.sendEmptyMessage(SETNOTCOLLECT);
                    this.state = STATE_CANCLE;
                    showCurrentItem();
                    break;
                case STATE_CANCLE:   //点击收藏,添加信息，设置收藏按钮为红色,改变监听器状态
                    Log.d("onClick","增加收藏");
                    new WriteXMLThread(STATE_ADD).start();  //增加收藏
                    myshowHandler.sendEmptyMessage(SETCOLLECT);
                    this.state = STATE_COLLECT;
                    showCurrentItem();
                    break;

            }
        }

        private void showCurrentItem() {
            for (int i=0 ; i<collectMtdID.size() ;i++) {
                Log.d("showCurrentItem"+i,""+collectMtdID.get(i).intValue());
            }
        }

    }

    private class ReadXMLThread extends Thread {
        @Override
        public void run() {
            if (MainActivity.isLogin) {   //登陆状态
                Log.d("ReadXMLThread", "Login");
                readProductXML();
            }else{
                myshowHandler.sendEmptyMessage(SETNOTCOLLECT);
                myListener.setState(STATE_LOGOUT);
            }
        }

        private void readProductXML() {
            share = getActivity().getSharedPreferences(MainActivity.account+"method", Activity.MODE_PRIVATE);
            int count = share.getInt("count",0);

            if (count != 0) {  //收藏数不为0
                Log.d("readProductXML","not zero");

                for (int i=0 ;i<count; i++) {   //读取方案ID,添加到array
                    collectMtdID.add(new Integer(share.getInt(""+i,0)));  //添加方案ID list
                }

                if (collectMtdID.contains(new Integer(showID))) {  //检查是否有收藏
                    myshowHandler.sendEmptyMessage(SETCOLLECT);  //按钮为红色
                    myListener.setState(STATE_COLLECT);
                }else{  //没有收藏
                    myshowHandler.sendEmptyMessage(SETNOTCOLLECT);  //按钮为白色
                    myListener.setState(STATE_CANCLE);
                }

            }else {  //收藏数为0
                Log.d("readProductXML","is zero");
                myshowHandler.sendEmptyMessage(SETNOTCOLLECT);  //按钮为白色
                myListener.setState(STATE_CANCLE);
            }
        }
    }

    private class getContentThread extends Thread {
        private int id;
        private String[] itemTitle;
        private String[] itemText;
        private int[] itemResource;

        public getContentThread(int id) {
            this.id = id;
        }

        @Override
        public void run() {

            switch (id) {
                case ProductListString.SMART_CAMPUS:

                case ProductListString.SMART_COMPANY:

                case ProductListString.INDUSTRY_INFORMATIZATION:

                case ProductListString.SECURITY_INFORMATIZATION:

                case ProductListString.MOVE_WORKING:

                case ProductListString.LIBRARY_CLOUD:

                case ProductListString.HOTEL_INFORMATIZATION:

                case ProductListString.FINANCIAL_INFORMATIZATION:

                case ProductListString.RURAL_INFORMATIZATION:
                    itemTitle = getActivity().getResources().getStringArray(R.array.smartCampusTitle);
                    itemText = getActivity().getResources().getStringArray(R.array.smartCampusTxet);
                    itemResource = ProductListString.SMART_CAMPUS_RESOURCE;
                    break;
            }

            Bundle bundle = new Bundle();
            bundle.putStringArray("title",itemTitle);
            bundle.putStringArray("text", itemText);
            bundle.putIntArray("resource", itemResource);
            Message msg = new Message();
            msg.what = ADD_CONTENT_ITEM;
            msg.setData(bundle);
            myshowHandler.sendMessage(msg);

        }
    }

    private class WriteXMLThread extends Thread {
        private int state;
        public WriteXMLThread(int state) {
            this.state = state;
        }

        @Override
        public void run() {
            if (state == STATE_ADD) {
                addMethodXML();
            }else if(state == STATE_DELETE) {
                deleteMethodXML();
            }
        }

        private void addMethodXML() {
            share = getActivity().getSharedPreferences(MainActivity.account + "method", Activity.MODE_PRIVATE);
            editor = share.edit();

            collectMtdID.add(new Integer(showID));  //添加这个showid

            editor.putInt("count", collectMtdID.size());  //
            editor.putInt("" + (collectMtdID.size() - 1), showID);
            editor.commit();
        }

        private void deleteMethodXML() {
            share = getActivity().getSharedPreferences(MainActivity.account + "method", Activity.MODE_PRIVATE);
            editor = share.edit();

            collectMtdID.remove(new Integer(showID));   //删除这个收藏ID

            editor.putInt("count", collectMtdID.size());
            for (int i=0 ;i<collectMtdID.size() ; i++) {    //重写xml文件
                editor.putInt(""+i,collectMtdID.get(i).intValue());   //重新写入
            }
            editor.commit();
        }
    }

    Handler myshowHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case SETCOLLECT:
                    MethodActivity.imagMethodCollect.setBackgroundColor(0xffff0000);  //设置红色背景
                    break;
                case SETNOTCOLLECT:
                    MethodActivity.imagMethodCollect.setBackgroundColor(0xff0080FF);   //设置蓝色背景
                    break;
                case SET_TITLE_TEXT:
                    break;
                case ADD_CONTENT_ITEM:

                    Bundle date = msg.getData();
                    String[] titleString = date.getStringArray("title");
                    String[] textString = date.getStringArray("text");
                    int[] resource = date.getIntArray("resource");

                    for (int i=0 ; i<titleString.length ; i++) {
                        ExpandableLayout item = (ExpandableLayout) getActivity().getLayoutInflater().inflate(R.layout.method_show_item,null);
                        TextView title = (TextView) item.findViewById(R.id.tv_method_list_header);
                        TextView text = (TextView) item.findViewById(R.id.tv_method_show_list_text);
                        ImageView imag = (ImageView) item.findViewById(R.id.iv_method_show_list_icon);

                        if (!titleString[i].equals("*")) {
                            title.setText(titleString[i]);
                        }

                        if (!textString[i].equals("*")) {
                            text.setText(textString[i]);
                        }else{
                            Log.d("setText","输入空格");
                            text.setText("                                                                                            ");
                            text.setSingleLine();
                        }

                        if (resource[i] != 0) {
                            imag.setImageResource(resource[i]);
                        }

                        layoutMethodContent.addView(item);
                    }
                    break;
            }
        }
    };
}
