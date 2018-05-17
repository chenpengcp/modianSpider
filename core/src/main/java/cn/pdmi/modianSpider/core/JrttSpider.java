package cn.pdmi.modianSpider.core;

import cn.pdmi.modianSpider.pojo.JrttDataModel;
import cn.pdmi.modianSpider.pojo.JrttModel;
import cn.pdmi.modianSpider.utils.*;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.net.URLEncoder;
import java.util.*;

public class JrttSpider implements Runnable {
    private String max_behot_time;
    private String uid;
    private String keyword;

    public JrttSpider(String max_behot_time, String uid, String keyword) {
        this.max_behot_time = max_behot_time;
        this.uid = uid;
        this.keyword = keyword;
    }

    public JrttSpider() {
    }

    public void getData(String max_behot_time, String uid, String keyword) throws Exception {
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
            Random random = new Random();
                Thread.sleep(1 * 1000);
            this.getData(String.valueOf(max_behot_time1), uid, keyword);
        } else {
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

    public Map<String, String> getUids() throws Exception {
        Map<String, String> uids = new HashMap<>();
        QueryRunner queryRunner = new QueryRunner(JDBCUtils.getDataSource());
        String sql = "SELECT * FROM jrttData WHERE uid!='0'";
        List<JrttDataModel> list = queryRunner.query(sql, new BeanListHandler<JrttDataModel>(JrttDataModel.class));
        for (JrttDataModel jrttDataModel : list
                ) {
            uids.put(jrttDataModel.getName(), jrttDataModel.getUid());
        }
        return uids;
    }

    public static void main(String[] args) throws Exception {
        JrttSpider jrttSpider = new JrttSpider();
//        Map<String, String> uids = jrttSpider.getUids();
//        for (String name : uids.keySet()
//                ) {
//            System.out.println(name + "===>" + uids.get(name));
//            jrttSpider.getData("", uids.get(name), name);
////            Thread thread = new Thread(new JrttSpider("", uids.get(name), name));
////            thread.start();
//        }
        //jrttSpider.getData("","3363403346","开封网");
        jrttSpider.getData("", "50502346173", "人民网");
    }

    @Override
    public void run() {
        try {
            this.getData(max_behot_time, uid, keyword);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
