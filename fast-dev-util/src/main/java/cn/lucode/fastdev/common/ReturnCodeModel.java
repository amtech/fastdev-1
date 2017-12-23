package cn.lucode.fastdev.common;

/**
 * @author yunfeng.lu
 * @create 2017/10/26.
 */
public enum ReturnCodeModel {

    SUCCESS("9999", "成功"),
    /**
     * 该错误一般是异常未处理 ，没有兜住错误
     */
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

    // 文件处理
    FILEDIRCREATE("2101","系统文件创建失败");




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
