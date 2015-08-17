package com.hotdoor.list;

/**
 * Created by Administrator on 2015/8/17.
 */
public class CmpIntroductItem {
    private String headerTitle;
    private String headerText;
    private int headerIconResource;
    private String contentText;
    private int contentIcon1Resource;
    private int contentIcon2Resource;

    public void setHeaderTitle(String title) {
        this.headerTitle = title;
    }

    public void setHeaderText(String text) {
        this.headerText = text;
    }

    public void setHeaderIconResource(int resource) {
        this.headerIconResource = resource;
    }

    public void setContentText(String text) {
        this.contentText = text;
    }

    public void setContentIcon1Resource(int resource) {
        this.contentIcon1Resource = resource;
    }

    public void setContentIcon2Resource(int resource) {
        this.contentIcon2Resource = resource;
    }

    public String getHeaderTitle() {
        return this.headerTitle;
    }

    public String getHeaderText() {
        return this.headerText;
    }

    public int getHeaderIconResource() {
        return this.headerIconResource;
    }

    public String getContentText() {
        return this.contentText;
    }

    public int getContentIcon1Resource() {
        return this.contentIcon1Resource;
    }

    public int getContentIcon2Resource() {
        return this.contentIcon2Resource;
    }
}
