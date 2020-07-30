package com.wipro.assignment.mvvm.utility
import android.util.Log
// In this class we have written the common functions to handle the logs in whole app.
object Tracer {
    private const val LOG_ENABLE = true
    fun debug(TAG: String, message: String?) {
        if (LOG_ENABLE) {
            if (message != null) {
                Log.d(AppConfig.App_log + TAG, message)
            }
        }
    }
    @JvmStatic
    fun info(TAG: String, message: String?) {
        if (LOG_ENABLE) {
            if (message != null) {
                Log.i(AppConfig.App_log + TAG, message)
            }
        }
    }
    @JvmStatic
    fun warning(TAG: String, message: String?) {
        if (LOG_ENABLE) {
            if (message != null) {
                Log.w(AppConfig.App_log + TAG, message)
            }
        }
    }
}