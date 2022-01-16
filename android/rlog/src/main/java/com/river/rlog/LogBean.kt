package com.river.rlog

/**
 * @Author: River
 * @Emial: 1632958163@qq.com
 * @Create: 2021/11/9
 **/
data class LogBean(
        val identity: String?,
        val level: String,
        var content: String,
        val threadName: String,
        val packageName: String,
        val versionCode: Int,
        val versionName: String,
        val time: String,
)