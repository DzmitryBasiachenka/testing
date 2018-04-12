package com.bsdim.web.project.service;

import java.util.List;

import com.bsdim.web.project.dao.api.IStatisticsDao;
import com.bsdim.web.project.dao.sql.StatisticsDaoSql;
import com.bsdim.web.project.domain.Statistics;

public class StatisticsService {
    private IStatisticsDao dao = new StatisticsDaoSql();

    public void addStatistics(Statistics statistics) {
        dao.create(statistics);
    }

    public Statistics findById(Integer id) {
        return dao.read(id);
    }

    public void updateStatistics(Statistics statistics) {
        dao.update(statistics);
    }

    public void deleteStatistics(Integer id) {
        dao.delete(id);
    }

    public List<Statistics> getStatistics() {
        return dao.getStatisticsList();
    }
}
