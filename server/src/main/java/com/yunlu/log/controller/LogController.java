package com.yunlu.log.controller;

import com.yunlu.log.domain.BaseResponse;
import com.yunlu.log.domain.Log;
import com.yunlu.log.dao.LogDao;
import com.yunlu.log.util.AESUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

/**
 * @Author: River
 * @Emial: 1632958163@qq.com
 * @Create: 2021/12/28
 **/
@RestController()
@RequestMapping("/log")
public class LogController {
    @Autowired
    public LogDao logDao;

    @RequestMapping("/add")
    public BaseResponse addLog() {
        Log log = new Log();
        log.setLevel(0);
        log.setThreadName("main");
        log.setContent("content");
        log.setTime(new Date().toString());

        Integer id = logDao.addLog(log);
        return BaseResponse.success(id);
    }

    @RequestMapping("/test")
    public String test() {
        String content = "MZ2d57Z0rrwsBcdouPfHbq4238PkhN1YW2cMdW1MAfUfeM0uEd4g83UfxRbuQDWUCwUDIpQA2rRe3ZQwGb0ET9jJVuqQICbffI+czH2145X7E5hNauHq4GVNMdBFdnUpDPocVBcOpwBXwsE0xF6E0A==";
        return AESUtil.decrypt(content);
    }
}
