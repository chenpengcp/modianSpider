package cn.pdmi.modianSpider.core;

import cn.pdmi.modianSpider.pojo.JrttUrl;
import cn.pdmi.modianSpider.pojo.WeiBoData;
import cn.pdmi.modianSpider.pojo.WyxwData;
import cn.pdmi.modianSpider.utils.CPUtils;
import cn.pdmi.modianSpider.utils.HttpSpiderUtils;
import cn.pdmi.modianSpider.utils.Modian_JDBCUtils;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.net.URL;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @description:
 * @author: chenpeng
 */
public class WeiBoSpider {
    public static void main(String[] args) {
        String regEx_html = "<[^>]+>";
        for (int j = 1; j < 357; j++) {
            try {
                URL originalUrl = new URL("https://weibo.com/aj/v6/mblog/info/big?ajwvr=6&id=4384856139772335&max_id=4384895801653997&page=" + j + "&__rnd=1560924152341");
                String result = HttpSpiderUtils.getAjax(originalUrl.toString());
                JSONObject jsonObject = JSON.parseObject(result);
                JSONObject data = jsonObject.getJSONObject("data");
                String html = data.getString("html");
                JSONObject page = data.getJSONObject("page");
                Integer totalpage = page.getInteger("totalpage");
                Integer pagenum = page.getInteger("pagenum");
                System.out.println("pagenum:" + pagenum + "==>" + "totalpage:" + totalpage);
//        System.out.println(html);
                Document document = Jsoup.parse(html);
                Elements elements = document.select("div.list_li.S_line1.clearfix");
                for (int i = 0; i < elements.size(); i++) {
                    Element element = elements.get(i);
                    WeiBoData weiBoData = new WeiBoData();
                    String url = element.select("div.WB_face.W_fl a").attr("href");
                    String userId = element.select("div.WB_face.W_fl a").attr("usercard");
                    weiBoData.setUrl(url);
                    weiBoData.setUserId(userId.replace("id=", ""));
                    String name = element.select("div.WB_text a").get(0).html();
                    String content = element.select("div.WB_text span").get(0).html();
                    content = replaceByEx(content, regEx_html);
                    weiBoData.setName(name);
                    weiBoData.setContent(content);
                    System.out.println(weiBoData);
//                    if (find(url) != null) {
                        insert(weiBoData);
//                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static void insert(WeiBoData weiBoData) throws Exception {
        QueryRunner queryRunner = new QueryRunner(Modian_JDBCUtils.getDataSource());
        String sql = "INSERT INTO weibodata (userId,name,url,content) " +
                "VALUES (?,?,?,?)";
        int update = queryRunner.update(sql, weiBoData.getUserId(), weiBoData.getName(), weiBoData.getUrl(), weiBoData.getContent());
        if (update == 1) {
            System.out.println("success!");
        } else {
            System.out.println("插入失败！");
        }

    }

    public static String replaceByEx(String s, String ex) {
        Pattern p_html = Pattern.compile(ex, Pattern.CASE_INSENSITIVE);
        Matcher m_html = p_html.matcher(s);
        return m_html.replaceAll(" ");
    }

    public static WeiBoData find(String url) throws Exception {
        QueryRunner queryRunner = new QueryRunner(CPUtils.getDataSource());
        String sql = "SELECT * FROM weibodata where url=?";
        return queryRunner.query(sql, new BeanHandler<WeiBoData>(WeiBoData.class), url);
    }
}
