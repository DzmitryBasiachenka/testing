package com.bsdim.web.project.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bsdim.web.project.action.about.AboutAction;
import com.bsdim.web.project.action.admin.AdminAction;
import com.bsdim.web.project.action.admin.AdminRoleAddAction;
import com.bsdim.web.project.action.admin.AdminRoleDeleteAction;
import com.bsdim.web.project.action.admin.AdminSubjectAction;
import com.bsdim.web.project.action.admin.AdminSubjectDeleteAction;
import com.bsdim.web.project.action.admin.AdminSubjectEditAction;
import com.bsdim.web.project.action.admin.AdminSubjectListAction;
import com.bsdim.web.project.action.admin.AdminUserDeleteAction;
import com.bsdim.web.project.action.admin.AdminUserAction;
import com.bsdim.web.project.action.admin.AdminUserListAction;
import com.bsdim.web.project.action.examination.ExaminationAction;
import com.bsdim.web.project.action.examination.ExaminationListAction;
import com.bsdim.web.project.action.examination.ExaminationTestAction;
import com.bsdim.web.project.action.question.QuestionAction;
import com.bsdim.web.project.action.question.QuestionDeleteAction;
import com.bsdim.web.project.action.question.QuestionNewAction;
import com.bsdim.web.project.action.statistics.StatisticsDeleteAction;
import com.bsdim.web.project.action.statistics.StatisticsListAction;
import com.bsdim.web.project.action.question.QuestionEditAction;

import com.bsdim.web.project.action.test.TestAction;
import com.bsdim.web.project.action.test.TestAddAction;
import com.bsdim.web.project.action.subject.SubjectAction;
import com.bsdim.web.project.action.subject.SubjectAddAction;
import com.bsdim.web.project.action.test.TestDeleteAction;
import com.bsdim.web.project.action.test.TestEditAction;
import com.bsdim.web.project.action.test.TestListAction;
import com.bsdim.web.project.action.profile.UserDeleteAction;
import com.bsdim.web.project.action.profile.UserEditAction;
import com.bsdim.web.project.action.IAction;
import com.bsdim.web.project.action.LogoutAction;
import com.bsdim.web.project.action.main.MainAction;
import com.bsdim.web.project.action.profile.ProfileAction;
import com.bsdim.web.project.util.ActionUtil;
import org.apache.log4j.Logger;

public class DispatcherServlet extends HttpServlet {
    private static final String ERROR_404_JSP = "error-404.jsp";

    private static Logger sLogger = Logger.getLogger(DispatcherServlet.class);

    private Map<String, IAction> mapGet;
    private Map<String, IAction> mapPost;

    @Override
    public void init() throws ServletException {
        sLogger.info("Init dispatcher servlet");
        initMapGet();
        initMapPost();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        process(req, resp, mapGet);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        process(req, resp, mapPost);
    }

    private void initMapGet() {
        mapGet = new HashMap<>();
        mapGet.put("/", new MainAction());
        mapGet.put("/about", new AboutAction());
        mapGet.put("/admin", new AdminAction());
        mapGet.put("/admin/user", new AdminUserAction());
        mapGet.put("/admin/user/delete", new AdminUserDeleteAction());
        mapGet.put("/admin/user/list", new AdminUserListAction());
        mapGet.put("/admin/subject", new AdminSubjectAction());
        mapGet.put("/admin/subject/delete", new AdminSubjectDeleteAction());
        mapGet.put("/admin/subject/list", new AdminSubjectListAction());
        mapGet.put("/examination", new ExaminationAction());
        //mapGet.put("/examination/exit", new ExaminationExitAction());
        mapGet.put("/examination/test", new ExaminationTestAction());
        mapGet.put("/question", new QuestionAction());
        mapGet.put("/question/delete", new QuestionDeleteAction());
        mapGet.put("/logout", new LogoutAction());
        mapGet.put("/statistics/delete", new StatisticsDeleteAction());
        mapGet.put("/statistics/list", new StatisticsListAction());
        mapGet.put("/subject", new SubjectAction());
        mapGet.put("/test", new TestAction());
        //mapGet.put("/test/exit", new TestExitAction());
        mapGet.put("/test/list", new TestListAction());
        mapGet.put("/test/delete", new TestDeleteAction());
        mapGet.put("/profile", new ProfileAction());
        mapGet.put("/user/delete", new UserDeleteAction());
    }

    private void initMapPost() {
        mapPost = new HashMap<>();
        mapPost.put("/admin/role/add", new AdminRoleAddAction());
        mapPost.put("/admin/role/delete", new AdminRoleDeleteAction());
        mapPost.put("/admin/subject/edit", new AdminSubjectEditAction());
        mapPost.put("/examination/list", new ExaminationListAction());
        //mapPost.put("/examination/question", new ExaminationQuestionAction());
        //mapPost.put("/question/add", new QuestionAddAction());
        mapPost.put("/question/edit", new QuestionEditAction());
        mapPost.put("/question/new", new QuestionNewAction());
        mapPost.put("/subject/add", new SubjectAddAction());
        mapPost.put("/test/add", new TestAddAction());
        mapPost.put("/test/edit", new TestEditAction());
        mapPost.put("/user/edit", new UserEditAction());
    }

    private void process(HttpServletRequest req, HttpServletResponse resp, Map<String, IAction> map)
            throws ServletException, IOException {
        String servletPath = req.getServletPath();
        IAction action = ActionUtil.findAction(servletPath, map);
        String jspName = ERROR_404_JSP;

        if (action != null) {
            jspName = action.perform(req, resp);
        }

        req.getRequestDispatcher("/WEB-INF/view/" + jspName).forward(req, resp);
    }
}
