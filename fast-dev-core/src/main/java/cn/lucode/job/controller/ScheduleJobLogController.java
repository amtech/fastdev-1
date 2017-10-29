package cn.lucode.job.controller;


import cn.lucode.exception.CommonException;
import cn.lucode.job.service.ScheduleJobLogService;
import cn.lucode.common.CommonResponseModel;
import cn.lucode.common.CommonBizTypeCode;
import cn.lucode.util.BeanUtil;
import cn.lucode.util.LogUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * 定时任务日志
 *
 * @author yunfeng.lu
 * @create 2017/10/26.
 */
@RestController
@RequestMapping("/job/scheduleLog")
public class ScheduleJobLogController {
    private static final Logger logger = LoggerFactory.getLogger(ScheduleJobLogController.class);

    @Autowired
    private ScheduleJobLogService scheduleJobLogService;

    /**
     * 定时任务日志列表
     */
    @GetMapping("/get/list/{pageNo}/{pageSize}")
    public Object list(@PathVariable("pageNo") String pageNo,
                       @PathVariable("pageSize") String pageSize) {
        try {

            //查询列表数据
            return CommonResponseModel.success(scheduleJobLogService.
                    queryList(Integer.valueOf(pageNo), Integer.valueOf(pageSize)));
        } catch (CommonException ce) {
            LogUtil.error(logger, CommonBizTypeCode.BIZ_JOB.getDesc() + "日志分页查询", ce);
            return CommonResponseModel.facade(ce.getException_type());
        } catch (Exception e) {
            LogUtil.error(logger, CommonBizTypeCode.BIZ_JOB.getDesc() + "日志分页查询", e);
            return CommonResponseModel.fail();
        }
    }

    /**
     * 定时任务日志信息
     */
    @GetMapping("/get/info/{logId}")
    public Object info(@PathVariable("logId") String logId) {
        try {
            return CommonResponseModel.success(BeanUtil.bean2Map(scheduleJobLogService.queryObject(logId)));
        } catch (CommonException ce) {
            LogUtil.error(logger, CommonBizTypeCode.BIZ_JOB.getDesc() + "logId查询", ce);
            return CommonResponseModel.facade(ce.getException_type());
        } catch (Exception e) {
            LogUtil.error(logger, CommonBizTypeCode.BIZ_JOB.getDesc() + " logId查询{0}", logId, e);
            return CommonResponseModel.fail();
        }
    }
}
