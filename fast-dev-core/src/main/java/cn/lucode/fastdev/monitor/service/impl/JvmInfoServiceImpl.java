package cn.lucode.fastdev.monitor.service.impl;

import cn.lucode.fastdev.monitor.jvm.JVMFlagEnum;
import cn.lucode.fastdev.monitor.jvm.JVMTotalBean;
import cn.lucode.fastdev.monitor.jvm.JvmRuleConfig;
import cn.lucode.fastdev.monitor.service.JvmInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * @author yunfeng.lu
 * @create 2018/1/2.
 */
@Service
public class JvmInfoServiceImpl implements JvmInfoService {
    private  static Map<String,Long> lastInfo=new HashMap<String,Long>();
    private  static final AtomicBoolean start = new AtomicBoolean(false);

    SimpleDateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    @Autowired
    private JvmRuleConfig jvmRuleConfig;


    public Map<String,String> getJvmInfo(){
        JVMTotalBean jvmTotalBean=JVMTotalBean.getInstance();
        Map<String,String> result=new HashMap<String,String>();
        try {
            if (start.compareAndSet(false, true)) {
                long heapMaxMem = jvmTotalBean.getHeapMaxMem();
                long heapUsedMem = jvmTotalBean.getHeapUsedMem();
                long nonHeapMaxMem = jvmTotalBean.getNonHeapMaxMem();
                long nonHeapUsedMem = jvmTotalBean.getNonHeapMaxUsed();
                long oldGenMax = jvmTotalBean.getOldGenMax();
                long oldGenUsed = jvmTotalBean.getOldGenUsed();
                long fGCCount = jvmTotalBean.getfGCCount();


                result.put("nodeName", jvmRuleConfig.getNodeName());
                result.put("nodeGroup", jvmRuleConfig.getNodeGroup());
                result.put("nodeIp", jvmTotalBean.getIp());
                result.put("yGCCount", jvmTotalBean.getyGCCount() + "");//
                result.put("yGCTime", jvmTotalBean.getyGCTime() + "");
                result.put("fGCCount", fGCCount + "");
                result.put("fGCTime", jvmTotalBean.getfGCTime() + "");
                result.put("heapMaxMem", heapMaxMem + "");
                result.put("heapUsedMem", heapUsedMem + "");
                result.put("nonHeapMaxMem", nonHeapMaxMem + "");
                result.put("nonHeapUsedMem", nonHeapUsedMem + "");
                result.put("permGenMax", jvmTotalBean.getOldGenMax() + "");
                result.put("permGenUsed", jvmTotalBean.getPermGenUsed() + "");
                result.put("oldGenMax", oldGenMax + "");
                result.put("oldGenUsed", oldGenUsed + "");
                result.put("edenSpaceMax", jvmTotalBean.getEdenSpaceMax() + "");
                result.put("edenSpaceUsed", jvmTotalBean.getEdenSpaceUsed() + "");
                result.put("survivorMax", jvmTotalBean.getSurvivorMax() + "");
                result.put("survivorUsed", jvmTotalBean.getSurvivorUsed() + "");
                result.put("daemonThreadCount", jvmTotalBean.getDaemonThreadCount() + "");
                result.put("threadCount", jvmTotalBean.getThreadCount() + "");
                result.put("totalStartedThreadCount", jvmTotalBean.getTotalStartedThreadCount() + "");
                result.put("deadLockedThreadCount", jvmTotalBean.getDeadLockedThreadCount() + "");
                result.put("processCpuTimeRate", jvmTotalBean.getProcessCpuTimeRate() + "");
                result.put("timeStamp", System.currentTimeMillis()+"");
                result.put("dateformat",dateformat.format(System.currentTimeMillis()));

                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder = nonHeapWarn(oldGenWarn(heapWarn(stringBuilder, heapMaxMem, heapUsedMem), oldGenUsed, oldGenMax), nonHeapUsedMem, nonHeapMaxMem);
                if (fGCCount > 0) {
                    stringBuilder = recoveryOldWarn(stringBuilder, oldGenUsed);
                }
                stringBuilder.append(JVMFlagEnum.HEALTH.getCode());
                result.put("operation_type", stringBuilder.toString());


                lastInfo.put("oldGenUsed", heapUsedMem);

            }
            start.set(false);
        }catch(Exception e){

        }

        return result;
    }

    /**
     *jvm 堆内存指标百分比警告
     */
    public  StringBuilder heapWarn(StringBuilder stringBuilder,long heapMaxMem ,long  heapUsedMem){
        if(((double)heapUsedMem/(double) heapMaxMem)>jvmRuleConfig.getHeapPercent()){
            stringBuilder.append(JVMFlagEnum.HEAP_OVER.getCode());
        }
        return  stringBuilder;
    }

    /**
     * jvm老年代百分比警告
     */
    public  StringBuilder oldGenWarn(StringBuilder stringBuilder,long oldGenUsed ,long  oldGenMax){
        if(((double)oldGenUsed/(double) oldGenMax)>jvmRuleConfig.getOldPercent()){
            stringBuilder.append(JVMFlagEnum.OLGGEN_OVER.getCode());
        }
        return  stringBuilder;
    }

    /**
     * jvm老年代回收百分比警告
     */
    public  StringBuilder recoveryOldWarn(StringBuilder stringBuilder,long oldGenUsed){
        if(((double)(lastInfo.get("oldGenUsed")-oldGenUsed)/(double)lastInfo.get("oldGenUsed"))<jvmRuleConfig.getRecoveryOldPercent()){
            stringBuilder.append(JVMFlagEnum.FGC_RATE.getCode());
        }
        return  stringBuilder;
    }

    /**
     * 非堆百分比警报
     * */

    public  StringBuilder nonHeapWarn(StringBuilder stringBuilder,long nonHeapUsedMem ,long  nonHeapMaxMem){
        if(((double)nonHeapUsedMem/(double)nonHeapMaxMem)>jvmRuleConfig.getNonheapPercent()){
            stringBuilder.append(JVMFlagEnum.NONHEAP_OVER.getCode());
        }
        return  stringBuilder;
    }


}
