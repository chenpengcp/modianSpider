package cn.pdmi.modianSpider.thread;

import cn.pdmi.modianSpider.pojo.JrttUrl;
import cn.pdmi.modianSpider.utils.CPUtils;
import cn.pdmi.modianSpider.utils.KeyWordUtils;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.dbutils.QueryRunner;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ShxwUrlThread implements Runnable {
    private String media;
    private String mediaName;

    @Override
    public void run() {
        try {
            handle(media, mediaName);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public ShxwUrlThread(String media, String mediaName) {
        this.media = media;
        this.mediaName = mediaName;
    }

    public void handle(String media, String mediaName) throws Exception {
        JrttUrl jrttUrl = new JrttUrl();
        jrttUrl.setMedia(media);
        jrttUrl.setMediaName(mediaName);
        if (!"无".equals(jrttUrl.getMediaName())) {
            String result = getResult(mediaName);
            JSONObject jsonObject = (JSONObject) JSON.parse(result);
            JSONArray array = (JSONArray) jsonObject.get("data");
            for (int i = 0; i < array.size(); i++) {
                JSONObject obj = (JSONObject) array.get(i);
                if (mediaName.equals(obj.get("userName"))) {
                    jrttUrl.setUrl((String) obj.get("weiboUrl"));
                }
                break;
            }
        } else {
            jrttUrl.setUrl("11111");
        }
        System.out.println(jrttUrl);
        insert(jrttUrl);
    }

    public static void main(String[] args) throws Exception {
        ExecutorService executorService = Executors.newFixedThreadPool(1);
        Map<Integer, Map<String, String>> keyWords = KeyWordUtils.getKeyWords("shxw");
        for (Integer i : keyWords.keySet()
                ) {
            Map<String, String> stringStringMap = keyWords.get(i);
            for (String a : stringStringMap.keySet()
                    ) {
                executorService.submit(new ShxwUrlThread(a, stringStringMap.get(a)));
            }
        }
    }

    public String getResult(String keyword) throws Exception {
        URL serverUrl = new URL("http://search.sohu.com/outer/search/media");
        HttpURLConnection conn = (HttpURLConnection) serverUrl.openConnection();
        conn.setRequestMethod("POST");
        conn.setRequestProperty("Content-type", "application/x-www-form-urlencoded");
        conn.setInstanceFollowRedirects(false);
        conn.setDoOutput(true);
        conn.setDoInput(true);
        conn.connect();
        String ss = "keyword=" + getEncode(keyword);
        OutputStream os = null;
        os = conn.getOutputStream();
        os.write(ss.getBytes());
        String result = getReturn(conn);
        return result;
    }

    /*请求url获取返回的内容*/
    public String getReturn(HttpURLConnection connection) throws IOException {
        StringBuffer buffer = new StringBuffer();
        //将返回的输入流转换成字符串
        try (InputStream inputStream = connection.getInputStream();
             InputStreamReader inputStreamReader = new InputStreamReader(inputStream, "utf-8");
             BufferedReader bufferedReader = new BufferedReader(inputStreamReader);) {
            String str = null;
            while ((str = bufferedReader.readLine()) != null) {
                buffer.append(str);
            }
            String result = buffer.toString();
            return result;
        }
    }

    public String getEncode(String url) throws Exception {
        return URLEncoder.encode(url, "utf-8");
    }

    public void insert(JrttUrl jrttUrl) throws Exception {
        QueryRunner queryRunner = new QueryRunner(CPUtils.getDataSource());
        String sql = "INSERT INTO shxwurl (media,mediaName,url) " +
                "VALUES (?,?,?)";
        int update = queryRunner.update(sql, jrttUrl.getMedia(), jrttUrl.getMediaName(), jrttUrl.getUrl());
        if (update == 1) {
            System.out.println("success!");
        } else {
            System.out.println("插入失败！");
        }

    }
}
