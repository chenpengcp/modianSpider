package cn.pdmi.modianSpider.thread;

import cn.pdmi.modianSpider.pojo.TxxwData;
import cn.pdmi.modianSpider.pojo.TxxwUrl;
import cn.pdmi.modianSpider.utils.CPUtils;
import cn.pdmi.modianSpider.utils.KeyWordUtils;
import cn.pdmi.modianSpider.utils.SpiderUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.text.DecimalFormat;
import java.util.Map;

public class TxxwUrlThread {
    public static void main(String[] args) throws Exception {
        Map<Integer, Map<String, String>> keyWords = KeyWordUtils.getKeyWords("txxw");
        for (Integer i : keyWords.keySet()
                ) {
            Map<String, String> stringStringMap = keyWords.get(i);
            for (String a : stringStringMap.keySet()
                    ) {
                handle(find(a,stringStringMap.get(a)));
            }
        }
    }

    public static void handle(TxxwData data) throws Exception {
        DecimalFormat df = new DecimalFormat("#");
        TxxwData txxwData = new TxxwData();
        String url = data.getUrl();
        txxwData.setUrl(url);
        txxwData.setMedia(data.getMedia());
        txxwData.setMediaName(data.getMediaName());
        if (url.startsWith("https://")) {
            String html = SpiderUtils.getAjax(url);
            Document doc = getDocument(html);
            if (doc.select("div._2eWXlf6TAn3S38b7txol5L") != null && doc.select("div._2mSVm6aLvJrHxYH-0yle4c") != null && doc.select("span._1PFzNcKQYVUf1QxqvCFufI").size() == 4) {
                String name = doc.select("div._2mSVm6aLvJrHxYH-0yle4c").html();

                String dy = doc.select("span._1PFzNcKQYVUf1QxqvCFufI").get(2).html();
                if (dy.contains("万")) {
                    Double aa = 0d;
                    dy = dy.replace("万", "");
                    aa = Double.parseDouble(dy) * 10000;
                    dy = String.valueOf(aa).replace(".0", "");
                    txxwData.setDy(dy);
                } else {
                    txxwData.setDy(dy);
                }
                String article = doc.select("span._1PFzNcKQYVUf1QxqvCFufI").get(0).html();
                if (article.contains("万")) {
                    Double bb = 0d;
                    article = article.replace("万", "");
                    bb = Double.parseDouble(article) * 10000;
                    article = String.valueOf(bb).replace(".0", "");
                    txxwData.setArticle(article);
                } else {
                    txxwData.setArticle(article);
                }
            }
        } else {
            txxwData.setDy("0");
            txxwData.setArticle("0");
        }
        insert(txxwData);
    }

    //解析网页
    public static Document getDocument(String html) {
        Document document = Jsoup.parse(html);
        return document;
    }

    public static void insert(TxxwData txxwData) throws Exception {
        QueryRunner queryRunner = new QueryRunner(CPUtils.getDataSource());
        String sql = "INSERT INTO txxwdataplus (media,mediaName,dy,article,url) " +
                "VALUES (?,?,?,?,?)";
        int update = queryRunner.update(sql, txxwData.getMedia(), txxwData.getMediaName(), txxwData.getDy(), txxwData.getArticle(), txxwData.getUrl());
        if (update == 1) {
            System.out.println("success!");
        } else {
            System.out.println("插入失败！");
        }

    }

    public static TxxwData find(String media,String mediaName) throws Exception {
        QueryRunner queryRunner = new QueryRunner(CPUtils.getDataSource());
        String sql = "SELECT * FROM txxwdata where media=? and mediaName=?";
        return queryRunner.query(sql, new BeanHandler<TxxwData>(TxxwData.class), media,mediaName);
    }

}
