package cn.lucode.job.controller;


import cn.lucode.common.CommonBizTypeCode;
import cn.lucode.common.CommonResponseModel;
import cn.lucode.exception.CommonException;
import cn.lucode.job.service.ScheduleJobService;
import cn.lucode.util.LogUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


/**
 * 定时任务
 */
@RestController
@RequestMapping("/job/schedule")
public class ScheduleJobController {
    private static final Logger logger = LoggerFactory.getLogger(ScheduleJobController.class);

    @Autowired
    private ScheduleJobService scheduleJobService;

    /**
     * 定时任务列表
     */
    @GetMapping("/get/list/{pageNo}/{pageSize}")
    public Object list(@PathVariable("pageNo") String pageNo,
                       @PathVariable("pageSize") String pageSize) {
        try {
            //查询列表数据
            return CommonResponseModel.success(scheduleJobService.
                    queryList(Integer.valueOf(pageNo), Integer.valueOf(pageSize)));
        } catch (CommonException ce) {
            LogUtil.error(logger, CommonBizTypeCode.BIZ_JOB.getDesc() + "分页查询", ce);
            return CommonResponseModel.facade(ce.getException_type());
        } catch (Exception e) {
            LogUtil.error(logger, CommonBizTypeCode.BIZ_JOB.getDesc() + "分页查询", e);
            return CommonResponseModel.fail();
        }
    }

//	/**
//	 * 定时任务信息
//	 */
//	@RequestMapping("/info/{jobId}")
//	@RequiresPermissions("sys:schedule:info")
//	public R info(@PathVariable("jobId") Long jobId){
//		ScheduleJobEntity schedule = scheduleJobService.queryObject(jobId);
//
//		return R.ok().put("schedule", schedule);
//	}
//
//	/**
//	 * 保存定时任务
//	 */
//	@SysLog("保存定时任务")
//	@RequestMapping("/save")
//	@RequiresPermissions("sys:schedule:save")
//	public R save(@RequestBody ScheduleJobEntity scheduleJob){
//		ValidatorUtils.validateEntity(scheduleJob);
//
//		scheduleJobService.save(scheduleJob);
//
//		return R.ok();
//	}
//
//	/**
//	 * 修改定时任务
//	 */
//	@SysLog("修改定时任务")
//	@RequestMapping("/update")
//	@RequiresPermissions("sys:schedule:update")
//	public R update(@RequestBody ScheduleJobEntity scheduleJob){
//		ValidatorUtils.validateEntity(scheduleJob);
//
//		scheduleJobService.update(scheduleJob);
//
//		return R.ok();
//	}
//
//	/**
//	 * 删除定时任务
//	 */
//	@SysLog("删除定时任务")
//	@RequestMapping("/delete")
//	@RequiresPermissions("sys:schedule:delete")
//	public R delete(@RequestBody Long[] jobIds){
//		scheduleJobService.deleteBatch(jobIds);
//
//		return R.ok();
//	}
//
	/**
	 * 立即执行任务
	 */
	@GetMapping("/run/{jobId}")
	public Object run(@PathVariable("jobId") String jobId){
        try {
            scheduleJobService.run(jobId);
            return CommonResponseModel.success();
        } catch (Exception e) {
            LogUtil.error(logger, CommonBizTypeCode.BIZ_JOB.getDesc() + "任务执行失败", e);
            return CommonResponseModel.fail();
        }

    }
//
//	/**
//	 * 暂停定时任务
//	 */
//	@SysLog("暂停定时任务")
//	@RequestMapping("/pause")
//	@RequiresPermissions("sys:schedule:pause")
//	public R pause(@RequestBody Long[] jobIds){
//		scheduleJobService.pause(jobIds);
//
//		return R.ok();
//	}
//
//	/**
//	 * 恢复定时任务
//	 */
//	@SysLog("恢复定时任务")
//	@RequestMapping("/resume")
//	@RequiresPermissions("sys:schedule:resume")
//	public R resume(@RequestBody Long[] jobIds){
//		scheduleJobService.resume(jobIds);
//
//		return R.ok();
//	}

}
