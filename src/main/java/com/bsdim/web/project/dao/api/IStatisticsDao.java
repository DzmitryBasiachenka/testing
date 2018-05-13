package com.bsdim.web.project.dao.api;

import java.util.List;

import com.bsdim.web.project.domain.Statistics;

public interface IStatisticsDao extends IDao<Integer, Statistics> {
    List<Statistics> getStatisticsListByUserId(Integer id);
}
