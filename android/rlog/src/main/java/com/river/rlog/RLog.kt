package com.river.rlog

import android.util.Log
import okhttp3.*
import java.io.File
import java.util.*
import java.util.concurrent.Executors

/**
 * @Author: River
 * @Emial: 1632958163@qq.com
 * @Create: 2021/11/9
 **/
object RLog {
    private val uploadExecutor = Executors.newSingleThreadExecutor()

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

        val logBean = LogBean(
            content = content,
            level = level,
            threadName = Thread.currentThread().name,
            time = Date().format()
        )

        for (printer in RLogConfig.printers) {
            printer.printer(logBean)
        }
    }

    fun uploadLog(passDay: Int) {
        uploadExecutor.execute {
            val filePath = AppGlobals.get().cacheDir!!.absolutePath + File.separator + "rlog" + File.separator + "20211229"
            val file = File(filePath)
            val okHttpClient = OkHttpClient()
            val requestBody = MultipartBody.Builder()
                .setType(MultipartBody.FORM)
                .addFormDataPart(
                    "file",
                    file.name,
                    RequestBody.create(MediaType.parse("multipart/form-data"), file)
                )
                .build()
            val request = Request.Builder()
                .header("Authorization", "Client-ID ${UUID.randomUUID()}")
                .url("http://10.66.21.92:8080/upload/file")
                .post(requestBody)
                .build()

            val execute = okHttpClient.newCall(request).execute()
            println(execute.body().toString())
        }
    }
}