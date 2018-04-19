package com.bsdim.web.project.util;

import org.apache.commons.lang3.StringUtils;

public final class WebUtil {
    private WebUtil() {}

    public static boolean isNotBlank(String... parameters) {
        for (String parameter : parameters) {
            if (StringUtils.isBlank(parameter)) {
                return false;
            }
        }
        return true;
    }
}
