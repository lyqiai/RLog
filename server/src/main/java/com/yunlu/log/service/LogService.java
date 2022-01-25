package com.yunlu.log.service;

import com.alibaba.fastjson.JSON;
import com.yunlu.log.dao.LogDao;
import com.yunlu.log.domain.BaseResponse;
import com.yunlu.log.domain.Log;
import com.yunlu.log.util.AESUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.ArrayList;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipInputStream;

@Service
@Slf4j
public class LogService implements ILogService {
    @Autowired
    private LogDao logDao;

    @Override
    public BaseResponse uploadFile2DB(MultipartFile file) {
        if (file.isEmpty()) {
            return BaseResponse.failed(-1, "文件不能为空");
        }

        ZipInputStream zis = null;
        ZipFile zipFile = null;

        BaseResponse response = new BaseResponse();

        ArrayList<Log> logs = new ArrayList<>();
        try {
            File tempFile = File.createTempFile("log", "zip");
            file.transferTo(tempFile);
            zipFile = new ZipFile(tempFile);
            zis = new ZipInputStream(new FileInputStream(tempFile));

            ZipEntry zipEntry = null;
            while ((zipEntry = zis.getNextEntry()) != null) {
                try (BufferedReader br = new BufferedReader(new InputStreamReader(zipFile.getInputStream(zipEntry)))) {
                    String line = null;
                    while ((line = br.readLine()) != null) {
                        log.info(String.format("原始数据:%s", line));
                        String decrypt = AESUtil.decrypt(line);
                        log.info(String.format("解析数据:%s", decrypt));

                        Log item = JSON.parseObject(decrypt, Log.class);
                        logs.add(item);
                    }
                }
            }
            boolean succ = false;

            if (!logs.isEmpty()) {
                succ = logDao.addLogs(logs);
            }

            response.setCode(succ ? BaseResponse.SUCCESS_CODE : -1);
            response.setMessage(succ ? "OK" : "数据插入失败");
        } catch (IOException e) {
            e.printStackTrace();
            response.setCode(-1);
            response.setMessage("文件解析失败");
        } finally {
            if (zis != null) {
                try {
                    zis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return response;
    }
}
