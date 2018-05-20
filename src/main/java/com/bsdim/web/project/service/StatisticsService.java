package com.bsdim.web.project.service;

import java.util.List;

import com.bsdim.web.project.connection.ConnectionContext;
import com.bsdim.web.project.dao.api.IStatisticsDao;
import com.bsdim.web.project.dao.sql.StatisticsDaoSql;
import com.bsdim.web.project.domain.Statistics;

/**
 * The statistics service.
 * <p>
 * Date: 2018-05-20
 *
 * @author Dzmitry Basiachenka
 */
public class StatisticsService {
    private IStatisticsDao dao = new StatisticsDaoSql();

    /**
     * Adds statistics.
     *
     * @param statistics the statistics.
     * @return the statistics id.
     */
    public Integer addStatistics(Statistics statistics) {
        try {
            return dao.create(statistics);
        } finally {
            ConnectionContext.releaseConnection();
        }
    }

    /**
     * Finds statistics by statistics id.
     *
     * @param id the statistics id.
     * @return the statistics.
     */
    public Statistics findById(Integer id) {
        try {
            return dao.read(id);
        } finally {
            ConnectionContext.releaseConnection();
        }
    }

    /**
     * Updates statistics.
     *
     * @param statistics the statistics.
     */
    public void updateStatistics(Statistics statistics) {
        try {
            dao.update(statistics);
        } finally {
            ConnectionContext.releaseConnection();
        }
    }

    /**
     * Deletes statistics.
     *
     * @param id the statistics id.
     */
    public void deleteStatistics(Integer id) {
        try {
            dao.delete(id);
        } finally {
            ConnectionContext.releaseConnection();
        }
    }

    /**
     * Gets statistics list by user id.
     *
     * @param id the user id.
     * @return the statistics list.
     */
    public List<Statistics> getStatisticsListByUserId(Integer id) {
        try {
            return dao.getStatisticsListByUserId(id);
        } finally {
            ConnectionContext.releaseConnection();
        }
    }

    /**
     * Gets student statistics by test id.
     *
     * @param id the test id.
     * @return the statistics list.
     */
    public List<Statistics> getStudentStatisticsByTestId(Integer id) {
        try {
            return dao.getStudentStatisticsByTestId(id);
        } finally {
            ConnectionContext.releaseConnection();
        }
    }
}
