package cn.pdmi.modianSpider.core;

import cn.pdmi.modianSpider.pojo.App;
import cn.pdmi.modianSpider.pojo.AppSearch;
import cn.pdmi.modianSpider.utils.JDBCUtils;
import cn.pdmi.modianSpider.utils.KeyWordUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import javax.tools.JavaCompiler;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by chen_ on 2018/4/24.
 */
public class AppleSearchSpider {
    private static final String BLANK = "  ";
    private static final String COMMAND = "D:/phantomjs-2.1.1-windows/bin/phantomjs.exe";
    private static final String JSCODE = "D:/phantomjs/qimai.js";
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
    public AppSearch getAppSearch(Document document, String keyWord, String date) {
        AppSearch appSearch = new AppSearch();
        appSearch.setName(keyWord);
        appSearch.setDate(date);
        appSearch.setSearchIndex(document.select("a.hints").html().replace("&nbsp;<span class=\"glyphicon glyphicon-trend\"></span>",""));
        appSearch.setSearchResult(document.select("a.search-no").html().replace("&nbsp;<span class=\"glyphicon glyphicon-trend\"></span>",""));
        return appSearch;
    }

    public void insert(AppSearch appSearch) throws Exception {
        QueryRunner queryRunner = new QueryRunner(JDBCUtils.getDataSource());
        String sql = "INSERT INTO appSearch (name,searchIndex,searchResult,date) " +
                "VALUES (?,?,?,?)";
        int update = queryRunner.update(sql, appSearch.getName(), appSearch.getSearchIndex(), appSearch.getSearchResult(), appSearch.getDate());
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
        AppleSearchSpider appleSearchSpider = new AppleSearchSpider();
        ArrayList<String> dates = new ArrayList<>();
        for (int i = 1; i <5 ; i++) {
            StringBuffer sb = new StringBuffer();
            sb.append("2018-0").append(i);
            for (int j = 1; j <31 ; j++) {
                StringBuffer sb2 = new StringBuffer();
                sb2.append("-");
                if (j<10){
                    sb2.append("0").append(j);
                }else {
                    sb2.append(j);
                }
                dates.add(sb.toString()+sb2.toString());
            }
        }
        dates.add("2018-01-31");
        dates.add("2018-03-31");
        dates.remove("2018-04-29");
        dates.remove("2018-04-30");
        List<String> keyWords = KeyWordUtils.getKeyWords();
        for (int i = 0; i <keyWords.size() ; i++) {
            for (String date:dates
                    ) {
                AppSearch appSearch = appleSearchSpider.getAppSearch(appleSearchSpider.getDocument(appleSearchSpider.getAjax("https://old.qimai.cn/search/index/country/cn/search/"+appleSearchSpider.getEncode(keyWords.get(i))+"/kdate/"+date)),
                        keyWords.get(i), date);
                appleSearchSpider.insert(appSearch);
            }
        }

    }
}
