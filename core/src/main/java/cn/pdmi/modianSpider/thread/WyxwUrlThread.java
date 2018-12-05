package cn.pdmi.modianSpider.thread;

import cn.pdmi.modianSpider.pojo.JrttUrl;
import cn.pdmi.modianSpider.utils.CPUtils;
import cn.pdmi.modianSpider.utils.KeyWordUtils;
import cn.pdmi.modianSpider.utils.SpiderUtils;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.dbutils.QueryRunner;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class WyxwUrlThread implements Runnable {
    private String media;
    private String mediaName;

    @Override
    public void run() {
        try {
            handle();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void handle() throws Exception {
        JrttUrl jrttUrl = new JrttUrl();
        jrttUrl.setMedia(media);
        jrttUrl.setMediaName(mediaName);
        if (!"无".equals(mediaName)) {
            String html = SpiderUtils.getAjax("http://dy.163.com/v2/media/search?word=" + getEncode(mediaName) + "&cid=");
            //System.out.println(getDocument(html).select("ul.column_list.clearfix"));
            String attr = getDocument(html).select("ul.column_list.clearfix li.clearfix.js_item").get(0).select("a").attr("href");
            attr = "http://dy.163.com" + attr;
            jrttUrl.setUrl(attr);
        } else {
            jrttUrl.setUrl("11111");
        }
        System.out.println(jrttUrl);
        insert(jrttUrl);
    }

    public WyxwUrlThread(String media, String mediaName) {
        this.media = media;
        this.mediaName = mediaName;
    }

    public WyxwUrlThread() {
    }

    public String getEncode(String url) throws Exception {
        return URLEncoder.encode(url, "gbk");
    }

    public void insert(JrttUrl jrttUrl) throws Exception {
        QueryRunner queryRunner = new QueryRunner(CPUtils.getDataSource());
        String sql = "INSERT INTO wyxwurl (media,mediaName,url) " +
                "VALUES (?,?,?)";
        int update = queryRunner.update(sql, jrttUrl.getMedia(), jrttUrl.getMediaName(), jrttUrl.getUrl());
        if (update == 1) {
            System.out.println("success!");
        } else {
            System.out.println("插入失败！");
        }

    }

//    public List<JrttUrl> find() throws Exception {
//        QueryRunner queryRunner = new QueryRunner(CPUtils.getDataSource());
//        String sql = "SELECT * FROM jrtturl";
//        return queryRunner.query(sql, new BeanListHandler<JrttUrl>(JrttUrl.class));
//    }

    //解析网页
    public Document getDocument(String html) {
        Document document = Jsoup.parse(html);
        return document;
    }

    public static void main(String[] args) throws Exception {
        ExecutorService executorService = Executors.newFixedThreadPool(1);
        Map<Integer, Map<String, String>> keyWords = KeyWordUtils.getKeyWords("wyxw");
        for (Integer i : keyWords.keySet()
                ) {
            Map<String, String> stringStringMap = keyWords.get(i);
            for (String a : stringStringMap.keySet()
                    ) {
                executorService.submit(new WyxwUrlThread(a, stringStringMap.get(a)));
            }
        }
    }
//    public static void main(String[] args) throws Exception {
//        WyxwUrlThread wyxwUrlThread = new WyxwUrlThread();
//        System.out.println(URLDecoder.decode("%C8%CB%C3%F1%C8%D5%B1%A8","gbk"));
//        }
}
