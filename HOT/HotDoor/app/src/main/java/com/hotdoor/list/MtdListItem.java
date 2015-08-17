package com.hotdoor.list;

/**
 * Created by Administrator on 2015/8/15.
 */
public class MtdListItem {
    private String title;
    private String introduce;
    private int imagResource;

    public void setTitle(String title) {
        this.title = title;
    }

    public void setIntroduce(String string) {
        this.introduce = string;
    }

    public void setImagResource(int resource) {
        this.imagResource = resource;
    }

    public String getTitle() {
        return this.title;
    }

    public String getIntroduce() {
        return this.introduce;
    }

    public int getImagResource() {
        return this.imagResource;
    }
}
