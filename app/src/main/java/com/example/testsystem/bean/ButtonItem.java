package com.example.testsystem.bean;

/**
 * @author syl
 * @time 2020/1/12 19:11
 * @detail
 */
public class ButtonItem {
    String name;
    int imageId;

    public ButtonItem(String name, int imageId) {
        this.name = name;
        this.imageId = imageId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getImageId() {
        return imageId;
    }

    public void setImageId(int imageId) {
        this.imageId = imageId;
    }
}
