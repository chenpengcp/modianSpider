package cn.pdmi.modianSpider.core;

import cn.pdmi.modianSpider.pojo.AndroidSearch;
import cn.pdmi.modianSpider.pojo.AppSearch;
import cn.pdmi.modianSpider.utils.JDBCUtils;
import cn.pdmi.modianSpider.utils.KeyWordUtils;
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
public class AndroidMarket_360_Spider {
    //解析网页
    public Document getDocument(String html) {
        Document document = Jsoup.parse(html);
        return document;
    }

    //封装数据模型
    public AndroidSearch getAndroidSearch(Document document, String keyWord) {
        AndroidSearch androidSearch = new AndroidSearch();
        androidSearch.setName(keyWord);
        //对于有搜索指数的
        if (document.select("ul").get(1).select("li").get(0).select("span.red").html() != null && !"".equals(document.select("ul").get(1).select("li").get(0).select("span.red").html())&&keyWord.toLowerCase().contains(document.select("ul").get(1).select("li").get(0).select("h3 a").attr("title").toLowerCase())) {
            androidSearch.setEnter(1);
            String count = document.select("ul").get(1).select("li").get(0).select("p.downNum").html();
            if (count.endsWith("亿次下载")) {
                androidSearch.setDownloads(String.valueOf(Integer.parseInt(count.substring(0, count.indexOf("万"))) * 1000000000));
            }
            if (count.endsWith("万次下载")) {
                androidSearch.setDownloads(String.valueOf(Integer.parseInt(count.substring(0, count.indexOf("万"))) * 10000));
            }
            if (count.endsWith("千次下载")) {
                androidSearch.setDownloads(String.valueOf(Integer.parseInt(count.substring(0, count.indexOf("千"))) * 1000));
            }
            if (count.endsWith("百次下载")) {
                androidSearch.setDownloads(String.valueOf(Integer.parseInt(count.substring(0, count.indexOf("百"))) * 100));
            }
            if (count.endsWith("十次下载")) {
                androidSearch.setDownloads(String.valueOf(Integer.parseInt(count.substring(0, count.indexOf("十"))) * 10));
            }
            if (count.endsWith("次下载")&&!count.contains("亿")&&!count.contains("万")&&!count.contains("千")&&!count.contains("百")&&!count.contains("十")) {
                androidSearch.setDownloads(String.valueOf(Integer.parseInt(count.substring(0, count.indexOf("次")))));
            }
        } else {
            androidSearch.setEnter(0);
            androidSearch.setDownloads("0");
        }
        return androidSearch;
    }

    public void insert(AndroidSearch androidSearch) throws Exception {
        QueryRunner queryRunner = new QueryRunner(JDBCUtils.getDataSource());
        String sql = "INSERT INTO androidSearch_360 (name,downloads,enter) " +
                "VALUES (?,?,?)";
        int update = queryRunner.update(sql, androidSearch.getName(), androidSearch.getDownloads(), androidSearch.getEnter());
        if (update == 1) {
            System.out.println("success!");
        } else {
            System.out.println("插入失败！");
        }

    }
    public String getEncode(String url)throws Exception{
        return URLEncoder.encode(url,"utf-8");
    }
    public static void main(String[] args) throws Exception {
        AndroidMarket_360_Spider androidMarket_360_Spider = new AndroidMarket_360_Spider();
        List<String> keyWords = KeyWordUtils.getKeyWords("androidExcel");
        for (int i = 0; i <keyWords.size() ; i++) {
            AndroidSearch androidSearch = androidMarket_360_Spider.getAndroidSearch(androidMarket_360_Spider.getDocument(SpiderUtils.getAjax(" http://zhushou.360.cn/search/index/?kw="+androidMarket_360_Spider.getEncode(keyWords.get(i)))),
                        keyWords.get(i));
            androidMarket_360_Spider.insert(androidSearch);
        }

    }
}
