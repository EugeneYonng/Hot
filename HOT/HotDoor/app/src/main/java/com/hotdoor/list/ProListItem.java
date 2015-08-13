package com.hotdoor.list;

/**
 * Created by Administrator on 2015/8/12.
 */
public class ProListItem {
    private int imagResource;
    private String title;
    private String productModel;

    public void setImageResource(int id) {
        this.imagResource = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setProductModel(String model) {
        this.productModel = model;
    }

    public int getImageResource() {
        return this.imagResource;
    }

    public String getTitle() {
        return this.title;
    }

    public String getProductModel() {
        return this.productModel;
    }
}
