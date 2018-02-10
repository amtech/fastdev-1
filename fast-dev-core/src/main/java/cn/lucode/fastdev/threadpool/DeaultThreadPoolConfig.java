package cn.lucode.fastdev.threadpool;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * @author yunfeng.lu
 * @create 2018/2/9.
 */
@Component
@ConfigurationProperties("threadPool")
public class DeaultThreadPoolConfig {
    /**
     * 这里读取配置文件示例 注意 test 是线程池的名字
     * threadPool.maxAllowThreadSize=1000
     * threadPool.poolInfo.test.coreSize=20
     * threadPool.poolInfo.test.maxSize=20
     * threadPool.poolInfo.test.capacity=20
     */
    private int maxAllowThreadSize;

    private Map<String,PoolInfoModel> poolInfo=new HashMap();

    public int getMaxAllowThreadSize() {
        return maxAllowThreadSize;
    }

    public void setMaxAllowThreadSize(int maxAllowThreadSize) {
        this.maxAllowThreadSize = maxAllowThreadSize;
    }

    public Map<String, PoolInfoModel> getPoolInfo() {
        return poolInfo;
    }

    public void setPoolInfo(Map<String, PoolInfoModel> poolInfo) {
        this.poolInfo = poolInfo;
    }


}
