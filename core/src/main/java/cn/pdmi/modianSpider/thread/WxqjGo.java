package cn.pdmi.modianSpider.thread;

import cn.pdmi.modianSpider.pojo.GrSearch;
import cn.pdmi.modianSpider.pojo.Member;
import cn.pdmi.modianSpider.pojo.Model;
import cn.pdmi.modianSpider.pojo.ModelPlus;
import cn.pdmi.modianSpider.utils.Modian_JDBCUtils;
import cn.pdmi.modianSpider.utils.SpiderUtils;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.net.URLEncoder;
import java.util.List;

/**
 * @description:
 * @author: chenpeng
 */
public class WxqjGo {
    public List<Model> findMember() {
        QueryRunner queryRunner = new QueryRunner(Modian_JDBCUtils.getDataSource());
        String sql = "select * from wxqj";
        try {
            return queryRunner.query(sql, new BeanListHandler<Model>(Model.class));
        } catch (Exception e) {
            return null;
        }
    }

    public static void main(String[] args) throws Exception {
        Integer count=0;
        WxqjGo wxqjGo = new WxqjGo();
        List<Model> member = wxqjGo.findMember();
        for (Model m : member
                ) {
            String str = SpiderUtils.getAjax("http://118.190.199.151:8033/snh48/search?name=" + wxqjGo.getEncode(m.getName()));
            str = str.replace("<html><head></head><body>", "").replace("</body></html>", "");
//            if (!"".equals(str.trim())) {
//                JSONArray jsonArray = JSON.parseArray(str);
//                for (int i = 0; i < jsonArray.size(); i++) {
//                    Object o = jsonArray.get(i);
//                    GrSearch grSearch = JSON.parseObject(JSON.toJSONString(o), GrSearch.class);
//                    ModelPlus modelPlus = new ModelPlus();
//                    modelPlus.setName(grSearch.getMemberName());
//                    modelPlus.setCount(1);
//                    wxqjGo.insert(modelPlus);
//                }
//            }
            if ("".equals(str.trim())) {
                count++;
            }
        }
        System.out.println(count);
    }

    public String getEncode(String url) throws Exception {
        return URLEncoder.encode(url, "utf-8");
    }

    public void insert(ModelPlus m) throws Exception {
        QueryRunner queryRunner = new QueryRunner(Modian_JDBCUtils.getDataSource());
        String sql = "INSERT INTO wxqjg (name,count) " +
                "VALUES (?,?)";
        int update = queryRunner.update(sql, m.getName(), m.getCount());
        if (update == 1) {
            System.out.println("success!");
        } else {
            System.out.println("插入失败！");
        }
    }
}
