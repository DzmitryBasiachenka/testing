package com.bsdim.web.project.dao.api;

import java.util.List;

import com.bsdim.web.project.domain.Statistics;

/**
 * Represents statistics dao interface.
 * <p>
 * Date: 2018-05-20
 *
 * @author Dzmitry Basiachenka
 */
public interface IStatisticsDao extends IDao<Integer, Statistics> {
    /**
     * Gets statistics list by user id.
     *
     * @param id the user id.
     * @return tle statistics list.
     */
    List<Statistics> getStatisticsListByUserId(Integer id);

    /**
     * Gets student statistics by test id.
     *
     * @param id the test id.
     * @return the statistics list.
     */
    List<Statistics> getStudentStatisticsByTestId(Integer id);
}
