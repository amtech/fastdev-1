package cn.lucode.job.dao;

import cn.lucode.job.model.ScheduleJobLog;

import java.util.List;

public interface ScheduleJobLogMapper {
    int deleteByPrimaryKey(String logId);

    int insert(ScheduleJobLog record);

    int insertSelective(ScheduleJobLog record);

    ScheduleJobLog selectByPrimaryKey(String logId);

    int updateByPrimaryKeySelective(ScheduleJobLog record);

    int updateByPrimaryKey(ScheduleJobLog record);

    List selectList();
}