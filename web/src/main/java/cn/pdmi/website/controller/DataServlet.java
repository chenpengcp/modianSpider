package cn.pdmi.website.controller;

import cn.pdmi.modianSpider.pojo.DataModel;
import cn.pdmi.modianSpider.pojo.Grjz;
import cn.pdmi.website.service.DataService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Created by chen_ on 2018/4/26.
 */
public class DataServlet extends HttpServlet{
    private DataService dataService=new DataService();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        List<Grjz> grjzs = dataService.selectDataModel();
        for (int i = 0; i <grjzs.size() ; i++) {
            req.setAttribute("dataModel"+i,grjzs.get(i));
        }
        req.getRequestDispatcher("/WEB-INF/jsp/index.jsp").forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
