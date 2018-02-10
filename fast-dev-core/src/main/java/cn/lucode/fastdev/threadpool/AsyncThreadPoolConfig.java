package cn.lucode.fastdev.threadpool;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import static cn.lucode.fastdev.threadpool.ThreadPoolCommons.ALIVETIME;
import static cn.lucode.fastdev.threadpool.ThreadPoolCommons.ASYNC_POOL;

/**
 * @author yunfeng.lu
 * @create 2018/2/9.
 */
@Configuration
@ConfigurationProperties(prefix = "threadPool.async")
public class AsyncThreadPoolConfig {

    private int coreSize;
    private int maxSize;
    private int capacity;

    @Bean(ASYNC_POOL)
    public ThreadPoolTaskExecutor taskExecutor() {
        if (maxSize == 0) {
            return null;
        }
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setThreadNamePrefix(ASYNC_POOL);
        executor.setMaxPoolSize(maxSize);
        executor.setCorePoolSize(coreSize);
        executor.setQueueCapacity(capacity);
        executor.setKeepAliveSeconds(ALIVETIME);

        // 设置拒绝策略  使用预定义的异常处理类
        executor.setRejectedExecutionHandler(new SimpleRejectedExecutionHandler());
        return executor;
    }

    public int getCoreSize() {
        return coreSize;
    }

    public void setCoreSize(int coreSize) {
        this.coreSize = coreSize;
    }

    public int getMaxSize() {
        return maxSize;
    }

    public void setMaxSize(int maxSize) {
        this.maxSize = maxSize;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }
}
