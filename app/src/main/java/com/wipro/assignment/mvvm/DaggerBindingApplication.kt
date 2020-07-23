package com.wipro.assignment.mvvm
import android.content.Context
import com.wipro.assignment.mvvm.utility.AppConfig
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication
import com.wipro.assignment.mvvm.di.component.DaggerAppComponent
class DaggerBindingApplication : DaggerApplication() {
    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        return DaggerAppComponent.builder().application(this).baseUrl(AppConfig.BASE_URL).build()

    }
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