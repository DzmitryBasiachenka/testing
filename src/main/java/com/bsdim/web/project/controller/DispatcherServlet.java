package com.bsdim.web.project.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bsdim.web.project.action.AboutAction;
import com.bsdim.web.project.action.IAction;
import com.bsdim.web.project.action.LogoutAction;
import com.bsdim.web.project.action.MainAction;

@MultipartConfig
public class DispatcherServlet extends HttpServlet {
    private static final String ERROR_404 = "404.jsp";
    private static final char SLASH = '/';

    private Map<String, IAction> mapGet;
    private Map<String, IAction> mapPost;

    @Override
    public void init() throws ServletException {
        initMapGet();
        //initMapPost();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        process(req, resp, mapGet);
        //req.getRequestDispatcher("/WEB-INF/view/main.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //process(req, resp, mapPost);
    }

    private void initMapGet() {
        mapGet = new HashMap<>();
        mapGet.put("/", new MainAction());
        //mapGet.put("/test", new TestAction());
        //mapGet.put("/examination", new ExaminationAction());
        //mapGet.put("/statistics", new StatisticsAction());
        mapGet.put("/about", new AboutAction());
        mapGet.put("/logout", new LogoutAction());
    }

    /*private void initMapPost() {
        mapPost = new HashMap<>();
        mapPost.put("/login", mapGet.get("/"));
        mapPost.put("/article/add", new SaveArticleAction());
        mapPost.put("/article/upload", new ArticleFileAction());
    }*/

    private void process(HttpServletRequest req, HttpServletResponse resp, Map<String, IAction> map)
            throws ServletException, IOException {
        String servletPath = req.getServletPath();
        IAction action = findAction(servletPath, map);
        String jspName = ERROR_404;

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
