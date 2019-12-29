package com.example.testsystem.bean;

import org.litepal.crud.DataSupport;

/**
 * @author syl
 * @time 2019/10/19 20:41
 * @detail
 */
public class GradeBean extends DataSupport {
    String username;
    String answerTime;
    int countError;
    int grade;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getAnswerTime() {
        return answerTime;
    }

    public void setAnswerTime(String answerTime) {
        this.answerTime = answerTime;
    }

    public int getCountError() {
        return countError;
    }

    public void setCountError(int countError) {
        this.countError = countError;
    }

    public int getGrade() {
        return grade;
    }

    public void setGrade(int grade) {
        this.grade = grade;
    }
}
