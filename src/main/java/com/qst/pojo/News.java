package com.qst.pojo;

import com.alibaba.fastjson.annotation.JSONField;

import java.util.Date;

public class News {
    @JSONField(format = "yyyy-MM-dd")
    private Date ctime;
    private String description;
    private String id;
    private String picUrl;
    private String source;
    private String title;
    private String url;
//    private list result;
    public News() {
    }
    public Date getCtime() {
        return ctime;
    }

    public String getDescription() {
        return description;
    }
    public String getId() {
        return id;
    }
    public String getPicUrl() {
        return picUrl;
    }
    public String getSource() {
        return source;
    }
    public String getTitle() {
        return title;
    }
    public String getUrl() {
        return url;
    }
    public void setCtime(Date ctime) {
        this.ctime = ctime;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public void setId(String id) {
        this.id = id;
    }
    public void setPicUrl(String picUrl) {
        this.picUrl = picUrl;
    }

    public void setSource(String source) {
        this.source = source;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public void setUrl(String url) {
        this.url = url;
    }
    @Override
    public String toString() {
        return "News [id=" + id + ", ctime=" + ctime + ", title=" + title + ", description=" + description + ", source="
                + source + ", picUrl=" + picUrl + ", url=" + url + "]";
    }
}
