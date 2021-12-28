package com.yunlu.log.entry;

import lombok.Data;

/**
 * @Author: River
 * @Emial: 1632958163@qq.com
 * @Create: 2021/12/28
 **/
@Data
public class BaseResponse<DATA> {
    public static final int SUCCESS_CODE = 0;

    private Integer code;
    private String message;
    private DATA data;

    public BaseResponse(Integer code, String message, DATA data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public static <DATA> BaseResponse<DATA> success(DATA data) {
        return new BaseResponse<>(SUCCESS_CODE, "ok", data);
    }

    public static <DATA> BaseResponse<DATA> failed(Integer code, String message) {
        return new BaseResponse<>(code, message, null);
    }
}
