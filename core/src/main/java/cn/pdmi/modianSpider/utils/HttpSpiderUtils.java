package cn.pdmi.modianSpider.utils;

import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class HttpSpiderUtils {

    public static String getAjax(String url) {
        String str = "";
        try {
            //创建httpclient实例
            CloseableHttpClient httpClient = HttpClients.createDefault();
            //创建httpget实例
            HttpGet httpGet = new HttpGet(url);  //系統有限制
            httpGet.setHeader("User-Agent", "Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/66.0.3359.170 Safari/537.36");
            httpGet.setHeader("content-type", "application/x-www-form-urlencoded");
            httpGet.setHeader("accept", "application/json, text/javascript");
            httpGet.setHeader("accept-language", "zh-CN,zh;q=0.9");
            httpGet.setHeader("cookie", "tt_webid=6553835321145755149; WEATHER_CITY=%E5%8C%97%E4%BA%AC; UM_distinctid=16348bc73844a7-0260ca6712e3e-3961430f-144000-16348bc7385993; tt_webid=6553835321145755149; uuid=\"w:c608865c7a15428caf330ee87a2c9b39\"; tt_track_id=dff11207fa65269a30ab4748930e5de6; __tasessionId=2iiuopf0l1526279717184; CNZZDATA1259612802=665972036-1525930563-https%253A%252F%252Fwww.baidu.com%252F%7C1526279443");
            RequestConfig config = RequestConfig.custom()
                    .setConnectTimeout(10000) //设置accept-language超时时间为10s
                    .build();
            httpGet.setConfig(config);

            //执行http get 请求
            CloseableHttpResponse response = null;
            response = httpClient.execute(httpGet);
            HttpEntity entity = response.getEntity();//获取返回实体
            //EntityUtils.toString(entity,"utf-8");//获取网页内容，指定编码
            str = EntityUtils.toString(entity, "utf-8");
            response.close();
            httpClient.close();
            return str.substring(str.indexOf("{"), str.lastIndexOf("}") + 1);
        } catch (Exception e) {
            //System.out.println("超时，重新连接！");
            return str;
        }
    }

    public static String getHtml(String url) throws ClientProtocolException, IOException {
        //创建httpclient实例
        CloseableHttpClient httpClient = HttpClients.createDefault();
        //创建httpget实例
        HttpGet httpGet = new HttpGet(url);  //系統有限制
        httpGet.setHeader("User-Agent", "Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/66.0.3359.170 Safari/537.36");
        httpGet.setHeader("content-type", "application/x-www-form-urlencoded");
        httpGet.setHeader("accept", "application/json, text/javascript");
        httpGet.setHeader("accept-language", "zh-CN,zh;q=0.9");
        httpGet.setHeader("cookie", "tt_webid=6553835321145755149; WEATHER_CITY=%E5%8C%97%E4%BA%AC; UM_distinctid=16348bc73844a7-0260ca6712e3e-3961430f-144000-16348bc7385993; tt_webid=6553835321145755149; uuid=\"w:c608865c7a15428caf330ee87a2c9b39\"; tt_track_id=dff11207fa65269a30ab4748930e5de6; __tasessionId=2iiuopf0l1526279717184; CNZZDATA1259612802=665972036-1525930563-https%253A%252F%252Fwww.baidu.com%252F%7C1526279443");
        RequestConfig config = RequestConfig.custom()
                .setConnectTimeout(10000).setSocketTimeout(10000) //设置accept-language超时时间为10s
                .build();
        httpGet.setConfig(config);

        //执行http get 请求
        CloseableHttpResponse response = null;
        response = httpClient.execute(httpGet);
        HttpEntity entity = response.getEntity();//获取返回实体
        //EntityUtils.toString(entity,"utf-8");//获取网页内容，指定编码
        String str = EntityUtils.toString(entity, "utf-8");
        response.close();
        httpClient.close();
        return str;
    }

    public static String getUid(String url) {
        HttpSpiderUtils httpSpiderUtils = new HttpSpiderUtils();
        String str = "";
        try {
            //创建httpclient实例
            CloseableHttpClient httpClient = HttpClients.createDefault();
            //创建httpget实例
            HttpGet httpGet = new HttpGet(url);  //系統有限制
            httpGet.setHeader("User-Agent", "Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/66.0.3359.170 Safari/537.36");
            httpGet.setHeader("content-type", "application/x-www-form-urlencoded");
            httpGet.setHeader("accept", "application/json, text/javascript");
            httpGet.setHeader("accept-language", "zh-CN,zh;q=0.9");
            RequestConfig config = RequestConfig.custom()
                    .setConnectTimeout(3000).setProxy(httpSpiderUtils.getHttpHost()) //设置accept-language超时时间为10s
                    .build();
            httpGet.setConfig(config);
            //执行http get 请求
            CloseableHttpResponse response = null;
            response = httpClient.execute(httpGet);
            HttpEntity entity = response.getEntity();//获取返回实体
            //EntityUtils.toString(entity,"utf-8");//获取网页内容，指定编码
            str = EntityUtils.toString(entity, "utf-8");
            response.close();
            httpClient.close();
        } catch (Exception e) {
            httpSpiderUtils.getUid(url);
        }
        return str;
    }

    public HttpHost getHttpHost() {
        HttpHost proxy1 = new HttpHost("111.192.36.119", 20439);
        HttpHost proxy2 = new HttpHost("222.131.156.191", 37834);
        HttpHost proxy3 = new HttpHost("221.219.51.38", 37720);
        HttpHost proxy4 = new HttpHost("61.51.83.250", 36567);
        HttpHost proxy5 = new HttpHost("221.223.11.92", 30657);
        HttpHost proxy6 = new HttpHost("123.112.207.150", 35516);
        HttpHost proxy7 = new HttpHost("221.219.52.72", 24686);
        HttpHost proxy8 = new HttpHost("114.253.35.32", 37661);
        HttpHost proxy9 = new HttpHost("114.252.35.117", 37929);
        HttpHost proxy10 = new HttpHost("123.118.199.72", 37756);
        List<HttpHost> httpHosts = new ArrayList<>();
        httpHosts.add(proxy1);
        httpHosts.add(proxy2);
        httpHosts.add(proxy3);
        httpHosts.add(proxy4);
        httpHosts.add(proxy5);
        httpHosts.add(proxy6);
        httpHosts.add(proxy7);
        httpHosts.add(proxy8);
        httpHosts.add(proxy9);
        httpHosts.add(proxy10);
        Random random = new Random();
        int i = random.nextInt(10);
        System.out.println(i);
        return httpHosts.get(i);
    }
}
