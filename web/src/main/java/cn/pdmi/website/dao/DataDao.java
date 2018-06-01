package cn.pdmi.website.dao;

import cn.pdmi.modianSpider.pojo.DataModel;
import cn.pdmi.modianSpider.pojo.Grjz;
import cn.pdmi.modianSpider.utils.CPUtils;
import cn.pdmi.modianSpider.utils.JDBCUtils;
import cn.pdmi.modianSpider.utils.CPUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by chen_ on 2018/4/26.
 */
public class DataDao {
    public Grjz find(String id) throws Exception {
        QueryRunner queryRunner = new QueryRunner(CPUtils.getDataSource());
        String sql = "SELECT * FROM fqy where name=?";
        Grjz grjz = queryRunner.query(sql, new BeanHandler<Grjz>(Grjz.class), id);
        return grjz;
    }

    public List<Grjz> selectAll() throws Exception {
        QueryRunner queryRunner = new QueryRunner(CPUtils.getDataSource());
        String sql = "SELECT * FROM (SELECT id,NAME,SUM(money) money FROM fqy GROUP BY NAME) t ORDER BY t.money DESC LIMIT 0,10";
        List<Grjz> list = queryRunner.query(sql, new BeanListHandler<Grjz>(Grjz.class));
        return list;
    }

    public Map<Integer, Double> selectOne(String id) throws Exception {
        QueryRunner queryRunner = new QueryRunner(CPUtils.getDataSource());
        String sql = "SELECT l.money FROM (SELECT NAME,SUM(money) money FROM fqy GROUP BY NAME) l WHERE l.name=?";
        Double money = queryRunner.query(sql, new ScalarHandler<Double>(), id);
        String sql1 = "SELECT o.rownum FROM (SELECT @rownum:=@rownum+1 AS rownum,t.name,t.money FROM (SELECT id,NAME,SUM(money) money FROM fqy GROUP BY NAME) t,(SELECT @rownum:=0) r  ORDER BY t.money DESC) o WHERE o.name=?";
        Integer rank = Integer.parseInt(String.valueOf(queryRunner.query(sql1, new ScalarHandler<Double>(), id)).replace(".0", ""));
        HashMap<Integer, Double> map = new HashMap<>();
        map.put(rank, money);
        return map;
    }

    public List<String> selectCount() throws Exception {
        ArrayList<String> list = new ArrayList<>();
        QueryRunner queryRunner = new QueryRunner(CPUtils.getDataSource());

        String sql1 = "SELECT COUNT(*) FROM (SELECT NAME,SUM(money) money FROM fqy GROUP BY NAME) l WHERE l.money>=10000";
        Long a = queryRunner.query(sql1, new ScalarHandler<Long>());
        list.add(String.valueOf(a));

        String sql2 = "SELECT COUNT(*) FROM (SELECT NAME,SUM(money) money FROM fqy GROUP BY NAME) l WHERE l.money>=5000 and l.money<10000";
        Long b = queryRunner.query(sql2, new ScalarHandler<Long>());
        list.add(String.valueOf(b));

        String sql3 = "SELECT COUNT(*) FROM (SELECT NAME,SUM(money) money FROM fqy GROUP BY NAME) l WHERE l.money>=1000 and l.money<5000";
        Long c = queryRunner.query(sql3, new ScalarHandler<Long>());
        list.add(String.valueOf(c));

        String sql4 = "SELECT COUNT(*) FROM (SELECT NAME,SUM(money) money FROM fqy GROUP BY NAME) l WHERE l.money>=500 and l.money<1000";
        Long d = queryRunner.query(sql4, new ScalarHandler<Long>());
        list.add(String.valueOf(d));

        String sql5 = "SELECT COUNT(*) FROM (SELECT NAME,SUM(money) money FROM fqy GROUP BY NAME) l WHERE l.money>=100 and l.money<500";
        Long e = queryRunner.query(sql5, new ScalarHandler<Long>());
        list.add(String.valueOf(e));

        String sql6 = "SELECT COUNT(*) FROM (SELECT NAME,SUM(money) money FROM fqy GROUP BY NAME) l WHERE l.money>=35 and l.money<100";
        Long f = queryRunner.query(sql6, new ScalarHandler<Long>());
        list.add(String.valueOf(f));

        String sql7 = "SELECT COUNT(*) FROM (SELECT NAME,SUM(money) money FROM fqy GROUP BY NAME) l WHERE l.money>=10 and l.money<35";
        Long g = queryRunner.query(sql7, new ScalarHandler<Long>());
        list.add(String.valueOf(g));

        String sql8 = "SELECT COUNT(*) FROM (SELECT NAME,SUM(money) money FROM fqy GROUP BY NAME) l WHERE l.money<10";
        Long h = queryRunner.query(sql8, new ScalarHandler<Long>());
        list.add(String.valueOf(h));
        return list;
    }

