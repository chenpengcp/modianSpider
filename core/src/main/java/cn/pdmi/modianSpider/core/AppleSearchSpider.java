package cn.pdmi.modianSpider.core;

import cn.pdmi.modianSpider.pojo.AppSearch;
import cn.pdmi.modianSpider.utils.*;
import org.apache.commons.dbutils.QueryRunner;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by chen_ on 2018/4/24.
 */
public class AppleSearchSpider implements Runnable {
    //解析网页
    public Document getDocument(String html) {
        Document document = Jsoup.parse(html);
        return document;
    }

    //封装数据模型
    public AppSearch getAppSearch(Document document, String keyWord, String date) {
        AppSearch appSearch = new AppSearch();
        appSearch.setName(keyWord);
        appSearch.setDate(date);
        //对于有搜索指数的
        if (document.select("a.hints") != null && !"".equals(document.select("a.hints").html())) {
            appSearch.setSearchIndex(document.select("a.hints").html().replace("&nbsp;<span class=\"glyphicon glyphicon-trend\"></span>", ""));
            appSearch.setSearchResult(document.select("a.search-no").html().replace("&nbsp;<span class=\"glyphicon glyphicon-trend\"></span>", ""));
        } else {
            appSearch.setSearchIndex("0");
            if (document.select("div.search-index-list table.table.table-border tbody tr td") != null && document.select("div.search-index-list table.table.table-border tbody tr td").size() > 3) {
                appSearch.setSearchResult(document.select("div.search-index-list table.table.table-border tbody tr td").get(2).html());
            } else {
                appSearch.setSearchResult("0");
            }
        }
        return appSearch;
    }

    public void insert(AppSearch appSearch) throws Exception {
        QueryRunner queryRunner = new QueryRunner(Modian_JDBCUtils.getDataSource());
        String sql = "INSERT INTO app_store_search1 (appName,searchIndex,searchResult,date) " +
                "VALUES (?,?,?,?)";
        int update = queryRunner.update(sql, appSearch.getName(), appSearch.getSearchIndex(), appSearch.getSearchResult(), appSearch.getDate());
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
        AppleSearchSpider appleSearchSpider = new AppleSearchSpider();
        try {
            appleSearchSpider.getData();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void getData() throws Exception {
        AppleSearchSpider appleSearchSpider = new AppleSearchSpider();
        ArrayList<String> dates = new ArrayList<>();
//        for (int i = 1; i < 5; i++) {
//            StringBuffer sb = new StringBuffer();
//            sb.append("2018-0").append(i);
//            for (int j = 1; j < 31; j++) {
//                StringBuffer sb2 = new StringBuffer();
//                sb2.append("-");
//                if (j < 10) {
//                    sb2.append("0").append(j);
//                } else {
//                    sb2.append(j);
//                }
//                dates.add(sb.toString() + sb2.toString());
//            }
//        }
        //dates.add("2018-01-31");
        //dates.add("2018-03-31");
        dates.add("2018-05-15");
//        for (int i = 5; i < 6; i++) {
//            StringBuffer sb = new StringBuffer();
//            sb.append("2018-0").append(i);
//            for (int j = 1; j < 26; j++) {
//                StringBuffer sb2 = new StringBuffer();
//                sb2.append("-");
//                if (j < 10) {
//                    sb2.append("0").append(j);
//                } else {
//                    sb2.append(j);
//                }
//                dates.add(sb.toString() + sb2.toString());
//            }
//        }
        List<String> keyWords = KeyWordUtils.getKeyWordsList("rank");
        for (int i = 0; i < keyWords.size(); i++) {
            for (String date : dates
                    ) {
                AppSearch appSearch = appleSearchSpider.getAppSearch(appleSearchSpider.getDocument(SpiderUtils.getAjax("https://old.qimai.cn/search/index/country/cn/search/" + appleSearchSpider.getEncode(keyWords.get(i)) + "/kdate/" + date)),
                        keyWords.get(i), date);
                appleSearchSpider.insert(appSearch);
            }
        }
    }
}
