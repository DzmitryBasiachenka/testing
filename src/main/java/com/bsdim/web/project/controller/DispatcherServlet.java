package com.bsdim.web.project.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bsdim.web.project.action.AboutAction;
import com.bsdim.web.project.action.ExaminationAction;
import com.bsdim.web.project.action.QuestionAction;
import com.bsdim.web.project.action.QuestionAddAction;
import com.bsdim.web.project.action.QuestionEditAction;
import com.bsdim.web.project.action.StatisticsAction;

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

public class DispatcherServlet extends HttpServlet {
    private static final String ERROR_404_JSP = "error-404.jsp";
    private static final char SLASH = '/';

    private Map<String, IAction> mapGet;
    private Map<String, IAction> mapPost;

//    private Map<String, IAction> mapGetStudent;
//    private Map<String, IAction> mapPostStudent;

    @Override
    public void init() throws ServletException {
        initMapGet();
        initMapPost();

//        initMapGetStudent();
//        initMapPostStudent();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        /*HttpSession session = req.getSession();
        UserSession userSession = (UserSession)session.getAttribute("userSession");
        if (userSession.getRole().equals(Role.STUDENT)) {
            System.out.println("student");
            process(req, resp, mapGetStudent);
        } else {
            process(req, resp, mapGet);
        }*/
        process(req, resp, mapGet);
        //req.getRequestDispatcher("/WEB-INF/view/main.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        /*HttpSession session = req.getSession();
        UserSession userSession = (UserSession)session.getAttribute("userSession");
        if (userSession.getRole().equals(Role.STUDENT)) {
            System.out.println("student");
            process(req, resp, mapPostStudent);
        }*/
        process(req, resp, mapPost);
    }

    /*private void initMapGetStudent() {
        mapGetStudent = new HashMap<>();
        mapGetStudent.put("/", new MainAction());
        mapGetStudent.put("/examination", new ExaminationAction());
        mapGetStudent.put("/statistics", new StatisticsAction());
        mapGetStudent.put("/about", new AboutAction());
        mapGetStudent.put("/profile", new ProfileAction());
        mapGetStudent.put("/logout", new LogoutAction());
        mapGetStudent.put("/user/delete", new UserDeleteAction());
    }*/

    /*private void initMapPostStudent() {
        mapPostStudent = new HashMap<>();
        mapPostStudent.put("/user/edit", new UserEditAction());
    }*/

    private void initMapGet() {
        mapGet = new HashMap<>();
        mapGet.put("/", new MainAction());
        mapGet.put("/about", new AboutAction());
        mapGet.put("/examination", new ExaminationAction());
        mapGet.put("/question", new QuestionAction());
        mapGet.put("/logout", new LogoutAction());
        mapGet.put("/statistics", new StatisticsAction());
        mapGet.put("/subject", new SubjectAction());
        mapGet.put("/test", new TestAction());
        mapGet.put("/test/list", new TestListAction());
        mapGet.put("/test/delete", new TestDeleteAction());
        mapGet.put("/profile", new ProfileAction());
        mapGet.put("/user/delete", new UserDeleteAction());
    }

    private void initMapPost() {
        mapPost = new HashMap<>();
        mapPost.put("/question/add", new QuestionAddAction());
        mapPost.put("/question/edit", new QuestionEditAction());
        mapPost.put("/subject/add", new SubjectAddAction());
        mapPost.put("/test/add", new TestAddAction());
        mapPost.put("/test/edit", new TestEditAction());
        mapPost.put("/user/edit", new UserEditAction());
    }

    private void process(HttpServletRequest req, HttpServletResponse resp, Map<String, IAction> map)
            throws ServletException, IOException {
        String servletPath = req.getServletPath();
        IAction action = findAction(servletPath, map);
        String jspName = ERROR_404_JSP;

        if (action != null) {
            jspName = action.perform(req, resp);
        }

        req.getRequestDispatcher("/WEB-INF/view/" + jspName).forward(req, resp);
    }

    private IAction findAction(String servletPath, Map<String, IAction> map) {
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
    }
}
