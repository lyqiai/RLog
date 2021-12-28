package com.yunlu.log.controller;

import com.yunlu.log.entry.BaseResponse;
import com.yunlu.log.entry.Log;
import com.yunlu.log.mapper.ILogMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Date;

/**
 * @Author: River
 * @Emial: 1632958163@qq.com
 * @Create: 2021/12/28
 **/
@RestController
public class LogController {
    @Autowired
    public ILogMapper logMapper;

    @RequestMapping("/log/add")
    public BaseResponse addLog() {
        Log log = new Log();
        log.setLevel(0);
        log.setThreadName("main");
        log.setContent("content");
        log.setTime(new Date().toString());

        Integer id = logMapper.addLog(log);

        return BaseResponse.success(null);
    }
}
