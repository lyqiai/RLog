package com.river.rlog.write

/**
 * @author  River
 * @date  2022/1/15 22:02
 */
interface IWriter {
    fun write(content: String)

    fun close()
}