package cn.lucode.fastdev.util;

import cn.hutool.core.util.RandomUtil;
import cn.lucode.fastdev.enums.TemplateType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Executors;

/**
 * @author yunfeng.lu
 * @create 2018/2/9.
 */
@Component
@Scope("singleton")
public class HTMLTemplateUtils {
    @Autowired
    //private final static TemplateEngine templateEngine = new TemplateEngine();
    private TemplateEngine templateEngine=new TemplateEngine();


    private Map<String,String> templateMap;

    @PostConstruct
    public void init(){
        if(templateMap==null){
            templateMap=new HashMap<>();
        }

        // 初始化的 时候目前将 各种模板加入 jvm 缓存中
        StringBuffer sb=new StringBuffer();
        sb.append("<p th:text='${name}'></p>");
        sb.append("<p>哈，某人用你的邮箱注册 lucode 网站了，如果是本人操作</p>");
        sb.append("<p>点击下面链接完成注册：</p>");
        sb.append("<p th:text='${url}'></p>");
        sb.append("<p>如果不是本人操作，真不好意思打扰到你了，可以访问我们网站看看 www.luyunfeng.cn</p>");
        sb.append("<img src=\"https://tuimeizi.cn/random?w=100&h=100&s=0&o=");
        sb.append(RandomUtil.randomInt(0,100));
        sb.append("\"/>");
        templateMap.put(TemplateType.REGISTER.getCode(),sb.toString());

    }

    /**
     * 使用 Thymeleaf 渲染 HTML
     * @param templateType  HTML模板 类型
     * @param params 参数
     * @return  渲染后的HTML
     */
    public String render(TemplateType templateType, Map<String, Object> params){
        Context context = new Context();
        context.setVariables(params);
        return templateEngine.process(templateMap.get(templateType.getCode()), context);
    }


    public static void main(String[] args) {
        HTMLTemplateUtils htmlTemplateUtils=new HTMLTemplateUtils();
        htmlTemplateUtils.init();
        Map<String, Object> params=new HashMap<>();
        params.put("name","wwww");
        params.put("url","www.baidu.com");
        String s=htmlTemplateUtils.render(TemplateType.REGISTER,params);
        System.out.println(s);
    }

}
