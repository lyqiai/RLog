package com.river.rlog.jsonParse

/**
 * @Author: River
 * @Emial: 1632958163@qq.com
 * @Create: 2021/11/9
 **/
interface IJsonParse {
    /**
     * 解析JSON
     * @param json String
     * @return String
     */
    fun parse(json: String): String
}