package cn.lucode.job.task;

import cn.lucode.util.LogUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author yunfeng.lu
 * @create 2017/10/29.
 */
@Component("testTask")
public class TestTask {
    private Logger logger = LoggerFactory.getLogger(TestTask.class);


    public void test(String params) {
        LogUtil.info(logger,"我是带参数的test方法，正在被执行，参数为{0}：" + params);

        try {
            Thread.sleep(1000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    public void test2() {
        LogUtil.info(logger,"我是不带参数的test2方法，正在被执行，参数为{0}：");
        //logger.info("我是不带参数的test2方法，正在被执行");
    }

}

