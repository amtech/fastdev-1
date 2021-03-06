package cn.lucode.fastdev.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @author yunfeng.lu
 * @create 2017/11/7.
 */

@Configuration
@EnableWebMvc
@EnableSwagger2
@ComponentScan(basePackages = {"cn.lucode.**"})
public class SwaggerConfig {

    public SwaggerConfig() {
    }

    ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("lucode Web SelfService APIs")
                .description("")
                .license("")
                .licenseUrl("")
                .termsOfServiceUrl("")
                .version("1.0.0")
                .build();
    }

//    @Bean
//    public Docket swaggerSettings() {
//        return new Docket(DocumentationType.SWAGGER_2).
//                apiInfo(this.apiInfo())
//                .select()
//                //.apis(RequestHandlerSelectors.withClassAnnotation(RestController.class))
//                .apis(RequestHandlerSelectors.basePackage("cn.lucode.controller"))
//                .paths(PathSelectors.any())
//                .build();
//    }


    @Bean
    public Docket customImplementation() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("cn.lucode"))
                .build()
                .directModelSubstitute(org.joda.time.LocalDate.class, java.sql.Date.class)
                .directModelSubstitute(org.joda.time.DateTime.class, java.util.Date.class)
                .apiInfo(apiInfo());
    }

}