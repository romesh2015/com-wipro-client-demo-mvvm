package com.wipro.assignment.mvvm.di
import com.wipro.assignment.mvvm.viewmodel.AboutViewModelFactory
import dagger.Component
import javax.inject.Singleton
@Singleton
@Component(modules = [NetworkModule::class])
interface NetworkComponent {
    fun inject(aboutViewModelFactory: AboutViewModelFactory)
}