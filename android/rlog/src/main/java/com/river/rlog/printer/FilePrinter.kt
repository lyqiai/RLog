package com.river.rlog.printer

import com.google.gson.Gson
import com.river.rlog.LogBean
import com.river.rlog.RLogConfig
import com.river.rlog.encrypt.AESEncrypt
import com.river.rlog.write.Writer
import java.util.concurrent.Executors
import java.util.concurrent.LinkedBlockingQueue

/**
 * @Author: River
 * @Emial: 1632958163@qq.com
 * @Create: 2021/11/9
 **/
class FilePrinter : IPrinter {
    private val executor = Executors.newSingleThreadExecutor()
    private val writeWork = WriteWork()
    private val encrypt = AESEncrypt(RLogConfig.encryptKey!!)
    private val write = Writer()


    init {
        executor.execute(writeWork)
    }

    inner class WriteWork : Runnable {
        private val queue = LinkedBlockingQueue<LogBean>()
        private val gson = Gson()

        override fun run() {
            while (true) {
                val logBean = queue.take()
                write.write(encrypt.encrypt(gson.toJson(logBean)))
            }
        }

        fun put(logBean: LogBean) {
            queue.put(logBean)
        }
    }

    override fun printer(logBean: LogBean) {
        writeWork.put(logBean)
    }

    fun close() {
        write.close()
    }
}