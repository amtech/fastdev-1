package cn.lucode.fastdev.alarm.model.service.impl;


import cn.lucode.fastdev.alarm.model.MessageModel;
import cn.lucode.fastdev.alarm.model.service.DingdingAlarmService;
import cn.lucode.fastdev.threadpool.DefaultPoolManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.jsoup.Jsoup;
import org.springframework.stereotype.Service;

import java.io.IOException;

/**
 * @author yunfeng.lu
 * @create 2018/3/18.
 */
@Service
public class DingdingAlarmServiceImpl implements DingdingAlarmService{
    private static Logger logger = LoggerFactory.getLogger(DingdingAlarmServiceImpl.class);

    //@Value("${DingDingRobotAPI}")
    private String DingDingRobotAPI = "https://oapi.dingtalk.com/robot/send?access_token=14d53c2ccd5e58af82d51d2b86ae8deb5fe95ad41c8966a767db87c1c92bf596";

    private final static String TYPE_TEXT = "text";

    /**
     * 默认线程池管理
     */
    @Autowired
    DefaultPoolManager defaultPoolManager;


    public void textMessage(MessageModel messageModel) {
        String content = "{\"msgtype\": \"" + TYPE_TEXT +
                "\", \"text\": {\"content\": \"" +
                messageModel.getRemark() + "\n==========\n" +
                "时间："+messageModel.getDate() + "\n==========\n" +
                "内容："+messageModel.getContent() + "\"}}";

        /**
         * 异步线程池  防止消息堵塞堵塞
         */
        defaultPoolManager.getAnsycTaskExecutor().execute(new Runnable() {
            @Override
            public void run() {
                try {
                    Jsoup.connect(DingDingRobotAPI).header("Content-Type", "application/json").
                            requestBody(content).ignoreContentType(true).post();
                } catch (IOException e) {
                    logger.error("钉钉群报警失败,请检查.....");
                }
            }
        });


    }


}
