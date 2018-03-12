package cn.lucode.fastdev.threadpool;

/**
 * 与线程池相关的常量配置
 * @author yunfeng.lu
 * @create 2018/2/9.
 */
public class ThreadPoolCommons {
    public static final String CREATE_THE_ERR="超过容器允许最大线程池，创建失败";
    public static final String ASSERT_POOL_NULL="线程池名不允许为空";
    public static final String ASSERT_POOL_SN= "已存在同名线程池";
    public static final String LOG_THREAD_MAXERR= "超过容器允许最大线程池，创建失败,当前大小:{0},待创建大小:{1}";
    public static final String ASYNC_POOL= "ASYNCPOOL";
    public final static int ALIVETIME=180;
}
