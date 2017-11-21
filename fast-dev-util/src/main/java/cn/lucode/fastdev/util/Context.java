package cn.lucode.fastdev.util;

/**
 * Created by yunfeng.lu on 2017/9/18.
 */
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

@Component
public class Context implements ApplicationContextAware {
    private static ApplicationContext applicationContext;

    private  static  final Logger logger= LoggerFactory.getLogger(Context.class);

    @Override
    public void setApplicationContext(ApplicationContext context) throws BeansException {
        applicationContext = context;
    }

    public static void setAppContext(ApplicationContext context) {
        LogUtil.info(logger,"获取到的springcontext对象 {0}", context);
    }

    public static ApplicationContext getApplicationContext() {
        return applicationContext;
    }
}