package com.river.rlog.encrypt

/**
 * @Author: River
 * @Emial: 1632958163@qq.com
 * @Create: 2021/11/9
 **/
interface IEncrypt {
    /**
     * 加密
     * @param content String
     * @return String
     */
    fun encrypt(content: String): String

    /**
     * 解密
     * @param content String
     * @return String
     */
    fun decrypt(content: String): String
}