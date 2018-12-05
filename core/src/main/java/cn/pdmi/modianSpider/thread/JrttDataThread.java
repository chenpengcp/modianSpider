package cn.pdmi.modianSpider.thread;

import cn.pdmi.modianSpider.pojo.JrttData;
import cn.pdmi.modianSpider.pojo.JrttUrl;
import cn.pdmi.modianSpider.utils.CPUtils;
import cn.pdmi.modianSpider.utils.SpiderUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.net.URLEncoder;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class JrttDataThread implements Runnable {
    private String media;
    private String mediaName;
    private String url;

    @Override
    public void run(){
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
        if (url.startsWith("http://www.toutiao.com/")) {
            String html = SpiderUtils.getAjax(url);
            String dy = getDocument(html).select("div.right div dl dd a h3").attr("number");
            jrttData.setDy(dy);
        } else {
            jrttData.setDy("0");
        }
        System.out.println(jrttData);
        insert(jrttData);
    }

    public JrttDataThread(String media, String mediaName, String url) {
        this.media = media;
        this.mediaName = mediaName;
        this.url = url;
    }

    public JrttDataThread() {
    }

    public String getEncode(String url) throws Exception {
        return URLEncoder.encode(url, "utf-8");
    }

    public void insert(JrttData jrttUrl) throws Exception {
        QueryRunner queryRunner = new QueryRunner(CPUtils.getDataSource());
        String sql = "INSERT INTO jrttdata (media,mediaName,dy) " +
                "VALUES (?,?,?)";
        int update = queryRunner.update(sql, jrttUrl.getMedia(), jrttUrl.getMediaName(), jrttUrl.getDy());
        if (update == 1) {
            System.out.println("success!");
        } else {
            System.out.println("插入失败！");
        }

    }

    public List<JrttUrl> find() throws Exception {
        QueryRunner queryRunner = new QueryRunner(CPUtils.getDataSource());
        String sql = "SELECT * FROM jrtturl";
        return queryRunner.query(sql,new BeanListHandler<JrttUrl>(JrttUrl.class));
    }
    //解析网页
    public Document getDocument(String html) {
        Document document = Jsoup.parse(html);
        return document;
    }

    public static void main(String[] args) throws Exception{
        ExecutorService executorService = Executors.newFixedThreadPool(1);
        JrttDataThread jrttDataThread = new JrttDataThread();
        List<JrttUrl> jrttUrls = jrttDataThread.find();
        for (JrttUrl j:jrttUrls
             ) {
            executorService.submit(new JrttDataThread(j.getMedia(),j.getMediaName(),j.getUrl()));
        }
    }
}
