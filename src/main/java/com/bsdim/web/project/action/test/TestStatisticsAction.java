package com.bsdim.web.project.action.test;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.bsdim.web.project.action.IAction;
import com.bsdim.web.project.domain.Statistics;
import com.bsdim.web.project.domain.Test;
import com.bsdim.web.project.service.StatisticsService;
import com.bsdim.web.project.service.TestService;
import com.bsdim.web.project.session.UserSession;
import com.bsdim.web.project.util.ActionUtil;

public class TestStatisticsAction implements IAction {
    private static final String TEST_STATISTICS_JSP = "test-statistics.jsp";

    private TestService testService = new TestService();
    private StatisticsService statisticsService = new StatisticsService();

    @Override
    public String perform(HttpServletRequest req, HttpServletResponse resp) {
        HttpSession session = req.getSession();
        UserSession userSession = (UserSession) session.getAttribute("userSession");

        String id = ActionUtil.getIdFromServletPath(req.getServletPath());
        if (ActionUtil.isIdPattern(id)) {
            int testId = Integer.parseInt(id);
            Test test = testService.findById(testId);
            if (userSession.getId() == test.getUser().getId()) {
                List<Statistics> statisticsList = statisticsService.getStudentStatisticsByTestId(testId);
                req.setAttribute("statisticsList", statisticsList);
            }
        }
        return TEST_STATISTICS_JSP;
    }
}
