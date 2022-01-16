package com.river.rlog.write

import com.river.rlog.RLogConfig
import java.io.BufferedOutputStream
import java.io.File
import java.io.FileOutputStream
import java.text.SimpleDateFormat
import java.util.*

/**
 * @author  River
 * @date  2022/1/15 22:03
 */
class Writer : IWriter {
    private val cacheDir = File(RLogConfig.cacheDir)
    private var file: File? = null
    private var fos: FileOutputStream? = null
    private var bos: BufferedOutputStream? = null

    init {
        if (!cacheDir.exists()) {
            cacheDir.mkdir()
        }
    }

    @Synchronized
    override fun write(content: String) {
        checkFile()
        bos?.write(content.toByteArray())
        bos?.write("\n".toByteArray())
        bos?.flush()
    }

    private fun checkFile() {
        if (file == null) {
            val day = SimpleDateFormat("yyyyMMdd").format(Date())
            var max = 1
            for (file in cacheDir.listFiles()) {
                val name = file.name
                if (name.contains(day)) {
                    val number = name.split("-")[1].toInt()
                    max = Math.max(max, number)
                }
            }
            createFile("$day-$max")
        }
        file?.let {
            if (it.length() >= MAX_FILE_SIZE) {
                val filenames = it.name.split("-")
                createFile("${filenames[0]}-${filenames[1].toInt() + 1}")
            }
        }
    }

    private fun createFile(fileName: String) {
        close()

        file = File(cacheDir.absolutePath, fileName)
        if (!file!!.exists()) {
            file!!.createNewFile()
        }

        fos = FileOutputStream(file, true)
        bos = BufferedOutputStream(fos)
    }

    @Synchronized
    override fun close() {
        file = null
        bos?.close()
        fos?.close()
    }

    companion object {
        const val MAX_FILE_SIZE = 1024 * 1024 * 1024
    }
}