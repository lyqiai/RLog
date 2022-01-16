package com.yunlu.log.controller;

import com.yunlu.log.domain.BaseResponse;
import com.yunlu.log.service.LogService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * @Author: River
 * @Emial: 1632958163@qq.com
 * @Create: 2021/12/28
 **/
@RestController
@Slf4j
public class UploadController {
    @Autowired
    private LogService logService;

    @RequestMapping("/upload/file")
    public BaseResponse upload(@RequestParam("file") MultipartFile file) {
        return logService.uploadFile2DB(file);
    }
}
