package com.wipro.assignment.mvvm
import android.app.Application
import android.content.Context
/**
 * This is application level class that used to handle application context.
 */
class DemoApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        wContext = this
    }
    companion object {
        private var wContext: Context? = null

        fun getsAppContext(): Context {
            return wContext!!.applicationContext
        }
    }
}