package com.qst.web;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLDecoder;

@WebServlet(value = "/newsdetails")
public class NewsDetailAction extends HttpServlet {
    /**
     * 处理get方式的请求
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String url= URLDecoder.decode(req.getParameter("url"),"utf-8");
        resp.sendRedirect(url);
    }
}
