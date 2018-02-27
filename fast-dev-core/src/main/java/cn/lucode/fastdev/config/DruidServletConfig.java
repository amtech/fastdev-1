package cn.lucode.fastdev.config;
import com.alibaba.druid.support.http.StatViewServlet;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;

/**
 * Created by yunfeng.lu on 2017/9/18.
 */
@Configuration
public class DruidServletConfig {
    private static final Logger logger = LoggerFactory.getLogger(DruidServletConfig.class);
    @Bean
    @Order
    public ServletRegistrationBean statViewServlet() {
        logger.info("==========druid配置启动========");
        StatViewServlet servlet = new StatViewServlet();
        ServletRegistrationBean bean = new ServletRegistrationBean(servlet, "/druid/*");
        bean.addInitParameter("loginUsername", "druid");
        bean.addInitParameter("loginPassword", "123");

        return bean;
    }
}
