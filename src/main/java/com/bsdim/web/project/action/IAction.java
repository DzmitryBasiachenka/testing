package com.bsdim.web.project.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface IAction {
    String perform(HttpServletRequest req, HttpServletResponse resp);
}
