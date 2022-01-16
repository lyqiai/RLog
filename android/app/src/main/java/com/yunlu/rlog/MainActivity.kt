package com.yunlu.rlog

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.button.MaterialButton
import com.river.rlog.RLog

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        findViewById<MaterialButton>(R.id.action_log).setOnClickListener {
            RLog.d("订单信息：100801642299，订单状态：成功")
        }

        findViewById<MaterialButton>(R.id.action_upload).setOnClickListener {
            RLog.uploadLog(1)
        }
    }
}