//package cn.lucode.fastdev.aop;
//
//import cn.lucode.fastdev.annotation.GetCache;
//import cn.lucode.fastdev.redis.service.IRedisOperation;
//import org.aspectj.lang.JoinPoint;
//import org.aspectj.lang.annotation.Aspect;
//import org.aspectj.lang.annotation.Before;
//import org.aspectj.lang.annotation.Pointcut;
//import org.aspectj.lang.reflect.MethodSignature;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//
//import java.lang.reflect.Method;
//
///**
// * @author yunfeng.lu
// * @create 2017/11/6.
// */
//@Aspect
//public class GetCacheAop {
//    private final static Logger logger = LoggerFactory.getLogger(LogAop.class);
//
//    @Autowired
//    IRedisOperation redisOperation;
//
//    ThreadLocal<Long> time=new ThreadLocal<Long>();
//    ThreadLocal<String> tag=new ThreadLocal<String>();
//
//    @Pointcut("@annotation(cn.lucode.fastdev.annotation.GetCache)")
//    public void getCache(){
//        logger.info("我是一个切入点");
//    }
//
//    /**
//     * 在所有标注@getCache的地方切入
//     * @param joinPoint
//     */
//    @Before("getCache()")
//    public void beforeExec(JoinPoint joinPoint){
//        MethodSignature ms=(MethodSignature) joinPoint.getSignature();
//        Method method=ms.getMethod();
//        String ActionName = method.getAnnotation(GetCache.class).name();
//        String fieldList = method.getAnnotation(GetCache.class).value();
//        for (String field:fieldList.split(","))
//        {
//
//        }
//
//        ActionUtil.setCache(true);
//        //如果是第一次取值.则将版本存放到redis数据库
//        if (operations.get(ActionName)==null)  {
//            operations.increment(ActionName, 1);
//            return;
//        }
//        if (operations.get(ActionName).equals(ActionUtil.getParameter("cache_version")))
//            throw new CacheException("数据没有更新,可以采用本地数据!");
//        ActionUtil.setCache_version(operations.get(ActionName)+"");
//    }
//}
