package com.yunlu.log.controller;

import com.yunlu.log.dao.LogDao;
import com.yunlu.log.domain.BaseResponse;
import com.yunlu.log.domain.GetLogsQuery;
import com.yunlu.log.domain.Log;
import com.yunlu.log.domain.PagerData;
import com.yunlu.log.service.LogService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.List;

/**
 * @Author: River
 * @Emial: 1632958163@qq.com
 * @Create: 2021/12/28
 **/
@RestController()
@RequestMapping("/log")
@Slf4j
public class LogController {
    @Autowired
    private LogService logService;

    @Autowired
    private LogDao logDao;

    @RequestMapping("/getAllPackages")
    public BaseResponse<List<String>> getAllPackages() {
        List<String> allPackage = logDao.getAllPackage();
        return BaseResponse.success(allPackage);
    }

    @RequestMapping("/getAllIdentity")
    public BaseResponse<List<String>> getAllIdentity() {
        List<String> allIdentity = logDao.getAllIdentity();
        return BaseResponse.success(allIdentity);
    }

    @RequestMapping("/getAllLevel")
    public BaseResponse<List<String>> getAllLevel() {
        List<String> allLevel = logDao.getAllLevel();
        return BaseResponse.success(allLevel);
    }

    @RequestMapping("/getLogs")
    public BaseResponse<PagerData<Log>> getLogs(GetLogsQuery query) {
        HashMap<String, Object> data = new HashMap<>();
        data.put("packageName", query.getPackageName());
        data.put("identity", query.getIdentity());
        data.put("level", query.getLevel());
        data.put("position", query.getPage() * query.getPageSize());
        data.put("pageSize", query.getPageSize());
        data.put("time", query.getTime());

        List<Log> logs = logDao.getLogs(data);
        int total = logDao.getLogsCount(data);

        PagerData<Log> pagerData = new PagerData<>();
        pagerData.setData(logs);
        pagerData.setTotal(total);

        return BaseResponse.success(pagerData);
    }


    @RequestMapping("/upload")
    public BaseResponse upload(@RequestParam("file") MultipartFile file) {
        return logService.uploadFile2DB(file);
    }
}
