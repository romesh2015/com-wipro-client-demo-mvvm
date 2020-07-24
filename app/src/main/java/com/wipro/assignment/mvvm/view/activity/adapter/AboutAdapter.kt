package com.wipro.assignment.mvvm.view.activity.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.wipro.assignment.mvvm.R
import com.wipro.assignment.mvvm.repository.data.AboutList
import com.wipro.assignment.mvvm.utility.Utility
import kotlinx.android.synthetic.main.layot_custom_view.view.*

class AboutAdapter(
    aboutList: List<AboutList>, private val interaction: FragmentActivity? = null
) : RecyclerView.Adapter<AboutAdapter.ViewHolder>() {
    private val about = mutableListOf<AboutList>()

    init {
        about.addAll(aboutList)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.layot_custom_view, parent, false)
        return ViewHolder(
            view,
            interaction
        )
    }

    override fun getItemCount() = about.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(about = about[position])

    }

    class ViewHolder(
        itemView: View,
        private val interaction: FragmentActivity?
    ) : RecyclerView.ViewHolder(itemView) {


        fun bind(about: AboutList) {

            if (about.title == null && about.description == null && about.imageHref == null) {
                itemView.title_adapter.visibility = View.GONE
                itemView.detail_adapter.visibility = View.GONE
                itemView.image_adapter.visibility = View.GONE
            } else {
                itemView.visibility = View.VISIBLE
                if (Utility.isConnected()) {
                    itemView.title_adapter.text = about.title
                    itemView.detail_adapter.text = about.description
                    Glide
                        .with(itemView)
                        .load(about.imageHref)
                        .into(itemView.image_adapter)
                    itemView.setOnClickListener {
                        Toast.makeText(interaction, about.title, Toast.LENGTH_SHORT).show()
                    }
                }
            }


        }
    }
}