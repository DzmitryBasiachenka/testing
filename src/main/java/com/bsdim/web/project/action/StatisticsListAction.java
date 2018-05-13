package com.bsdim.web.project.action;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.bsdim.web.project.domain.Statistics;
import com.bsdim.web.project.service.StatisticsService;
import com.bsdim.web.project.session.UserSession;

public class StatisticsListAction implements IAction {
    private static final String STATISTICS_LIST_JSP = "statistics-list.jsp";
    private static final String USER_SESSION = "userSession";
    private static final String STATISTICS_LIST = "statisticsList";

    private StatisticsService service = new StatisticsService();

    @Override
    public String perform(HttpServletRequest req, HttpServletResponse resp) {
        HttpSession session = req.getSession();
        UserSession userSession = (UserSession)session.getAttribute(USER_SESSION);

        List<Statistics> statisticsList = service.getStatisticsListByUserId(userSession.getId());
        req.setAttribute(STATISTICS_LIST, statisticsList);
        return STATISTICS_LIST_JSP;
    }
}
