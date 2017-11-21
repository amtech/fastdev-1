package cn.lucode.fastdev.job.service.impl;


import cn.lucode.fastdev.annotation.CommonAppCode;
import cn.lucode.fastdev.annotation.LogAuto;
import cn.lucode.fastdev.common.ReturnCodeModel;
import cn.lucode.fastdev.exception.CommonException;
import cn.lucode.fastdev.job.dao.ScheduleJobLogMapper;
import cn.lucode.fastdev.job.model.ScheduleJobLog;
import cn.lucode.fastdev.job.service.ScheduleJobLogService;
import cn.lucode.fastdev.util.UUIDGenerator;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * @author yunfeng.lu
 * @create 2017/10/24.
 */
@Service("scheduleJobLogService")
public class ScheduleJobLogServiceImpl implements ScheduleJobLogService {

    @Autowired

    private ScheduleJobLogMapper scheduleJobLogMapper;

    @Override
    @LogAuto(CommonAppCode.FastDevApp)
    public ScheduleJobLog queryObject(String jobId) throws Exception {
        return scheduleJobLogMapper.selectByPrimaryKey(jobId);
    }

    @Override
    @LogAuto(CommonAppCode.FastDevApp)
    public Map queryList(Integer pageNo, Integer pageSize) throws Exception {
        PageHelper.startPage(pageNo, pageSize);
        List<ScheduleJobLog> scheduleJobLogList = scheduleJobLogMapper.selectList();
        PageInfo<ScheduleJobLog> pageInfo = new PageInfo<ScheduleJobLog>(scheduleJobLogList);
        if (pageInfo.getList() == null || pageInfo.getList().size() == 0) {
            throw new CommonException(ReturnCodeModel.NO_DATE);
        }
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("scheduleJobLogList", scheduleJobLogList);
        map.put("totalPage", pageInfo.getPages());
        map.put("total", pageInfo.getTotal());
        return map;
    }

    @Override
    @LogAuto(CommonAppCode.FastDevApp)
    public int deleteObject(String jobId) throws Exception {
        return scheduleJobLogMapper.deleteByPrimaryKey(jobId);
    }

    @Override
    @LogAuto(CommonAppCode.FastDevApp)
    public void save(ScheduleJobLog log) throws Exception {
        log.setLogId(UUIDGenerator.generate());
        scheduleJobLogMapper.insertSelective(log);
    }

}
