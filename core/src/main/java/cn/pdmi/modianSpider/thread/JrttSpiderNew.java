package cn.pdmi.modianSpider.thread;

import cn.pdmi.modianSpider.pojo.JrttUrl;
import cn.pdmi.modianSpider.utils.JDBCUtils;
import cn.pdmi.modianSpider.utils.KeyWordUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class JrttSpiderNew {
    public void getJrttUrlList() throws Exception {
        ExecutorService executorService = Executors.newFixedThreadPool(1);
        List<JrttUrl> list = new ArrayList<>();
        Map<Integer, Map<String, String>> keyWords = KeyWordUtils.getKeyWords("jrtt");
        for (Integer i : keyWords.keySet()
                ) {
            Map<String, String> stringStringMap = keyWords.get(i);
            for (String a : stringStringMap.keySet()
                    ) {
                executorService.submit(new JrttUrlThread(a, stringStringMap.get(a)));
            }
        }
    }

    public String getEncode(String url) throws Exception {
        return URLEncoder.encode(url, "utf-8");
    }

    //解析网页
    public Document getDocument(String html) {
        Document document = Jsoup.parse(html);
        return document;
    }

    public void insert(JrttUrl jrttUrl) throws Exception {
        QueryRunner queryRunner = new QueryRunner(JDBCUtils.getDataSource());
        String sql = "INSERT INTO jrttUrl (media,mediaName,url) " +
                "VALUES (?,?,?)";
        int update = queryRunner.update(sql, jrttUrl.getMedia(), jrttUrl.getMediaName(), jrttUrl.getUrl());
        if (update == 1) {
            System.out.println("success!");
        } else {
            System.out.println("插入失败！");
        }

    }

    public static void main(String[] args) throws Exception {
        JrttSpiderNew jrttSpiderNew = new JrttSpiderNew();
        jrttSpiderNew.getJrttUrlList();;
    }
}