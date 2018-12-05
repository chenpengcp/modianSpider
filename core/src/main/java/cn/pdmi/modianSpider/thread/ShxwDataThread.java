package cn.pdmi.modianSpider.thread;

import cn.pdmi.modianSpider.pojo.JrttUrl;
import cn.pdmi.modianSpider.pojo.ShxwData;
import cn.pdmi.modianSpider.utils.CPUtils;
import cn.pdmi.modianSpider.utils.SpiderUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.net.URLEncoder;
import java.text.DecimalFormat;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ShxwDataThread implements Runnable {
    private String media;
    private String mediaName;
    private String url;

    @Override
    public void run() {
        try {
            handle();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void handle() throws Exception {
        DecimalFormat df = new DecimalFormat("#");
        ShxwData shxwData = new ShxwData();
        shxwData.setMedia(media);
        shxwData.setMediaName(mediaName);
        if (url!=null&&url.startsWith("http://")) {
            String html = SpiderUtils.getAjax(url);
            if (getDocument(html).select("div.sohu_detail span").size()>2) {
                String dy = getDocument(html).select("div.sohu_detail span").get(2).html();
                if (dy.contains("万")) {
                    Double dd = 0d;
                    dy = dy.replace("万", "");
                    dd = Double.parseDouble(dy) * 10000;
                    dy = String.valueOf(dd).replace(".0", "");
                }
                shxwData.setDy(dy);
            } else {
                shxwData.setDy("0");
            }
            String article = getDocument(html).select("div.data_acticle p").get(0).html();
            if (article.contains("万")) {
                Double aa = 0d;
                article = article.replace("万", "");
                aa = Double.parseDouble(article) * 10000;
                article = String.valueOf(aa).replace(".0", "");
            }
            shxwData.setArticle(article);
            String read = getDocument(html).select("div.data_read p").get(0).html();
            if (read.contains("万")) {
                Double bb = 0d;
                read = read.replace("万", "");
                bb = Double.parseDouble(read) * 10000;
                read = String.valueOf(df.format(bb)).replace(".0", "");
            }
            if (read.contains("亿")) {
                Double cc = 0d;
                read = read.replace("亿", "");
                if (read.contains(".")) {
                    cc = Double.parseDouble(read) * 1000;
                    read = String.valueOf(df.format(cc)) + "00000";
                } else {
                    read=read+"000000000";
                }
            }
            shxwData.setRead(read);
        } else {
            shxwData.setDy("0");
            shxwData.setArticle("0");
            shxwData.setRead("0");
        }
        System.out.println(shxwData);
        insert(shxwData);
    }

    public ShxwDataThread(String media, String mediaName, String url) {
        this.media = media;
        this.mediaName = mediaName;
        this.url = url;
    }

    public ShxwDataThread() {
    }

    public String getEncode(String url) throws Exception {
        return URLEncoder.encode(url, "utf-8");
    }

    public void insert(ShxwData shxwData) throws Exception {
        QueryRunner queryRunner = new QueryRunner(CPUtils.getDataSource());
        String sql = "INSERT INTO shxwdata (media,mediaName,dy,article,readcount) VALUES (?,?,?,?,?)";
        int update = queryRunner.update(sql, shxwData.getMedia(), shxwData.getMediaName(), shxwData.getDy(), shxwData.getArticle(), shxwData.getRead());
        if (update == 1) {
            System.out.println("success!");
        } else {
            System.out.println("插入失败！");
        }

    }

    public List<JrttUrl> find() throws Exception {
        QueryRunner queryRunner = new QueryRunner(CPUtils.getDataSource());
        String sql = "SELECT * FROM shxwurl";
        return queryRunner.query(sql, new BeanListHandler<JrttUrl>(JrttUrl.class));
    }

    //解析网页
    public Document getDocument(String html) {
        Document document = Jsoup.parse(html);
        return document;
    }

    public static void main(String[] args) throws Exception {
        ExecutorService executorService = Executors.newFixedThreadPool(1);
        ShxwDataThread shxwDataThread = new ShxwDataThread();
        List<JrttUrl> list = shxwDataThread.find();
        for (JrttUrl j : list
                ) {
            executorService.submit(new ShxwDataThread(j.getMedia(), j.getMediaName(), j.getUrl()));
        }
    }
}
