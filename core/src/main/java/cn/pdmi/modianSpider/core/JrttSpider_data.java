package cn.pdmi.modianSpider.core;

import cn.pdmi.modianSpider.pojo.App;
import cn.pdmi.modianSpider.pojo.JrttDataModel;
import cn.pdmi.modianSpider.pojo.JrttModel;
import cn.pdmi.modianSpider.utils.*;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.dbutils.QueryRunner;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class JrttSpider_data implements Runnable {
    private String name;
    private String uid;

    public JrttSpider_data(String name, String uid) {
        this.name = name;
        this.uid = uid;
    }

    public JrttSpider_data() {
    }

    public Document getDocument(String html) {
        Document document = Jsoup.parse(html);
        return document;
    }

    public String getEncode(String url) throws Exception {
        return URLEncoder.encode(url, "utf-8");
    }

    public void getUids() throws Exception {
        List<String> keyWords = KeyWordUtils.getKeyWords("mediaExcel");
        for (String keyword : keyWords
                ) {
            String html = JrttSpiderUtils.getAjax("https://www.toutiao.com/search/?keyword=" + this.getEncode(keyword));
            Document document = this.getDocument(html);
            if (document.select("div.feedBox div div.sections div.userCard.aladdin a.y-box.link") != null) {
                String href = document.select("div.feedBox div div.sections div.userCard.aladdin a.y-box.link").attr("href");
                if (!"".equals(href.trim())) {
                    String uid = href.substring(href.indexOf("r") + 2, href.length() - 1);
                    System.out.println(keyword + "===>" + uid);
                    Thread thread = new Thread(new JrttSpider_data(keyword, uid));
                    thread.start();
                } else {
                    System.out.println(keyword + "===>" + "0");
                    Thread thread = new Thread(new JrttSpider_data(keyword, "0"));
                    thread.start();
                }
            } else {
                System.out.println(keyword + "===>" + "0");
                Thread thread = new Thread(new JrttSpider_data(keyword, "0"));
                thread.start();
            }
        }
    }

    public void getData(String name, String uid) throws Exception {
        JrttDataModel jrttDataModel = new JrttDataModel();
        jrttDataModel.setName(name);
        if ("0".equals(uid)) {
            jrttDataModel.setUid(uid);
            jrttDataModel.setFans("0");
            jrttDataModel.setFollow("0");
            jrttDataModel.setEnter("0");
        } else {
            jrttDataModel.setUid(uid);
            jrttDataModel.setEnter("1");
            String html = JrttSpiderUtils.getAjax("https://www.toutiao.com/c/user/" + uid + "/#mid=" + uid);
            Document document = this.getDocument(html);
            jrttDataModel.setFollow(document.select("div.right div dl.statistics dt a h3 em i").html());
            jrttDataModel.setFans(document.select("div.right div dl.statistics dd a h3 em").html().contains("万") ? String.valueOf(Double.parseDouble(document.select("div.right div dl.statistics dd a h3 em i").html()) * 10000)
                    .substring(0,String.valueOf(Double.parseDouble(document.select("div.right div dl.statistics dd a h3 em i").html()) * 10000).lastIndexOf(".")) : document.select("div.right div dl.statistics dd a h3 em i").html());
        }
        this.insert(jrttDataModel);
    }

    @Override
    public void run() {
        try {
            this.getData(name, uid);
        } catch (Exception e) {
            System.out.println("插入失败！");
        }

    }

    public void insert(JrttDataModel jrttDataModel) throws Exception {
        QueryRunner queryRunner = new QueryRunner(JDBCUtils.getDataSource());
        String sql = "INSERT INTO jrttData (name,uid,follow,fans,enter) " +
                "VALUES (?,?,?,?,?)";
        int update = queryRunner.update(sql, jrttDataModel.getName(), jrttDataModel.getUid(), jrttDataModel.getFollow(), jrttDataModel.getFans(), jrttDataModel.getEnter());
        if (update == 1) {
            System.out.println("success!");
        } else {
            System.out.println("插入失败！");
        }
    }

    public static void main(String[] args) throws Exception {
        JrttSpider_data jrttSpider_data = new JrttSpider_data();
        jrttSpider_data.getUids();
    }
}
