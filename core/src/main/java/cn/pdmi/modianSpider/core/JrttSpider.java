package cn.pdmi.modianSpider.core;

import cn.pdmi.modianSpider.pojo.JrttModel;
import cn.pdmi.modianSpider.utils.*;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class JrttSpider {
    public void getData(String max_behot_time, String uid,String keyword) throws Exception {
        List<JrttModel> list = new ArrayList<>();
        Map<String, String> asCp = TouTiaoUtils.getAsCp();
        String str = HttpSpiderUtils.getAjax("https://www.toutiao.com/pgc/ma/?page_type=1&max_behot_time=" + max_behot_time + "&uid=" + uid + "&media_id=" + uid + "&output=json&is_json=" +
                "1&count=20&from=user_profile_app&version=2&as=" + asCp.get("as") + "&cp=" + asCp.get("cp"));
        JSONObject jsonObj = JSON.parseObject(str);
        JSONArray dataArray = jsonObj.getJSONArray("data");
        JSONObject data = null;
        for (int i = 0; i < dataArray.size(); i++) {
            data = dataArray.getJSONObject(i);
            JrttModel jrttModel = new JrttModel();
            jrttModel.setCreate_time(data.getString("datetime"));
            jrttModel.setRead_count(data.getInteger("total_read_count"));
            jrttModel.setComment_count(data.getInteger("comment_count"));
            jrttModel.setTitle(data.getString("title"));
            jrttModel.setName(keyword);
            jrttModel.setInsertDate(DateUtils.getDate());
            list.add(jrttModel);
        }
        for (JrttModel jm : list
                ) {
            System.out.println(jm);
        }
        if (jsonObj.getInteger("has_more") == 1) {
            JSONObject object = jsonObj.getJSONObject("next");
            Integer max_behot_time1 = object.getInteger("max_behot_time");
            Thread.sleep(1 * 1000);
            this.getData(String.valueOf(max_behot_time1), uid,keyword);
        }else {
            System.out.println("没了！");
        }
    }

    public Document getDocument(String html) {
        Document document = Jsoup.parse(html);
        return document;
    }

    public String getEncode(String url) throws Exception {
        return URLEncoder.encode(url, "utf-8");
    }

    public  Map<String,String> getUids() throws Exception {
        Map<String,String> uids = new HashMap<>();
        List<String> keyWords = KeyWordUtils.getKeyWords("mediaExcel");
        for (String keyword : keyWords
                ) {
            String html = JrttSpiderUtils.getAjax("https://www.toutiao.com/search/?keyword=" + this.getEncode(keyword));
            Document document = this.getDocument(html);
            if (document.select("div.feedBox div div.sections div.userCard.aladdin a.y-box.link") != null) {
                String href = document.select("div.feedBox div div.sections div.userCard.aladdin a.y-box.link").attr("href");
                if (!"".equals(href.trim())) {
                    String uid = href.substring(href.indexOf("r") + 2, href.length() - 1);
                    System.out.println(uid+""+keyword);
                    uids.put(keyword,uid);
                    Thread thread = new Thread(new JrttSpider_Thread("",uid,keyword));
                    thread.start();
                }
            }
        }
        System.out.println("uid加载完毕");
        return uids;
    }

    public static void main(String[] args) throws Exception {
        JrttSpider jrttSpider = new JrttSpider();
//        Map<String, String> uids = jrttSpider.getUids();
//        for (String uid : uids.keySet()
//                ) {
//            jrttSpider.getData("",uid,uids.get(uid));
//        }
        jrttSpider.getData("","50502346173","人民网");
    }


}
