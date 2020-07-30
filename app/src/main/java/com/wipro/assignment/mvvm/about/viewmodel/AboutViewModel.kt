package com.wipro.assignment.mvvm.about.viewmodel
import androidx.lifecycle.*
import com.wipro.assignment.mvvm.about.model.AboutList
import com.wipro.assignment.mvvm.network.api.ApiClient
import com.wipro.assignment.mvvm.utility.Constants.TITLE
import com.wipro.assignment.mvvm.utility.SharedPrefsHelper
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.launch
import javax.inject.Inject
// This About view model class handle network operation for app and testing purpose both.
class AboutViewModel
@Inject constructor(private val coroutinesDispatcher: CoroutineDispatcher,
                    private val apiClient : ApiClient
                    ) : ViewModel(), LifecycleObserver {
    val loading: MutableLiveData<Boolean> = MutableLiveData()
    private val errorOnAPI = MutableLiveData<String>()
   var mutableLiveDataAbout  = MutableLiveData<List<AboutList>>()

    // Function is used to hit the network call
    fun getDataValue() {
            viewModelScope.launch(coroutinesDispatcher) {
                try {
                    val response = apiClient.getData()
                    if (response.isSuccessful) {
                        mutableLiveDataAbout.postValue(response.body()?.rows)
                        loading.postValue(false)
                        val tile= SharedPrefsHelper
                        tile.getInstance()?.save(TITLE, response.body()?.title)
                    } else {
                        loading.postValue(false)
                        errorOnAPI.postValue("Something went wrong::${response.message()}")
                    }

                } catch (e: Exception) {
                    loading.postValue(false)
                    errorOnAPI.postValue("Something went wrong::${e.localizedMessage}")
                }
        }
    }
    fun fetchError(): LiveData<String> = errorOnAPI
    fun fetchLoadStatus(): LiveData<Boolean> = loading
    fun fetchAboutLiveData(): MutableLiveData<List<AboutList>> = mutableLiveDataAbout
}