package cn.lucode.fastdev.job.task;

import cn.lucode.fastdev.alarm.model.MessageModel;
import cn.lucode.fastdev.alarm.model.service.DingdingAlarmService;
import cn.lucode.fastdev.monitor.service.JvmInfoService;
import cn.lucode.fastdev.util.DateFormatUtil;
import cn.lucode.fastdev.util.LogUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @author yunfeng.lu
 * @create 2017/10/29.
 */
@Component("testTask")
public class TestTask {
    private Logger logger = LoggerFactory.getLogger(TestTask.class);


    @Autowired
    private JvmInfoService jvmInfoService;
    @Autowired
    private DingdingAlarmService dingdingAlarmService;


    /**
     * jvm 系统参数报警
     */
    public void test() {
        MessageModel messageModel=new MessageModel();
        messageModel.setRemark("jvm 参数监控巡检");
        messageModel.setDate(DateFormatUtil.format02(new Date()));
        messageModel.setContent(jvmInfoService.getJvmInfo().toString());
        dingdingAlarmService.textMessage(messageModel);
    }

    public void test2() {
        LogUtil.info(logger,"我是不带参数的test2方法，正在被执行，参数为{0}：");
        //logger.info("我是不带参数的test2方法，正在被执行");
    }

}

