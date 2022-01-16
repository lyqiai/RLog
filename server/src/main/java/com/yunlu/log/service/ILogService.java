package com.yunlu.log.service;

import com.yunlu.log.domain.BaseResponse;
import org.springframework.web.multipart.MultipartFile;

/**
 * @Author: River
 * @Emial: 1632958163@qq.com
 * @Create: 2021/12/28
 **/
public interface ILogService {
    BaseResponse uploadFile2DB(MultipartFile file);
}
