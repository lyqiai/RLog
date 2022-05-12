package com.river.rlog

import java.io.File
import java.io.RandomAccessFile
import java.nio.BufferOverflowException
import java.nio.MappedByteBuffer
import java.nio.channels.FileChannel
import java.text.SimpleDateFormat
import java.util.*

/**
 * @Author: River
 * @Emial: 1632958163@qq.com
 * @Create: 2021/11/9
 **/
class Cache(private val cacheDir: String) {
    private val mmapFilePath = cacheDir + File.separator + MMAP_FILE_NAME
    private val mmapFile = File(mmapFilePath)
    private var mmapRef: RandomAccessFile? = null
    private var mmapChannel: FileChannel? = null
    private var mmapMbb: MappedByteBuffer? = null

    init {
        if (mmapFile.exists()) {
            flushCache()
        }
    }

    fun write(bytes: ByteArray) {
        try {
            getMmap().put(bytes)
        } catch (e: BufferOverflowException) {
            flush()
            write(bytes)
        }
    }

    fun flush() {
        flushMmap2File(getMmap().position().toLong())
    }

    private fun flushCache() {
        var end = 0L
        for (i in MMAP_SIZE - 1 downTo 0) {
            val byte = getMmap().get(i.toInt())
            if (byte > 0) {
                end = i + 1
                break
            }
        }

        flushMmap2File(end)
    }

    private fun flushMmap2File(end: Long) {
        val cacheFilePath = cacheDir + File.separator + getLogFileName()
        val cacheRef = RandomAccessFile(cacheFilePath, RAF_MODE)
        val cacheChannel = cacheRef.channel
        val cacheMap = cacheChannel.map(FileChannel.MapMode.READ_WRITE, cacheRef.length(), end)

        getMmap().force()

        for (i in 0 until end) {
            cacheMap.put(getMmap().get(i.toInt()))
        }

        cacheMap.force()
        cacheChannel.close()
        cacheRef.close()

        mmapChannel?.close()
        mmapRef?.close()
        mmapFile.delete()
        mmapMbb = null
        mmapChannel = null
        mmapRef = null
    }

    private fun getMmap(): MappedByteBuffer {
        if (mmapMbb != null) {
            return mmapMbb!!
        }

        mmapRef = RandomAccessFile(mmapFile, RAF_MODE)
        mmapChannel = mmapRef!!.channel
        mmapMbb = mmapChannel!!.map(FileChannel.MapMode.READ_WRITE, 0, MMAP_SIZE)
        return mmapMbb!!
    }

    private fun getLogFileName(): String {
        val formatter = SimpleDateFormat("yyyyMMdd")
        return formatter.format(Date()) + ".log"
    }

    companion object {
        private const val RAF_MODE = "rw"
        private const val MMAP_FILE_NAME = "log_cache"
        private const val MMAP_SIZE = 1 * 1024 * 1024L
    }
}