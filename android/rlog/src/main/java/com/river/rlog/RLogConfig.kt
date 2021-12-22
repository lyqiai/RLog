package com.river.rlog

import com.river.rlog.encrypt.IEncrypt
import com.river.rlog.identity.IIdentity
import com.river.rlog.printer.IPrinter

/**
 * @Author: River
 * @Emial: 1632958163@qq.com
 * @Create: 2021/11/9
 **/
object RLogConfig {
    //身份标识
    private var identity: IIdentity? = null

    //打印处理器
    private var printers = mutableListOf<IPrinter>()

    //加解密
    private var encrypt: IEncrypt? = null

    /**
     * 设置身份标识
     * @param identity IIdentity
     * @return RLogConfig
     */
    fun identity(identity: IIdentity): RLogConfig {
        this.identity = identity
        return this
    }

    /**
     * 设置打印处理器
     * @param printers Array<out IPrinter>
     * @return RLogConfig
     */
    fun printers(vararg printers: IPrinter): RLogConfig {
        this.printers.clear()

        for (printer in printers) {
            this.printers.add(printer)
        }

        return this
    }

    /**
     * 设置加解密
     * @param encrypt IEncrypt?
     * @return RLogConfig
     */
    fun encrypt(encrypt: IEncrypt?): RLogConfig {
        this.encrypt = encrypt
        return this
    }
}