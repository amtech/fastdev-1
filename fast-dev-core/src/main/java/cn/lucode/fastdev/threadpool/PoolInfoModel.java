package cn.lucode.fastdev.threadpool;

/**
 * @author yunfeng.lu
 * @create 2018/2/9.
 */
public class PoolInfoModel {
    private int coreSize;
    private int maxSize;
    private int capacity;

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

    @Override
    public String toString() {
        return "PoolInfoModel{" +
                "coreSize=" + coreSize +
                ", maxSize=" + maxSize +
                ", capacity=" + capacity +
                '}';
    }
}