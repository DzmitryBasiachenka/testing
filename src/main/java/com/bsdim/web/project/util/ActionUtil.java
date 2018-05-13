package com.bsdim.web.project.util;

import java.util.Map;

import com.bsdim.web.project.action.IAction;

public final class ActionUtil {
    private static final char SLASH = '/';
    private static final String TABS_AND_EXTRA_SPACES_REGEXP = "(\\t+)|( {2,})";
    private static final String SPACE = " ";

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

    public static String replaceExtraSpaces(String text) {
        return text.replaceAll(TABS_AND_EXTRA_SPACES_REGEXP, SPACE);
    }
}
