package com.hotdoor.products.main;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.hotdoor.regularHexagon.RegularHexagonView;
import com.hotdoor.textview.MyTextView;

/**
 * Created by Yip on 2015/8/3.
 */
public class MethodActivity extends Activity {

    private ImageView methodLeft;
    private ImageView methodIcon;
    private MyTextView methodTitle;
    private RelativeLayout smartCompany;
    private RelativeLayout smartCampus;
    private RegularHexagonView infoSafe;
    private RegularHexagonView vallInfo;
    private RegularHexagonView moveWork;
    private RegularHexagonView holtInfo;
    private RegularHexagonView finaInfo;
    private RegularHexagonView libiInfo;
    private RegularHexagonView induInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.method);

        methodLeft = (ImageView)findViewById(R.id.iv_methodLeft);
        methodIcon = (ImageView)findViewById(R.id.iv_methonIcon);
        methodTitle = (MyTextView)findViewById(R.id.mtv_methodTitle);
        smartCampus = (RelativeLayout)findViewById(R.id.rl_smartCamp);
        smartCompany = (RelativeLayout)findViewById(R.id.rl_smartComp);
        infoSafe = (RegularHexagonView)findViewById(R.id.rhv_infoSafe);
        vallInfo = (RegularHexagonView)findViewById(R.id.rhv_vallInfo);
        moveWork = (RegularHexagonView)findViewById(R.id.rhv_moveWork);
        holtInfo = (RegularHexagonView)findViewById(R.id.rhv_holtInfo);
        finaInfo = (RegularHexagonView)findViewById(R.id.rhv_finaInfo);
        libiInfo = (RegularHexagonView)findViewById(R.id.rhv_libiInfo);
        induInfo = (RegularHexagonView)findViewById(R.id.rhv_induInfo);

        methodLeft.setOnClickListener(new myListener());
        methodTitle.setOnClickListener(new myListener());
        methodIcon.setOnClickListener(new myListener());

    }

    private class myListener implements View.OnClickListener{
        @Override
        public void onClick(View v){
            switch (v.getId()){
                case R.id.iv_methodLeft:
                case R.id.iv_methonIcon:
                case R.id.mtv_methodTitle:
                    Intent intent = new Intent(MainActivity.ACTION_MAINACTIVITY);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    MethodActivity.this.startActivity(intent);
                    finish();
                    break;
                case R.id.rl_smartCamp:
                    break;
                case R.id.rl_smartComp:
                    break;
                case R.id.rhv_finaInfo:
                    break;
                case R.id.rhv_holtInfo:
                    break;
                case R.id.rhv_induInfo:
                    break;
                case R.id.rhv_infoSafe:
                    break;
                case R.id.rhv_libiInfo:
                    break;
                case R.id.rhv_moveWork:
                    break;
                case R.id.rhv_vallInfo:
                    break;

            }
        }
    }

}
