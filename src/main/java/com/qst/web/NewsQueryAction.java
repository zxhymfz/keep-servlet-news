package com.qst.web;

import com.qst.pojo.News;
import com.qst.pojo.ResultModel;
import com.qst.tools.NewTools;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.List;
import java.util.stream.Collectors;

public class NewsQueryAction extends HttpServlet {

    private String key;
    private String num;
    private String httpUrl;

    /**
     * Servlet初始化
     */
    public void init() throws ServletException {
        ServletContext servletContext = this.getServletContext();// 获取Servelt上下文对象
        key = servletContext.getInitParameter("key");// 根据参数名获取参数值
        num = servletContext.getInitParameter("num");
        httpUrl = servletContext.getInitParameter("httpUrl");
    }

    /**
     * 处理post请求
     */
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");// 设置取参时的解码字符集
        String keyword = req.getParameter("keyword");
        String httpArg = "key=" + key + "&num=" + num + "&word=" + keyword;// 拼接参数
//        String httpArg = "key=" + key + "&num=" + num;// 拼接参数
        String jsonResult = NewTools.sendRequest(httpUrl, httpArg);
        List<News> newsList = NewTools.URLcoding(jsonResult);
        // 将处理后的新闻列表存入请求作用域
        req.setAttribute("newsList", newsList);
        // 转向到newspage页面,在页面上呈现新闻列表
        this.getServletContext().getRequestDispatcher("/newspage.jsp").forward(req, resp);
    }
}

