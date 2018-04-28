package cn.pdmi.modianSpider.core;

import cn.pdmi.modianSpider.pojo.App;
import cn.pdmi.modianSpider.pojo.DataModel;
import cn.pdmi.modianSpider.utils.JDBCUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by chen_ on 2018/4/24.
 */
public class AppleSpider {
    private static final String BLANK = "  ";
    private static final String COMMAND = "D:/phantomjs-2.1.1-windows/bin/phantomjs.exe";
    private static final String JSCODE = "D:/phantomjs/apple.js";
    //private static final String PATH = "D:/modian.png";

    //抓取网页html
    public String getAjax(String url) throws IOException {
        Runtime runtime = Runtime.getRuntime();
        Process p = runtime.exec(COMMAND + BLANK + JSCODE + BLANK + url);
        InputStream is = p.getInputStream();
        BufferedReader br = new BufferedReader(new InputStreamReader(is));
        StringBuffer sbf = new StringBuffer();
        String temp = "";
        while ((temp = br.readLine()) != null) {
            sbf.append(temp);
        }
        //System.out.println("抓取成功！");
        return sbf.toString();
    }

    //解析网页
    public Document getDocument(String html) {
        Document document = Jsoup.parse(html);
        return document;
    }

    //封装数据模型
    public List<App> getApps(Document document) {
        ArrayList<App> apps = new ArrayList<>();
        for (int i = 0; i <100 ; i++) {
            App app = new App();
            app.setName(document.select("section.section.apps.grid ul li").get(i).select("a img").attr("alt"));
            apps.add(app);
        }

        return apps;
    }

    public void insert(List<App> list) throws Exception {
        QueryRunner queryRunner = new QueryRunner(JDBCUtils.getDataSource());
        String sql = "INSERT INTO paid_apps (name) " +
                "VALUES (?)";
        for (App app:list
             ) {
            int update = queryRunner.update(sql,app.getName());
            if (update == 1) {
                System.out.println("success!");
            } else {
                System.out.println("插入失败！");
            }
        }
    }

    public static void main(String[] args) throws Exception{
        AppleSpider appleSpider = new AppleSpider();
        //List<App> apps = appleSpider.getApps(appleSpider.getDocument(appleSpider.getAjax("https://www.apple.com/cn/itunes/charts/free-apps/")));
        List<App> apps = appleSpider.getApps(appleSpider.getDocument(appleSpider.getAjax("https://www.apple.com/cn/itunes/charts/paid-apps/")));
        appleSpider.insert(apps);
    }
}
