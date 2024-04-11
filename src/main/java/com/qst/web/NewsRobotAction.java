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

@WebServlet(value = "/newsrobot", initParams = {
        @WebInitParam(name = "httpUrl", value = "http://api.tianapi.com/military/index"),
//        @WebInitParam(name = "httpUrl", value = "https://apis.tianapi.com/military/index"),
        @WebInitParam(name = "key", value = "7c90b9a18796547b10fbed288e55fad1"),
        @WebInitParam(name = "num", value = "6")

})
public class NewsRobotAction extends HttpServlet {

    private String key;
    private String num;
    private String httpUrl;
    private int page=1;
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
     * 处理get请求
     */
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        String robot=req.getParameter("robot");
        req.setAttribute("robot", robot);
        String httpArg = "key="+key+"&num=" +num+"&page="+("true".equals(robot)?(page+1):page);
        String jsonResult = NewTools.sendRequest(httpUrl, httpArg);
        List<News> newsList = NewTools.URLcoding(jsonResult);
        req.setAttribute("newsList", newsList);
        this.getServletContext().getRequestDispatcher("/newspage.jsp")
                .forward(req, resp);
        page=(page+1)%5;
    }
}
