package com.yunlu.rlog

import android.os.Bundle
import android.util.Base64
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.river.rlog.AppGlobals
import com.river.rlog.RLog
import java.io.ByteArrayOutputStream
import java.io.File
import java.io.FileInputStream
import java.io.FileOutputStream
import javax.crypto.Cipher
import javax.crypto.CipherInputStream
import javax.crypto.CipherOutputStream
import javax.crypto.spec.SecretKeySpec

class MainActivity : AppCompatActivity() {
    private var count = 0
    private val AES_ALGORITHM_TYPE = "AES/ECB/PKCS5Padding"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        findViewById<TextView>(R.id.action_log).setOnClickListener {
            RLog.i("test")

            val filePath =
                AppGlobals.get().cacheDir!!.absolutePath + File.separator + "rlog" + File.separator + "20211229"
            RLog.uploadLog(1)
        }
    }
}