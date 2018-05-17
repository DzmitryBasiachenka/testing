package com.bsdim.web.project.action.statistics;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.bsdim.web.project.action.IAction;
import com.bsdim.web.project.domain.Statistics;
import com.bsdim.web.project.service.StatisticsService;
import com.bsdim.web.project.session.UserSession;
import com.bsdim.web.project.util.ActionUtil;

public class StatisticsDeleteAction implements IAction {
    private static final String USER_SESSION = "userSession";
    private static final String STATISTICS_DELETED = "statisticsDeleted";
    private static final String STATISTICS_DELETED_MESSAGE = "The statistics deleted";

    private StatisticsService service = new StatisticsService();

    @Override
    public String perform(HttpServletRequest req, HttpServletResponse resp) {
        HttpSession session = req.getSession();
        UserSession userSession = (UserSession)session.getAttribute(USER_SESSION);

        String id = ActionUtil.getIdFromServletPath(req.getServletPath());
        if (ActionUtil.isIdPattern(id)) {
            int statisticsId = Integer.parseInt(id);
            List<Statistics> statisticsList = service.getStatisticsListByUserId(userSession.getId());
            for (Statistics statistics : statisticsList) {
                if (statistics.getId() == statisticsId) {
                    service.deleteStatistics(statisticsId);
                    req.setAttribute(STATISTICS_DELETED, STATISTICS_DELETED_MESSAGE);
                    break;
                }
            }
        }
        return new StatisticsListAction().perform(req, resp);
    }
}
