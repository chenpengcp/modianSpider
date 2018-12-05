package cn.pdmi.modianSpider.thread;

import cn.pdmi.modianSpider.pojo.JrttData;
import cn.pdmi.modianSpider.pojo.JrttUrl;
import cn.pdmi.modianSpider.utils.CPUtils;
import cn.pdmi.modianSpider.utils.KeyWordUtils;
import cn.pdmi.modianSpider.utils.SpiderUtils;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.net.URLEncoder;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class YdzxDataThread implements Runnable {
    private String media;
    private String mediaName;

    @Override
    public void run() {
        try {
            handle();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void handle() throws Exception {
        JrttData jrttData = new JrttData();
        jrttData.setMedia(media);
        jrttData.setMediaName(mediaName);
        if (!"无".equals(mediaName)) {
            String html = SpiderUtils.getAjax("http://www.yidianzixun.com/home/q/search_channel?word=" + getEncode(mediaName) + "&appid=web_yidian&_=1543540779494");
            html = getDocument(html).select("pre").html();
            JSONObject jsonObject = (JSONObject) JSON.parse(html);
            JSONArray channels = (JSONArray) jsonObject.get("channels");
            for (int i = 0; i < channels.size(); i++) {
                JSONObject obj = (JSONObject) channels.get(i);
                if (mediaName.equals(obj.get("name")) && obj.get("bookcount") != null) {
                    String ss = "";
                    Double dd = 0d;
                    ss = (String) obj.get("bookcount");
                    ss = ss.replace("人订阅", "");
                    if (ss.contains("万")) {
                        ss = ss.replace("万", "");
                        dd = Double.parseDouble(ss) * 10000;
                        jrttData.setDy(String.valueOf(dd).replace(".0",""));
                        break;
                    } else {
                        jrttData.setDy(ss);
                    }
                }
            }
        } else {
            jrttData.setDy("0");
        }
        System.out.println(jrttData);
        insert(jrttData);
    }

    public YdzxDataThread(String media, String mediaName) {
        this.media = media;
        this.mediaName = mediaName;
    }

    public YdzxDataThread() {
    }

    public String getEncode(String url) throws Exception {
        return URLEncoder.encode(url, "utf-8");
    }

    public void insert(JrttData jrttUrl) throws Exception {
        QueryRunner queryRunner = new QueryRunner(CPUtils.getDataSource());
        String sql = "INSERT INTO ydzxdata (media,mediaName,dy) " +
                "VALUES (?,?,?)";
        int update = queryRunner.update(sql, jrttUrl.getMedia(), jrttUrl.getMediaName(), jrttUrl.getDy());
        if (update == 1) {
            System.out.println("success!");
        } else {
            System.out.println("插入失败！");
        }

    }

//    public List<JrttUrl> find() throws Exception {
//        QueryRunner queryRunner = new QueryRunner(CPUtils.getDataSource());
//        String sql = "SELECT * FROM jrtturl";
//        return queryRunner.query(sql, new BeanListHandler<JrttUrl>(JrttUrl.class));
//    }

    //解析网页
    public Document getDocument(String html) {
        Document document = Jsoup.parse(html);
        return document;
    }

        public static void main(String[] args) throws Exception {
        ExecutorService executorService = Executors.newFixedThreadPool(1);
        Map<Integer, Map<String, String>> keyWords = KeyWordUtils.getKeyWords("ydzx");
        for (Integer i : keyWords.keySet()
                ) {
            Map<String, String> stringStringMap = keyWords.get(i);
            for (String a : stringStringMap.keySet()
                    ) {
                executorService.submit(new YdzxDataThread(a, stringStringMap.get(a)));
            }
        }
    }
//    public static void main(String[] args) throws Exception {
//        String html = SpiderUtils.getAjax("http://www.yidianzixun.com/home/q/search_channel?word=%E4%BA%BA%E6%B0%91%E6%97%A5%E6%8A%A5&appid=web_yidian&_=1543540779494");
//        YdzxDataThread ydzxDataThread = new YdzxDataThread();
//        html = ydzxDataThread.getDocument(html).select("pre").html();
//        JSONObject jsonObject = (JSONObject) JSON.parse(html);
//        JSONArray channels = (JSONArray) jsonObject.get("channels");
//        for (int i = 0; i < channels.size(); i++) {
//            JSONObject obj = (JSONObject) channels.get(i);
//            if ("人民日报".equals(obj.get("name"))) {
//                System.out.println(obj.get("bookcount"));
//            }
//        }
//    }
}
