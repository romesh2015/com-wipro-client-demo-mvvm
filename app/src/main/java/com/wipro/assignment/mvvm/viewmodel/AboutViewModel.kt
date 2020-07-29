package com.wipro.assignment.mvvm.viewmodel
import android.widget.Toast
import androidx.lifecycle.*
import com.wipro.assignment.mvvm.DemoApplication
import com.wipro.assignment.mvvm.R
import com.wipro.assignment.mvvm.model.AboutList
import com.wipro.assignment.mvvm.network.api.ApiClient
import com.wipro.assignment.mvvm.utility.Constants.TITLE
import com.wipro.assignment.mvvm.utility.SharedPrefsHelper
import com.wipro.assignment.mvvm.utility.Utility
import kotlinx.coroutines.MainCoroutineDispatcher
import kotlinx.coroutines.launch
import javax.inject.Inject
class AboutViewModel
@Inject constructor(private val coroutinesDispatcher: MainCoroutineDispatcher,
                    private val apiClient : ApiClient
                    ) : ViewModel(), LifecycleObserver {
    private val loading: MutableLiveData<Boolean> = MutableLiveData()
    var errorOnAPI = MutableLiveData<String>()
    var mutableLiveDataAbout  = MutableLiveData<List<AboutList>>()

    fun getData() {
            loading.postValue(true)
            if (!Utility.isConnected()) {
                Toast.makeText(
                    DemoApplication.getsAppContext(),
                    DemoApplication.getsAppContext().getString(
                        R.string.internet_connection
                    ),
                    Toast.LENGTH_SHORT
                ).show()
                return
            }
            viewModelScope.launch(coroutinesDispatcher) {
                try {
                    val response = apiClient.getData()
                    if (response.isSuccessful) {
                        val tile= SharedPrefsHelper
                        tile.getInstance()?.save(TITLE, response.body()?.title)
                        mutableLiveDataAbout.postValue(response.body()?.rows)
                        loading.postValue(false)
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