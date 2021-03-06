package cn.pdmi.modianSpider.core;

import cn.pdmi.modianSpider.pojo.AndroidSearch;
import cn.pdmi.modianSpider.utils.DateUtils;
import cn.pdmi.modianSpider.utils.JDBCUtils;
import cn.pdmi.modianSpider.utils.KeyWordUtils;
import cn.pdmi.modianSpider.utils.SpiderUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.net.URLEncoder;
import java.text.DecimalFormat;
import java.util.List;
import java.util.Map;

/**
 * Created by chen_ on 2018/4/24.
 */
public class AndroidMarket_qq_Spider implements Runnable {
    //解析网页
    public Document getDocument(String html) {
        Document document = Jsoup.parse(html);
        return document;
    }

    //封装数据模型
    public AndroidSearch getAndroidSearch(Document document, String keyWord) {
        DecimalFormat df = new DecimalFormat("#");
        AndroidSearch androidSearch = new AndroidSearch();
        androidSearch.setName(keyWord);
        androidSearch.setInsertDate(DateUtils.getDate());
        if (document.select("div.name-line") != null && document.select("div.name-line").size() > 0) {
            if (keyWord.toLowerCase().contains(document.select("div.name-line").get(0).select("div.name a.appName").html().toLowerCase()) || document.select("div.name-line").get(0).select("div.name a.appName").html().toLowerCase().contains(keyWord)) {
                String count = document.select("div.data-box").get(0).select("div.down-line").html();
                if (count.endsWith("万人下载")) {
                    androidSearch.setDownloads(String.valueOf(df.format(Double.parseDouble(count.substring(0, count.indexOf("万"))) * 10000)));
                } else if (count.contains("亿人下载")) {
                    androidSearch.setDownloads(String.valueOf(df.format(Double.parseDouble(count.substring(0, count.indexOf("亿"))) * 100000000)));
                } else {
                    androidSearch.setDownloads(count.substring(0, count.indexOf("人")));
                }
                androidSearch.setEnter(1);
            } else {
                androidSearch.setDownloads("0");
                androidSearch.setEnter(0);
            }
        } else {
            androidSearch.setDownloads("0");
            androidSearch.setEnter(0);
        }
        return androidSearch;
    }

    public void insert(AndroidSearch androidSearch) throws Exception {
        QueryRunner queryRunner = new QueryRunner(JDBCUtils.getDataSource());
        String sql = "INSERT INTO androidSearch_qq (mediaName,appName,downloads,enter,insertDate) " +
                "VALUES (?,?,?,?,?)";
        int update = queryRunner.update(sql, androidSearch.getMediaName(), androidSearch.getName(), androidSearch.getDownloads(), androidSearch.getEnter(), androidSearch.getInsertDate());
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
        AndroidMarket_qq_Spider androidMarket_qq_Spider = new AndroidMarket_qq_Spider();
        try {
            androidMarket_qq_Spider.getData();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void getData() throws Exception {
        AndroidMarket_qq_Spider androidMarket_qq_Spider = new AndroidMarket_qq_Spider();
        Map<Integer, Map<String, String>> map = KeyWordUtils.getKeyWords("androidExcel");
        for (int i = 0; i < map.keySet().size(); i++) {
            Map<String, String> keyWords = map.get(i);
            for (String key : keyWords.keySet()
                    ) {
                if (!"".equals(keyWords.get(key).trim())) {
                    AndroidSearch androidSearch = androidMarket_qq_Spider.getAndroidSearch(androidMarket_qq_Spider.getDocument(SpiderUtils.getAjax("http://sj.qq.com/myapp/search.htm?kw=" + androidMarket_qq_Spider.getEncode(keyWords.get(key)))),
                            keyWords.get(key));
                    androidSearch.setMediaName(key);
                    androidMarket_qq_Spider.insert(androidSearch);
                } else {
                    AndroidSearch androidSearch = new AndroidSearch();
                    androidSearch.setMediaName(key);
                    androidSearch.setInsertDate(DateUtils.getDate());
                    androidMarket_qq_Spider.insert(androidSearch);
                }
            }
        }
    }
}
