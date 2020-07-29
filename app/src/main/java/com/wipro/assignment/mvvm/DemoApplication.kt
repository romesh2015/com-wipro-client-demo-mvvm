package com.wipro.assignment.mvvm
import android.app.Application
import android.content.Context
import com.wipro.assignment.mvvm.utility.AppConfig
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication
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