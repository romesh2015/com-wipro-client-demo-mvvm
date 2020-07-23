package com.wipro.assignment.mvvm.di.appmodule
import com.wipro.assignment.mvvm.di.appmodule.ViewModelModule
import com.wipro.assignment.mvvm.view.activity.AboutActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector
@Module
abstract class ActivityBuilderModule {
    @ContributesAndroidInjector(modules = [ViewModelModule::class])
    abstract fun contributesInjectMainActivity(): AboutActivity

}