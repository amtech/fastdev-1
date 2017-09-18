package cn.lucode.config;
import com.alibaba.druid.support.http.StatViewServlet;

import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.core.annotation.Order;

/**
 * Created by yunfeng.lu on 2017/9/18.
 */
public class DruidServletConfig {
    @Bean
    @Order
    public ServletRegistrationBean statViewServlet() {
        StatViewServlet servlet = new StatViewServlet();
        ServletRegistrationBean bean = new ServletRegistrationBean(servlet, "/druid/*");
        bean.addInitParameter("loginUsername", "druid");
        bean.addInitParameter("loginPassword", "pinche");

        return bean;
    }
}
