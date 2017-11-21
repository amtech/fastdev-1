package cn.lucode.fastdev.common;

/**
 * @author yunfeng.lu
 * @create 2017/10/26.
 */
public enum  CommonBizTypeCode {
    BIZ_JOB("0001", "定时任务");

    private String code;
    private String desc;

    public String getCode() {
        return this.code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDesc() {
        return this.desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    private CommonBizTypeCode(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    private CommonBizTypeCode() {
    }

    public static CommonBizTypeCode getByCode(String code) {
        CommonBizTypeCode[] arr$ = values();
        int len$ = arr$.length;

        for(int i$ = 0; i$ < len$; ++i$) {
            CommonBizTypeCode param = arr$[i$];
            if(param.getCode().equals(code)) {
                return param;
            }
        }

        return null;
    }
}
