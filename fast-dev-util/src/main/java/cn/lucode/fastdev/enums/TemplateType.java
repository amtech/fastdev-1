package cn.lucode.fastdev.enums;

/**
 *  模板类型
 *  @author yunfeng.lu
 * @create 2018/2/9.
 */
public enum TemplateType {
    /**
     * 注册邮件
     */
    REGISTER("REGISTER");

    /**
     * 代码
     */
    private String code;

    /**
     * @param code
     */
    private TemplateType(String code) {
        this.code = code;
    }

    /**
     * 通过枚举<code>code</code>获得枚举
     *
     * @param code
     * @return
     */
    public static TemplateType getByCode(String code) {
        for (TemplateType param : values()) {
            if (param.getCode().equals(code)) {
                return param;
            }
        }
        return null;
    }

    /**
     * Getter method for property <tt>code</tt>.
     *
     * @return property value of code
     */
    public String getCode() {
        return code;
    }
}
