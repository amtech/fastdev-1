package cn.lucode.fastdev.config;

import com.github.pagehelper.PageInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Properties;
/**
 * Created by yunfeng.lu on 2017/9/18.
 * 注册MyBatis分页插件PageHelper
 */
@Configuration
public class MybatisPageHelperConfig {

    @Bean
    public PageInterceptor pageHelper() {
        PageInterceptor pageHelper = new PageInterceptor();
        Properties p = new Properties();
        p.setProperty("offsetAsPageNum", "true");
        p.setProperty("rowBoundsWithCount", "true");
        // 一般用于超过这个页数是否显示数据的
        p.setProperty("reasonable", "true");
        pageHelper.setProperties(p);
        return pageHelper;
    }
}