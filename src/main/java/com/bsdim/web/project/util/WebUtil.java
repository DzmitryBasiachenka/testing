package com.bsdim.web.project.util;

import org.apache.commons.lang3.StringUtils;

/**
 * The web util.
 * <p>
 * Date: 2018-05-20
 *
 * @author Dzmitry Basiachenka
 */
public final class WebUtil {
    private WebUtil() {}

    /**
     * Checks the blank of parameters.
     *
     * @param parameters the parameters.
     * @return true or false.
     */
    public static boolean isNotBlank(String... parameters) {
        for (String parameter : parameters) {
            if (StringUtils.isBlank(parameter)) {
                return false;
            }
        }
        return true;
    }
}
