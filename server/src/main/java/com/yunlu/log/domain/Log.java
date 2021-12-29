package com.yunlu.log.domain;

import lombok.Data;

/**
 * @Author: River
 * @Emial: 1632958163@qq.com
 * @Create: 2021/12/28
 **/
@Data
public class Log {
    private Long id;
    private Integer level;
    private String threadName;
    private String time;
    private String content;
}
