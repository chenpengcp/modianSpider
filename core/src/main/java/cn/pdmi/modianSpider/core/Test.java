package cn.pdmi.modianSpider.core;

import cn.pdmi.modianSpider.utils.HttpSpiderUtils;
import cn.pdmi.modianSpider.utils.SpiderUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

public class Test {
    public static void main(String[] args) throws Exception {
        Test test = new Test();
        String html = "uu674Euu827Auu5F64";
        String[] uus = html.split("uu");
        String s=uus[0];
        for (int i = 1; i <uus.length ; i++) {
            String ss=uus[i];
            s += String.valueOf(Integer.parseInt(ss.substring(0, 4),16));
            //System.out.println(ss);
        }
        System.out.println(s);
    }
    public Map<String, String> getMap(Document document, Integer i) {
        Map<String, String> map = new HashMap<>();
        String name = document.select("div.skilbssa div table.skillbar-bar tbody tr").get(i).select("td").get(1).html();
        String money = document.select("div.skilbssa div table.skillbar-bar tbody tr").get(i).select("td").get(2).html();
        map.put(name, money);
        return map;
    }
    public String getEncode(String url) throws Exception {
        return URLEncoder.encode(url, "utf-8");
    }
}
