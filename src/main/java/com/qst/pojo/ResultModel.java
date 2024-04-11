package com.qst.pojo;


import java.util.List;

public class ResultModel {
    private int code;
    private String msg;
    private List<News> newsList;

    public ResultModel() {
        super();
        // TODO Auto-generated constructor stub
    }

    public ResultModel(int code, String msg, List<News> newsList) {
        super();
        this.code = code;
        this.msg = msg;
        this.newsList = newsList;
    }

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

    public List<News> getNewsList() {
        return newsList;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
    public void setNewsList(List<News> newsList) {
        this.newsList = newsList;
    }
    @Override
    public String toString() {
        return "ResultModel [code=" + code + ", msg=" + msg + ", newsList=" + newsList + "]";
    }
}

