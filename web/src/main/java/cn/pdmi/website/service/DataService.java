package cn.pdmi.website.service;

import cn.pdmi.modianSpider.pojo.DataModel;
import cn.pdmi.website.dao.DataDao;

import java.util.List;

/**
 * Created by chen_ on 2018/4/26.
 */
public class DataService {
    private DataDao dataDao = new DataDao();

    public List<DataModel> selectDataModel() {
        try {
            return dataDao.selectAll();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
