package cn.lucode.fastdev.monitor.controller;

import cn.lucode.fastdev.common.CommonResponseModel;
import cn.lucode.fastdev.monitor.service.JvmInfoService;
import cn.lucode.fastdev.util.JsonUtil;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;

/**
 * @author yunfeng.lu
 * @create 2018/1/2.
 */
@RestController
@Api("jvm")
public class JvmInfoController {

    @Autowired
    private JvmInfoService jvmInfoService;

    @GetMapping(value = "/jvminfo/getInfo")
    public Object rmLocal() throws Exception {
       return CommonResponseModel.success(jvmInfoService.getJvmInfo());

    }

    @GetMapping(value = "/cache/testbyte/{key}")
    public String rmLocal(@PathVariable("key") long key) {
        ArrayList a = new ArrayList();
        for (long i = 0; i < key; i++) {
            Byte[] bytes = new Byte[1024 * 1024 * 10];
            a.add(bytes);
        }
        System.gc();
        return "success";
    }

}
