package cn.pdmi.website.controller;

import cn.pdmi.modianSpider.core.JzbSpider;
import cn.pdmi.modianSpider.pojo.Grjz;
import cn.pdmi.modianSpider.pojo.Team;
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
public class TeamServlet extends HttpServlet {
    private DataService dataService = new DataService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        JzbSpider jzbSpider = new JzbSpider();
        List<Team> teamList = jzbSpider.getTeam();
        for (int i = 0; i < teamList.size(); i++) {
            req.setAttribute("data" + i, teamList.get(i));
        }
        req.getRequestDispatcher("/WEB-INF/jsp/team.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
