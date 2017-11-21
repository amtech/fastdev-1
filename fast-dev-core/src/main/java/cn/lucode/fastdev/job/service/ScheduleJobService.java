package cn.lucode.fastdev.job.service;


import cn.lucode.fastdev.job.model.ScheduleJob;

import java.util.Map;

/**
 * @author yunfeng.lu
 * @create 2017/10/24.
 */
public interface ScheduleJobService {
    /**
     * 根据ID，查询定时任务
     */
    ScheduleJob queryObject(String jobId) throws Exception;

    /**
     * 查询定时任务列表
     */
    Map queryList(Integer pageNo, Integer pageSize) throws Exception;


    /**
     * 保存定时任务
     */
    void save(ScheduleJob scheduleJob) throws Exception;

    /**
     * 更新定时任务
     */
    void update(ScheduleJob scheduleJob) throws Exception;

    /**
     * 批量删除定时任务
     */
    void deleteBatch(String[] jobIds) throws Exception;

    /**
     * 批量更新定时任务状态
     */
    void updateBatch(String[] jobIds, int status) throws Exception;

    /**
     * 立即执行
     */
    void run(String jobId) throws Exception;

    /**
     * 暂停运行
     */
    void pause(String jobId) throws Exception;

    /**
     * 恢复运行
     */
    void resume(String[] jobIds) throws Exception;
}
