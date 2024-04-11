package com.qst.tools;

import com.alibaba.fastjson.JSONObject;
import com.qst.pojo.News;
import com.qst.pojo.ResultModel;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.List;
import java.util.stream.Collectors;

public class NewTools {

    /**
     * @param httpUrl  请求接口
     * @param httpArg  参数
     * @return 返回结果
     */
    public static String sendRequest(String httpUrl, String httpArg) {
        BufferedReader reader = null;
        String result = null;
        StringBuffer sbf = new StringBuffer();
        httpUrl = httpUrl + "?" + httpArg;
        try {
            URL url = new URL(httpUrl);
            HttpURLConnection connection = (HttpURLConnection) url
                    .openConnection();
            connection.setRequestMethod("GET");
            InputStream is = connection.getInputStream();
            reader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
            String strRead = null;
            while ((strRead = reader.readLine()) != null) {
                sbf.append(strRead);
                sbf.append("\r\n");
            }
            reader.close();
            result = sbf.toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
    /**
     * 将json字符串转成json对象
     * @param jsonResult
     * @return
     */
    public static ResultModel parseJsonToResult(String jsonResult) {
        ResultModel resultModel= JSONObject.parseObject(jsonResult, ResultModel.class);
        return resultModel;
    }

    /**
     * 将返回结果中的URL编码转换为UTF-8
     * @param jsonResult
     * @return
     */
    public static List<News> URLcoding(String jsonResult) {
        ResultModel resultModel = NewTools.parseJsonToResult(jsonResult);
        // 从RusultModel中获取新闻数据
        List<News> newsList=resultModel.getNewsList();
        //对新闻的url进行编码处理
        newsList=newsList.stream().map(n->{
            try {
                n.setUrl(URLEncoder.encode(n.getUrl(), "utf-8"));
                return n;
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
            return null;
        }).collect(Collectors.toList());
        return newsList;
    }
}
