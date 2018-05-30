package cn.pdmi.website.controller;

import cn.pdmi.modianSpider.pojo.DataModel;
import cn.pdmi.modianSpider.utils.HttpSpiderUtils;
import cn.pdmi.website.service.DataService;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by chen_ on 2018/4/26.
 */
public class JzbServlet extends HttpServlet {
    private DataService dataService = new DataService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
            String html = HttpSpiderUtils.getHtml("http://112.74.41.218/");
            Document document = Jsoup.parse(html);
            Map<String, String> map = getMap(document, 0);
            for (String k : map.keySet()
                    ) {
                System.out.println(k + map.get(k));
            }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }

    public Map<String, String> getMap(Document document, Integer i) {
        Map<String, String> map = new HashMap<>();
        String name = document.select("div.skilbssa div table.skillbar-bar tbody tr").get(i).select("td").get(1).html();
        String money = document.select("div.skilbssa div table.skillbar-bar tbody tr").get(i).select("td").get(2).html();
        map.put(name, money);
        return map;
    }
}
