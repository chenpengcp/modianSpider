package cn.pdmi.modianSpider.core;

import cn.pdmi.modianSpider.pojo.Member;
import cn.pdmi.modianSpider.utils.*;
import org.apache.commons.dbutils.QueryRunner;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

public class Snh48Spider {
    public void insert(Member m) throws Exception {
        QueryRunner queryRunner = new QueryRunner(Modian_JDBCUtils.getDataSource());
        String sql = "INSERT INTO member (name,party,team,periods,status) " +
                "VALUES (?,?,?,?,?)";
        int update = queryRunner.update(sql, m.getName(), m.getParty(), m.getTeam(), m.getPeriods(),m.getStatus());
        if (update == 1) {
            System.out.println("success!");
        } else {
            System.out.println("插入失败！");
        }
    }

    //解析网页
    public Document getDocument(String html) {
        Document document = Jsoup.parse(html);
        return document;
    }

    public void getData() throws Exception {
        String html = SpiderUtils.getAjax("http://www.snh48club.com/member/");
        //System.out.println(html);
        Document doc = getDocument(html);
        Elements el = doc.select("div#divSort0").select("ul.list.clear");
        for (int i = 0; i < el.size(); i++) {
            String team = el.get(i).select("h1").html();
            Elements li = el.get(i).select("li");
            for (int j = 0; j < li.size(); j++) {
                Member member = new Member();
                member.setName(li.get(j).select("a p").get(0).html());
                member.setTeam(team);
                member.setStatus("0");
                member.setParty(li.get(j).select("a p.p2").get(0).html().substring(0, 5));
                member.setPeriods(li.get(j).select("a p.p2").get(0).html());
//                FileDownload.download("http://www.snh48club.com" + li.get(j).select("a img").attr("src"), "D:/downloads/" + PYUtils.getPinyin(member.getName()) + ".jpg");
//                ImageUtils.zoomImage("D:/downloads/" + PYUtils.getPinyin(member.getName()) + ".jpg");
                System.out.println(member);
                insert(member);
            }
        }
    }

    public static void main(String[] args) throws Exception {
        Snh48Spider snh48Spider = new Snh48Spider();
        snh48Spider.getData();
    }
}
