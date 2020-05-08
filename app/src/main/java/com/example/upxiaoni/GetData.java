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
import org.w3c.dom.Entity;

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
        params.add(new BasicNameValuePair("id", "15391"));//获取圈数,jsonarray为圈数
        String entity=EntityUtils.toString(new UrlEncodedFormEntity(params,Charset.forName("utf8")));
        HttpGet httpGet = new HttpGet("https://www.bigiot.net/oauth/historydata"+"?"+entity);
        HttpEntity entity2=null;
        CloseableHttpResponse httpResponse = httpClient.execute(httpGet);
        if (httpResponse.getCode()==200){
            entity2=httpResponse.getEntity();
        }
        JSONArray jsonArray=new JSONArray(EntityUtils.toString(entity2,"utf-8"));
        EntityUtils.consume(entity2);

        params.set(1,new BasicNameValuePair("id","15422"));//获取选手号码,jsonarray1为号码
        entity=EntityUtils.toString(new UrlEncodedFormEntity(params,Charset.forName("utf8")));
        httpGet = new HttpGet("https://www.bigiot.net/oauth/historydata"+"?"+entity);
        HttpEntity entity3=null;
        httpResponse = httpClient.execute(httpGet);
        if (httpResponse.getCode()==200){
            entity3=httpResponse.getEntity();
        }
        JSONArray jsonArray1=new JSONArray(EntityUtils.toString(entity3,"utf-8"));
        EntityUtils.consume(entity3);

        params.set(1,new BasicNameValuePair("id","15423"));//获取模式
        entity=EntityUtils.toString(new UrlEncodedFormEntity(params,Charset.forName("utf8")));
        httpGet = new HttpGet("https://www.bigiot.net/oauth/historydata"+"?"+entity);
        HttpEntity entityMode=null;
        httpResponse = httpClient.execute(httpGet);
        if (httpResponse.getCode()==200){
            entityMode=httpResponse.getEntity();
        }
        JSONArray jsonArrayMode=new JSONArray(EntityUtils.toString(entityMode,"utf-8"));
        EntityUtils.consume(entityMode);

        params.set(1,new BasicNameValuePair("id","15424"));//获取成绩
        entity=EntityUtils.toString(new UrlEncodedFormEntity(params,Charset.forName("utf8")));
        httpGet=new HttpGet("https://www.bigiot.net/oauth/historydata"+"?"+entity);
        HttpEntity entityScore=null;
        httpResponse=httpClient.execute(httpGet);
        if (httpResponse.getCode()==200){
            entityScore=httpResponse.getEntity();
        }
        JSONArray jsonArrayScore=new JSONArray(EntityUtils.toString(entityScore,"utf-8"));
        EntityUtils.consume(entityScore);

        List<Score> scores=new ArrayList<Score>();
        int roundCount=0;//若为模式2速滑模式则添加记录后roundCount++
        for (int i=0;i<jsonArrayScore.length();i++){
            JSONObject s=(JSONObject) jsonArrayScore.get(i);
            JSONObject num=(JSONObject) jsonArray1.get(i);
            JSONObject mode=(JSONObject) jsonArrayMode.get(i);
            Score score=null;
            if (mode.getString("value").equals("2")) {
                JSONObject round = (JSONObject) jsonArray.get(roundCount);
                roundCount++;
                score = new Score(s.getString("value"), s.getString("time"),round.getString("value"),num.getString("value"),mode.getString("value"));
            }
            else{//若非速滑模式则round为0
                score = new Score(s.getString("value"), s.getString("time"),"0",num.getString("value"),mode.getString("value"));
            }
            scores.add(score);
        }
        return scores;
    }
}
