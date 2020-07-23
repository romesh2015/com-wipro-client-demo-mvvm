package com.wipro.assignment.mvvm.di.appmodule
import androidx.lifecycle.ViewModel
import com.wipro.assignment.mvvm.di.annotation.ViewModelKey
import com.wipro.assignment.mvvm.viewmodel.AboutActivityViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
@Module
abstract class ViewModelModule {
    @Binds
    @IntoMap
    @ViewModelKey(
        AboutActivityViewModel::class)
    abstract fun bindMainViewModel(mainActivityViewModel: AboutActivityViewModel) : ViewModel
}
