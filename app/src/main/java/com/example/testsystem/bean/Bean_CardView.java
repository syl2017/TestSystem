package com.example.testsystem.bean;

/**
 * @author syl
 * @time 2019/10/17 22:32
 * @detail
 */
public class Bean_CardView {
    private String ThingContent;
    private String RemindTime;

    public Bean_CardView(String thingContent, String remindTime, String countTime) {
        ThingContent = thingContent;
        RemindTime = remindTime;
        CountTime = countTime;
    }

    public String getThingContent() {
        return ThingContent;
    }

    public void setThingContent(String thingContent) {
        ThingContent = thingContent;
    }

    public String getRemindTime() {
        return RemindTime;
    }

    public void setRemindTime(String remindTime) {
        RemindTime = remindTime;
    }

    public String getCountTime() {
        return CountTime;
    }

    public void setCountTime(String countTime) {
        CountTime = countTime;
    }

    private String CountTime;
}
