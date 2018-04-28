package cn.pdmi.modianSpider.core;

import cn.pdmi.modianSpider.pojo.SingletonModel;
import cn.pdmi.modianSpider.utils.JDBCUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by chen_ on 2018/4/24.
 */
public class DataSpider {
    private static final String BLANK = "  ";
    private static final String COMMAND = "D:/phantomjs-2.1.1-windows/bin/phantomjs.exe";
    private static final String JSCODE = "D:/phantomjs/modian.js";
    //private static final String PATH = "D:/modian.png";


    //抓取网页html
    public String getAjax(String url) throws IOException {
        Runtime runtime = Runtime.getRuntime();
        Process p = runtime.exec(COMMAND + BLANK + JSCODE + BLANK + url);
        InputStream is = p.getInputStream();
        BufferedReader br = new BufferedReader(new InputStreamReader(is));
        StringBuffer sbf = new StringBuffer();
        String temp = "";
        while ((temp = br.readLine()) != null) {
            sbf.append(temp);
        }
        //System.out.println("抓取成功！");
        return sbf.toString();
    }

    //解析网页
    public Document getDocument(String html) {
        Document document = Jsoup.parse(html);
        return document;
    }

    //封装数据模型
    public List<SingletonModel> getSingletonModel(Document document) {
        ArrayList<SingletonModel> list = new ArrayList<>();
        SingletonModel singletonModel1 = new SingletonModel();
        SingletonModel singletonModel2 = new SingletonModel();
        SingletonModel singletonModel3 = new SingletonModel();
        SingletonModel singletonModel4 = new SingletonModel();
        SingletonModel singletonModel5 = new SingletonModel();
        SingletonModel singletonModel6 = new SingletonModel();
        SingletonModel singletonModel7 = new SingletonModel();
        SingletonModel singletonModel8 = new SingletonModel();
        SingletonModel singletonModel9 = new SingletonModel();
        SingletonModel singletonModel10 = new SingletonModel();
        SingletonModel singletonModel11 = new SingletonModel();
        SingletonModel singletonModel12 = new SingletonModel();
        SingletonModel singletonModel13 = new SingletonModel();
        SingletonModel singletonModel14 = new SingletonModel();
        SingletonModel singletonModel15 = new SingletonModel();
        SingletonModel singletonModel16 = new SingletonModel();
        SingletonModel singletonModel17 = new SingletonModel();
        SingletonModel singletonModel18 = new SingletonModel();
        SingletonModel singletonModel19 = new SingletonModel();
        SingletonModel singletonModel20 = new SingletonModel();
        if (document.select("li.wds_item.first div.item_cont p") != null) {
                if (document.select("li.wds_item.first div.item_cont p").first() != null){

                    singletonModel1.setName(document.select("li.wds_item.first div.item_cont p").first().html());
                }
            }
            if (document.select("li.wds_item.first div.item_cont p") != null) {
                if (document.select("li.wds_item.first div.item_cont p").size()>1){
                    singletonModel1.setMoney(Double.parseDouble(document.select("li.wds_item.first div.item_cont p").get(1).html().substring(document.select("li.wds_item.first div.item_cont p").get(1).html().indexOf("¥") + 1).replace(",", "")));
                }
            }
            if (document.select("li.wds_item.second div.item_cont p") != null) {
                if (document.select("li.wds_item.second div.item_cont p").size()>1){
                    singletonModel2.setMoney(Double.parseDouble(document.select("li.wds_item.second div.item_cont p").get(1).html().substring(document.select("li.wds_item.second div.item_cont p").get(1).html().indexOf("¥") + 1).replace(",", "")));
                }
            }
            if (document.select("li.wds_item.third div.item_cont p") != null) {
                if (document.select("li.wds_item.third div.item_cont p").size()>1){
                    singletonModel3.setMoney(Double.parseDouble(document.select("li.wds_item.third div.item_cont p").get(1).html().substring(document.select("li.wds_item.third div.item_cont p").get(1).html().indexOf("¥") + 1).replace(",", "")));
                }
            }
            if (document.select("li.wds_item.second div.item_cont p") != null) {
                if (document.select("li.wds_item.second div.item_cont p").first() != null){
                    singletonModel2.setName(document.select("li.wds_item.second div.item_cont p").first().html());
                }
            }
            if (document.select("li.wds_item.third div.item_cont p") != null) {
                if (document.select("li.wds_item.third div.item_cont p").first() != null){
                    singletonModel3.setName(document.select("li.wds_item.third div.item_cont p").first().html());
                }
            }
            if (document.select("li.wds_item").size()>3){
                if (document.select("li.wds_item").get(3) != null) {
                    singletonModel4.setName(document.select("li.wds_item").get(3).select("div.item_cont p").first().html());
                    singletonModel4.setMoney(Double.parseDouble(document.select("li.wds_item").get(3).select("div.item_cont p").get(1).html().substring(document.select("li.wds_item").get(3).select("div.item_cont p").get(1).html()
                            .indexOf("¥") + 1).replace(",", "")));
                }
            }
            if (document.select("li.wds_item").size()>4){
                if (document.select("li.wds_item").get(4) != null) {
                    singletonModel5.setName(document.select("li.wds_item").get(4).select("div.item_cont p").first().html());
                    singletonModel5.setMoney(Double.parseDouble(document.select("li.wds_item").get(4).select("div.item_cont p").get(1).html().substring(document.select("li.wds_item").get(4).select("div.item_cont p").get(1).html()
                            .indexOf("¥") + 1).replace(",", "")));
                }
            }
        if (document.select("li.wds_item").size()>5){
            if (document.select("li.wds_item").get(5) != null) {
                singletonModel6.setName(document.select("li.wds_item").get(5).select("div.item_cont p").first().html());
                singletonModel6.setMoney(Double.parseDouble(document.select("li.wds_item").get(5).select("div.item_cont p").get(1).html().substring(document.select("li.wds_item").get(5).select("div.item_cont p").get(1).html()
                        .indexOf("¥") + 1).replace(",", "")));
            }
        }
        if (document.select("li.wds_item").size()>6){
            if (document.select("li.wds_item").get(6) != null) {
                singletonModel7.setName(document.select("li.wds_item").get(6).select("div.item_cont p").first().html());
                singletonModel7.setMoney(Double.parseDouble(document.select("li.wds_item").get(6).select("div.item_cont p").get(1).html().substring(document.select("li.wds_item").get(6).select("div.item_cont p").get(1).html()
                        .indexOf("¥") + 1).replace(",", "")));
            }
        }
        if (document.select("li.wds_item").size()>7){
            if (document.select("li.wds_item").get(7) != null) {
                singletonModel8.setName(document.select("li.wds_item").get(7).select("div.item_cont p").first().html());
                singletonModel8.setMoney(Double.parseDouble(document.select("li.wds_item").get(7).select("div.item_cont p").get(1).html().substring(document.select("li.wds_item").get(7).select("div.item_cont p").get(1).html()
                        .indexOf("¥") + 1).replace(",", "")));
            }
        }
        if (document.select("li.wds_item").size()>8){
            if (document.select("li.wds_item").get(8) != null) {
                singletonModel8.setName(document.select("li.wds_item").get(8).select("div.item_cont p").first().html());
                singletonModel8.setMoney(Double.parseDouble(document.select("li.wds_item").get(8).select("div.item_cont p").get(1).html().substring(document.select("li.wds_item").get(8).select("div.item_cont p").get(1).html()
                        .indexOf("¥") + 1).replace(",", "")));
            }
        }

        return list;
    }

//    public void insert(SingletonModel dataModel) throws Exception {
//        QueryRunner queryRunner = new QueryRunner(JDBCUtils.getDataSource());
//        String sql = "INSERT INTO datamodel (title,totalMoney,goalMoney,COUNT,name1,money1,name2,money2,name3,money3,name4,money4,name5,money5,url,start_time,end_time) " +
//                "VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
//        int update = queryRunner.update(sql, dataModel.getTitle(), dataModel.getTotalMoney(), dataModel.getGoalMoney(), dataModel.getCount(),
//                dataModel.getName1(), dataModel.getMoney1(), dataModel.getName2(), dataModel.getMoney2(), dataModel.getName3(), dataModel.getMoney3(), dataModel.getName4(), dataModel.getMoney4(), dataModel.getName5(), dataModel.getMoney5(),
//                dataModel.getUrl(),dataModel.getStart_time(),dataModel.getEnd_time());
//        if (update == 1) {
//            System.out.println("success!");
//        } else {
//            System.out.println("插入失败！");
//        }
//    }

//    public void startServer() throws Exception {
//        ArrayList<String> list = new ArrayList<>();
//        String url1 = "https://zhongchou.modian.com/item/12794.html";
//        String url2 = "https://zhongchou.modian.com/item/14510.html";
//        String url3 = "https://zhongchou.modian.com/item/11030.html";
//        String url4 = "https://zhongchou.modian.com/item/10586.html";
//        String url5 = "https://zhongchou.modian.com/item/11964.html";
//        String url6 = "https://zhongchou.modian.com/item/13968.html";
//        list.add(url1);
//        list.add(url2);
//        list.add(url3);
//        list.add(url4);
//        list.add(url5);
//        list.add(url6);
//
////        String url1 = "https://zhongchou.modian.com/item/11775.html";
////        String url2 = "https://zhongchou.modian.com/item/14630.html";
////        String url3 = "https://zhongchou.modian.com/item/12767.html";
////        String url4 = "https://zhongchou.modian.com/item/11696.html";
////        String url5 = "https://zhongchou.modian.com/item/10703.html";
////        String url6 = "https://zhongchou.modian.com/item/10520.html";
////        String url7 = "https://zhongchou.modian.com/item/13935.html";
////        String url8 = "https://zhongchou.modian.com/item/13933.html";
////        String url9 = "https://zhongchou.modian.com/item/15445.html";
////        //String url10 = "https://zhongchou.modian.com/item/10520.html";
////        list.add(url1);
////        list.add(url2);
////        list.add(url3);
////        list.add(url4);
////        list.add(url5);
////        list.add(url6);
////        list.add(url7);
////        list.add(url8);
////        list.add(url9);
////        //list.add(url10);
//
////        String url1 = "https://zhongchou.modian.com/item/11390.html";
////        String url2 = "https://zhongchou.modian.com/item/10790.html";
////        String url3 = "https://zhongchou.modian.com/item/15207.html";
////        String url4 = "https://zhongchou.modian.com/item/15313.html";
////        String url5 = "https://zhongchou.modian.com/item/13649.html";
////        String url6 = "https://zhongchou.modian.com/item/12771.html";
////        list.add(url1);
////        list.add(url2);
////        list.add(url3);
////        list.add(url4);
////        list.add(url5);
////        list.add(url6);
//        for (int i = 0; i < list.size(); i++) {
//            this.handle(list.get(i));
//        }
//    }

//    public void handle(String url) throws Exception {
//        DataSpider coreSpider = new DataSpider();
//        String html = coreSpider.getAjax(url);
//        if (html != null && !"".equals(html)) {
//            Document document = coreSpider.getDocument(html);
//                SingletonModel dataModel = coreSpider.getSingletonModel(document);
//                dataModel.setUrl(url);
//                coreSpider.insert(dataModel);
//        }
//    }
//    public static void main(String[] args) throws Exception {
//        CoreSpider coreSpider = new CoreSpider();
//        for (int i = 14750; i < 14800; i++) {
//            String url = "https://zhongchou.modian.com/item/" + i + ".html";
//            String html = coreSpider.getAjax(url);
//            if (html != null && !"".equals(html)) {
//                Document document = coreSpider.getDocument(html);
//                if (document.select("h3.title span").html().contains("SNH")) {
//                    coreSpider.insert(coreSpider.getSingletonModel(document));
//                }
//            }
//        }
//        //String html = document.select("li.dialog_user_list.support_user.wds").html();
//    }
}
