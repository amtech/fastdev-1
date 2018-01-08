package cn.lucode.fastdev.monitor.controller;

import cn.lucode.fastdev.monitor.service.JvmInfoService;
import cn.lucode.fastdev.util.JsonUtil;
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
public class JvmInfoController {

    @Autowired
    private JvmInfoService jvmInfoService;

    @RequestMapping(value = "/jvminfo/getInfo")
    @ResponseStatus(HttpStatus.OK)
    public @ResponseBody
    String rmLocal(HttpServletRequest request) throws Exception {
        return JsonUtil.obj2Json(jvmInfoService.getJvm());

    }

    @RequestMapping(value = "/cache/testbyte/{key}")
    @ResponseStatus(HttpStatus.OK)
    public @ResponseBody
    String rmLocal(HttpServletRequest request, @PathVariable("key") long key) {
        ArrayList a = new ArrayList();
        for (long i = 0; i < key; i++) {
            Byte[] bytes = new Byte[1024 * 1024 * 10];
            a.add(bytes);
        }
        System.gc();
        return "success";
    }


}
