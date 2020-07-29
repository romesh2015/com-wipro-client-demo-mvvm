package com.wipro.assignment.mvvm.view.activity.fragment
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
import com.wipro.assignment.mvvm.model.AboutList
import com.wipro.assignment.mvvm.utility.Constants.TITLE
import com.wipro.assignment.mvvm.utility.SharedPrefsHelper
import com.wipro.assignment.mvvm.utility.Tracer
import com.wipro.assignment.mvvm.view.activity.ItemClickListener
import com.wipro.assignment.mvvm.view.activity.adapter.AboutAdapter
import com.wipro.assignment.mvvm.viewmodel.AboutViewModel
import com.wipro.assignment.mvvm.viewmodel.AboutViewModelFactory
import kotlinx.android.synthetic.main.fragment_about_list.*
@Suppress("DEPRECATION")
class AboutListFragment : Fragment(), ItemClickListener {
    val TAG = "AboutActivity"
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
        initViewModel()
        initAdapter()
        observeViewModel()
        aboutViewModel.getData()

        // swipe to refresh called
        swiperefresh.setOnRefreshListener {
            aboutViewModel.getData()
            Handler().postDelayed(Runnable {
                swiperefresh.isRefreshing = false
            }, getString(R.string.stop_interval).toLong()) // Delay in millis

        }
        // provide the custom color to swipe refresh
        swiperefresh.setColorSchemeResources(
            android.R.color.holo_blue_bright,
            android.R.color.holo_green_light,
            android.R.color.holo_orange_light,
            android.R.color.holo_red_light
        )
    }

    override fun onStart() {
        super.onStart()

    }

    private fun initViewModel(){
        progress_circular.visibility = View.VISIBLE
        var aboutViewModelFactory = AboutViewModelFactory()
        aboutViewModel = ViewModelProviders.of(this, aboutViewModelFactory).get(AboutViewModel::class.java)
    }

    private fun initAdapter(){
        aboutAdapter = AboutAdapter(arrayListOf(), this@AboutListFragment.requireActivity(),this)
        recyclerview.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = aboutAdapter
        }
    }
    override fun onResume() {
        super.onResume()

    }

    private fun observeViewModel(){
        aboutViewModel.fetchAboutLiveData()?.observe(viewLifecycleOwner, Observer {
            it?.let {
                aboutAdapter?.refreshAdapter(it)
                val tile = SharedPrefsHelper
                toolbar_fragment?.title = tile.getInstance()!!.get<String>(TITLE)
            }
        })

        aboutViewModel.fetchLoadStatus().observe(viewLifecycleOwner, Observer {
            if(!it){
                println(it)
                progress_circular.visibility  = View.GONE
            }
        })

        aboutViewModel.fetchError().observe(viewLifecycleOwner, Observer {
            it?.let {
                if(!TextUtils.isEmpty(it)){
                    progress_circular.visibility = View.GONE
                    Toast.makeText(context,"$it", Toast.LENGTH_LONG).show()
                }

            }
        })
    }
    override fun setClickedInfo(aboutList: AboutList) {
        Tracer.debug(TAG,aboutList.description)
        Toast.makeText(context, aboutList.title, Toast.LENGTH_SHORT).show()
    }

}