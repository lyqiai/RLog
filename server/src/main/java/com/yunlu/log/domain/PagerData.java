package com.yunlu.log.domain;

import lombok.Data;

import java.util.List;

@Data
public class PagerData<DATA> {
    private int total;
    private List<DATA> data;
}
