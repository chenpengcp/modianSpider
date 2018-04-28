package cn.pdmi.modianSpider.core;

import cn.pdmi.modianSpider.pojo.DataModel;
import cn.pdmi.modianSpider.utils.JDBCUtils;
import cn.pdmi.modianSpider.utils.SpiderUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.util.ArrayList;

/**
 * Created by chen_ on 2018/4/24.
 */
public class CoreSpider {

    //解析网页
    public Document getDocument(String html) {
        Document document = Jsoup.parse(html);
        return document;
    }

    //封装数据模型
    public DataModel getDataModel(Document document) {
        DataModel dataModel = new DataModel();
        dataModel.setTitle(document.select("h3.title span").html());
        dataModel.setStart_time(document.select("div.col2.remain-time h3").attr("start_time"));
        dataModel.setEnd_time(document.select("div.col2.remain-time h3").attr("end_time"));
        dataModel.setTotalMoney(Double.parseDouble(document.select("div.col1.project-goal h3 span").html().replace(",", "")));
        dataModel.setGoalMoney(Double.parseDouble(document.select("span.goal-money").html().substring(document.select("span.goal-money").html().indexOf("¥") + 1).replace(",", "")));
        dataModel.setCount(Integer.parseInt(document.select("div.col3.support-people h3 span").html()));
        if (dataModel.getCount() > 0) {
            if (document.select("li.wds_item.first div.item_cont p") != null) {
                if (document.select("li.wds_item.first div.item_cont p").first() != null){
                    dataModel.setName1(document.select("li.wds_item.first div.item_cont p").first().html());
                }
            }
            if (document.select("li.wds_item.first div.item_cont p") != null) {
                if (document.select("li.wds_item.first div.item_cont p").size()>1){
                    dataModel.setMoney1(Double.parseDouble(document.select("li.wds_item.first div.item_cont p").get(1).html().substring(document.select("li.wds_item.first div.item_cont p").get(1).html().indexOf("¥") + 1).replace(",", "")));
                }
            }
            if (document.select("li.wds_item.second div.item_cont p") != null) {
                if (document.select("li.wds_item.second div.item_cont p").size()>1){
                    dataModel.setMoney2(Double.parseDouble(document.select("li.wds_item.second div.item_cont p").get(1).html().substring(document.select("li.wds_item.second div.item_cont p").get(1).html().indexOf("¥") + 1).replace(",", "")));
                }
            }
            if (document.select("li.wds_item.third div.item_cont p") != null) {
                if (document.select("li.wds_item.third div.item_cont p").size()>1){
                    dataModel.setMoney3(Double.parseDouble(document.select("li.wds_item.third div.item_cont p").get(1).html().substring(document.select("li.wds_item.third div.item_cont p").get(1).html().indexOf("¥") + 1).replace(",", "")));
                }
            }
            if (document.select("li.wds_item.second div.item_cont p") != null) {
                if (document.select("li.wds_item.second div.item_cont p").first() != null){
                    dataModel.setName2(document.select("li.wds_item.second div.item_cont p").first().html());
                }
            }
            if (document.select("li.wds_item.third div.item_cont p") != null) {
                if (document.select("li.wds_item.third div.item_cont p").first() != null){
                    dataModel.setName3(document.select("li.wds_item.third div.item_cont p").first().html());
                }
            }
            if (document.select("li.wds_item").size()>3){
                if (document.select("li.wds_item").get(3) != null) {
                    dataModel.setName4(document.select("li.wds_item").get(3).select("div.item_cont p").first().html());
                    dataModel.setMoney4(Double.parseDouble(document.select("li.wds_item").get(3).select("div.item_cont p").get(1).html().substring(document.select("li.wds_item").get(3).select("div.item_cont p").get(1).html()
                            .indexOf("¥") + 1).replace(",", "")));
                }
            }
            if (document.select("li.wds_item").size()>4){
                if (document.select("li.wds_item").get(4) != null) {
                    dataModel.setName5(document.select("li.wds_item").get(4).select("div.item_cont p").first().html());
                    dataModel.setMoney5(Double.parseDouble(document.select("li.wds_item").get(4).select("div.item_cont p").get(1).html().substring(document.select("li.wds_item").get(4).select("div.item_cont p").get(1).html()
                            .indexOf("¥") + 1).replace(",", "")));
                }
            }

        }
        return dataModel;
    }

