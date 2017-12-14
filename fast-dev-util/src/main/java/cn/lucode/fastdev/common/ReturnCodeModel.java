package cn.lucode.fastdev.common;

/**
 * @author yunfeng.lu
 * @create 2017/10/26.
 */
public enum ReturnCodeModel {

    SUCCESS("9999", "成功"),
    FAIL("0000", "系统异常"),
    SYSTEM_IS_BUSY("0001", "系统忙"),
    PARAMETER_IS_MISSING("0002", "参数缺失！"),
    JSON_XML_PARSE_ERROR("0003", "格式解析错误"),
    SERVER_ERROR("0004", "服务错误,请联系我们"),
    NO_DATE("0005", "服务端没有数据"),
    SCHEDULE_JOB_ERROR("0006","执行定时任务失败"),

    // 登陆 用户相关
    UNAUTHORIZED("2007", "无效的认证"),
    AUTHTOKEN_INVALID("2008", "AUTH TOKEN失效，登陆过期"),
    DEVICE_INVALID("2009", "终端、ip 不一致"),
    SMS_LIMIT("2010", "短信数量限制"),
    SMS_ERROR("2011", "短信发送失败"),
    SMS_CHECK_FAIL("2012", "短信验证失败"),
    ALREADY_BOUND_QUICKLOGIN_ERROR("2013", "该账号已绑定第三方登录"),
    ALREADY_REGISTERED("2014", "该账号已被注册"),
    UNREGISTERED("2015", "该账号还未注册"),
    NOT_GENERATED_PASSWORD("2016", "您未设置过密码，请使用验证码登录"),
    PASSWORD_ERROR("2017", "密码错误"),
    USER_NOT_EXIST("2018", "该用户不存在"),
    SMS_SEND_INTERVAL("2019", "短信验证发送时间间隔过短"),
    USER_ACCOUNT_BLOCKED("2020", "该账号已冻结"),
    OLD_PASSWORD_ERROR("2021", "原密码输入错误"),
    SMS_MOBILE_ERROR("2022", "您输入的手机号有误，请重新输入"),
    SMS_CHECK_INVALID("2023", "短信验证码已失效，请重新获取"),
    ALIPAY_ACCESS_TOKEN_INVALID("2024", "支付宝ACCESS_TOKEN失效"),
    VERIFYIDENTITY_REJECT_BIND("2025","您的账号存在风险无法绑定"),
    VERIFYIDENTITY_REJECT_REGISTER("2026","您的手机号存在风险无法注册"),
    UNKNOWN_LOGIN_SOURCE("2027","未知授权登陆来源"),
    PERMISSION_DENIED("2028","用户权限不够");


    private String code;
    private String msg;

    private ReturnCodeModel(String code, String msg) {
        this.code=code;
        this.msg=msg;

    }
    private ReturnCodeModel() {}

    public static ReturnCodeModel getByCode(String code) {
        for (ReturnCodeModel param: ReturnCodeModel.values()) {
            if (param.getCode().equals(code)) {
                return param;
            }
        }
        return null;
    }
    public String getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
