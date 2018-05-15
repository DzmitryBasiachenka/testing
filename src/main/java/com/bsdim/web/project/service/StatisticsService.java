package com.bsdim.web.project.service;

import java.util.List;

import com.bsdim.web.project.connection.ConnectionContext;
import com.bsdim.web.project.dao.api.IStatisticsDao;
import com.bsdim.web.project.dao.sql.StatisticsDaoSql;
import com.bsdim.web.project.domain.Statistics;

public class StatisticsService {
    private IStatisticsDao dao = new StatisticsDaoSql();

    public Integer addStatistics(Statistics statistics) {
        try {
            return dao.create(statistics);
        } finally {
            ConnectionContext.releaseConnection();
        }
    }

    public Statistics findById(Integer id) {
        try {
            return dao.read(id);
        } finally {
            ConnectionContext.releaseConnection();
        }
    }

    public void updateStatistics(Statistics statistics) {
        try {
            dao.update(statistics);
        } finally {
            ConnectionContext.releaseConnection();
        }
    }

    public void deleteStatistics(Integer id) {
        try {
            dao.delete(id);
        } finally {
            ConnectionContext.releaseConnection();
        }
    }

    public List<Statistics> getStatisticsListByUserId(Integer id) {
        try {
            return dao.getStatisticsListByUserId(id);
        } finally {
            ConnectionContext.releaseConnection();
        }
    }
}
