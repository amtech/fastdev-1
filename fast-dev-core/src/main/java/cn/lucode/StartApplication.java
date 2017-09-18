package cn.lucode;

import cn.lucode.util.Context;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.ImportResource;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * Created by yunfeng.lu on 2017/9/18.
 */

@ImportResource(locations={"classpath*:beans/**/*.xml"})
@SpringBootApplication
@EnableScheduling
@EnableAspectJAutoProxy(exposeProxy=true)
public class StartApplication {
    public static void main(String[] args) {
        //启动服务
        ApplicationContext context = SpringApplication.run(StartApplication.class, args);
        Context.setAppContext(context);
    }
}
