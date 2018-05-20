package com.bsdim.web.project.action.statistics;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.bsdim.web.project.action.IAction;
import com.bsdim.web.project.action.test.TestListAction;
import com.bsdim.web.project.domain.Test;
import com.bsdim.web.project.service.StatisticsService;
import com.bsdim.web.project.service.TestService;
import com.bsdim.web.project.session.UserSession;
import com.bsdim.web.project.util.ActionUtil;
import org.apache.log4j.Logger;

/**
 * The statistics delete action.
 * <p>
 * Date: 2018-05-20
 *
 * @author Dzmitry Basiachenka
 */
public class StatisticsDeleteAction implements IAction {
    private static final String USER_SESSION = "userSession";
    private static final String STATISTICS_DELETED = "statisticsDeleted";
    private static final String STATISTICS_DELETED_MESSAGE = "t.statistics.deleted.message";

    private static Logger sLogger = Logger.getLogger(StatisticsDeleteAction.class);

    private StatisticsService service = new StatisticsService();
    private TestService testService = new TestService();

    @Override
    public String perform(HttpServletRequest req, HttpServletResponse resp) {
        HttpSession session = req.getSession();
        UserSession userSession = (UserSession) session.getAttribute(USER_SESSION);

        String statisticsIdParameter = ActionUtil.getIdFromServletPath(req.getServletPath());
        String testIdParameter = req.getParameter("testId");
        if (ActionUtil.isIdPattern(statisticsIdParameter) & ActionUtil.isIdPattern(testIdParameter)) {
            int statisticsId = Integer.parseInt(statisticsIdParameter);
            int testId = Integer.parseInt(testIdParameter);

            Test test = testService.findById(testId);

            if (test.getUser().getId() == userSession.getId()) {
                service.deleteStatistics(statisticsId);
                sLogger.info("The statistics deleted");
                req.setAttribute(STATISTICS_DELETED, STATISTICS_DELETED_MESSAGE);
            }
        } else {
            sLogger.warn(String.format("'%1$s' or '%2$s' does not match id pattern of statistics",
                    statisticsIdParameter, testIdParameter));
        }
        return new TestListAction().perform(req, resp);
    }
}
