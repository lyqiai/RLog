package com.river.rlog.upload

import android.content.Context
import androidx.work.*
import com.river.rlog.AppGlobals
import com.river.rlog.RLog
import java.util.concurrent.TimeUnit


/**
 * @Author: River
 * @Emial: 1632958163@qq.com
 * @Create: 2021/11/9
 **/
class UploadWorker(appContext: Context, workerParams: WorkerParameters): Worker(appContext, workerParams) {
    override fun doWork(): Result {
        RLog.i("自动上传任务开始...")

        RLog.upload()

        return Result.success()
    }

    companion object {
        const val WORK_TAG = "UploadWorker"

        fun startWork() {
            val constraints = Constraints.Builder()
                .setRequiredNetworkType(NetworkType.CONNECTED)
//                .setRequiresBatteryNotLow(true)
//                .setRequiresStorageNotLow(true)
//
//            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.M) {
//                constraints.setRequiresDeviceIdle(true)
//            }

            val worker = PeriodicWorkRequestBuilder<UploadWorker>(15, TimeUnit.MINUTES)
                .setConstraints(constraints.build())
                .addTag(WORK_TAG)
                .build()

            WorkManager.getInstance(AppGlobals.get())
                .enqueueUniquePeriodicWork(WORK_TAG, ExistingPeriodicWorkPolicy.KEEP, worker)
        }
    }
}