    public void insert(DataModel dataModel) throws Exception {
        QueryRunner queryRunner = new QueryRunner(JDBCUtils.getDataSource());
        String sql = "INSERT INTO datamodel (title,totalMoney,goalMoney,COUNT,name1,money1,name2,money2,name3,money3,name4,money4,name5,money5,url,start_time,end_time) " +
                "VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
        int update = queryRunner.update(sql, dataModel.getTitle(), dataModel.getTotalMoney(), dataModel.getGoalMoney(), dataModel.getCount(),
                dataModel.getName1(), dataModel.getMoney1(), dataModel.getName2(), dataModel.getMoney2(), dataModel.getName3(), dataModel.getMoney3(), dataModel.getName4(), dataModel.getMoney4(), dataModel.getName5(), dataModel.getMoney5(),
                dataModel.getUrl(),dataModel.getStart_time(),dataModel.getEnd_time());
        if (update == 1) {
            System.out.println("success!");
        } else {
            System.out.println("插入失败！");
        }
    }

    public void startServer() throws Exception {
        ArrayList<String> list = new ArrayList<>();
        String url1 = "https://zhongchou.modian.com/item/12794.html";
        String url2 = "https://zhongchou.modian.com/item/14510.html";
        String url3 = "https://zhongchou.modian.com/item/11030.html";
        String url4 = "https://zhongchou.modian.com/item/10586.html";
        String url5 = "https://zhongchou.modian.com/item/11964.html";
        String url6 = "https://zhongchou.modian.com/item/13968.html";
        list.add(url1);
        list.add(url2);
        list.add(url3);
        list.add(url4);
        list.add(url5);
        list.add(url6);

//        String url1 = "https://zhongchou.modian.com/item/11775.html";
//        String url2 = "https://zhongchou.modian.com/item/14630.html";
//        String url3 = "https://zhongchou.modian.com/item/12767.html";
//        String url4 = "https://zhongchou.modian.com/item/11696.html";
//        String url5 = "https://zhongchou.modian.com/item/10703.html";
//        String url6 = "https://zhongchou.modian.com/item/10520.html";
//        String url7 = "https://zhongchou.modian.com/item/13935.html";
//        String url8 = "https://zhongchou.modian.com/item/13933.html";
//        String url9 = "https://zhongchou.modian.com/item/15445.html";
//        //String url10 = "https://zhongchou.modian.com/item/10520.html";
//        list.add(url1);
//        list.add(url2);
//        list.add(url3);
//        list.add(url4);
//        list.add(url5);
//        list.add(url6);
//        list.add(url7);
//        list.add(url8);
//        list.add(url9);
//        //list.add(url10);

//        String url1 = "https://zhongchou.modian.com/item/11390.html";
//        String url2 = "https://zhongchou.modian.com/item/10790.html";
//        String url3 = "https://zhongchou.modian.com/item/15207.html";
//        String url4 = "https://zhongchou.modian.com/item/15313.html";
//        String url5 = "https://zhongchou.modian.com/item/13649.html";
//        String url6 = "https://zhongchou.modian.com/item/12771.html";
//        list.add(url1);
//        list.add(url2);
//        list.add(url3);
//        list.add(url4);
//        list.add(url5);
//        list.add(url6);
        for (int i = 0; i < list.size(); i++) {
            this.handle(list.get(i));
        }
    }

    public void handle(String url) throws Exception {
        CoreSpider coreSpider = new CoreSpider();
        String html = SpiderUtils.getAjax(url);
        if (html != null && !"".equals(html)) {
            Document document = coreSpider.getDocument(html);
                DataModel dataModel = coreSpider.getDataModel(document);
                dataModel.setUrl(url);
                coreSpider.insert(dataModel);
        }
    }
//    public static void main(String[] args) throws Exception {
//        CoreSpider coreSpider = new CoreSpider();
//        for (int i = 14750; i < 14800; i++) {
//            String url = "https://zhongchou.modian.com/item/" + i + ".html";
//            String html = coreSpider.getAjax(url);
//            if (html != null && !"".equals(html)) {
//                Document document = coreSpider.getDocument(html);
//                if (document.select("h3.title span").html().contains("SNH")) {
//                    coreSpider.insert(coreSpider.getDataModel(document));
//                }
//            }
//        }
//        //String html = document.select("li.dialog_user_list.support_user.wds").html();
//    }
}
