package cn.lucode.fastdev.monitor.jvm;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * @author yunfeng.lu
 * @create 2018/1/2.
 */
public class JVMTotalBean {


    private static final JVMTotalBean instance = new JVMTotalBean();

    public static JVMTotalBean getInstance() {
        return instance;
    }

    private JVMGC jvmgc;
    private JVMMemory jvmMemory;
    private JVMThread jvmThread;


    public JVMTotalBean() {
        this.jvmgc = JVMGC.getInstance();
        this.jvmMemory = JVMMemory.getInstance();
        this.jvmThread = JVMThread.getInstance();

    }

    public long getyGCCount() {
        return jvmgc.getSpanYoungGCCollectionCount();
    }

    public long getyGCTime() {
        return jvmgc.getSpanYoungGCCollectionTime();
    }

    public long getfGCCount() {
        return jvmgc.getSpanFullGCCollectionCount();
    }

    public long getfGCTime() {
        return jvmgc.getSpanFullGCCollectionTime();
    }

    public long getHeapMaxMem() {
        return jvmMemory.getHeapMemoryMax();
    }

    public long getHeapUsedMem() {
        return jvmMemory.getHeapMemoryUsed();
    }

    public long getNonHeapMaxMem() {
        return jvmMemory.getNonHeapMemoryMax();
    }

    public long getNonHeapMaxUsed() {
        return jvmMemory.getNonHeapMemoryUsed();
    }

    public long getPermGenMax() {
        return jvmMemory.getPermGenMax();
    }

    public long getPermGenUsed() {
        return jvmMemory.getPermGenUsed();
    }

    public long getOldGenMax() {
        return jvmMemory.getOldGenMax();
    }

    public long getOldGenUsed() {
        return jvmMemory.getOldGenUsed();
    }

    public long getEdenSpaceMax() {
        return jvmMemory.getEdenSpaceMax();
    }

    public long getEdenSpaceUsed() {
        return jvmMemory.getEdenSpaceUsed();
    }

    public long getSurvivorMax() {
        return jvmMemory.getSurvivorMax();
    }

    public long getSurvivorUsed() {
        return jvmMemory.getSurvivorUsed();
    }

    public int getDaemonThreadCount() {
        return jvmThread.getDaemonThreadCount();
    }

    public int getThreadCount() {
        return jvmThread.getThreadCount();
    }

    public Long getTotalStartedThreadCount() {
        return jvmThread.getTotalStartedThreadCount();
    }

    public int getDeadLockedThreadCount() {
        return jvmThread.getDeadLockedThreadCount();
    }

    public double getProcessCpuTimeRate() {
        return jvmThread.getProcessCpuTimeRate().doubleValue();
    }

    public String getIp() throws UnknownHostException {
        return InetAddress.getLocalHost().getHostAddress();
    }

}
