package com.yunlu.log.dao;

import com.yunlu.log.domain.Log;

import java.util.List;
import java.util.Map;


/**
 * @Author: River
 * @Emial: 1632958163@qq.com
 * @Create: 2021/12/28
 **/
public interface LogDao {
    boolean addLogs(List<Log> logs);

    List<String> getAllPackage();

    List<String> getAllIdentity();

    List<String> getAllLevel();

    List<Log> getLogs(Map<String, Object> map);

    int getLogsCount(Map<String, Object> map);
}
