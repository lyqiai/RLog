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
            RLog.d("自动上传日志内容测试")
        }

        findViewById<MaterialButton>(R.id.action_upload).setOnClickListener {
            RLog.upload()
        }
    }
}