package cn.lucode.fastdev.job.dao;

import cn.lucode.fastdev.job.model.ScheduleJob;

import java.util.List;

public interface ScheduleJobMapper {

    int deleteByPrimaryKey(String jobId);

    int insert(ScheduleJob record);

    int insertSelective(ScheduleJob record);

    ScheduleJob selectByPrimaryKey(String jobId);

    int updateByPrimaryKeySelective(ScheduleJob record);

    int updateByPrimaryKey(ScheduleJob record);

    List selectList();
}