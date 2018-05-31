package cn.pdmi.modianSpider.core;

import cn.pdmi.modianSpider.pojo.JzData;
import cn.pdmi.modianSpider.pojo.Team;
import cn.pdmi.modianSpider.utils.HttpSpiderUtils;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class JzbSpider {
    public List<JzData> getData() throws Exception {
        String html = HttpSpiderUtils.getHtml("http://112.74.41.218/pot.asp");
        JSONObject jsonObject = JSON.parseObject(html);
        JSONArray jsonArray = jsonObject.getJSONArray("first");
        JSONObject data = null;
        List<JzData> list = new ArrayList<>();
        for (int i = 0; i < 32; i++) {
            JzData jzData = new JzData();
            data = jsonArray.getJSONObject(i);
            jzData.setId(data.getString("id"));
            jzData.setXm(unicodeToCn(data.getString("xm").replace("uu", "\\u")));
            jzData.setJe(data.getDouble("je"));
            jzData.setPs(data.getDouble("ps"));
            jzData.setDw(data.getString("dw"));
            list.add(jzData);
        }
        return list;
    }

    public List<Team> getTeam(){
        List<Team> list = new ArrayList<>();
        try {
            String html = HttpSpiderUtils.getHtml("http://112.74.41.218/poa.asp");
            JSONObject jsonObject = JSON.parseObject(html);
            JSONArray jsonArray = jsonObject.getJSONArray("first");
            JSONObject data = null;
            for (int i = 0; i < jsonArray.size(); i++) {
                Team team = new Team();
                data = jsonArray.getJSONObject(i);
                team.setId(data.getString("id"));
                team.setXm(data.getString("xm"));
                team.setJe(data.getDouble("je"));
                list.add(team);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public String getJson() {
        JzbSpider jzbSpider = new JzbSpider();
        String s = "";
        try {
            List<JzData> list = jzbSpider.getData();
            s = JSON.toJSONString(list);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return s;
    }

    private String unicodeToCn(String unicode) {
        String[] strs = unicode.split("\\\\u");
        String returnStr = "";
        // 由于unicode字符串以 \ u 开头，因此分割出的第一个字符是""。
        for (int i = 1; i < strs.length; i++) {
            returnStr += (char) Integer.valueOf(strs[i], 16).intValue();
        }
        return returnStr;
    }
}
