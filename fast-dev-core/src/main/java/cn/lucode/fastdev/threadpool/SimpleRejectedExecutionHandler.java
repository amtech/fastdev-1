package cn.lucode.fastdev.threadpool;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * @author yunfeng.lu
 * @create 2018/2/9.
 */
public class SimpleRejectedExecutionHandler implements RejectedExecutionHandler {
    private static Logger log = LoggerFactory.getLogger(SimpleRejectedExecutionHandler.class);


    @Override
    public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
        //调用监控服务？？？？
        log.error(r.toString() + " : 线程池满了，一大波任务被拒绝....");


    }
}
