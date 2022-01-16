package com.river.rlog.encrypt

import android.util.Base64
import javax.crypto.Cipher
import javax.crypto.spec.SecretKeySpec

/**
 * @Author: River
 * @Emial: 1632958163@qq.com
 * @Create: 2021/11/9
 **/
class AESEncrypt(private val key: ByteArray) : IEncrypt {
    private val AES_ALGORITHM_TYPE = "AES/ECB/PKCS5Padding"

    override fun encrypt(content: String): String {
        val secretKeySpec = SecretKeySpec(key, "AES")
        val aes: Cipher = Cipher.getInstance(AES_ALGORITHM_TYPE)
        aes.init(Cipher.ENCRYPT_MODE, secretKeySpec)
        val bytes: ByteArray = aes.doFinal(content.toByteArray(charset("utf-8")))
        val encode = Base64.encode(bytes, Base64.NO_WRAP)
        return String(encode, charset("utf-8"))
    }

    override fun decrypt(content: String): String {
        val secretKeySpec = SecretKeySpec(key, "AES")
        val aes: Cipher = Cipher.getInstance(AES_ALGORITHM_TYPE)
        aes.init(Cipher.DECRYPT_MODE, secretKeySpec)
        val base64 = Base64.decode(content, Base64.NO_WRAP)
        val decode = aes.doFinal(base64)
        return String(decode, charset("utf-8"))
    }
}