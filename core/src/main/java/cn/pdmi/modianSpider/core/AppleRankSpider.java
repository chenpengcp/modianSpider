package cn.pdmi.modianSpider.core;

import cn.pdmi.modianSpider.pojo.AppRank;
import cn.pdmi.modianSpider.utils.KeyWordUtils;
import cn.pdmi.modianSpider.utils.Modian_JDBCUtils;
import cn.pdmi.modianSpider.utils.SpiderUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by chen_ on 2018/4/24.
 */
public class AppleRankSpider implements Runnable {
    //解析网页
    public Document getDocument(String html) {
        Document document = Jsoup.parse(html);
        return document;
    }

    //封装数据模型
    public AppRank getAppRank(Document document, String keyWord, String date) {
        AppRank appRank = new AppRank();
        appRank.setName(keyWord);
        appRank.setDate(date);
        //对于有搜索指数的
        if (document.select("div.app-list div.media.keyword-histroy") != null && document.select("div.app-list div.media.keyword-histroy").size() > 0) {
            if (document.select("div.app-list div.media.keyword-histroy").get(0).select("div.media-body h4 a").html().contains(keyWord)) {
                appRank.setTotalRank(document.select("div.app-list div.media.keyword-histroy").get(0).select("div.media-right.mobile-hide table.class-rank tr").get(1).select("td").get(0).html().equals("-") ?
                        "0" : document.select("div.app-list div.media.keyword-histroy").get(0).select("div.media-right.mobile-hide table.class-rank tr").get(1).select("td").get(0).html());
                appRank.setListRank(document.select("div.app-list div.media.keyword-histroy").get(0).select("div.media-right.mobile-hide table.class-rank tr").get(1).select("td").get(1).html().equals("-") ?
                        "0" : document.select("div.app-list div.media.keyword-histroy").get(0).select("div.media-right.mobile-hide table.class-rank tr").get(1).select("td").get(1).html());
            } else {
                appRank.setTotalRank("0");
                appRank.setListRank("0");
            }

        } else {
            appRank.setTotalRank("0");
            appRank.setListRank("0");
        }
        return appRank;
    }

    public void insert(AppRank appRank) throws Exception {
        QueryRunner queryRunner = new QueryRunner(Modian_JDBCUtils.getDataSource());
        String sql = "INSERT INTO app_rank1 (appName,totalRank,listRank,date) " +
                "VALUES (?,?,?,?)";
        int update = queryRunner.update(sql, appRank.getName(), appRank.getTotalRank(), appRank.getListRank(), appRank.getDate());
        if (update == 1) {
            System.out.println("success!");
        } else {
            System.out.println("插入失败！");
        }

    }

    public String getEncode(String url) throws Exception {
        return URLEncoder.encode(url, "utf-8");
    }

    @Override
    public void run() {
        AppleRankSpider appleRankSpider = new AppleRankSpider();
        try {
            appleRankSpider.getData();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void getData() throws Exception {
        AppleRankSpider appleRankSpider = new AppleRankSpider();
        ArrayList<String> dates = new ArrayList<>();
        dates.add("2018-05-15");
        List<String> keyWords = KeyWordUtils.getKeyWordsList("rank");
        for (int i = 0; i < keyWords.size(); i++) {
            for (String date : dates
                    ) {
                AppRank appRank = appleRankSpider.getAppRank(appleRankSpider.getDocument(SpiderUtils.getAjax("https://old.qimai.cn/search/index/country/cn/search/" + appleRankSpider.getEncode(keyWords.get(i)) + "/kdate/" + date)),
                        keyWords.get(i), date);
                appleRankSpider.insert(appRank);
            }
        }
    }
}