    public List<String> selectJe() throws Exception {
        ArrayList<String> list = new ArrayList<>();
        QueryRunner queryRunner = new QueryRunner(CPUtils.getDataSource());
        String sql = "SELECT SUM(money) FROM fqy";
        Double total = queryRunner.query(sql, new ScalarHandler<Double>());

        String sql1 = "SELECT SUM(l.money) FROM (SELECT NAME,SUM(money) money FROM fqy GROUP BY NAME) l WHERE l.money>=10000";
        Double a = queryRunner.query(sql1, new ScalarHandler<Double>());
        list.add(String.valueOf(a * 100 / total).substring(0, String.valueOf(a * 100 / total).indexOf(".")));

        String sql2 = "SELECT SUM(l.money) FROM (SELECT NAME,SUM(money) money FROM fqy GROUP BY NAME) l WHERE l.money>=5000 and l.money<10000";
        Double b = queryRunner.query(sql2, new ScalarHandler<Double>());
        list.add(String.valueOf(b * 100 / total).substring(0, String.valueOf(b * 100 / total).indexOf(".")));

        String sql3 = "SELECT SUM(l.money) FROM (SELECT NAME,SUM(money) money FROM fqy GROUP BY NAME) l WHERE l.money>=1000 and l.money<5000";
        Double c = queryRunner.query(sql3, new ScalarHandler<Double>());
        list.add(String.valueOf(c * 100 / total).substring(0, String.valueOf(c * 100 / total).indexOf(".")));

        String sql4 = "SELECT SUM(l.money) FROM (SELECT NAME,SUM(money) money FROM fqy GROUP BY NAME) l WHERE l.money>=500 and l.money<1000";
        Double d = queryRunner.query(sql4, new ScalarHandler<Double>());
        list.add(String.valueOf(d * 100 / total).substring(0, String.valueOf(d * 100 / total).indexOf(".")));

        String sql5 = "SELECT SUM(l.money) FROM (SELECT NAME,SUM(money) money FROM fqy GROUP BY NAME) l WHERE l.money>=100 and l.money<500";
        Double e = queryRunner.query(sql5, new ScalarHandler<Double>());
        list.add(String.valueOf(e * 100 / total).substring(0, String.valueOf(e * 100 / total).indexOf(".")));

        String sql6 = "SELECT SUM(l.money) FROM (SELECT NAME,SUM(money) money FROM fqy GROUP BY NAME) l WHERE l.money>=35 and l.money<100";
        Double f = queryRunner.query(sql6, new ScalarHandler<Double>());
        list.add(String.valueOf(f * 100 / total).substring(0, String.valueOf(f * 100 / total).indexOf(".")));

        String sql7 = "SELECT SUM(l.money) FROM (SELECT NAME,SUM(money) money FROM fqy GROUP BY NAME) l WHERE l.money>=10 and l.money<35";
        Double g = queryRunner.query(sql7, new ScalarHandler<Double>());
        list.add(String.valueOf(g * 100 / total).substring(0, String.valueOf(g * 100 / total).indexOf(".")));

        String sql8 = "SELECT SUM(l.money) FROM (SELECT NAME,SUM(money) money FROM fqy GROUP BY NAME) l WHERE l.money<10";
        Double h = queryRunner.query(sql8, new ScalarHandler<Double>());
        list.add(String.valueOf(h * 100 / total).substring(0, String.valueOf(h * 100 / total).indexOf(".")));
        return list;
    }
}
