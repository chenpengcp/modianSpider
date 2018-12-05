package cn.pdmi.modianSpider.thread;

import cn.pdmi.modianSpider.pojo.TxxwData;
import cn.pdmi.modianSpider.pojo.TxxwUrl;
import cn.pdmi.modianSpider.utils.CPUtils;
import cn.pdmi.modianSpider.utils.KeyWordUtils;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class TxxwDataThread {

    public static void insert(TxxwData txxwData) throws Exception {
        QueryRunner queryRunner = new QueryRunner(CPUtils.getDataSource());
        String sql = "INSERT INTO txxwdata (media,mediaName,dy,article,url) " +
                "VALUES (?,?,?,?,?)";
        int update = queryRunner.update(sql, txxwData.getMedia(), txxwData.getMediaName(), txxwData.getDy(), txxwData.getArticle(), txxwData.getUrl());
        if (update == 1) {
            System.out.println("success!");
        } else {
            System.out.println("插入失败！");
        }

    }

    public static TxxwUrl find(String name) throws Exception {
        QueryRunner queryRunner = new QueryRunner(CPUtils.getDataSource());
        String sql = "SELECT * FROM txxwurl where name=?";
        return queryRunner.query(sql, new BeanHandler<TxxwUrl>(TxxwUrl.class), name);
    }


    public static void main(String[] args) throws Exception {
        ExecutorService executorService = Executors.newFixedThreadPool(1);
        Map<Integer, Map<String, String>> keyWords = KeyWordUtils.getKeyWords("txxw");
        for (Integer i : keyWords.keySet()
                ) {
            Map<String, String> stringStringMap = keyWords.get(i);
            for (String a : stringStringMap.keySet()
                    ) {
                TxxwData txxwData = new TxxwData();
                txxwData.setMedia(a);
                txxwData.setMediaName(stringStringMap.get(a));
                if (find(stringStringMap.get(a)) != null) {
                    TxxwUrl txxwUrl = find(stringStringMap.get(a));
                    txxwData.setDy(txxwUrl.getDy());
                    txxwData.setArticle(txxwUrl.getArticle());
                    txxwData.setUrl(txxwUrl.getUrl());
                } else {
                    txxwData.setDy("0");
                    txxwData.setArticle("0");
                    txxwData.setUrl("11111");
                }
                insert(txxwData);
            }
        }
    }
}
