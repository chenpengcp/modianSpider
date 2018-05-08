package cn.pdmi.modianSpider.core;

import cn.pdmi.modianSpider.pojo.SingletonModel;

import cn.pdmi.modianSpider.utils.JDBCUtils;
import cn.pdmi.modianSpider.utils.SpiderUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by chen_ on 2018/4/24.
 */
public class DataSpider {

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
            if (document.select("li.wds_item.first div.item_cont p").first() != null) {

                singletonModel1.setName(document.select("li.wds_item.first div.item_cont p").first().html());
            }
        }
        if (document.select("li.wds_item.first div.item_cont p") != null) {
            if (document.select("li.wds_item.first div.item_cont p").size() > 1) {
                singletonModel1.setMoney(Double.parseDouble(document.select("li.wds_item.first div.item_cont p").get(1).html().substring(document.select("li.wds_item.first div.item_cont p").get(1).html().indexOf("¥") + 1).replace(",", "")));
            }
        }
        if (document.select("li.wds_item.second div.item_cont p") != null) {
            if (document.select("li.wds_item.second div.item_cont p").size() > 1) {
                singletonModel2.setMoney(Double.parseDouble(document.select("li.wds_item.second div.item_cont p").get(1).html().substring(document.select("li.wds_item.second div.item_cont p").get(1).html().indexOf("¥") + 1).replace(",", "")));
            }
        }
        if (document.select("li.wds_item.third div.item_cont p") != null) {
            if (document.select("li.wds_item.third div.item_cont p").size() > 1) {
                singletonModel3.setMoney(Double.parseDouble(document.select("li.wds_item.third div.item_cont p").get(1).html().substring(document.select("li.wds_item.third div.item_cont p").get(1).html().indexOf("¥") + 1).replace(",", "")));
            }
        }
        if (document.select("li.wds_item.second div.item_cont p") != null) {
            if (document.select("li.wds_item.second div.item_cont p").first() != null) {
                singletonModel2.setName(document.select("li.wds_item.second div.item_cont p").first().html());
            }
        }
        if (document.select("li.wds_item.third div.item_cont p") != null) {
            if (document.select("li.wds_item.third div.item_cont p").first() != null) {
                singletonModel3.setName(document.select("li.wds_item.third div.item_cont p").first().html());
            }
        }
        if (document.select("li.wds_item").size() > 3) {
            if (document.select("li.wds_item").get(3) != null) {
                singletonModel4.setName(document.select("li.wds_item").get(3).select("div.item_cont p").first().html());
                singletonModel4.setMoney(Double.parseDouble(document.select("li.wds_item").get(3).select("div.item_cont p").get(1).html().substring(document.select("li.wds_item").get(3).select("div.item_cont p").get(1).html()
                        .indexOf("¥") + 1).replace(",", "")));
            }
        }
        if (document.select("li.wds_item").size() > 4) {
            if (document.select("li.wds_item").get(4) != null) {
                singletonModel5.setName(document.select("li.wds_item").get(4).select("div.item_cont p").first().html());
                singletonModel5.setMoney(Double.parseDouble(document.select("li.wds_item").get(4).select("div.item_cont p").get(1).html().substring(document.select("li.wds_item").get(4).select("div.item_cont p").get(1).html()
                        .indexOf("¥") + 1).replace(",", "")));
            }
        }
        if (document.select("li.wds_item").size() > 5) {
            if (document.select("li.wds_item").get(5) != null) {
                singletonModel6.setName(document.select("li.wds_item").get(5).select("div.item_cont p").first().html());
                singletonModel6.setMoney(Double.parseDouble(document.select("li.wds_item").get(5).select("div.item_cont p").get(1).html().substring(document.select("li.wds_item").get(5).select("div.item_cont p").get(1).html()
                        .indexOf("¥") + 1).replace(",", "")));
            }
        }
        if (document.select("li.wds_item").size() > 6) {
            if (document.select("li.wds_item").get(6) != null) {
                singletonModel7.setName(document.select("li.wds_item").get(6).select("div.item_cont p").first().html());
                singletonModel7.setMoney(Double.parseDouble(document.select("li.wds_item").get(6).select("div.item_cont p").get(1).html().substring(document.select("li.wds_item").get(6).select("div.item_cont p").get(1).html()
                        .indexOf("¥") + 1).replace(",", "")));
            }
        }
        if (document.select("li.wds_item").size() > 7) {
            if (document.select("li.wds_item").get(7) != null) {
                singletonModel8.setName(document.select("li.wds_item").get(7).select("div.item_cont p").first().html());
                singletonModel8.setMoney(Double.parseDouble(document.select("li.wds_item").get(7).select("div.item_cont p").get(1).html().substring(document.select("li.wds_item").get(7).select("div.item_cont p").get(1).html()
                        .indexOf("¥") + 1).replace(",", "")));
            }
        }
        if (document.select("li.wds_item").size() > 8) {
            if (document.select("li.wds_item").get(8) != null) {
                singletonModel9.setName(document.select("li.wds_item").get(8).select("div.item_cont p").first().html());
                singletonModel9.setMoney(Double.parseDouble(document.select("li.wds_item").get(8).select("div.item_cont p").get(1).html().substring(document.select("li.wds_item").get(8).select("div.item_cont p").get(1).html()
                        .indexOf("¥") + 1).replace(",", "")));
            }
        }
        if (document.select("li.wds_item").size() > 9) {
            if (document.select("li.wds_item").get(9) != null) {
                singletonModel10.setName(document.select("li.wds_item").get(9).select("div.item_cont p").first().html());
                singletonModel10.setMoney(Double.parseDouble(document.select("li.wds_item").get(9).select("div.item_cont p").get(1).html().substring(document.select("li.wds_item").get(9).select("div.item_cont p").get(1).html()
                        .indexOf("¥") + 1).replace(",", "")));
            }
        }
        if (document.select("li.wds_item").size() > 10) {
            if (document.select("li.wds_item").get(10) != null) {
                singletonModel11.setName(document.select("li.wds_item").get(10).select("div.item_cont p").first().html());
                singletonModel11.setMoney(Double.parseDouble(document.select("li.wds_item").get(10).select("div.item_cont p").get(1).html().substring(document.select("li.wds_item").get(10).select("div.item_cont p").get(1).html()
                        .indexOf("¥") + 1).replace(",", "")));
            }
        }
        if (document.select("li.wds_item").size() > 11) {
            if (document.select("li.wds_item").get(11) != null) {
                singletonModel12.setName(document.select("li.wds_item").get(11).select("div.item_cont p").first().html());
                singletonModel12.setMoney(Double.parseDouble(document.select("li.wds_item").get(11).select("div.item_cont p").get(1).html().substring(document.select("li.wds_item").get(11).select("div.item_cont p").get(1).html()
                        .indexOf("¥") + 1).replace(",", "")));
            }
        }
        if (document.select("li.wds_item").size() > 12) {
            if (document.select("li.wds_item").get(12) != null) {
                singletonModel13.setName(document.select("li.wds_item").get(12).select("div.item_cont p").first().html());
                singletonModel13.setMoney(Double.parseDouble(document.select("li.wds_item").get(12).select("div.item_cont p").get(1).html().substring(document.select("li.wds_item").get(12).select("div.item_cont p").get(1).html()
                        .indexOf("¥") + 1).replace(",", "")));
            }
        }
        if (document.select("li.wds_item").size() > 13) {
            if (document.select("li.wds_item").get(13) != null) {
                singletonModel14.setName(document.select("li.wds_item").get(13).select("div.item_cont p").first().html());
                singletonModel14.setMoney(Double.parseDouble(document.select("li.wds_item").get(13).select("div.item_cont p").get(1).html().substring(document.select("li.wds_item").get(13).select("div.item_cont p").get(1).html()
                        .indexOf("¥") + 1).replace(",", "")));
            }
        }
        if (document.select("li.wds_item").size() > 14) {
            if (document.select("li.wds_item").get(14) != null) {
                singletonModel15.setName(document.select("li.wds_item").get(14).select("div.item_cont p").first().html());
                singletonModel15.setMoney(Double.parseDouble(document.select("li.wds_item").get(14).select("div.item_cont p").get(1).html().substring(document.select("li.wds_item").get(14).select("div.item_cont p").get(1).html()
                        .indexOf("¥") + 1).replace(",", "")));
            }
        }
        if (document.select("li.wds_item").size() > 15) {
            if (document.select("li.wds_item").get(15) != null) {
                singletonModel16.setName(document.select("li.wds_item").get(15).select("div.item_cont p").first().html());
                singletonModel16.setMoney(Double.parseDouble(document.select("li.wds_item").get(15).select("div.item_cont p").get(1).html().substring(document.select("li.wds_item").get(15).select("div.item_cont p").get(1).html()
                        .indexOf("¥") + 1).replace(",", "")));
            }
        }
        if (document.select("li.wds_item").size() > 16) {
            if (document.select("li.wds_item").get(16) != null) {
                singletonModel17.setName(document.select("li.wds_item").get(16).select("div.item_cont p").first().html());
                singletonModel17.setMoney(Double.parseDouble(document.select("li.wds_item").get(16).select("div.item_cont p").get(1).html().substring(document.select("li.wds_item").get(16).select("div.item_cont p").get(1).html()
                        .indexOf("¥") + 1).replace(",", "")));
            }
        }
        if (document.select("li.wds_item").size() > 17) {
            if (document.select("li.wds_item").get(17) != null) {
                singletonModel18.setName(document.select("li.wds_item").get(17).select("div.item_cont p").first().html());
                singletonModel18.setMoney(Double.parseDouble(document.select("li.wds_item").get(17).select("div.item_cont p").get(1).html().substring(document.select("li.wds_item").get(17).select("div.item_cont p").get(1).html()
                        .indexOf("¥") + 1).replace(",", "")));
            }
        }
        if (document.select("li.wds_item").size() > 18) {
            if (document.select("li.wds_item").get(18) != null) {
                singletonModel19.setName(document.select("li.wds_item").get(18).select("div.item_cont p").first().html());
                singletonModel19.setMoney(Double.parseDouble(document.select("li.wds_item").get(18).select("div.item_cont p").get(1).html().substring(document.select("li.wds_item").get(18).select("div.item_cont p").get(1).html()
                        .indexOf("¥") + 1).replace(",", "")));
            }
        }
        if (document.select("li.wds_item").size() > 19) {
            if (document.select("li.wds_item").get(19) != null) {
                singletonModel20.setName(document.select("li.wds_item").get(19).select("div.item_cont p").first().html());
                singletonModel20.setMoney(Double.parseDouble(document.select("li.wds_item").get(19).select("div.item_cont p").get(1).html().substring(document.select("li.wds_item").get(19).select("div.item_cont p").get(1).html()
                        .indexOf("¥") + 1).replace(",", "")));
            }
        }
        list.add(singletonModel1);
        list.add(singletonModel2);
        list.add(singletonModel3);
        list.add(singletonModel4);
        list.add(singletonModel5);
        list.add(singletonModel6);
        list.add(singletonModel7);
        list.add(singletonModel8);
        list.add(singletonModel9);
        list.add(singletonModel10);
        list.add(singletonModel11);
        list.add(singletonModel12);
        list.add(singletonModel13);
        list.add(singletonModel14);
        list.add(singletonModel15);
        list.add(singletonModel16);
        list.add(singletonModel17);
        list.add(singletonModel18);
        list.add(singletonModel19);
        list.add(singletonModel20);
        for (SingletonModel sm : list
                ) {
            sm.setTitle(document.select("h3.title span").html());
            sm.setTotalMoney(Double.parseDouble(document.select("div.col1.project-goal h3 span").html().replace(",", "")));
        }
        return list;
    }

    public void insert(SingletonModel singletonModel) throws Exception {
        QueryRunner queryRunner = new QueryRunner(JDBCUtils.getDataSource());
        String sql = "INSERT INTO zdn (name,money,totalMoney,title,url) " +
                "VALUES (?,?,?,?,?)";
        int update = queryRunner.update(sql, singletonModel.getName(), singletonModel.getMoney(), singletonModel.getTotalMoney(), singletonModel.getTitle(), singletonModel.getUrl());
        if (update == 1) {
            System.out.println("success!");
        } else {
            System.out.println("插入失败！");
        }
    }

    public void startServer() throws Exception {
        ArrayList<String> list = new ArrayList<>();
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

        //链接地址
        String url1 = "https://zhongchou.modian.com/item/16039.html";
        list.add(url1);
        for (int i = 0; i < list.size(); i++) {
            this.handle(list.get(i));
        }
    }

    public void handle(String url) throws Exception {
        DataSpider dataSpider = new DataSpider();
        String html = SpiderUtils.getAjax(url);
        if (html != null && !"".equals(html)) {
            Document document = dataSpider.getDocument(html);
            List<SingletonModel> singletonModels = dataSpider.getSingletonModel(document);
            for (SingletonModel singletonModel : singletonModels
                    ) {
                singletonModel.setUrl(url);
                dataSpider.insert(singletonModel);
            }

        }
    }

}
