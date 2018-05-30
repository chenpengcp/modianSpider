package cn.pdmi.website.dao;

import cn.pdmi.modianSpider.pojo.DataModel;
import cn.pdmi.modianSpider.utils.CPUtils;
import cn.pdmi.modianSpider.utils.JDBCUtils;
import cn.pdmi.modianSpider.utils.Modian_JDBCUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.util.List;

/**
 * Created by chen_ on 2018/4/26.
 */
public class DataDao {

    public List<DataModel> selectAll() throws Exception{
        QueryRunner queryRunner = new QueryRunner(Modian_JDBCUtils.getDataSource());
        String sql="SELECT * FROM datamodel";
        List<DataModel> list = queryRunner.query(sql, new BeanListHandler<DataModel>(DataModel.class));
        return list;
    }
}
