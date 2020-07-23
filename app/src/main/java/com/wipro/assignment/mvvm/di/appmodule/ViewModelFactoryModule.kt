package com.wipro.assignment.mvvm.di.appmodule
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.wipro.assignment.mvvm.di.factory.ViewModelFactory
import dagger.Module
import dagger.Provides
import javax.inject.Provider
import javax.inject.Singleton
@Module
class ViewModelFactoryModule {
    @Provides
    @Singleton
    fun viewModelFactory(providerMap: Map<Class<out ViewModel>, Provider<ViewModel>>): ViewModelProvider.Factory {
        return ViewModelFactory(
            providerMap
        )
    }
}
