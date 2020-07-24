package com.wipro.assignment.mvvm.di.appmodule
import com.wipro.assignment.mvvm.di.appmodule.ViewModelModule
import com.wipro.assignment.mvvm.view.activity.AboutActivity
import com.wipro.assignment.mvvm.view.activity.fragment.AboutListFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector
@Module
abstract class FragmentBuilderModule {
    @ContributesAndroidInjector(modules = [ViewModelModule::class])
    abstract fun contributesInjectMainActivity(): AboutListFragment

}