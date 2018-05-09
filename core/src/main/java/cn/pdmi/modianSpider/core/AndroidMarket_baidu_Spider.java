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

/**
 * Created by chen_ on 2018/4/24.
 */
public class AndroidMarket_baidu_Spider {
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
        if (document.select("div.app") != null && document.select("div.app").size() > 0) {
            if (keyWord.toLowerCase().equals(document.select("div.app").get(0).select("div.info div.top a.app-name").html().toLowerCase()) || document.select("div.app").get(0).select("div.info div.top a.app-name").html().toLowerCase().contains(keyWord.toLowerCase()) ||
                    keyWord.toLowerCase().contains(document.select("div.app").get(0).select("div.info div.top a.app-name").html().toLowerCase())) {
                String count = document.select("div.app").get(0).select("div.info div.middle").get(1).select("em span.download-num").html();
                if (count.endsWith("万")) {
                    androidSearch.setDownloads(String.valueOf(df.format(Double.parseDouble(count.substring(0, count.indexOf("万"))) * 10000)));
                } else if (count.contains("亿")) {
                    androidSearch.setDownloads(String.valueOf(df.format(Double.parseDouble(count.substring(0, count.indexOf("亿"))) * 100000000)));
                } else {
                    androidSearch.setDownloads(count);
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
        String sql = "INSERT INTO androidSearch_baidu (appName,downloads,enter,insertDate) " +
                "VALUES (?,?,?,?)";
        int update = queryRunner.update(sql, androidSearch.getName(), androidSearch.getDownloads(), androidSearch.getEnter(), androidSearch.getInsertDate());
        if (update == 1) {
            System.out.println("success!");
        } else {
            System.out.println("插入失败！");
        }

    }

    public String getEncode(String url) throws Exception {
        return URLEncoder.encode(url, "utf-8");
    }

    public static void main(String[] args) throws Exception {
        AndroidMarket_baidu_Spider androidMarket_baidu_Spider = new AndroidMarket_baidu_Spider();
        List<String> keyWords = KeyWordUtils.getKeyWords("androidExcel");
        for (int i = 0; i < keyWords.size(); i++) {
            AndroidSearch androidSearch = androidMarket_baidu_Spider.getAndroidSearch(androidMarket_baidu_Spider.getDocument(SpiderUtils.getAjax("http://shouji.baidu.com/s?wd=" + androidMarket_baidu_Spider.getEncode(keyWords.get(i)) + "&data_type=app&f=header_all%40input")),
                    keyWords.get(i));
            androidMarket_baidu_Spider.insert(androidSearch);
            //System.out.println(androidSearch);
        }

    }
}
