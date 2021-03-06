package cn.lucode.fastdev.aop;

import cn.lucode.fastdev.annotation.LogAuto;
import cn.lucode.fastdev.exception.CommonException;
import cn.lucode.fastdev.util.IPUtils;
import cn.lucode.fastdev.util.LogUtil;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Arrays;
import java.util.Collection;

/**
 * Created by yunfeng.lu on 2017/9/18.
 */
@Component
@Aspect
public class LogAop {
    private final static Logger logger = LoggerFactory.getLogger(LogAop.class);

    private final static Logger monitor_logger = LoggerFactory.getLogger("LogMonitor");

    private final static String DEF_PROCESS_SUCCESS_RESULT = "T";

    private final static String DEF_PROCESS_SLOW_RESULT = "S";

    private final static String DEF_PROCESS_ERROR_RESULT = "E";

    private final static String DEF_SUCCESS_CODE = "9999";

    private final static String DEF_ERROR_CODE = "0000";

    private static String ip_address = null;

    static {
        try {
            ip_address = InetAddress.getLocalHost().getHostAddress();
        } catch (UnknownHostException e) {

            logger.error("获取本地IP信息异常 请检查..");
        }
    }

    @Around("@annotation(logAuto)")
    public Object aroud(ProceedingJoinPoint pjp, LogAuto logAuto) throws Throwable {
        String requestIp = null;
        try {
            HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
            requestIp = IPUtils.getIpAddress(request);
            //logger.info("请求 ip {}", requestIp);
        } catch (Exception ex) {
            logger.error("请求 ip 获取失败");
        }

        Object result = null;
        LogAuto.ParamPrintOption printOption = logAuto.outParamPrint();
        long current_time = System.currentTimeMillis();
        String inParam = Arrays.toString(pjp.getArgs());
        String className = pjp.getTarget().getClass().getName();
        String methodName = pjp.getSignature().getName();
        String relateId = String.valueOf(new StringBuilder(String.valueOf(current_time)).append(className).append(methodName).toString().hashCode());
        String processResult = DEF_PROCESS_SUCCESS_RESULT;
        String errorCode = DEF_SUCCESS_CODE;
        try {

            result = pjp.proceed();

            if (LogAuto.ParamPrintOption.UNCONFIG.equals(printOption)) {
                if (result != null && result instanceof Collection) {
                    printOption = LogAuto.ParamPrintOption.IGNORE;
                } else if (result != null && result.getClass().isArray()) {
                    printOption = LogAuto.ParamPrintOption.IGNORE;
                } else {
                    printOption = LogAuto.ParamPrintOption.PRINT;
                }
            }

            long lastTime = System.currentTimeMillis() - current_time;

            if (lastTime >= 2000) {
                processResult = DEF_PROCESS_SLOW_RESULT;
                LogUtil.info(logger, "请求ip：{0}——{1}.{2}:{3}，该方法执行较慢请检查... 入参: {4} \t出参: {5} 响应时间:{6}毫秒",requestIp, className,
                        methodName, relateId, inParam,
                        printOption.equals(LogAuto.ParamPrintOption.PRINT) ? result : "", lastTime);
            } else {
                //ip 类名.方法名.请求id 入参 出参
                LogUtil.info(logger, "请求ip：{0}——{1}.{2}:{3} 入参: {4} \t出参: {5} 响应时间:{6}毫秒", requestIp,className,
                        methodName, relateId,
                        inParam,
                        printOption.equals(LogAuto.ParamPrintOption.PRINT) ? result : "参数太长不打印了", lastTime);
            }

            //{请求ip}~{类名}~{方法名}~{关联ID}~{设备ID}~{IP地址}~{错误码}~{错误消息}~{执行结果}~{执行时间}
            monitor_logger.info("{}~{}~{}~{}~{}~{}~{}~{}~{}~{}", requestIp,className, methodName, relateId, "uid/设备ID",ip_address, errorCode,"执行成功",processResult,lastTime);

        } catch (CommonException ex) {
            processResult = DEF_PROCESS_ERROR_RESULT;

            LogUtil.error(logger, ex, "{0}.{1}:{2} 执行报错,入参: {3}", pjp.getTarget().getClass().getName(),
                    pjp.getSignature().getName(), relateId, Arrays.toString(pjp.getArgs()));

            monitor_logger.info("{}~{}~{}~{}~{}~{}~{}~{}~{}~{}", requestIp,className, methodName, relateId, "uid/设备ID",ip_address, ex.getErrorCode(),ex.getMessage(),
                    processResult,System.currentTimeMillis() - current_time);
            throw ex;

        } catch (Throwable throwable) {
            errorCode = DEF_ERROR_CODE;

            processResult = DEF_PROCESS_ERROR_RESULT;

            LogUtil.error(logger, throwable, "{0}.{1}:{2} 执行报错,入参: {3}", pjp.getTarget().getClass().getName(),
                    pjp.getSignature().getName(), relateId, Arrays.toString(pjp.getArgs()));

            monitor_logger.info("{}~{}~{}~{}~{}~{}~{}~{}~{}~{}", requestIp,className, methodName, relateId, "uid/设备ID",ip_address,errorCode,throwable.getMessage(),
                    processResult,System.currentTimeMillis() - current_time);

            throw throwable;
        }

        return result;

    }
}