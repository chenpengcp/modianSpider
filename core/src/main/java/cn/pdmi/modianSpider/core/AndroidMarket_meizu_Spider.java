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
public class AndroidMarket_meizu_Spider implements Runnable {
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
        if (document.select("div.search_one.downloading") != null && document.select("div.search_one.downloading").size() > 0) {
            if (keyWord.toLowerCase().equals(document.select("div.search_one.downloading").get(0).select("div.one_right a.ellipsis").html().toLowerCase()) || document.select("div.search_one.downloading").get(0).select("div.one_right a.ellipsis").html().toLowerCase().contains(keyWord.toLowerCase()) ||
                    keyWord.toLowerCase().contains(document.select("div.search_one.downloading").get(0).select("div.one_right a.ellipsis").html().toLowerCase())) {
                String count = document.select("div.search_one.downloading").get(0).select("div.one_right span.download_num").html();

                if (count.endsWith("W+")) {
                    androidSearch.setDownloads(String.valueOf(df.format(Double.parseDouble(count.trim().substring(5, count.trim().indexOf("W"))) * 10000)));
                } else if (count.contains("KW+")) {
                    androidSearch.setDownloads(String.valueOf(df.format(Double.parseDouble(count.trim().substring(5, count.trim().indexOf("K"))) * 10000000)));
                } else if (count.contains("K+")) {
                    androidSearch.setDownloads(String.valueOf(df.format(Double.parseDouble(count.trim().substring(5, count.trim().indexOf("K"))) * 1000)));
                } else {
                    androidSearch.setDownloads(count.trim().substring(5));
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
        String sql = "INSERT INTO androidSearch_meizu (mediaName,appName,downloads,enter,insertDate) " +
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
        AndroidMarket_meizu_Spider androidMarket_meizu_Spider = new AndroidMarket_meizu_Spider();
        try {
            androidMarket_meizu_Spider.getData();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void getData() throws Exception {
        AndroidMarket_meizu_Spider androidMarket_meizu_Spider = new AndroidMarket_meizu_Spider();
        Map<Integer, Map<String, String>> map = KeyWordUtils.getKeyWords("androidExcel");
        for (int i = 0; i < map.keySet().size(); i++) {
            Map<String, String> keyWords = map.get(i);
            for (String key : keyWords.keySet()
                    ) {
                if (!"".equals(keyWords.get(key).trim())) {
                    AndroidSearch androidSearch = androidMarket_meizu_Spider.getAndroidSearch(androidMarket_meizu_Spider.getDocument(SpiderUtils.getAjax("http://app.meizu.com/apps/public/search?keyword=" + androidMarket_meizu_Spider.getEncode(keyWords.get(key)))),
                            keyWords.get(key));
                    androidSearch.setMediaName(key);
                    androidMarket_meizu_Spider.insert(androidSearch);
                } else {
                    AndroidSearch androidSearch = new AndroidSearch();
                    androidSearch.setMediaName(key);
                    androidSearch.setInsertDate(DateUtils.getDate());
                    androidMarket_meizu_Spider.insert(androidSearch);
                }
            }
        }
    }
}
