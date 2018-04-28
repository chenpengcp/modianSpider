package cn.pdmi.modianSpider.core;

import cn.pdmi.modianSpider.pojo.App;
import cn.pdmi.modianSpider.utils.JDBCUtils;
import cn.pdmi.modianSpider.utils.SpiderUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by chen_ on 2018/4/24.
 */
public class AppleSpider {

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
        List<App> apps = appleSpider.getApps(appleSpider.getDocument(SpiderUtils.getAjax("https://www.apple.com/cn/itunes/charts/paid-apps/")));
        appleSpider.insert(apps);
    }
}
