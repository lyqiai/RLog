package com.yunlu.log.controller;

import com.sun.org.apache.bcel.internal.generic.NEW;
import com.yunlu.log.domain.BaseResponse;
import org.apache.logging.log4j.Logger;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;

/**
 * @Author: River
 * @Emial: 1632958163@qq.com
 * @Create: 2021/12/28
 **/
@RestController
public class UploadController {
    @RequestMapping("/upload/file")
    public BaseResponse upload(@RequestParam("file")MultipartFile file) {
        if (file.isEmpty()) {
            return BaseResponse.failed(-1, "文件不能为空");
        }
        InputStream inputStream = null;
        ByteArrayOutputStream bos = null;
        try {
            inputStream = file.getInputStream();
            bos = new ByteArrayOutputStream();
            byte[] bytes = new byte[1024];
            int i;
            while ((i = inputStream.read(bytes)) > 0) {
                bos.write(bytes,0, i);
            }

            System.out.println(bos.toString());
            return BaseResponse.success(bos.toString());
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (bos != null) {
                try {
                    bos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return BaseResponse.success("hello");
    }
}
