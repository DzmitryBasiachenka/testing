package com.bsdim.web.project.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bsdim.web.project.action.AboutAction;
import com.bsdim.web.project.action.AdminAction;
import com.bsdim.web.project.action.AdminSubjectListAction;
import com.bsdim.web.project.action.AdminUserDeleteAction;
import com.bsdim.web.project.action.AdminUserListAction;
import com.bsdim.web.project.action.ExaminationAction;
import com.bsdim.web.project.action.ExaminationListAction;
import com.bsdim.web.project.action.ExaminationTestAction;
import com.bsdim.web.project.action.QuestionAction;
import com.bsdim.web.project.action.QuestionDeleteAction;
import com.bsdim.web.project.action.QuestionNewAction;
import com.bsdim.web.project.action.StatisticsDeleteAction;
import com.bsdim.web.project.action.StatisticsListAction;
import com.bsdim.web.project.action.QuestionEditAction;

import com.bsdim.web.project.action.TestAction;
import com.bsdim.web.project.action.TestAddAction;
import com.bsdim.web.project.action.SubjectAction;
import com.bsdim.web.project.action.SubjectAddAction;
import com.bsdim.web.project.action.TestDeleteAction;
import com.bsdim.web.project.action.TestEditAction;
import com.bsdim.web.project.action.TestListAction;
import com.bsdim.web.project.action.UserDeleteAction;
import com.bsdim.web.project.action.UserEditAction;
import com.bsdim.web.project.action.IAction;
import com.bsdim.web.project.action.LogoutAction;
import com.bsdim.web.project.action.MainAction;
import com.bsdim.web.project.action.ProfileAction;
import com.bsdim.web.project.util.ActionUtil;

public class DispatcherServlet extends HttpServlet {
    private static final String ERROR_404_JSP = "error-404.jsp";

    private Map<String, IAction> mapGet;
    private Map<String, IAction> mapPost;

    @Override
    public void init() throws ServletException {
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
        mapGet.put("/admin/user/delete", new AdminUserDeleteAction());
        mapGet.put("/admin/user/list", new AdminUserListAction());
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

    /*private IAction findAction(String servletPath, Map<String, IAction> map) {
        while (!servletPath.isEmpty()) {
            IAction action = map.get(servletPath);
            if (action == null) {
                int index = servletPath.lastIndexOf(SLASH, servletPath.length());
                servletPath = servletPath.substring(0, index);
            } else {
                return action;
            }
        }
        return null;
    }*/
}
