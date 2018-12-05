package cn.pdmi.modianSpider.thread;

import cn.pdmi.modianSpider.pojo.WyxwData;
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

public class WyxwDataThread implements Runnable {
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
        WyxwData wyxwData = new WyxwData();
        wyxwData.setMedia(media);
        wyxwData.setMediaName(mediaName);
        if (url.startsWith("http://")) {
            String html = SpiderUtils.getAjax(url);
            String dy = getDocument(html).select("div.normal div.colum_dy.clearfix div").get(1).select("p.num").html();
            wyxwData.setDy(dy);
            String article = getDocument(html).select("div.normal div.colum_dy.clearfix div").get(0).select("p.num").html();
            wyxwData.setArticle(article);
        } else {
            wyxwData.setDy("0");
            wyxwData.setArticle("0");
        }
        System.out.println(wyxwData);
        insert(wyxwData);
    }

    public WyxwDataThread(String media, String mediaName, String url) {
        this.media = media;
        this.mediaName = mediaName;
        this.url = url;
    }

    public WyxwDataThread() {
    }

    public String getEncode(String url) throws Exception {
        return URLEncoder.encode(url, "utf-8");
    }

    public void insert(WyxwData wyxwData) throws Exception {
        QueryRunner queryRunner = new QueryRunner(CPUtils.getDataSource());
        String sql = "INSERT INTO wyxwdata (media,mediaName,dy,article) " +
                "VALUES (?,?,?,?)";
        int update = queryRunner.update(sql, wyxwData.getMedia(), wyxwData.getMediaName(), wyxwData.getDy(),wyxwData.getArticle());
        if (update == 1) {
            System.out.println("success!");
        } else {
            System.out.println("插入失败！");
        }

    }

    public List<JrttUrl> find() throws Exception {
        QueryRunner queryRunner = new QueryRunner(CPUtils.getDataSource());
        String sql = "SELECT * FROM wyxwurl";
        return queryRunner.query(sql,new BeanListHandler<JrttUrl>(JrttUrl.class));
    }
    //解析网页
    public Document getDocument(String html) {
        Document document = Jsoup.parse(html);
        return document;
    }

    public static void main(String[] args) throws Exception{
        ExecutorService executorService = Executors.newFixedThreadPool(1);
        WyxwDataThread wyxwDataThread = new WyxwDataThread();
        List<JrttUrl> wyxwDatas = wyxwDataThread.find();
        for (JrttUrl j:wyxwDatas
             ) {
            executorService.submit(new WyxwDataThread(j.getMedia(),j.getMediaName(),j.getUrl()));
        }
    }
}
