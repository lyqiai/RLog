package com.river.rlog

import android.util.Log
import androidx.annotation.IntDef
import java.lang.annotation.Retention
import java.lang.annotation.RetentionPolicy

/**
 * @Author: River
 * @Emial: 1632958163@qq.com
 * @Create: 2021/11/9
 **/
object RLogType {
    const val V = Log.VERBOSE
    const val D = Log.DEBUG
    const val I = Log.INFO
    const val W = Log.WARN
    const val E = Log.ERROR
    const val A = Log.ASSERT

    @IntDef(V, D, I, W, E, A)
    @Retention(RetentionPolicy.SOURCE)
    annotation class TYPE

    fun getLevelString(@TYPE level: Int): String {
        return when (level) {
            V -> VERBOSE
            D -> DEBUG
            I -> INFO
            W -> WARN
            E -> ERROR
            A -> ASSERT
            else -> UNKNOWN
        }
    }

    fun getLevel(level: String): Int {
        return when (level) {
            VERBOSE -> V
            DEBUG -> D
            INFO -> I
            WARN -> W
            ERROR -> E
            ASSERT -> A
            else -> I
        }
    }

    const val VERBOSE = "VERBOSE"
    const val DEBUG = "DEBUG"
    const val INFO = "INFO"
    const val WARN = "WARN"
    const val ERROR = "ERROR"
    const val ASSERT = "ASSERT"
    const val UNKNOWN = "UNKNOWN"
}