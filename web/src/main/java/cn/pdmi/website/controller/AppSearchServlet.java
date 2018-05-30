package cn.pdmi.website.controller;

import cn.pdmi.modianSpider.core.AppleSearchSpider;
import cn.pdmi.modianSpider.core.JrttSpider;
import cn.pdmi.modianSpider.pojo.DataModel;
import cn.pdmi.website.service.DataService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by chen_ on 2018/4/26.
 */
public class AppSearchServlet extends HttpServlet {
    private static ExecutorService executorService = Executors.newCachedThreadPool();
    private DataService dataService = new DataService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            executorService.submit(new JrttSpider("", "50502346173", "人民网"));
            req.getRequestDispatcher("/WEB-INF/jsp/test.jsp").forward(req,resp);
        } catch (Exception e) {
        }

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
