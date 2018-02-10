package cn.lucode.fastdev.threadpool;

import javax.annotation.PostConstruct;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Component;

import static cn.lucode.fastdev.threadpool.ThreadPoolCommons.ASYNC_POOL;
import static cn.lucode.fastdev.threadpool.ThreadPoolCommons.CREATE_THE_ERR;
import static cn.lucode.fastdev.threadpool.ThreadPoolCommons.LOG_THREAD_MAXERR;

/**
 * @author yunfeng.lu
 * @create 2018/2/9.
 */
@Component
public class DefaultPoolManager extends ParentPoolManager {
    private final static Logger logger = LoggerFactory
            .getLogger(DefaultPoolManager.class);
    @Autowired
    private DeaultThreadPoolConfig deaultThreadPoolConfig;
    @Autowired
    @Qualifier(value = ASYNC_POOL)
    private ThreadPoolTaskExecutor ansycTaskExecutor;

    @PostConstruct
    private void start() throws Exception {
        Map<String, PoolInfoModel> threadPoolInfo = deaultThreadPoolConfig.getPoolInfo();
        // 系统启动时 把所有的线程池完成初始化
        for (Map.Entry<String, PoolInfoModel> entry : threadPoolInfo.entrySet()) {
            //  key 线程池名字
            String key = entry.getKey();
            PoolInfoModel values = entry.getValue();
            this.createPool(key,
                    values.getCoreSize(),
                    values.getMaxSize(),
                    values.getCapacity());
        }
        // 初始化异步线池
        if (ansycTaskExecutor != null) {
            registerPool(ASYNC_POOL, ansycTaskExecutor);
            // 系统需要保存当前 最大 线程数
            plusMax(ansycTaskExecutor.getMaxPoolSize());
        }
    }

    /**
     * 获取异步线程池 
     * @return
     */
    public ThreadPoolTaskExecutor getAnsycTaskExecutor() {
        if (!poolContainer.containsKey(ASYNC_POOL)) {
            // 在获取不到的情况下重新创建新的 异步线程池，保证服务的高可用
            registerPool(ASYNC_POOL, ansycTaskExecutor);
            // 系统需要保存当前 最大 线程数
            plusMax(ansycTaskExecutor.getMaxPoolSize());
            
        }
        return poolContainer.get(ASYNC_POOL);
    }


    /**
     * 新创建线程池 需要检查 最大线程数
     * @param maxSize
     */
    @Override
    protected void checkMaxSize(int maxSize) {
        int temp = getNowMaxThreadSize()+maxSize;
        if (deaultThreadPoolConfig.getMaxAllowThreadSize() < temp) {
            logger.error(LOG_THREAD_MAXERR, getNowMaxThreadSize(), maxSize);
            throw new SimpleThreadException(CREATE_THE_ERR);
        }
    }
}
