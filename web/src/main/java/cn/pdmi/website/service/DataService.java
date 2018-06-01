package cn.pdmi.website.service;

import cn.pdmi.modianSpider.pojo.DataModel;
import cn.pdmi.modianSpider.pojo.Grjz;
import cn.pdmi.website.dao.DataDao;

import java.util.List;
import java.util.Map;

/**
 * Created by chen_ on 2018/4/26.
 */
public class DataService {
    private DataDao dataDao = new DataDao();

    public List<Grjz> selectDataModel() {
        try {
            return dataDao.selectAll();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<String> selectCount() {
        try {
            return dataDao.selectCount();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<String> selectJe() {
        try {
            return dataDao.selectJe();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public Grjz find(String id) {
        try {
            return dataDao.find(id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public Map<Integer, Double> selectOne(String id) {
        try {
            return dataDao.selectOne(id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
