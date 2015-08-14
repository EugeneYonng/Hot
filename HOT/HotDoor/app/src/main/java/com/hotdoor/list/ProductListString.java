package com.hotdoor.list;

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

    public static final ArrayList<String> PRODUCT_ID = new ArrayList<String>(Arrays.asList("GXY_EDU12",
            "GXY_EDU26","GXY_ENT12","GXY_ENT26","GXY_AEDU26","STD26","GXY_ENH26","STD26","GXY_ENH26",
            "GXY_ADV26","GXY_ADV14B","GXY_EXS26","GXY_ERS26","GXY_DTP12","GXY_DTP26","GXY_OA12","GXY_OA26","GXY_C3","GXY_37"));

    public static final String[][] cloudSpeed = new String[][] {
            {"教育型","GXY_EDU12"},{"教育型","GXY_EDU26"},{"企业型","GXY_ENT12"},{"企业型","GXY_ENT26"}
    };
    public static final String[][] cloudSafe = new String[][] {
            {"教育型","GXY_AEDU26"},{"标准型","STD26"},{"增强型","GXY_ENH26"},{"高级型","GXY_ADV26"},
            {"高级型","GXY_ADV14B"}
    };
    public static final String[][] cloudSave = new String[][] {
            {"企业型","GXY_EXS26"},{"企业型","GXY_ERS26"}
    };
    public static final String[][] cloudManager = new String[][] {
            {"企业型","GXY_DTP12"},{"企业型","GXY_DTP26"}
    };
    public static final String[][] smartCloudServer = new String[][] {
            {"企业型","GXY_OA12"},{"企业型","GXY_OA26"}
    };
    public static final String[][] cloudComputer = new String[][] {
            {"教育型","GXY_C3"},{"教育型","GXY_37"}
    };
}
