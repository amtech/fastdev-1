package cn.lucode.fastdev.monitor.jvm;

/**
 * @author yunfeng.lu
 * @create 2018/1/2.
 */
public enum JVMFlagEnum {
    HEAP_OVER("1"),
    NONHEAP_OVER("2"),
    OLGGEN_OVER("3"),
    FGC_RATE("4"),
    HEALTH("0");
    private   JVMFlagEnum(String code){
        this.code=code;
    }
    private String code;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}