package com.river.rlog

/**
 * @Author: River
 * @Emial: 1632958163@qq.com
 * @Create: 2021/11/9
 **/
data class LogBean(
    var content: String,
    val level: Int,
    val threadName: String,
    val time: String,
)