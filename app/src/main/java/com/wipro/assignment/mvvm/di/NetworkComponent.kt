package com.wipro.assignment.mvvm.di
import com.wipro.assignment.mvvm.about.viewmodel.AboutViewModelFactory
import dagger.Component
import javax.inject.Singleton
/**
 * Here we have to define all viw models factory which deals with networking call only
 */
@Singleton
@Component(modules = [NetworkModule::class])
interface NetworkComponent {
    fun inject(aboutViewModelFactory: AboutViewModelFactory)
}