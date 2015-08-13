package com.hotdoor.products.product;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.andexert.library.RippleView;
import com.hotdoor.list.ProductListString;
import com.hotdoor.products.main.ProductActivity;
import com.hotdoor.products.main.R;
import com.hotdoor.textview.MyTextView;

import java.util.Timer;
import java.util.TimerTask;

import me.relex.circleindicator.CircleIndicator;

/**
 * Created by Administrator on 2015/8/12.
 */
public class ProductFragment extends Fragment {
    private ViewPager pagerProduct;
    private CircleIndicator indiProduct;

    private RippleView rippCloudSpeed;
    private RippleView rippCloudSafe;
    private RippleView rippCloudSave;
    private RippleView rippCloudManager;
    private RippleView rippCloudServer;
    private RippleView rippCloudComputer;
    private MyTextView textProdTitle;
    private Timer pagerTimer;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.product_fragment, container, false);
        init(view);

        return view;
    }

    private void init(View view) {
        pagerProduct = (ViewPager) view.findViewById(R.id.vp_product_pager);
        indiProduct = (CircleIndicator) view.findViewById(R.id.ci_product_indicator);
        rippCloudSave = (RippleView) view.findViewById(R.id.rv_cloud_save);
        rippCloudComputer = (RippleView) view.findViewById(R.id.rv_cloud_computer);
        rippCloudManager = (RippleView) view.findViewById(R.id.rv_cloud_manager);
        rippCloudSafe = (RippleView) view.findViewById(R.id.rv_cloud_safe);
        rippCloudServer = (RippleView) view.findViewById(R.id.rv_smart_cloud_server);
        rippCloudSpeed = (RippleView) view.findViewById(R.id.rv_cloud_speed);

        rippCloudSpeed.setRippleDuration(100);
        rippCloudServer.setRippleDuration(100);
        rippCloudSafe.setRippleDuration(100);
        rippCloudManager.setRippleDuration(100);
        rippCloudComputer.setRippleDuration(100);
        rippCloudSave.setRippleDuration(100);

        rippCloudComputer.setOnRippleCompleteListener(new myRippListener());
        rippCloudManager.setOnRippleCompleteListener(new myRippListener());
        rippCloudServer.setOnRippleCompleteListener(new myRippListener());
        rippCloudSafe.setOnRippleCompleteListener(new myRippListener());
        rippCloudSpeed.setOnRippleCompleteListener(new myRippListener());
        rippCloudSave.setOnRippleCompleteListener(new myRippListener());

        pagerProduct.setAdapter(new PagerAdapter() {
            @Override
            public int getCount() {
                return 3;
            }

            @Override
            public boolean isViewFromObject(View view, Object object) {
                return view == object;
            }

            @Override
            public void destroyItem(ViewGroup container, int position, Object obj) {
                container.removeView((View) obj);
            }

            @Override
            public Object instantiateItem(ViewGroup container, final int position) {
                TextView text = new TextView(getActivity());
                text.setGravity(Gravity.CENTER);
                text.setTextSize(30);
                text.setTextColor(Color.WHITE);
                text.setText("Page " + position);
                text.setPadding(30, 30, 30, 30);
                int bg = Color.rgb((int) Math.floor(Math.random() * 128) + 64,
                        (int) Math.floor(Math.random() * 128) + 64,
                        (int) Math.floor(Math.random() * 128) + 64);
                text.setBackgroundColor(bg);
                container.addView(text, ViewGroup.LayoutParams.MATCH_PARENT,
                        ViewGroup.LayoutParams.MATCH_PARENT);

                return text;
            }

        });

        indiProduct.setViewPager(pagerProduct);
        pagerTimer = new Timer();
        pagerTimer.schedule(new myTimeTask(), 4000, 4000);
    }

    public void setTitleText(MyTextView text){
        this.textProdTitle = text;
    }

    private class myRippListener implements RippleView.OnRippleCompleteListener {
        @Override
        public void onComplete(RippleView v) {
            FragmentTransaction transaction = getFragmentManager().beginTransaction();
            transaction.setCustomAnimations(R.anim.fragment_slide_in_right, R.anim.fragment_slide_out_left
                    , R.anim.fragment_slide_in_right, R.anim.fragment_slide_out_left);
            ProListFragment fragProList = null;
            switch(v.getId()){
                case R.id.rv_cloud_computer:
                    Log.d("onComplete", "cloud_computer");
                    fragProList = new ProListFragment(ProductListString.CLOUDCOMPUTER);
                    ProductFragment.this.textProdTitle.setText("云电脑");
                    ProductActivity.titleBuffer[1] = "云电脑";
                 //   fragProList.set
                    break;
                case R.id.rv_cloud_manager:
                    fragProList = new ProListFragment(ProductListString.CLOUDMANAGER);
                    ProductFragment.this.textProdTitle.setText("云管理");
                    ProductActivity.titleBuffer[1] = "云管理";
                    break;
                case R.id.rv_cloud_safe:
                    fragProList = new ProListFragment(ProductListString.CLOUDSAFE);
                    ProductFragment.this.textProdTitle.setText("云安全");
                    ProductActivity.titleBuffer[1] = "云安全";
                    break;
                case R.id.rv_cloud_save:
                    fragProList = new ProListFragment(ProductListString.CLOUDSAVE);
                    ProductFragment.this.textProdTitle.setText("云存储");
                    ProductActivity.titleBuffer[1] = "云存储";
                    break;
                case R.id.rv_smart_cloud_server:
                    fragProList = new ProListFragment(ProductListString.SMARTCLOUDSERVER);
                    ProductFragment.this.textProdTitle.setText("睿云服务器");
                    ProductActivity.titleBuffer[1] = "睿云服务器";
                    break;
                case R.id.rv_cloud_speed:
                    fragProList = new ProListFragment(ProductListString.CLOUDSPEED);
                    ProductFragment.this.textProdTitle.setText("云提速");
                    ProductActivity.titleBuffer[1] = "云提速";
                    break;
            }

            pagerTimer.cancel();//关闭定时器
            fragProList.setTitleText(ProductFragment.this.textProdTitle);
            transaction.replace(R.id.fl_product, fragProList, "FragProList");
            transaction.addToBackStack(null);
            transaction.commit();
        }
    }


    private class myTimeTask extends TimerTask {
        @Override
        public void run() {
            myhandler.sendEmptyMessage(0);
        }
    }

    Handler myhandler = new Handler() {
        private int index = 0;
        @Override
        public void handleMessage(Message msg) {
            if(index > 2)
                index = 0;
            pagerProduct.setCurrentItem(index);
            index++;

        }

    };
}
