package com.wp.unset.action.utils;

import com.wp.unset.action.constant.HttpConstant;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.RequestBuilder;
import org.apache.http.entity.StringEntity;
import org.apache.http.message.BasicHeader;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class HttpClientUtil {

    public static String sendPostByJson(String url, String jsonParam,int reSend) {
        String result = "";
        long startTime=System.currentTimeMillis();
        long endTime= 0L;
        HttpEntity httpEntity = null;
        HttpResponse httpResponse = null;
        HttpClient httpClient = null;
        try {
            httpClient = HttpClientFactory.getInstance().getHttpClient();

            HttpPost httpPost = HttpClientFactory.getInstance().httpPost(url);
            Header header=new BasicHeader("Accept-Encoding",null);
            httpPost.setHeader(header);

            StringEntity stringEntity = new StringEntity(jsonParam, HttpConstant.UTF8_ENCODE);
            stringEntity.setContentEncoding(HttpConstant.UTF8_ENCODE);
            stringEntity.setContentType(HttpConstant.APPLICATION_JSON);
            httpPost.setEntity(stringEntity);

            httpResponse = httpClient.execute(httpPost);
            httpEntity= httpResponse.getEntity();
            result = EntityUtils.toString(httpEntity);
        } catch (Exception e) {
            if (reSend > 0) {
                result = sendPostByJson(url, jsonParam, reSend - 1);
                if (result != null && !"".equals(result)) {
                    return result;
                }
            }
        }finally {
            try {
                EntityUtils.consume(httpEntity);
            } catch (IOException e) {
            }
        }

        endTime=System.currentTimeMillis();
       return result;

    }

    public static String sendPostByForm(String url, Map<String,String> map,int reSend) {
        String result = "";
        long startTime=System.currentTimeMillis();

        long endTime= 0L;
        HttpEntity httpEntity = null;
        UrlEncodedFormEntity entity = null;
        HttpResponse httpResponse = null;
        HttpClient httpClient = null;
        try {
            httpClient = HttpClientFactory.getInstance().getHttpClient();
            HttpPost httpPost = HttpClientFactory.getInstance().httpPost(url);

            List<NameValuePair> list = new ArrayList<NameValuePair>();
            Iterator iterator = map.entrySet().iterator();
            while(iterator.hasNext()){
                Map.Entry<String,String> elem = (Map.Entry<String, String>) iterator.next();
                list.add(new BasicNameValuePair(elem.getKey(),elem.getValue()));
            }
            entity = new UrlEncodedFormEntity(list,HttpConstant.UTF8_ENCODE);
            httpPost.setEntity(entity);
            httpResponse = httpClient.execute(httpPost);
            httpEntity= httpResponse.getEntity();
            result = EntityUtils.toString(httpEntity);
        } catch (Exception e) {
            if (reSend > 0) {
                 result = sendPostByForm(url, map, reSend - 1);
                if (result != null && !"".equals(result)) {
                    return result;
                }
            }
        }finally {
            try {
                EntityUtils.consume(httpEntity);
            } catch (IOException e) {
            }
        }

        endTime=System.currentTimeMillis();
        return result;

    }

    public static String sendPostByXml(String url, String xmlParam,int reSend) {
        String result = "";
        long startTime = System.currentTimeMillis();

        long endTime = 0L;
        HttpEntity httpEntity = null;
        HttpResponse httpResponse = null;
        HttpClient httpClient = null;
        try {

            httpClient = HttpClientFactory.getInstance().getHttpClient();

            HttpPost httpPost = HttpClientFactory.getInstance().httpPost(url);
            StringEntity stringEntity = new StringEntity(xmlParam, HttpConstant.UTF8_ENCODE);
            stringEntity.setContentEncoding(HttpConstant.UTF8_ENCODE);
            stringEntity.setContentType(HttpConstant.TEXT_XML);
            httpPost.setEntity(stringEntity);

            httpResponse = httpClient.execute(httpPost);
            httpEntity = httpResponse.getEntity();
            result = EntityUtils.toString(httpEntity,HttpConstant.UTF8_ENCODE);
        } catch (Exception e) {
            if (reSend > 0) {
              result = sendPostByJson(url, xmlParam, reSend - 1);
                if (result != null && !"".equals(result)) {
                    return result;
                }
            }
        } finally {
            try {
                EntityUtils.consume(httpEntity);
            } catch (IOException e) {
            }
            endTime = System.currentTimeMillis();
           return result;
        }

    }


    public static String sendGetByUrl(String url,int reSend) {
        String result = "";
        long startTime = System.currentTimeMillis();

        long endTime = 0L;
        HttpEntity httpEntity = null;
        HttpResponse httpResponse = null;
        HttpClient httpClient = null;
        try {
            HttpClientFactory instance = HttpClientFactory.getInstance();
            RequestBuilder requestBuilder = RequestBuilder.get(url);
            httpClient = instance.getHttpClient();
            HttpResponse execute = httpClient.execute(requestBuilder.build());

            httpEntity = execute.getEntity();
            result = EntityUtils.toString(httpEntity,HttpConstant.UTF8_ENCODE);
        } catch (Exception e) {
            if (reSend > 0) {
                result = sendGetByUrl(url,  reSend - 1);
                if (result != null && !"".equals(result)) {
                    return result;
                }
            }
        } finally {
            try {
                EntityUtils.consume(httpEntity);
            } catch (IOException e) {
            }
            endTime = System.currentTimeMillis();
            return result;
        }

    }
}
