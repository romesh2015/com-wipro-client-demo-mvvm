package com.wipro.assignment.mvvm.di.component
import android.app.Application
import com.wipro.assignment.mvvm.DaggerBindingApplication
import com.wipro.assignment.mvvm.di.appmodule.*
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Named
import javax.inject.Singleton
@Singleton
@Component(    modules = [AndroidSupportInjectionModule::class, ActivityBuilderModule::class,
        AppModule::class, ApiModule::class, ViewModelFactoryModule::class, DatabaseModule::class,FragmentBuilderModule::class]
)
interface AppComponent : AndroidInjector<DaggerBindingApplication> {

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder

        @BindsInstance
        fun baseUrl(@Named("baseUrl") baseUrl: String): Builder

        fun build(): AppComponent
    }

}