package cn.lucode.fastdev.monitor.jvm;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @author yunfeng.lu
 * @create 2018/1/2.
 */
@Configuration
@ConfigurationProperties(prefix = "jvm.info")
public class JvmRuleConfig {
    private  String nodeName;
    private  String nodeGroup;
    private  double oldPercent;
    private  double heapPercent;
    private  double  nonheapPercent;
    private  double  recoveryOldPercent;

    public String getNodeName() {
        return nodeName;
    }

    public void setNodeName(String nodeName) {
        this.nodeName = nodeName;
    }

    public String getNodeGroup() {
        return nodeGroup;
    }

    public void setNodeGroup(String nodeGroup) {
        this.nodeGroup = nodeGroup;
    }

    public double getOldPercent() {
        return oldPercent;
    }

    public void setOldPercent(double oldPercent) {
        this.oldPercent = oldPercent;
    }

    public double getHeapPercent() {
        return heapPercent;
    }

    public void setHeapPercent(double heapPercent) {
        this.heapPercent = heapPercent;
    }

    public double getNonheapPercent() {
        return nonheapPercent;
    }

    public void setNonheapPercent(double nonheapPercent) {
        this.nonheapPercent = nonheapPercent;
    }

    public double getRecoveryOldPercent() {
        return recoveryOldPercent;
    }

    public void setRecoveryOldPercent(double recoveryOldPercent) {
        this.recoveryOldPercent = recoveryOldPercent;
    }
}
