package com.example.testsystem.bean;

import org.litepal.crud.DataSupport;

/**
 * @author syl
 * @time 2019/10/17 22:38
 * @detail
 */
public class UserBean extends DataSupport {
    private String userName;
    private String userEmail;
    private String userPassword;
    private String userSignTime;
    private String userTpye;
    private String userRecord;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public String getUserSignTime() {
        return userSignTime;
    }

    public void setUserSignTime(String userSignTime) {
        this.userSignTime = userSignTime;
    }

    public String getUserTpye() {
        return userTpye;
    }

    public void setUserTpye(String userTpye) {
        this.userTpye = userTpye;
    }

    public String getUserRecord() {
        return userRecord;
    }

    public void setUserRecord(String userRecord) {
        this.userRecord = userRecord;
    }
}
