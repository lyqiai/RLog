package com.river.rlog

/**
 * @Author: River
 * @Emial: 1632958163@qq.com
 * @Create: 2021/11/9
 **/
object RLog {
    fun v(content: String) {
        log(RLogType.V, content)
    }

    fun d(content: String) {
        log(RLogType.D, content)
    }

    fun i(content: String) {
        log(RLogType.I, content)
    }

    fun w(content: String) {
        log(RLogType.W, content)
    }

    fun e(content: String) {
        log(RLogType.E, content)
    }

    fun a(content: String) {
        log(RLogType.A, content)
    }

    fun log(@RLogType.TYPE level: Int, content: String) {

    }
}