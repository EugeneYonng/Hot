package com.hotdoor.list;

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

    public static final String[][] cloudSpeed = new String[][] {
            {"教育型","GXY_EDU12"},{"教育型","GXY_EDU26"},{"企业型","GXY_ENT12"},{"企业型","GXY_ENT26"}
    };
    public static final String[][] cloudSafe = new String[][] {
            {"教育型","GXY_AEDU26"},{"标准型","STD26"},{"增强型","GXY-ENH26"},{"高级型","GXY-ADV26"},
            {"高级型","GXY-ADV14B"}
    };
    public static final String[][] cloudSave = new String[][] {
            {"企业型","GXY-EXS26"},{"企业型","GXY-ERS26"}
    };
    public static final String[][] cloudManager = new String[][] {
            {"企业型","GXY_DTP12系列"},{"企业型","GXY-DTP26系列"}
    };
    public static final String[][] smartCloudServer = new String[][] {
            {"企业型","GXY-OA12系列"},{"企业型","GXY-OA26系列"}
    };
    public static final String[][] cloudComputer = new String[][] {
            {"教育型","GXY_C3系列"},{"教育型","GXY-37系列"}
    };
}
