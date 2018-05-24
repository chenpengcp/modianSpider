package cn.pdmi.modianSpider.core;

import cn.pdmi.modianSpider.pojo.AndroidSearch;
import cn.pdmi.modianSpider.utils.*;
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
public class AndroidMarket_coolpad_Spider implements Runnable {
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
        if (document.select("div.list-content.line-bottom") != null && document.select("div.list-content.line-bottom").size() > 0) {
            if (keyWord.toLowerCase().equals(document.select("div.list-content.line-bottom").get(0).select("div.content h1").html().toLowerCase()) || document.select("div.list-content.line-bottom").get(0).select("div.content h1").html().toLowerCase().contains(keyWord.toLowerCase()) ||
                    keyWord.toLowerCase().contains(document.select("div.list-content.line-bottom").get(0).select("div.content h1").html().toLowerCase())) {
                String count = document.select("div.list-content.line-bottom").get(0).select("div.content span").html();

                if (count.contains("万")) {
                    androidSearch.setDownloads(String.valueOf(df.format(Double.parseDouble(count.substring(0, count.indexOf("万"))) * 10000)));
                } else {
                    androidSearch.setDownloads(count.substring(0, count.indexOf("+")));
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
        String sql = "INSERT INTO androidSearch_coolpad (mediaName,appName,downloads,enter,insertDate) " +
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
        AndroidMarket_coolpad_Spider androidMarket_coolpad_Spider = new AndroidMarket_coolpad_Spider();
        try {
            androidMarket_coolpad_Spider.getData();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void getData() throws Exception {
        AndroidMarket_coolpad_Spider androidMarket_coolpad_Spider = new AndroidMarket_coolpad_Spider();
        Map<Integer, Map<String, String>> map = KeyWordUtils.getKeyWords("androidExcel");
        for (int i = 0; i < map.keySet().size(); i++) {
            Map<String, String> keyWords = map.get(i);
            for (String key : keyWords.keySet()
                    ) {
                if (!"".equals(keyWords.get(key).trim())) {
                    AndroidSearch androidSearch = androidMarket_coolpad_Spider.getAndroidSearch(androidMarket_coolpad_Spider.getDocument(CoolMartSpiderUtils.getAjax("http://www.coolmart.net.cn/components/search.html", keyWords.get(key))),
                            keyWords.get(key));
                    androidSearch.setMediaName(key);
                    androidMarket_coolpad_Spider.insert(androidSearch);
                } else {
                    AndroidSearch androidSearch = new AndroidSearch();
                    androidSearch.setMediaName(key);
                    androidSearch.setInsertDate(DateUtils.getDate());
                    androidMarket_coolpad_Spider.insert(androidSearch);
                }
            }
        }
    }
}
