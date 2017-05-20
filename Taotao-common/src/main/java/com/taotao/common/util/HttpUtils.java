package com.taotao.common.util;

import org.apache.http.HttpEntity;
import org.apache.http.HttpRequest;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class HttpUtils {

    public static String POST(String url, Map<String, Object> params) {
        HttpPost httpPost;
        List<NameValuePair> nameValuePairs;
        UrlEncodedFormEntity entity;
        try {
            httpPost = new HttpPost(url);
            nameValuePairs = new ArrayList<>();
            if (params != null && params.size() > 0) {
                Set<Map.Entry<String, Object>> entries = params.entrySet();
                for (Map.Entry<String, Object> entry : entries) {
                    NameValuePair nameValuePair = new BasicNameValuePair(entry.getKey(), entry.getValue().toString());
                    nameValuePairs.add(nameValuePair);
                }
            }
            entity = new UrlEncodedFormEntity(nameValuePairs, "UTF-8");
            httpPost.setEntity(entity);
            return execute(httpPost);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static String JsonPOST(String url, String jsonData) {

        HttpPost httpPost;
        StringEntity stringEntity;
        httpPost = new HttpPost(url);
        stringEntity = new StringEntity(jsonData, ContentType.APPLICATION_JSON);
        httpPost.setEntity(stringEntity);
        return execute(httpPost);
    }

    public static String GET(String url, Map<String, Object> params) {
        if (params != null && params.size() > 0) {
            String strParams = "?";
            Set<Map.Entry<String, Object>> entries = params.entrySet();
            for (Map.Entry<String, Object> entry : entries) {
                strParams += entry.getKey() + "=" + entry.getValue() + "&";
            }
            strParams.substring(0, strParams.length() - 1);
            url += strParams;
        }
        return GET(url);
    }

    public static String GET(String url) {
        HttpGet httpGet = new HttpGet(url);
        return execute(httpGet);
    }

    public static String execute(HttpUriRequest request) {
        HttpClient client;
        HttpResponse response;
        HttpEntity responseEntity;
        InputStream content;
        BufferedInputStream bufferedInputStream = null;
        StringBuffer stringBuffer;
        try {
            // 1.获取客户端
            client = HttpClients.createDefault();
            // 2.执行并获取响应
            response = client.execute(request);
            // 3.获取相应内容
            responseEntity = response.getEntity();
            // 4.读取流
            content = responseEntity.getContent();
            bufferedInputStream = new BufferedInputStream(content);
            byte[] data = new byte[2048];
            int len;
            stringBuffer = new StringBuffer();
            while ((len = bufferedInputStream.read(data)) != -1) {
                stringBuffer.append(new String(data, 0, len));
            }
            return stringBuffer.toString();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        } finally {
            if (bufferedInputStream != null) {
                try {
                    bufferedInputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
