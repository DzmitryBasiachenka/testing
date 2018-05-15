package com.bsdim.web.project.action;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.bsdim.web.project.domain.Statistics;
import com.bsdim.web.project.service.StatisticsService;
import com.bsdim.web.project.session.UserSession;

public class StatisticsDeleteAction implements IAction {
    private static final String USER_SESSION = "userSession";
    private static final String STATISTICS_DELETED = "statisticsDeleted";
    private static final String STATISTICS_DELETED_MESSAGE = "The statistics deleted";
    private static final char SLASH = '/';

    private StatisticsService service = new StatisticsService();

    @Override
    public String perform(HttpServletRequest req, HttpServletResponse resp) {
        HttpSession session = req.getSession();
        UserSession userSession = (UserSession)session.getAttribute(USER_SESSION);

        String servletPath = req.getServletPath();
        int index = servletPath.lastIndexOf(SLASH, servletPath.length());
        int statisticsId = Integer.parseInt(servletPath.substring(index + 1));

        List<Statistics> statisticsList = service.getStatisticsListByUserId(userSession.getId());
        for (Statistics statistics : statisticsList) {
            if (statistics.getId() == statisticsId) {
                service.deleteStatistics(statisticsId);
                req.setAttribute(STATISTICS_DELETED, STATISTICS_DELETED_MESSAGE);
                break;
            }
        }
        return new StatisticsListAction().perform(req, resp);
    }
}
