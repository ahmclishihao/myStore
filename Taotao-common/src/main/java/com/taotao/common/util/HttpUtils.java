package com.taotao.common.util;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
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
        // -1.转换参数
        List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
        if (params != null && params.size() > 0) {
            Set<Map.Entry<String, Object>> entries = params.entrySet();
            for (Map.Entry<String, Object> entry : entries) {
                NameValuePair nameValuePair = new BasicNameValuePair(entry.getKey(), entry.getValue().toString());
                nameValuePairs.add(nameValuePair);
            }
        }

        // 0.获取客户端
        HttpClient client = HttpClients.createDefault();

        HttpPost httpPost = new HttpPost(url);

        UrlEncodedFormEntity entity = null;
        try {
            // 1.设置param
            entity = new UrlEncodedFormEntity(nameValuePairs, "UTF-8");
            httpPost.setEntity(entity);

            // 2.执行并获取响应
            HttpResponse response = client.execute(httpPost);
            HttpEntity entity1 = response.getEntity();
            // 3.获取相应内容
            InputStream content = entity1.getContent();
            BufferedInputStream bufferedInputStream = new BufferedInputStream(content);
            byte[] data = new byte[2048];
            int len;
            StringBuffer stringBuffer = new StringBuffer();
            while ((len = bufferedInputStream.read(data)) != -1) {
                stringBuffer.append(new String(data, 0, len));
            }
            return stringBuffer.toString();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
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
        // 1.client
        CloseableHttpClient aDefault = HttpClients.createDefault();
        // 2.httpRequest
        HttpGet httpGet = new HttpGet(url);
        // 3.httpResponse
        try {
            CloseableHttpResponse httpResponse = aDefault.execute(httpGet);
            // 4.httpEntity
            HttpEntity entity = httpResponse.getEntity();
            // 5.content
            InputStream content = entity.getContent();
            // 6.读取信息
            BufferedInputStream bufferedInputStream = new BufferedInputStream(content);
            byte[] data = new byte[2048];
            int len;
            StringBuffer stringBuffer = new StringBuffer();
            while ((len = bufferedInputStream.read(data)) != -1) {
                stringBuffer.append(new String(data, 0, len));
            }
            return stringBuffer.toString();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

}
