package com.yunlu.rlog

import android.app.Application
import com.river.rlog.RLog
import com.river.rlog.RLogConfig
import com.river.rlog.identity.IIdentity
import com.river.rlog.printer.FilePrinter
import com.river.rlog.printer.TerminalPrinter

/**
 * @Author: River
 * @Emial: 1632958163@qq.com
 * @Create: 2021/11/9
 **/
class App : Application() {
    override fun onCreate() {
        super.onCreate()

        RLogConfig
            .setIdentity(object : IIdentity {
                override fun identity(): String {
                    return "River"
                }
            })
            .setHost("http://10.66.21.92:8080")
            .setEncryptKey("1234567890123456".toByteArray())
            .setPrinters(TerminalPrinter(), FilePrinter())

        RLog.autoUpload()
    }
}