package cn.lucode.fastdev.monitor.service;

import cn.lucode.fastdev.monitor.jvm.JVMFlagEnum;
import cn.lucode.fastdev.monitor.jvm.JVMTotalBean;
import cn.lucode.fastdev.monitor.jvm.JvmRuleConfig;
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
public interface  JvmInfoService {

     Map<String,String> getJvmInfo();

    /**
     *jvm 堆内存指标百分比警告
     */
    public  StringBuilder heapWarn(StringBuilder stringBuilder,long heapMaxMem ,long  heapUsedMem);

    /**
     * jvm老年代百分比警告
     */
    public  StringBuilder oldGenWarn(StringBuilder stringBuilder,long oldGenUsed ,long  oldGenMax);

    /**
     * jvm老年代回收百分比警告
     */
    public  StringBuilder recoveryOldWarn(StringBuilder stringBuilder,long oldGenUsed);

    /**
     * 非堆百分比警报
     * */
    public  StringBuilder nonHeapWarn(StringBuilder stringBuilder,long nonHeapUsedMem ,long  nonHeapMaxMem);


}
