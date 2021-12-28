package com.yunlu.log.mapper;

import com.yunlu.log.entry.Log;
import org.apache.ibatis.annotations.Mapper;

/**
 * @Author: River
 * @Emial: 1632958163@qq.com
 * @Create: 2021/12/28
 **/
@Mapper
public interface ILogMapper {
    Integer addLog(Log log);
}
