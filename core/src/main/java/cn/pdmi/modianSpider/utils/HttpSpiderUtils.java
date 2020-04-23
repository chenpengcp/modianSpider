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
            httpGet.setHeader("cookie", "SINAGLOBAL=119762719648.03331.1524020818190; sensorsdata2015jssdkcross=%7B%22distinct_id%22%3A%2216517494022ad-0946d3c67654ce-51422e1f-1327104-16517494025655%22%2C%22props%22%3A%7B%22%24latest_referrer%22%3A%22%22%2C%22%24latest_referrer_host%22%3A%22%22%7D%7D; _s_tentry=www.baidu.com; Apache=424813178262.20886.1560923177437; ULV=1560923177450:76:3:1:424813178262.20886.1560923177437:1560300468000; Ugrow-G0=d52660735d1ea4ed313e0beb68c05fc5; SCF=AnyrE_tHtXUR3VyI4zk6OhP11nBrGqVp5BG0hcvN-vZkLVVISyx9hQ9RUmFs2xlIurgbf1OoaupkMH-WBDACFo0.; SUB=_2A25wDb3jDeRhGeNN7VQX9yzKzj2IHXVTeqgrrDV8PUNbmtAKLXD2kW9NSYrt4FqCcRWXdq1N8XFchsQNJk5UdcnZ; SUBP=0033WrSXqPxfM725Ws9jqgMF55529P9D9WWCIkILnOWQgu5G_6mJU6Nd5JpX5KzhUgL.Fo-0SoqcS0zcSK22dJLoIXnLxK-L1K5LBo.LxK.LBKeL12-LxKMLBKeL1h2LxKqL1-2LBoBLxK.L1K.LBKnLxKML1-qLBoeLxKqL12-LBKnLxKqLBoeLBKzt; SUHB=0u5hKEwz3YSuyA; ALF=1592459571; SSOLoginState=1560923571; UOR=www.51testing.com,widget.weibo.com,mail.qq.com; TC-V5-G0=666db167df2946fecd1ccee47498a93b; wb_view_log_5366672651=1536*8641.25; YF-V5-G0=95d69db6bf5dfdb71f82a9b7f3eb261a; TC-Page-G0=c4376343b8c98031e29230e0923842a5|1560923972|1560923737; YF-Page-G0=02467fca7cf40a590c28b8459d93fb95|1560924069|1560923858; webim_unReadCount=%7B%22time%22%3A1560924137917%2C%22dm_pub_total%22%3A0%2C%22chat_group_pc%22%3A0%2C%22allcountNum%22%3A0%2C%22msgbox%22%3A0%7D");
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
