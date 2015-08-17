package com.hotdoor.list;

import com.hotdoor.products.main.R;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by Administrator on 2015/8/12.
 */
public final class ProductListString {
    public static final int CLOUDSPEED = 0;
    public static final int CLOUDSAFE = 1;
    public static final int CLOUDSAVE = 2;
    public static final int CLOUDMANAGER = 3;
    public static final int SMARTCLOUDSERVER = 4;
    public static final int CLOUDCOMPUTER =5;

    public static final int GXY_EDU12 = 0;
    public static final int GXY_EDU26 = 1;
    public static final int GXY_ENT12 = 2;
    public static final int GXY_ENT26 = 3;
    public static final int GXY_AEDU26 = 4;
    public static final int STD26 = 5;
    public static final int GXY_ENH26 = 6;
    public static final int GXY_ADV26 = 7;
    public static final int GXY_ADV14B = 8;
    public static final int GXY_EXS26 = 9;
    public static final int GXY_ERS26 = 10;
    public static final int GXY_DTP12 = 11;
    public static final int GXY_DTP26 = 12;
    public static final int GXY_OA12 = 13;
    public static final int GXY_OA26 = 14;
    public static final int GXY_C3 = 15;
    public static final int GXY_37 = 16;

    public static final int SMART_COMPANY = 0;
    public static final int SMART_CAMPUS = 1;
    public static final int SECURITY_INFORMATIZATION = 2;
    public static final int MOVE_WORKING = 3;
    public static final int LIBRARY_CLOUD = 4;
    public static final int HOTEL_INFORMATIZATION = 5;
    public static final int INDUSTRY_INFORMATIZATION = 6;
    public static final int FINANCIAL_INFORMATIZATION = 7;
    public static final int RURAL_INFORMATIZATION = 8;

    public static final int PATENT = 0;
    public static final int HONOUR = 1;
    public static final int CULTURE = 2;
    public static final int PARTNER = 3;
    public static final int CONNECT = 4;

    public static final ArrayList<String> PRODUCT_ID = new ArrayList<String>(Arrays.asList("GXY_EDU12",
            "GXY_EDU26","GXY_ENT12","GXY_ENT26","GXY_AEDU26","STD26","GXY_ENH26","STD26","GXY_ENH26",
            "GXY_ADV26","GXY_ADV14B","GXY_EXS26","GXY_ERS26","GXY_DTP12","GXY_DTP26","GXY_OA12","GXY_OA26","GXY_C3","GXY_37"));

    public static final int[][] PATENT_IMAGE_RESOURCE = new int[][]{
            {R.drawable.ic_expand_more_black_24dp ,R.drawable.testimage,0},{R.drawable.ic_expand_more_black_24dp ,R.drawable.testimage,0},{R.drawable.ic_expand_more_black_24dp ,R.drawable.testimage,0},{R.drawable.ic_expand_more_black_24dp ,R.drawable.testimage,0}
    };

    public static final int[] SMART_CAMPUS_RESOURCE = new int[]{
            R.drawable.testimage,0,R.drawable.testimage
    };

    public static final int[] CLOUD_SPEED_RESOURCE = new int[] {
           R.drawable.testimage, R.drawable.testimage,R.drawable.testimage,R.drawable.testimage,
    };

    public static final int[] CLOUD_SAFE_RESOURCE = new int[] {
            R.drawable.testimage, R.drawable.testimage,R.drawable.testimage,R.drawable.testimage,R.drawable.testimage,
    };

    public static final int[] CLOUD_SAVE_RESOURCE = new int[] {
            R.drawable.testimage, R.drawable.testimage
    };

    public static final int[] CLOUD_MANAGER_RESOURCE = new int[] {
            R.drawable.testimage, R.drawable.testimage
    };

    public static final int[] CLOUD_SERVER_RESOURCE = new int[] {
            R.drawable.testimage, R.drawable.testimage
    };

    public static final int[] CLOUD_COMPUTER_RESOURCE = new int[] {
            R.drawable.testimage, R.drawable.testimage
    };

    public static final int[] METHOD_IMAGE_RESOURCE = new int[] {   //图片顺序参照方法ID
            R.drawable.testimage,R.drawable.testimage,R.drawable.testimage,R.drawable.testimage,R.drawable.testimage,R.drawable.testimage,R.drawable.testimage,R.drawable.testimage,R.drawable.testimage
    };
}
