package com.example.testsystem.bean;

import org.litepal.crud.DataSupport;

/**
 * @author syl
 * @time 2019/10/24 20:04
 * @detail
 */
public class QuestionBean extends DataSupport {
    private int question_id;
    private int media_type;
    private String label;
    private String question;
    private byte[] media_content;
    private String answer;
    private String option_a;
    private String option_b;
    private String option_c;
    private String option_d;
    private String explain;
    private int option_type;
    private int USER_ANSWER;
    private int sou_type;
    private int error_type;

    public int getQuestion_id() {
        return question_id;
    }

    public void setQuestion_id(int question_id) {
        this.question_id = question_id;
    }

    public int getMedia_type() {
        return media_type;
    }

    public void setMedia_type(int media_type) {
        this.media_type = media_type;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public byte[] getMedia_content() {
        return media_content;
    }

    public void setMedia_content(byte[] media_content) {
        this.media_content = media_content;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public String getOption_a() {
        return option_a;
    }

    public void setOption_a(String option_a) {
        this.option_a = option_a;
    }

    public String getOption_b() {
        return option_b;
    }

    public void setOption_b(String option_b) {
        this.option_b = option_b;
    }

    public String getOption_c() {
        return option_c;
    }

    public void setOption_c(String option_c) {
        this.option_c = option_c;
    }

    public String getOption_d() {
        return option_d;
    }

    public void setOption_d(String option_d) {
        this.option_d = option_d;
    }

    public String getExplain() {
        return explain;
    }

    public void setExplain(String explain) {
        this.explain = explain;
    }

    public int getOption_type() {
        return option_type;
    }

    public void setOption_type(int option_type) {
        this.option_type = option_type;
    }

    public int getUSER_ANSWER() {
        return USER_ANSWER;
    }

    public void setUSER_ANSWER(int USER_ANSWER) {
        this.USER_ANSWER = USER_ANSWER;
    }

    public int getSou_type() {
        return sou_type;
    }

    public void setSou_type(int sou_type) {
        this.sou_type = sou_type;
    }

    public int getError_type() {
        return error_type;
    }

    public void setError_type(int error_type) {
        this.error_type = error_type;
    }


}
