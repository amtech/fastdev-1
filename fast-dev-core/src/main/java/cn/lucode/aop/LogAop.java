package cn.lucode.aop;

import cn.lucode.annotation.LogAuto;
import cn.lucode.exception.CommonException;
import cn.lucode.util.LogUtil;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Arrays;
/**
 * Created by yunfeng.lu on 2017/9/18.
 */
@Component
@Aspect
public class LogAop {
    private final static Logger logger = LoggerFactory.getLogger(LogAop.class);

    private final static Logger monitor_logger = LoggerFactory.getLogger("LogMonitor");

    private static String ip_address = null;

    static
    {
        try
        {
            ip_address = InetAddress.getLocalHost().getHostAddress();
        }
        catch (UnknownHostException e) {

            logger.error("获取本地IP信息异常 请检查..");
        }
    }

    @Around("@annotation(logAuto)")
    public Object aroud(ProceedingJoinPoint pjp, LogAuto logAuto) throws Throwable {

        Object result = null;
        long current_time = System.currentTimeMillis();
        String inParam = Arrays.toString(pjp.getArgs());
        String className = pjp.getTarget().getClass().getName();
        String methodName = pjp.getSignature().getName();
        String relateId = String.valueOf(new StringBuilder(String.valueOf(current_time)).append(className).append(methodName).toString().hashCode());
        try {
            result = pjp.proceed();

            long lastTime = System.currentTimeMillis() - current_time;

            if(lastTime >= 2000)
            {
                LogUtil.info(logger, "{0}.{1}:{2}该方法执行较慢请检查  入参: {3}, \t出参: {4} 响应时间:{5}毫秒",className,
                        methodName, relateId, inParam, result,lastTime);
            }
            else
            {
                LogUtil.info(logger, "{0}.{1}:{2} 入参: {3} \t出参: {4} 响应时间:{5}毫秒",className,
                        methodName,relateId, inParam, result,lastTime);
            }

            monitor_logger.info("{}|{}|{}|{}|{}|{}|{}|{}|{}",className,methodName,relateId,"uid/设备ID",logAuto.value(),ip_address,inParam,"T",lastTime);

        }
        catch(CommonException ex)
        {
            LogUtil.error(logger, ex, "{0}.{1}:{2} 执行报错,入参: {3}", pjp.getTarget().getClass().getName(),
                    pjp.getSignature().getName(),relateId,Arrays.toString(pjp.getArgs()));

            monitor_logger.info("{}|{}|{}|{}|{}|{}|{}|{}|{}",className,methodName,relateId,"uid/设备ID",logAuto.value(),ip_address,inParam,"T",0);

            throw ex;
        }
        catch (Throwable throwable) {
            LogUtil.error(logger, throwable, "{0}.{1}:{2} 执行报错,入参: {3}", pjp.getTarget().getClass().getName(),
                    pjp.getSignature().getName(),relateId,Arrays.toString(pjp.getArgs()));

            monitor_logger.info("{}|{}|{}|{}|{}|{}|{}|{}|{}",className,methodName,relateId,"uid/设备ID",logAuto.value(),ip_address,inParam,"F",0);

            throw throwable;
        }

        return result;

    }
}