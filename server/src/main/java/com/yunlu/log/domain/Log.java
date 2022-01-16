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
    private String identity;
    private String level;
    private String threadName;
    private String content;
    private String packageName;
    private String versionName;
    private Integer versionCode;
    private String time;
}
