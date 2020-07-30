package com.wipro.assignment.mvvm.about.view.fragment
import android.os.Bundle
import android.os.Handler
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.wipro.assignment.mvvm.R
import com.wipro.assignment.mvvm.about.model.AboutList
import com.wipro.assignment.mvvm.utility.Constants.TITLE
import com.wipro.assignment.mvvm.utility.SharedPrefsHelper
import com.wipro.assignment.mvvm.utility.Tracer
import com.wipro.assignment.mvvm.utility.Utility
import com.wipro.assignment.mvvm.about.view.ItemClickListener
import com.wipro.assignment.mvvm.about.view.adapter.AboutAdapter
import com.wipro.assignment.mvvm.about.viewmodel.AboutViewModel
import com.wipro.assignment.mvvm.about.viewmodel.AboutViewModelFactory
import kotlinx.android.synthetic.main.fragment_about_list.*
// This fragment is showing the list data with adapter.
@Suppress("DEPRECATION")
class AboutListFragment : Fragment(), ItemClickListener {
    val TAG = "AboutListFragment"
    lateinit var aboutViewModel : AboutViewModel
    private var aboutAdapter: AboutAdapter?=null
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_about_list, container, false)
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // Initialisation the the about video model
        initAboutViewModel()
        // Initialisation the the About adapter
        initAboutAdapter()
        // call the observer
        observeAboutViewModel()
        if (!Utility.isConnected()) {
            Toast.makeText(context, R.string.internet_connection, Toast.LENGTH_SHORT).show()
                return
            }
        // hit the api called
        aboutViewModel.getDataValue()

        // swipe to refresh called
        fragment_about_swipe_fresh.setOnRefreshListener {
            aboutViewModel.getDataValue()
            Handler().postDelayed(Runnable {
                fragment_about_swipe_fresh.isRefreshing = false
            }, getString(R.string.stop_interval).toLong()) // Delay in millis
        }
        // provide the custom color to swipe refresh
        fragment_about_swipe_fresh.setColorSchemeResources(
            android.R.color.holo_blue_bright,
            android.R.color.holo_green_light,
            android.R.color.holo_orange_light,
            android.R.color.holo_red_light
        )
    }
    private fun initAboutViewModel(){
        fragment_about_progress_circular.visibility = View.VISIBLE
        var aboutViewModelFactory = AboutViewModelFactory()
        aboutViewModel = ViewModelProviders.of(this, aboutViewModelFactory).get(AboutViewModel::class.java)
    }
    private fun initAboutAdapter(){
        aboutAdapter = AboutAdapter(arrayListOf(), this@AboutListFragment.requireActivity(),this)
        fragment_about_recyclerview.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = aboutAdapter
        }
    }
    private fun observeAboutViewModel(){
        aboutViewModel.fetchAboutLiveData()?.observe(viewLifecycleOwner, Observer {
            it?.let {
                aboutAdapter?.refreshAdapter(it)
                val tile = SharedPrefsHelper
                fragment_about_toolbar_fragment?.title = tile.getInstance()!!.get<String>(TITLE)
            }
        })
        aboutViewModel.fetchLoadStatus().observe(viewLifecycleOwner, Observer {
            if(!it){
                println(it)
                fragment_about_progress_circular.visibility  = View.GONE
            }
        })
        aboutViewModel.fetchError().observe(viewLifecycleOwner, Observer {
            it?.let {
                if(!TextUtils.isEmpty(it)){
                    fragment_about_progress_circular.visibility = View.GONE
                    Toast.makeText(context,"$it", Toast.LENGTH_LONG).show()
                }
            }
        })
    }
    // here we get the item value to show on toast.
    override fun setClickedInfo(aboutList: AboutList) {
        Tracer.debug(TAG,aboutList.description)
        Toast.makeText(context, aboutList.title, Toast.LENGTH_SHORT).show()
    }

}