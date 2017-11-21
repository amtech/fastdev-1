package cn.lucode.fastdev.job.service;

import cn.lucode.fastdev.job.model.ScheduleJobLog;

import java.util.Map;

/**
 * @author yunfeng.lu
 * @create 2017/10/24.
 */
public interface ScheduleJobLogService {
    ScheduleJobLog queryObject(String jobId) throws Exception;

    Map queryList(Integer pageNo, Integer pageSize) throws Exception;

    int deleteObject(String jobId) throws Exception;

    void save(ScheduleJobLog log) throws Exception;
}
