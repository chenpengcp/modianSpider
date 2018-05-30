package cn.pdmi.modianSpider.core;

import cn.pdmi.modianSpider.pojo.JrttDataModel;
import cn.pdmi.modianSpider.pojo.JrttModel;
import cn.pdmi.modianSpider.utils.*;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.net.URLEncoder;
import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class JrttSpider implements Runnable {
    //private static Logger logger = Logger.getLogger(JrttSpider.class);

    private static ExecutorService executorService = Executors.newCachedThreadPool();
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
        String str = "";
        List<JrttModel> list = new ArrayList<>();
        //List<Integer> time = new ArrayList<>();
        Map<String, String> asCp = TouTiaoUtils.getAsCp();
        while (true) {
            str = HttpSpiderUtils.getAjax("https://www.toutiao.com/pgc/ma/?page_type=1&max_behot_time=" + max_behot_time + "&uid=" + uid + "&media_id=" + uid + "&output=json&is_json=" +
                    "1&count=20&from=user_profile_app&version=2&as=" + asCp.get("as") + "&cp=" + asCp.get("cp"));
            if (!"".equals(str)) {
                break;
            }
        }
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
            //logger.debug(jrttModel.getTitle() + "====>" + jrttModel.getCreate_time());
            list.add(jrttModel);
        }
        for (JrttModel jm : list
                ) {
            //System.out.println(jm);
            this.insert(jm);
            //Thread.sleep(1 * 1000);
        }
        if (jsonObj.getInteger("has_more") == 1) {
            JSONObject object = jsonObj.getJSONObject("next");
            Integer max_behot_time1 = object.getInteger("max_behot_time");
            Thread.sleep(1 * 1000);
            //System.out.println("===================================>" + Thread.currentThread().getName());
            //time.clear();
            //time.add(max_behot_time1);
            executorService.execute(new JrttSpider(String.valueOf(max_behot_time1), uid, keyword));
        } else {
            System.out.println("重新执行方法！");
            //logger.error("重新执行方法！");
            //executorService.execute(new JrttSpider(String.valueOf(time.get(list.size() - 1)), uid, keyword));
        }
    }

    public void insert(JrttModel jrttModel) throws Exception {
        QueryRunner queryRunner = new QueryRunner(Modian_JDBCUtils.getDataSource());
        //String sql1 = "SELECT * FROM jrtt_article where NAME=? AND title=?";
        //JrttModel model = queryRunner.query(sql1, new BeanHandler<JrttModel>(JrttModel.class), jrttModel.getName(), jrttModel.getTitle());
        //if (model == null) {
            String sql2 = "INSERT INTO jrtt_article_4 (name,title,comment_count,read_count,create_time,insertDate) " +
                    "VALUES (?,?,?,?,?,?)";
            int update = queryRunner.update(sql2, jrttModel.getName(), jrttModel.getTitle(), jrttModel.getComment_count(), jrttModel.getRead_count(), jrttModel.getCreate_time(), jrttModel.getInsertDate());
            if (update == 1) {
                System.out.println("success!");
            } else {
                System.out.println("插入失败！");
            }
//        } else {
//            System.out.println("第" + model.getId() + "条");
//        }
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
        //jrttSpider.getData("", "50502346173", "人民网");
        jrttSpider.getData("", "5806115967", "光明网");
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
