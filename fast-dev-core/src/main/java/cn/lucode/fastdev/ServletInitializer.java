package cn.lucode.fastdev;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;

/**
 * Created by yunfeng.lu on 2017/9/18.
 */

public class ServletInitializer extends SpringBootServletInitializer {

    private static final Logger logger = LoggerFactory.getLogger(ServletInitializer.class);

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        logger.info("开始启动spring boot 配置初始化");
        return application.sources(StartApplication.class);
    }

}

