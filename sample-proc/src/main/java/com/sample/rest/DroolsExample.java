package com.sample.rest;

import javax.enterprise.context.ApplicationScoped;

import org.apache.http.HttpEntity;
import org.apache.http.HttpHeaders;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;

import com.sample.model.Message;

@ApplicationScoped
public class DroolsExample {

    public Message runRules(Message message) throws Exception {
        // リクエストのURLを指定
        String url = "http://localhost:8088/dm_proc";

        // HTTP POSTリクエストの作成
        HttpPost httpPost = new HttpPost(url);

        // HTTPヘッダーの設定
        httpPost.setHeader(HttpHeaders.CONTENT_TYPE, ContentType.APPLICATION_JSON.getMimeType());
        httpPost.setHeader(HttpHeaders.ACCEPT, ContentType.APPLICATION_JSON.getMimeType());

        // リクエストボディの作成
        JSONObject requestBody = new JSONObject();
        JSONObject messageJson = new JSONObject();
        messageJson.put("status", message.getStatus());
        messageJson.put("message", message.getMessage());

        requestBody.put("message", messageJson);

        // リクエストボディの設定
        StringEntity requestEntity = new StringEntity(requestBody.toString(), ContentType.APPLICATION_JSON);
        httpPost.setEntity(requestEntity);

        // HttpClientの作成
        HttpClient httpClient = HttpClientBuilder.create().build();

        // HTTP POSTリクエストの送信
        HttpResponse httpResponse = httpClient.execute(httpPost);

        // HTTPレスポンスの取得
        HttpEntity httpEntity = httpResponse.getEntity();
        String responseString = EntityUtils.toString(httpEntity, "UTF-8");
        JSONObject responseJson = new JSONObject(responseString);

        JSONObject messageObject = responseJson.getJSONObject("message");

        // 結果の作成
        Message resultMessage = new Message();
        resultMessage.setStatus(messageObject.getInt("status"));
        resultMessage.setMessage(messageObject.getString("message"));

        return resultMessage;
    }

    public static void main(String[] args) {
        com.sample.rest.DroolsExample de = new com.sample.rest.DroolsExample();

        com.sample.model.Message msg = new com.sample.model.Message();
        msg.setStatus(0);
        msg.setMessage("Hello World!");

        com.sample.model.Message result = null;
        try {
            result = de.runRules(msg);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        System.out.println("結果：" + result);
    }
}