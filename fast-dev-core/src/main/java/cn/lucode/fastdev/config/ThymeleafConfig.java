//package cn.lucode.fastdev.config;
//
//import cn.lucode.fastdev.user.controller.UserController;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.ComponentScan;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.web.servlet.ViewResolver;
//import org.springframework.web.servlet.config.annotation.EnableWebMvc;
//import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
//import org.thymeleaf.TemplateEngine;
//import org.thymeleaf.spring4.SpringTemplateEngine;
//import org.thymeleaf.spring4.templateresolver.SpringResourceTemplateResolver;
//import org.thymeleaf.spring4.view.ThymeleafViewResolver;
//import org.thymeleaf.templatemode.TemplateMode;
//import org.thymeleaf.templateresolver.ITemplateResolver;
//
//import static cn.lucode.fastdev.util.SpringContextUtils.applicationContext;
//
///**
// * @author yunfeng.lu
// * @create 2018/2/10.
// */
//@Configuration
//@EnableWebMvc
//@ComponentScan("cn.lucode")
//public class ThymeleafConfig extends WebMvcConfigurerAdapter {
//    private static final Logger logger = LoggerFactory.getLogger(ThymeleafConfig.class);
//
//    @Bean
//    public ViewResolver viewResolver() {
//        ThymeleafViewResolver resolver = new ThymeleafViewResolver();
//        resolver.setTemplateEngine(templateEngine());
//        resolver.setCharacterEncoding("UTF-8");
//        return resolver;
//    }
//
//    @Bean
//    public TemplateEngine templateEngine() {
//        logger.info("=====================");
//        SpringTemplateEngine engine = new SpringTemplateEngine();
//        engine.setEnableSpringELCompiler(true);
//        engine.setTemplateResolver(templateResolver());
//        return engine;
//    }
//
//    private ITemplateResolver templateResolver() {
//        SpringResourceTemplateResolver resolver = new SpringResourceTemplateResolver();
//        resolver.setCharacterEncoding("UTF-8");
//        resolver.setApplicationContext(applicationContext);
//        resolver.setPrefix("classpath:/templates/");
//        resolver.setTemplateMode(TemplateMode.HTML);
//        return resolver;
//    }
//
//}
