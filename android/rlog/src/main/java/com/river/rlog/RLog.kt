package com.river.rlog

import android.content.pm.PackageManager
import java.util.*

/**
 * @Author: River
 * @Emial: 1632958163@qq.com
 * @Create: 2021/11/9
 **/
object RLog {
    private val logUpload = LogUpload()

    init {
        assert(RLogConfig.identity != null) { "identity must be init!" }
    }

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
        val packageInfo = AppGlobals.get().packageManager.getPackageInfo(AppGlobals.get().packageName, PackageManager.GET_CONFIGURATIONS)
        val logBean = LogBean(
                identity = RLogConfig.identity?.identity(),
                content = content,
                level = RLogType.getLevelString(level),
                threadName = Thread.currentThread().name,
                time = Date().format(),
                packageName = AppGlobals.get().packageName,
                versionCode = packageInfo.versionCode,
                versionName = packageInfo.versionName
        )

        for (printer in RLogConfig.printers) {
            printer.printer(logBean)
        }
    }

    fun uploadLog(passDay: Int) {
        logUpload.upload(passDay)
    }
}