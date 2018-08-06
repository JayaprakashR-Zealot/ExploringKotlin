package com.example.learnkotlin.service

import android.app.IntentService
import android.content.Intent
import android.support.v4.content.LocalBroadcastManager
import com.example.learnkotlin.Constants

private const val ACTION_FLITER = Constants.FLITER_RECEIVER


// TODO: Rename parameters
private const val EXTRA_PARAM1 = "com.example.learnkotlin.extra.PARAM1"
private const val EXTRA_PARAM2 = "com.example.learnkotlin.extra.PARAM2"

/**
 * An [IntentService] subclass for handling asynchronous task requests in
 * a service on a separate handler thread.
 * TODO: Customize class - update intent actions, extra parameters and static
 * helper methods.
 */
class MyIntentService : IntentService("MyIntentService") {

    override fun onHandleIntent(intent: Intent?) {
        when (intent?.action) {
            ACTION_FLITER -> {
                handleReceiver()
            }
        }
    }

    /**
     * Handle action Foo in the provided background thread with the provided
     * parameters.
     */
    private fun handleReceiver() {
        var i: Int = 0

        while (i < 10) {

            try {
                Thread.sleep(1000)
                i++
                sendDataToActivity(i)
            } catch (e: Exception) {
            }
        }
    }

    private fun sendDataToActivity(dataTOActivity: Int) {
        val intent = Intent().apply {
            putExtra("Count", dataTOActivity)
        }
        intent.action = ACTION_FLITER
        LocalBroadcastManager.getInstance(this).sendBroadcast(intent)
    }

}
