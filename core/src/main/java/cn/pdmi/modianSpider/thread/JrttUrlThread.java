package cn.pdmi.modianSpider.thread;

import cn.pdmi.modianSpider.pojo.JrttUrl;
import cn.pdmi.modianSpider.utils.CPUtils;
import cn.pdmi.modianSpider.utils.SpiderUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.net.URLEncoder;

public class JrttUrlThread implements Runnable {
    private String media;
    private String mediaName;

    @Override
    public void run(){
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
        String html = SpiderUtils.getAjax("https://www.toutiao.com/search/?keyword=" + getEncode(mediaName));
        String href = getDocument(html).select("div#J_section_0 a").attr("href");
        jrttUrl.setUrl(href);
        System.out.println(jrttUrl);
        insert(jrttUrl);
    }

    public JrttUrlThread(String media, String mediaName) {
        this.media = media;
        this.mediaName = mediaName;
    }

    public String getEncode(String url) throws Exception {
        return URLEncoder.encode(url, "utf-8");
    }

    public void insert(JrttUrl jrttUrl) throws Exception {
        QueryRunner queryRunner = new QueryRunner(CPUtils.getDataSource());
        String sql = "INSERT INTO jrtturl (media,mediaName,url) " +
                "VALUES (?,?,?)";
        int update = queryRunner.update(sql, jrttUrl.getMedia(), jrttUrl.getMediaName(), jrttUrl.getUrl());
        if (update == 1) {
            System.out.println("success!");
        } else {
            System.out.println("插入失败！");
        }

    }

    //解析网页
    public Document getDocument(String html) {
        Document document = Jsoup.parse(html);
        return document;
    }
}
