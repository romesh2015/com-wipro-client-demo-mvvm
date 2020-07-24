package com.wipro.assignment.mvvm.viewmodel
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.wipro.assignment.mvvm.DaggerBindingApplication
import com.wipro.assignment.mvvm.R
import com.wipro.assignment.mvvm.db.AboutListDao
import com.wipro.assignment.mvvm.network.api.ApiClient
import com.wipro.assignment.mvvm.network.api.CoroutinesDispatcherProvider
import com.wipro.assignment.mvvm.utility.Constants
import com.wipro.assignment.mvvm.utility.Constants.TITLE
import com.wipro.assignment.mvvm.utility.SharedPrefsHelper
import com.wipro.assignment.mvvm.utility.Utility
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject
@ExperimentalCoroutinesApi
class AboutActivityViewModel
@Inject constructor(private val apiClient: ApiClient,
                    private val coroutinesDispatcherProvider: CoroutinesDispatcherProvider,
                    private val aboutListDao: AboutListDao) : ViewModel() {
    private val _state : MutableLiveData<AboutActivityViewState> = MutableLiveData()
    val state: LiveData<AboutActivityViewState> = _state

    init {
        _state.postValue(AboutActivityViewState.ShowLoading)
        getData()
    }
    private fun getData() {
        if(!Utility.isConnected()){
            Toast.makeText(
                DaggerBindingApplication.getsAppContext(), DaggerBindingApplication.getsAppContext().getString(
                    R.string.internet_connection), Toast.LENGTH_SHORT).show()
            return
        }
        viewModelScope.launch {
            withContext(coroutinesDispatcherProvider.io){
                flowOf(apiClient.getData())
                    .catch { throwable ->
                        _state.postValue(
                            AboutActivityViewState.ShowError(
                                throwable
                            )
                        )
                    }.map { result ->
                        if(!result.rows.isNullOrEmpty()){
                            aboutListDao.deleteAllAboutData()
                            val tile= SharedPrefsHelper
                            tile.getInstance()?.save(TITLE, result.title)
                            aboutListDao.insertAboutList(result.rows)
                        }
                    }.collect()
            }
        }
    }
}