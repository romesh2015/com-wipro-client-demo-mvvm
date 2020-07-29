package com.wipro.assignment.mvvm.viewmodel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.wipro.assignment.mvvm.di.DaggerNetworkComponent
import com.wipro.assignment.mvvm.network.api.ApiClient
import kotlinx.coroutines.Dispatchers
import retrofit2.Retrofit
import javax.inject.Inject
class AboutViewModelFactory: ViewModelProvider.Factory {
    @Inject
    lateinit var retrofit: Retrofit
    lateinit var networkAPIService: ApiClient
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        DaggerNetworkComponent.create().inject(this)
        networkAPIService = retrofit.create(ApiClient::class.java)
        if (modelClass.isAssignableFrom(AboutViewModel::class.java)) {
            return AboutViewModel(Dispatchers.Main,networkAPIService) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}