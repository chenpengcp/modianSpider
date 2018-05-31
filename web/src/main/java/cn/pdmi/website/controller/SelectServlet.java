package cn.pdmi.website.controller;

import cn.pdmi.modianSpider.pojo.Grjz;
import cn.pdmi.website.service.DataService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * Created by chen_ on 2018/4/26.
 */
public class SelectServlet extends HttpServlet {
    private DataService dataService = new DataService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        resp.setCharacterEncoding("utf-8");
        String id = req.getParameter("id");
        Map<Integer, Double> map = dataService.selectOne(id);
        StringBuilder sb = new StringBuilder();
        sb.append("[{\"pm\":");
        for (Integer key : map.keySet()
                ) {
            sb.append(key).append(",\"je\":").append(map.get(key));
        }
        sb.append("}]");
        resp.getWriter().write(sb.toString());
    }
}
