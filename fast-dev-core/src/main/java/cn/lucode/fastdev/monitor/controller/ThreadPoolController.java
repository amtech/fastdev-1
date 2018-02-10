package cn.lucode.fastdev.monitor.controller;

import cn.lucode.fastdev.threadpool.DefaultPoolManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * @author yunfeng.lu
 * @create 2018/2/10.
 */
@RestController
@RequestMapping("/threadpool")
public class ThreadPoolController {

    @Autowired
    DefaultPoolManager defaultPoolManager;

    @GetMapping("info")
    public Map getThreadPoolInfo(){
        return defaultPoolManager.getAllPoolInfo();
    }



}
