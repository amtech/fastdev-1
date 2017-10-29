package cn.lucode.job.service.impl;


import cn.lucode.annotation.CommonAppCode;
import cn.lucode.annotation.LogAuto;
import cn.lucode.common.CommonBizTypeCode;
import cn.lucode.common.ReturnCodeModel;
import cn.lucode.exception.CommonException;
import cn.lucode.job.controller.ScheduleJobLogController;
import cn.lucode.job.dao.ScheduleJobMapper;
import cn.lucode.job.model.ScheduleJob;
import cn.lucode.job.service.ScheduleJobService;
import cn.lucode.job.utils.ScheduleStatus;
import cn.lucode.job.utils.ScheduleUtils;
import cn.lucode.util.LogUtil;
import cn.lucode.util.UUIDGenerator;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.quartz.CronTrigger;
import org.quartz.Scheduler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author yunfeng.lu
 * @create 2017/10/24.
 */
@Service("scheduleJobService")
public class ScheduleJobServiceImpl implements ScheduleJobService {

    private static final Logger logger = LoggerFactory.getLogger(ScheduleJobServiceImpl.class);


    @Autowired
    private Scheduler scheduler;
    @Autowired
    private ScheduleJobMapper scheduleJobMapper;

    /**
     * 项目启动时，初始化定时器
     */
    @PostConstruct
    @LogAuto(CommonAppCode.FastDevApp)
    public void init() {
        List<ScheduleJob> scheduleJobList = scheduleJobMapper.selectList();

        for (ScheduleJob scheduleJob : scheduleJobList) {
            CronTrigger cronTrigger = ScheduleUtils.getCronTrigger(scheduler, scheduleJob.getJobId());
            //如果不存在，则创建
            if (cronTrigger == null) {
                ScheduleUtils.createScheduleJob(scheduler, scheduleJob);
            } else {
                ScheduleUtils.updateScheduleJob(scheduler, scheduleJob);
            }
        }
    }

    @Override
    @LogAuto(CommonAppCode.FastDevApp)
    public ScheduleJob queryObject(String jobId) throws Exception {
        return scheduleJobMapper.selectByPrimaryKey(jobId);
    }

    @Override
    @LogAuto(CommonAppCode.FastDevApp)
    public Map queryList(Integer pageNo, Integer pageSize) throws Exception {

        PageHelper.startPage(pageNo, pageSize);
        List<ScheduleJob> scheduleJobList = scheduleJobMapper.selectList();

        PageInfo<ScheduleJob> pageInfo = new PageInfo<ScheduleJob>(scheduleJobList);
        if (pageInfo.getList() == null || pageInfo.getList().size() == 0) {
            throw new CommonException(ReturnCodeModel.NO_DATE);
        }
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("scheduleJobList", scheduleJobList);
        map.put("totalPage", pageInfo.getPages());
        map.put("total", pageInfo.getTotal());

        return map;
    }

    @Override
    @Transactional
    @LogAuto(CommonAppCode.FastDevApp)
    public void save(ScheduleJob scheduleJob) throws Exception {
        scheduleJob.setJobId(UUIDGenerator.generate());
        scheduleJob.setCreateTime(new Date());
        scheduleJob.setStatus(ScheduleStatus.NORMAL.getValue());
        scheduleJobMapper.updateByPrimaryKeySelective(scheduleJob);
        ScheduleUtils.createScheduleJob(scheduler, scheduleJob);
    }

    @Override
    @Transactional
    @LogAuto(CommonAppCode.FastDevApp)
    public void update(ScheduleJob scheduleJob) throws Exception {
        ScheduleUtils.updateScheduleJob(scheduler, scheduleJob);
        scheduleJobMapper.updateByPrimaryKeySelective(scheduleJob);
    }

    @Override
    @Transactional
    @LogAuto(CommonAppCode.FastDevApp)
    public void deleteBatch(String[] jobIds) throws Exception {
        for (String jobId : jobIds) {
            ScheduleUtils.deleteScheduleJob(scheduler, jobId);
            //删除数据
            scheduleJobMapper.deleteByPrimaryKey(jobId);
        }

    }

    @Override
    @LogAuto(CommonAppCode.FastDevApp)
    public void updateBatch(String[] jobIds, int status) throws Exception {
        for (String jobId : jobIds) {
            ScheduleJob scheduleJob = new ScheduleJob();
            scheduleJob.setJobId(jobId);
            scheduleJob.setStatus(status);
            this.update(scheduleJob);
        }
    }

    @Override
    @Transactional
    @LogAuto(CommonAppCode.FastDevApp)
    public void run(String jobId) throws Exception {
        //for(String jobId : jobIds){

        ScheduleUtils.run(scheduler, this.queryObject(jobId));
        LogUtil.info(logger, CommonBizTypeCode.BIZ_JOB.getDesc() + "任务执行{0}", this.queryObject(jobId));
        //}
    }

    @Override
    @Transactional
    @LogAuto(CommonAppCode.FastDevApp)
    public void pause(String jobId) throws Exception {
        //for(String jobId : jobIds){
        ScheduleUtils.pauseJob(scheduler, jobId);
        //}
        //update(jobId, ScheduleStatus.PAUSE.getValue());
    }

    /**
     * 重新开始
     *
     * @param jobIds
     * @throws Exception
     */
    @Override
    @Transactional
    @LogAuto(CommonAppCode.FastDevApp)
    public void resume(String[] jobIds) throws Exception {
        for (String jobId : jobIds) {
            ScheduleUtils.resumeJob(scheduler, jobId);
        }
        updateBatch(jobIds, ScheduleStatus.NORMAL.getValue());
    }

}