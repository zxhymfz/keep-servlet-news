package com.qst.web;


import com.qst.pojo.News;
import com.qst.pojo.ResultModel;
import com.qst.tools.NewTools;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.List;
import java.util.stream.Collectors;

@WebServlet(value = "/MilitaryNews", initParams = {
        @WebInitParam(name = "httpUrl", value = "https://api.tianapi.com/military/index"),
        @WebInitParam(name = "key", value = "这里填自己的key"),
        @WebInitParam(name = "num", value = "6")

})
public class NewsListAction extends HttpServlet {

    private String key;
    private String num;
    private String httpUrl;
    /**
     * Servlet初始化
     */
    public void init(ServletConfig config) throws ServletException {
        // TODO Auto-generated method stub
        super.init(config);
        key=config.getInitParameter("key");
        num=config.getInitParameter("num");
        httpUrl=config.getInitParameter("httpUrl");
    }

    /**
     * 处理get方式的请求
     */
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws 	ServletException, IOException {
        String httpArg = "key="+key+"&num="+num;
        //发出请求获得json格式的字符串
        String jsonResult = NewTools.sendRequest(httpUrl, httpArg);
        List<News> newsList=NewTools.URLcoding(jsonResult);
        //将处理后的新闻列表存入请求作用域
        req.setAttribute("newsList", newsList);
        //转向到newspage页面,在页面上呈现新闻列表
        this.getServletContext().getRequestDispatcher("/newspage.jsp")
                。forward(req, resp);
    }
}
