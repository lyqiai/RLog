package com.river.rlog.upload

import com.river.rlog.RLog
import com.river.rlog.RLogConfig
import com.river.rlog.printer.FilePrinter
import okhttp3.*
import java.io.File
import java.io.FileOutputStream
import java.text.SimpleDateFormat
import java.util.*
import java.util.concurrent.Executors
import java.util.zip.ZipEntry
import java.util.zip.ZipOutputStream

class LogUpload : ILogUpload {
    private val uploadExecutor = Executors.newSingleThreadExecutor()
    private val cacheDir = File(RLogConfig.cacheDir)

    override fun upload() {
        upload(365)
    }

    override fun upload(passDay: Int) {
        uploadExecutor.submit {
            val uploadLogFiles = mutableListOf<File>()
            val dateFormat = SimpleDateFormat("yyyyMMdd")
            val zip = File(cacheDir, "upload.zip")
            if (zip.exists()) {
                zip.delete()
            }
            val zos = ZipOutputStream(FileOutputStream(zip))
            for (file in cacheDir.listFiles()) {
                if (!file.name.endsWith("zip")) {
                    val date = file.name.split("-")[0]
                    val parseDate = Calendar.getInstance().apply { time = dateFormat.parse(date) }
                    val passDay = Calendar.getInstance().apply {
                        set(Calendar.HOUR_OF_DAY, 0)
                        set(Calendar.MINUTE, 0)
                        set(Calendar.SECOND, 0)
                        set(Calendar.MILLISECOND, 0)
                        add(Calendar.DAY_OF_MONTH, -passDay)
                    }
                    if (passDay.before(parseDate)) {
                        uploadLogFiles.add(file)

                        val zipEntry = ZipEntry(file.name)
                        zos.putNextEntry(zipEntry)
                        zos.write(file.readBytes())
                        zos.closeEntry()
                    }
                }
            }
            zos.close()

            if (uploadLogFiles.isEmpty()) {
                return@submit
            }

            val okHttpClient = OkHttpClient()
            val requestBody = MultipartBody.Builder()
                .setType(MultipartBody.FORM)
                .addFormDataPart(
                    "file",
                    "log.zip",
                    RequestBody.create(MediaType.parse("multipart/form-data"), zip)
                )
                .build()
            val request = Request.Builder()
                .header("Authorization", "Client-ID ${UUID.randomUUID()}")
                .url(String.format("%s/upload/file", RLogConfig.host))
                .post(requestBody)
                .build()
            try {
                val execute = okHttpClient.newCall(request).execute()
                if (execute.isSuccessful && execute.body().string().contains("\"code\":0")) {
                    for (printer in RLogConfig.printers) {
                        if (printer is FilePrinter) {
                            printer.close()
                            break
                        }
                    }
                    for (uploadLogFile in uploadLogFiles) {
                        uploadLogFile.delete()
                    }
                    zip.delete()

                    RLog.i("上传成功")
                } else {
                    RLog.i("上传失败")
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}