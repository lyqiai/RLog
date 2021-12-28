package com.yunlu.log.controller;

import com.yunlu.log.entry.BaseResponse;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: River
 * @Emial: 1632958163@qq.com
 * @Create: 2021/12/28
 **/
@RestController
public class UploadController {
    @RequestMapping("/upload/file")
    public BaseResponse upload() {
        return BaseResponse.success("hello");
    }
}
