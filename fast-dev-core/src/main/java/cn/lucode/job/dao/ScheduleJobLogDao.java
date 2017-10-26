package cn.lucode.job.dao;

import cn.lucode.job.entity.ScheduleJobEntity;
import cn.lucode.job.entity.ScheduleJobLogEntity;

import java.util.List;
import java.util.Map;

/**
 * @author yunfeng.lu
 * @create 2017/10/24.
 */
public interface ScheduleJobLogDao {
    /**
     * 根据ID，查询定时任务日志
     */
    ScheduleJobLogEntity queryObject(Long jobId);

    /**
     * 查询定时任务日志列表
     */
    List<ScheduleJobLogEntity> queryList(Map<String, Object> map);

    /**
     * 查询总数
     */
    int queryTotal(Map<String, Object> map);

    /**
     * 保存定时任务日志
     */
    void save(ScheduleJobLogEntity log);

}
