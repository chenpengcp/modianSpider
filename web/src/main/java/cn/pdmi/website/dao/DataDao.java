package cn.pdmi.website.dao;

import cn.pdmi.modianSpider.pojo.DataModel;
import cn.pdmi.modianSpider.pojo.Grjz;
import cn.pdmi.modianSpider.utils.CPUtils;
import cn.pdmi.modianSpider.utils.JDBCUtils;
import cn.pdmi.modianSpider.utils.Modian_JDBCUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by chen_ on 2018/4/26.
 */
public class DataDao {

    public List<Grjz> selectAll() throws Exception {
        QueryRunner queryRunner = new QueryRunner(Modian_JDBCUtils.getDataSource());
        String sql = "SELECT * FROM (SELECT id,NAME,SUM(money) money FROM lzy GROUP BY NAME) t ORDER BY t.money DESC LIMIT 0,10";
        List<Grjz> list = queryRunner.query(sql, new BeanListHandler<Grjz>(Grjz.class));
        return list;
    }

    public Map<Integer, Double> selectOne(String id) throws Exception {
        QueryRunner queryRunner = new QueryRunner(Modian_JDBCUtils.getDataSource());
        String sql = "SELECT l.money FROM (SELECT NAME,SUM(money) money FROM lzy GROUP BY NAME) l WHERE l.name=?";
        Double money = queryRunner.query(sql, new ScalarHandler<Double>(), id);
        String sql1 = "SELECT o.rownum FROM (SELECT @rownum:=@rownum+1 AS rownum,t.name,t.money FROM (SELECT id,NAME,SUM(money) money FROM lzy GROUP BY NAME) t,(SELECT @rownum:=0) r  ORDER BY t.money DESC) o WHERE o.name=?";
        Integer rank = Integer.parseInt(String.valueOf(queryRunner.query(sql1, new ScalarHandler<Double>(), id)).replace(".0", ""));
        HashMap<Integer, Double> map = new HashMap<>();
        map.put(rank, money);
        return map;
    }
}
