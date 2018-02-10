package cn.lucode.fastdev.monitor.jvm;

import java.lang.management.ManagementFactory;
import java.lang.management.MemoryMXBean;
import java.lang.management.MemoryPoolMXBean;
import java.util.List;

/**
 * @author yunfeng.lu
 * @create 2018/1/2.
 */
public class JVMMemory   {

    private static final JVMMemory instance = new JVMMemory();

    public static JVMMemory getInstance() {
        return instance;
    }

    private MemoryMXBean memoryMXBean;

    private MemoryPoolMXBean permGenMxBean;
    private MemoryPoolMXBean oldGenMxBean;
    private MemoryPoolMXBean edenSpaceMxBean;
    private MemoryPoolMXBean pSSurvivorSpaceMxBean;

    private JVMMemory() {
        memoryMXBean = ManagementFactory.getMemoryMXBean();

        List<MemoryPoolMXBean> list = ManagementFactory.getMemoryPoolMXBeans();
        for (MemoryPoolMXBean item : list) {
            if ("CMS Perm Gen".equals(item.getName())
                    || "Perm Gen".equals(item.getName())
                    || "PS Perm Gen".equals(item.getName())
                    || "G1 Perm Gen".equals(item.getName())
                    ) {
                permGenMxBean = item;
            } else if ("CMS Old Gen".equals(item.getName())
                    || "Tenured Gen".equals(item.getName())
                    || "PS Old Gen".equals(item.getName())
                    || "G1 Old Gen".equals(item.getName())
                    ) {
                oldGenMxBean = item;
            } else if ("Par Eden Space".equals(item.getName())
                    || "Eden Space".equals(item.getName())
                    || "PS Eden Space".equals(item.getName())
                    || "G1 Eden".equals(item.getName())
                    ) {
                edenSpaceMxBean = item;
            } else if ("Par Survivor Space".equals(item.getName())
                    || "Survivor Space".equals(item.getName())
                    || "PS Survivor Space".equals(item.getName())
                    || "G1 Survivor".equals(item.getName())
                    ) {
                pSSurvivorSpaceMxBean = item;
            }
        }
    }

    // Memory Heap



    public long getHeapMemoryMax() {
        return memoryMXBean.getHeapMemoryUsage().getMax();
    }

    public long getHeapMemoryUsed() {
        return memoryMXBean.getHeapMemoryUsage().getUsed();
    }

    // Memory NonHeap

    public long getNonHeapMemoryMax() {
        return memoryMXBean.getNonHeapMemoryUsage().getMax();
    }

    public long getNonHeapMemoryUsed() {
        return memoryMXBean.getNonHeapMemoryUsage().getUsed();
    }

    // memory permGen


    public long getPermGenMax() {
        if (null == permGenMxBean) {
            return 0;
        }
        return permGenMxBean.getUsage().getMax();
    }

    public long getPermGenUsed() {
        if (null == permGenMxBean) {
            return 0;
        }
        return permGenMxBean.getUsage().getUsed();
    }

    // memory oldGen


    public long getOldGenMax() {
        if (null == oldGenMxBean) {
            return 0;
        }
        return oldGenMxBean.getUsage().getMax();
    }

    public long getOldGenUsed() {
        if (null == oldGenMxBean) {
            return 0;
        }
        return oldGenMxBean.getUsage().getUsed();
    }

    // memory edenSpace


    public long getEdenSpaceMax() {
        if (null == edenSpaceMxBean) {
            return 0;
        }
        return edenSpaceMxBean.getUsage().getMax();
    }

    public long getEdenSpaceUsed() {
        if (null == edenSpaceMxBean) {
            return 0;
        }
        return edenSpaceMxBean.getUsage().getUsed();
    }

    // memory survivor

    public long getSurvivorMax() {
        if (null == pSSurvivorSpaceMxBean) {
            return 0;
        }
        return pSSurvivorSpaceMxBean.getUsage().getMax();
    }

    public long getSurvivorUsed() {
        if (null == pSSurvivorSpaceMxBean) {
            return 0;
        }
        return pSSurvivorSpaceMxBean.getUsage().getUsed();
    }

}
