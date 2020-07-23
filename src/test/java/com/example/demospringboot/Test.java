package com.example.demospringboot;

import com.example.demospringboot.restful.Signature;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicHeader;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;


public class Test {

    private static final String token = "test01";

    @org.junit.Test
    public void authTest() throws Exception {
        //创建一个httpclient对象
        CloseableHttpClient client = HttpClients.createDefault();

        //创建一个post对象
        HttpPost post = new HttpPost("http://localhost:8080/api/auth/test");

        //创建一个Entity，模拟表单数据
        List<NameValuePair> formList = new ArrayList<>();

        //添加表单数据
        long clientTime = Calendar.getInstance().getTimeInMillis();
        String info = "这是一个测试 restful api的 info";
        String reqStr = info + clientTime + token;
        formList.add(new BasicNameValuePair("authKey", "1000001"));
        formList.add(new BasicNameValuePair("info", info));
        String md5 = Signature.md5(reqStr);
        formList.add(new BasicNameValuePair("sign", md5));
        //包装成一个Entity对象
        StringEntity entity = new UrlEncodedFormEntity(formList, "utf-8");

        //设置请求的内容
        post.setEntity(entity);

        //设置请求的报文头部的编码
        post.setHeader(
                new BasicHeader("Content-Type", "application/x-www-form-urlencoded; charset=utf-8"));

        //设置期望服务端返回的编码
        post.setHeader(new BasicHeader("Accept", "text/plain;charset=utf-8"));

        //设置时间
        post.setHeader(new BasicHeader("ReqTime-Time", clientTime + ""));

        System.out.println("客户端未签名时为:" + reqStr);

        System.out.println("客户端签名之后为:" + md5);

        //执行post请求
        CloseableHttpResponse response = client.execute(post);

        //获取响应码
        int statusCode = response.getStatusLine().getStatusCode();

        if (statusCode == 200) {

            //获取数据
            String resStr = EntityUtils.toString(response.getEntity());

            //输出
            System.out.println("请求成功,请求返回内容为: " + resStr);
        } else {

            //输出
            System.out.println("请求失败,错误码为: " + statusCode);
        }

        //关闭response和client
        response.close();
        client.close();
    }
}
