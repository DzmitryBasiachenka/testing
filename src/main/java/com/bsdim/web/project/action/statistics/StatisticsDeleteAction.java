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
import org.apache.log4j.Logger;

public class StatisticsDeleteAction implements IAction {
    private static final String USER_SESSION = "userSession";
    private static final String STATISTICS_DELETED = "statisticsDeleted";
    private static final String STATISTICS_DELETED_MESSAGE = "t.statistics.deleted.message";

    private static Logger sLogger = Logger.getLogger(StatisticsDeleteAction.class);

    private StatisticsService service = new StatisticsService();

    @Override
    public String perform(HttpServletRequest req, HttpServletResponse resp) {
        HttpSession session = req.getSession();
        UserSession userSession = (UserSession) session.getAttribute(USER_SESSION);

        String id = ActionUtil.getIdFromServletPath(req.getServletPath());
        if (ActionUtil.isIdPattern(id)) {
            int statisticsId = Integer.parseInt(id);
            List<Statistics> statisticsList = service.getStatisticsListByUserId(userSession.getId());
            for (Statistics statistics : statisticsList) {
                if (statistics.getId() == statisticsId) {
                    service.deleteStatistics(statisticsId);
                    sLogger.info("The statistics deleted");

                    req.setAttribute(STATISTICS_DELETED, STATISTICS_DELETED_MESSAGE);
                    break;
                }
            }
        } else {
            sLogger.warn(String.format("'%1$s' does not match id pattern of statistics", id));
        }
        return new StatisticsListAction().perform(req, resp);
    }
}
