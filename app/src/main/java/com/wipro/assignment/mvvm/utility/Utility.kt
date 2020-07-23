package com.wipro.assignment.mvvm.utility
import android.content.Context
import android.net.ConnectivityManager
import com.wipro.assignment.mvvm.DaggerBindingApplication

object Utility {
    fun isConnected(): Boolean {
        var connected = false
        try {
            val cm =
                DaggerBindingApplication.getsAppContext()
                    .getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            val nInfo = cm.activeNetworkInfo
            connected = nInfo != null && nInfo.isAvailable && nInfo.isConnected
            return connected
        } catch (e: Exception) {
            Tracer.warning("Connectivity Exception", e.message)
        }
        return connected
    }

}