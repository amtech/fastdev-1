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


/**
 * @author yunfeng.lu
 * @create 2018/2/9.
 */
@Component
@Scope("singleton")
public class HTMLTemplateUtils {
    @Autowired
    //private final static TemplateEngine templateEngine = new TemplateEngine();
    private  TemplateEngine templateEngine;


    private  Map<String, String> templateMap;

    @PostConstruct
    public void init() {
        if (templateMap == null) {
            templateMap = new HashMap<>();
        }
        templateMap.put(TemplateType.REGISTER.getCode(),"email");

    }

    /**
     * 使用 Thymeleaf 渲染 HTML
     *
     * @param templateType HTML模板 类型
     * @param params       参数
     * @return 渲染后的HTML
     */
    public String render(TemplateType templateType, Map<String, Object> params) {
        Context context = new Context();
        context.setVariables(params);
        return templateEngine.process(templateMap.get(templateType.getCode()), context);
    }


}
