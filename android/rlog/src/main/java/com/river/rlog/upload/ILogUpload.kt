package com.river.rlog.upload

/**
 * @Author: River
 * @Emial: 1632958163@qq.com
 * @Create: 2021/11/9
 **/
interface ILogUpload {
    /**
     * 上传所有日志
     */
    fun upload()

    /**
     * 上传过去几天的日志
     * @param passDay Int
     */
    fun upload(passDay: Int)
}