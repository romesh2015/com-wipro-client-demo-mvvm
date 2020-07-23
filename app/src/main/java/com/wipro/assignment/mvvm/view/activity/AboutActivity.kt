@file:Suppress("DEPRECATION")
package com.wipro.assignment.mvvm.view.activity
import android.os.Bundle
import android.os.Handler
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.wipro.assignment.mvvm.R
import com.wipro.assignment.mvvm.db.AboutListDao
import com.wipro.assignment.mvvm.di.factory.ViewModelFactory
import com.wipro.assignment.mvvm.network.api.CoroutinesDispatcherProvider
import com.wipro.assignment.mvvm.repository.data.AboutList
import com.wipro.assignment.mvvm.utility.Constants.abutTitle
import com.wipro.assignment.mvvm.utility.Tracer
import com.wipro.assignment.mvvm.utility.showToast
import com.wipro.assignment.mvvm.view.activity.adapter.AboutAdapter
import com.wipro.assignment.mvvm.viewmodel.AboutActivityViewModel
import com.wipro.assignment.mvvm.viewmodel.AboutActivityViewState
import dagger.android.support.DaggerAppCompatActivity
import kotlinx.android.synthetic.main.activity_about.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

class AboutActivity : DaggerAppCompatActivity(), AboutAdapter.Interaction {
    val TAG = "AboutActivity"
    private val mainActivityViewModel: AboutActivityViewModel by viewModels { viewModelFactory }

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    @Inject
    lateinit var aboutDao: AboutListDao

    @Inject
    lateinit var coroutinesDispatcherProvider: CoroutinesDispatcherProvider
    lateinit var adapter: AboutAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_about)
        // swipe to refresh called
        swiperefresh.setOnRefreshListener {
            callRefreshData()
            Handler().postDelayed(Runnable {
                swiperefresh.setRefreshing(false)
            }, 4000) // Delay in millis

        }
        // provide the custom color to swipe refresh
        swiperefresh.setColorSchemeResources(
            android.R.color.holo_blue_bright,
            android.R.color.holo_green_light,
            android.R.color.holo_orange_light,
            android.R.color.holo_red_light
        );
    }

    fun callRefreshData() {
        Tracer.info(TAG, getString(R.string.refresh_call))
        observeViewState()
        observeUsersInDatabase()
    }

    override fun onStart() {
        super.onStart()
        callRefreshData()
    }

    private fun observeViewState() {
        mainActivityViewModel.state.observe(this, Observer { state ->
            when (state) {
                is AboutActivityViewState.ShowLoading -> {
                    showLoading()
                }
                is AboutActivityViewState.ShowError -> {
                    showError(state.error)
                }
            }
        })
    }

    // Aoi call from here
    private fun observeUsersInDatabase() {
        CoroutineScope(coroutinesDispatcherProvider.main).launch {
            aboutDao.getAllAboutData().collect { users ->
                showData(users)
            }
        }
    }

    private fun showLoading() {
        progress_circular.visibility = View.VISIBLE
    }

    // Update the list data here
    private fun showData(data: List<AboutList>) {
        removeProgressDialog()
        progress_circular.visibility = View.GONE
        recyclerview.visibility = View.VISIBLE
        recyclerview.layoutManager = LinearLayoutManager(this)
        recyclerview.setHasFixedSize(true)
        toolbar.setTitle(abutTitle)
        adapter = AboutAdapter(data, this@AboutActivity)
        recyclerview.adapter = adapter
    }

    private fun showError(error: Throwable) {
        removeProgressDialog()
        showToast(error.message, Toast.LENGTH_LONG)
    }

    private fun removeProgressDialog() {
        progress_circular.visibility = View.GONE
    }

    override fun onItemSelected(position: Int, about: AboutList) {
        var title = about.title
        if (null == title) {
            title = getString(R.string.title_name)
        }
        showToast(title + " " + getString(R.string.select), Toast.LENGTH_SHORT)

    }
}
