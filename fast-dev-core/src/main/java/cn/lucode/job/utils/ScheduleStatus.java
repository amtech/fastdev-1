package cn.lucode.job.utils;

/**
 * @author yunfeng.lu
 * @create 2017/10/26.
 */
public enum ScheduleStatus {
    /**
     * 正常
     */
    NORMAL(0),
    /**
     * 暂停
     */
    PAUSE(1);

    private int value;

    ScheduleStatus(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
