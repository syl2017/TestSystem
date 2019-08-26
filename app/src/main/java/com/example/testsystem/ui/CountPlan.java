package com.example.testsystem.ui;

public class CountPlan {
    private String thingContent;
    private String remindTime;
    private String countTime;

    public CountPlan(String thingContent, String remindTime, String countTime) {
        this.thingContent = thingContent;
        this.remindTime = remindTime;
        this.countTime = countTime;
    }

    public String getThingContent() {
        return thingContent;
    }

    public void setThingContent(String thingContent) {
        this.thingContent = thingContent;
    }

    public String getRemindTime() {
        return remindTime;
    }

    public void setRemindTime(String remindTime) {
        this.remindTime = remindTime;
    }

    public String getCountTime() {
        return countTime;
    }

    public void setCountTime(String countTime) {
        this.countTime = countTime;
    }
}
