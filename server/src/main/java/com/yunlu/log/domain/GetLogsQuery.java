package com.yunlu.log.domain;

import lombok.Data;

@Data
public class GetLogsQuery {
    private int page;
    private int pageSize;
    private String level;
    private String identity;
    private String packageName;
    private String time;
}
