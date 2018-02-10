package cn.lucode.fastdev.threadpool;

import cn.hutool.core.lang.Assert;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.RejectedExecutionHandler;

import static cn.lucode.fastdev.threadpool.ThreadPoolCommons.ALIVETIME;
import static cn.lucode.fastdev.threadpool.ThreadPoolCommons.ASSERT_POOL_NULL;
import static cn.lucode.fastdev.threadpool.ThreadPoolCommons.ASSERT_POOL_SN;


/**
 * @author yunfeng.lu
 * @create 2018/2/9.
 */
public abstract class ParentPoolManager {

    private final static Logger logger = LoggerFactory.getLogger(ParentPoolManager.class);

    private static Map<String, ThreadPoolTaskExecutor> poolContainer = new ConcurrentHashMap();

    private static int nowMaxThreadSize = 0;

    public ThreadPoolTaskExecutor createPool(String name, int corePoolSize, int maxPoolSize, int queueCapacity) {
        return registerPool(name, initPool(name, corePoolSize, maxPoolSize, queueCapacity));
    }

    /**
     * 注册线程池
     * @param name
     * @param pool
     * @return
     */
    synchronized ThreadPoolTaskExecutor registerPool(String name, ThreadPoolTaskExecutor pool) {
        if (poolContainer.containsKey(name)) {
            //已存在同名线程池
            logger.error(ASSERT_POOL_SN);
            throw new SimpleThreadException(ASSERT_POOL_SN);
        } else {
            poolContainer.put(name, pool);
        }
        return pool;
    }


    /**
     * 从管理器中获取线程池，如果不存在则执行addPoolToContainer，如果方法没执行则添加一个默认线程池并返回
     * @param poolName
     * @return
     */
    public ThreadPoolTaskExecutor getPool(String poolName) {
        Assert.notNull(poolName, ASSERT_POOL_NULL);
        if (poolContainer.containsKey(poolName)) {
            return poolContainer.get(poolName);
        }
        return null;
    }

    /**
     *
     * @param poolName  线程池名字
     * @param isShutDownNow 是否 立刻 关掉
     */
    public void shutDownPool(String poolName, boolean isShutDownNow) {
        Assert.notNull(poolName, ASSERT_POOL_NULL);
        if (poolContainer.containsKey(poolName)) {
            ThreadPoolTaskExecutor threadPoolTaskExecutor = poolContainer.remove(poolName);
            threadPoolTaskExecutor.setWaitForTasksToCompleteOnShutdown(isShutDownNow);
            minusMax(threadPoolTaskExecutor.getMaxPoolSize());
            threadPoolTaskExecutor.shutdown();
        }
    }

    public void shutDownPool(String poolName) {
        this.shutDownPool(poolName, true);
    }


    protected ThreadPoolTaskExecutor initPool(String poolName, int corePoolSize,
                                              int maxPoolSize, int queueCapacity) {
        checkMaxSize(maxPoolSize);
        RejectedExecutionHandler rejectedHandler = new SimpleRejectedExecutionHandler();
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(corePoolSize);
        executor.setMaxPoolSize(maxPoolSize);
        executor.setQueueCapacity(queueCapacity);
        executor.setRejectedExecutionHandler(rejectedHandler);
        executor.setKeepAliveSeconds(ALIVETIME);
        executor.setBeanName(poolName);
        executor.initialize();
        plusMax(maxPoolSize);
        return executor;
    }


    protected static int getNowMaxThreadSize() {
        return nowMaxThreadSize;
    }

    protected static void setNowMaxThreadSize(int nowMaxThreadSize) {
        ParentPoolManager.nowMaxThreadSize = nowMaxThreadSize;
    }

    protected void minusMax(int size) {
        nowMaxThreadSize -= size;

    }

    protected void plusMax(int size) {
        nowMaxThreadSize += size;

    }

    protected abstract void checkMaxSize(int maxSize);

    public static Map getPoolContainer() {
        return poolContainer;
    }


    public static Map<String,Object> getAllPoolInfo(){

        Map<String,Object> poolInfo=new HashMap<>();
        for (Map.Entry<String, ThreadPoolTaskExecutor> entry : poolContainer.entrySet()) {
            //  key 线程池名字
            String key = entry.getKey();
            ThreadPoolTaskExecutor value = entry.getValue();
            Map<String,String> info=new HashMap<>();
            info.put("ThreadNamePrefix",value.getThreadNamePrefix().replace("-",""));
            info.put("ActiveCount",value.getActiveCount()+"");
            info.put("CorePoolSize",value.getCorePoolSize()+"");
            info.put("KeepAliveSeconds",value.getKeepAliveSeconds()+"");
            info.put("MaxPoolSize",value.getMaxPoolSize()+"");
            info.put("ThreadPriority",value.getThreadPriority()+"");
            poolInfo.put(key,info);
        }
        return poolInfo;
    }

}

