package com.river.rlog.printer

import com.river.rlog.LogBean
import com.river.rlog.RLogConfig
import com.river.rlog.encrypt.AESEncrypt
import com.river.rlog.formatter.FileFormatter
import java.io.File
import java.text.SimpleDateFormat
import java.util.*
import java.util.concurrent.Executors
import java.util.concurrent.LinkedBlockingQueue

/**
 * @Author: River
 * @Emial: 1632958163@qq.com
 * @Create: 2021/11/9
 **/
class FilePrinter : IPrinter {
    private val formatter = FileFormatter()
    private val executor = Executors.newSingleThreadExecutor()
    private val writeWork = WriteWork()
    private val cacheDir = RLogConfig.cacheDir
    private val encrypt = AESEncrypt(RLogConfig.encryptKey!!)

    init {
        val file = File(cacheDir)
        if (!file.exists()) {
            file.mkdir()
        }

        executor.execute(writeWork)
    }

    inner class WriteWork : Runnable {
        private val queue = LinkedBlockingQueue<String>()

        override fun run() {
            while (true) {
                val data = queue.take()
                val day = SimpleDateFormat("yyyyMMdd").format(Date())
                val fileName = "$cacheDir${File.separator}$day"
                val file = File(fileName)
                if (!file.exists()) {
                    file.createNewFile()
                }
                file.appendText(data)
            }
        }

        fun put(content: String) {
            queue.put(content)
        }
    }

    override fun printer(logBean: LogBean) {
        writeWork.put(encrypt.encrypt(formatter.format(logBean)))
    }
}