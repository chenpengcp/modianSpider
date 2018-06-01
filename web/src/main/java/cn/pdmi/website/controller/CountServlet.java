package cn.pdmi.website.controller;

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
public class CountServlet extends HttpServlet {
    private DataService dataService = new DataService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        resp.setCharacterEncoding("utf-8");
        List<String> list = dataService.selectCount();
        List<String> set = dataService.selectJe();
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        for (int i = 0; i < list.size(); i++) {
            if (i != list.size() - 1) {
                sb.append("{\"count\":").append(list.get(i)).append(",\"bl\":").append(set.get(i)).append("},");
            } else {
                sb.append("{\"count\":").append(list.get(i)).append(",\"bl\":").append(set.get(i)).append("}]");
            }
        }
        resp.getWriter().write(sb.toString());
    }
}
