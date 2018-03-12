package cn.lucode.fastdev.monitor.service.impl;

import cn.lucode.fastdev.monitor.service.LogService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

/**
 * 日志监控系统 目前 监控的是系统的响应时间 统一记录超过 100 毫秒的
 * 由于监控日志每个只打印50M 所以把这个50M 文件全部填入 内存
 * @author yunfeng.lu
 * @create 2018/3/4.
 */
@Service
public class LogServiceImpl  implements LogService{

    // 获取监控日志的打印路径
    @Value("${monitorLogPath}")
    private String monitorLogPath;

    @PostConstruct
    public void init(){



    }



}
