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
public class AndroidMarket_sogou_Spider implements Runnable {
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
        if (document.select("ul.list.clearfix") != null && document.select("ul.list.clearfix li").size() > 0) {
            if (keyWord.toLowerCase().equals(document.select("ul.list.clearfix li").get(0).select("a").get(0).attr("title").toLowerCase()) || document.select("ul.list.clearfix li").get(0).select("a").get(0).attr("title").toLowerCase().contains(keyWord.toLowerCase()) ||
                    keyWord.toLowerCase().contains(document.select("ul.list.clearfix li").get(0).select("a").get(0).attr("title").toLowerCase())) {
                String count = document.select("ul.list.clearfix li").get(0).select("p.count").html().substring(0, document.select("ul.list.clearfix li").get(0).select("p.count").html().indexOf("<"));
                if (count.endsWith("万")) {
                    androidSearch.setDownloads(String.valueOf(df.format(Double.parseDouble(count.substring(0, count.indexOf("万"))) * 10000)));
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
        String sql = "INSERT INTO androidSearch_sougou (appName,downloads,enter,insertDate) " +
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

    @Override
    public void run() {
        AndroidMarket_sogou_Spider androidMarket_sogou_Spider = new AndroidMarket_sogou_Spider();
        try {
            androidMarket_sogou_Spider.getData();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void getData() throws Exception {
        AndroidMarket_sogou_Spider androidMarket_sougo_Spider = new AndroidMarket_sogou_Spider();
        List<String> keyWords = KeyWordUtils.getKeyWords("androidExcel");
        for (int i = 0; i < keyWords.size(); i++) {
            AndroidSearch androidSearch = androidMarket_sougo_Spider.getAndroidSearch(androidMarket_sougo_Spider.getDocument(SpiderUtils.getAjax("http://zhushou.sogou.com/apps/search.html?from=index&key=" + androidMarket_sougo_Spider.getEncode(keyWords.get(i)))),
                    keyWords.get(i));
            androidMarket_sougo_Spider.insert(androidSearch);
            //System.out.println(androidSearch);
        }

    }
}
