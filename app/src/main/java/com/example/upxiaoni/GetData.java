package com.example.upxiaoni;

import org.apache.hc.client5.http.classic.methods.HttpGet;
import org.apache.hc.client5.http.classic.methods.HttpPost;
import org.apache.hc.client5.http.entity.UrlEncodedFormEntity;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.CloseableHttpResponse;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.apache.hc.core5.http.HttpEntity;
import org.apache.hc.core5.http.NameValuePair;
import org.apache.hc.core5.http.io.entity.EntityUtils;
import org.apache.hc.core5.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONObject;

import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

public class GetData {
    private static String access_token=null;
    public static String access_token()throws Exception{
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpPost httpPost=new HttpPost("https://www.bigiot.net/oauth/token");
        List<NameValuePair> params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair("Content-Type", "application/x-www-form-urlencoded"));
        params.add(new BasicNameValuePair("client_id","835"));
        params.add(new BasicNameValuePair("client_secret","b65a2cd5b5"));
        params.add(new BasicNameValuePair("username","12121"));
        params.add(new BasicNameValuePair("password","049c778498"));
        params.add(new BasicNameValuePair("grant_type","password"));
        UrlEncodedFormEntity entity = new UrlEncodedFormEntity(params, Charset.forName("utf8"));
        httpPost.setEntity(entity);
        CloseableHttpResponse httpResponse = httpClient.execute(httpPost);
        HttpEntity entity2=null;
        if (httpResponse.getCode()==200) {
            entity2=httpResponse.getEntity();
        }
        access_token=EntityUtils.toString(entity2,"utf-8");
        EntityUtils.consume(entity2);
        JSONObject jsonObject=new JSONObject(access_token);
        access_token=jsonObject.getString("access_token");
        return  access_token;
    }
    public static List<Score> api_value()throws Exception{
        CloseableHttpClient httpClient = HttpClients.createDefault();
        List<NameValuePair> params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair("access_token", access_token));
        params.add(new BasicNameValuePair("id", "15391"));
        String entity=EntityUtils.toString(new UrlEncodedFormEntity(params,Charset.forName("utf8")));
        HttpGet httpGet = new HttpGet("https://www.bigiot.net/oauth/historydata"+"?"+entity);
        HttpEntity entity2=null;
        CloseableHttpResponse httpResponse = httpClient.execute(httpGet);
        if (httpResponse.getCode()==200){
            entity2=httpResponse.getEntity();
        }
        JSONArray jsonArray=new JSONArray(EntityUtils.toString(entity2,"utf-8"));
        EntityUtils.consume(entity2);
        params.set(1,new BasicNameValuePair("id","15422"));
        entity=EntityUtils.toString(new UrlEncodedFormEntity(params,Charset.forName("utf8")));
        httpGet = new HttpGet("https://www.bigiot.net/oauth/historydata"+"?"+entity);
        HttpEntity entity3=null;
        httpResponse = httpClient.execute(httpGet);
        if (httpResponse.getCode()==200){
            entity3=httpResponse.getEntity();
        }
        JSONArray jsonArray1=new JSONArray(EntityUtils.toString(entity3,"utf-8"));
        EntityUtils.consume(entity3);
        List<Score> scores=new ArrayList<Score>();
        for (int i=0;i<jsonArray.length();i++){
            JSONObject s=(JSONObject) jsonArray.get(i);
            JSONObject ms=(JSONObject) jsonArray1.get(i);
            Score score=null;
            if (Double.parseDouble(ms.getString("value"))<10){
                score=new Score(s.getString("value")+".00"+ms.getString("value"),s.getString("time"));
            }
            else if(Double.parseDouble(ms.getString("value"))<100){
                score=new Score(s.getString("value")+".0"+ms.getString("value"),s.getString("time"));
            }
            else{
                score=new Score(s.getString("value")+"."+ms.getString("value"),s.getString("time"));
            }
            scores.add(score);
        }
        return scores;
    }
}
