package cn.lucode.job.service;

import cn.lucode.job.model.ScheduleJobLog;

import java.util.List;
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
