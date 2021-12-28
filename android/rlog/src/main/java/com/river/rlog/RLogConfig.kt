package com.river.rlog

import com.river.rlog.identity.IIdentity
import com.river.rlog.printer.IPrinter
import java.io.File

/**
 * @Author: River
 * @Emial: 1632958163@qq.com
 * @Create: 2021/11/9
 **/
object RLogConfig {
    //身份标识
    internal var identity: IIdentity? = null

    //打印处理器
    internal var printers = mutableListOf<IPrinter>()

    //全局TAG
    internal var tag: String = "JT"

    internal var encryptKey: ByteArray? = null

    internal val cacheDir = AppGlobals.get().cacheDir!!.absolutePath + File.separator + "rlog"

    internal var host: String? = null

    /**
     * 设置身份标识
     * @param identity IIdentity
     * @return RLogConfig
     */
    fun setIdentity(identity: IIdentity): RLogConfig {
        this.identity = identity
        return this
    }

    /**
     * 设置打印处理器
     * @param printers Array<out IPrinter>
     * @return RLogConfig
     */
    fun setPrinters(vararg printers: IPrinter): RLogConfig {
        this.printers.clear()

        for (printer in printers) {
            this.printers.add(printer)
        }

        return this
    }

    fun setTag(tag: String): RLogConfig {
        this.tag = tag
        return this
    }

    fun setEncryptKey(key: ByteArray): RLogConfig {
        encryptKey = key
        return this
    }

    fun setHost(host: String): RLogConfig {
        this.host = host
        return this
    }
}