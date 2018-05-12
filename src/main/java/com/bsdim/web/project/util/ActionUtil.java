package com.bsdim.web.project.util;

import java.util.Map;

import com.bsdim.web.project.action.IAction;

public final class ActionUtil {
    private static final char SLASH = '/';

    private ActionUtil() {}

    public static IAction findAction(String servletPath, Map<String, IAction> map) {
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
