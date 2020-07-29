package com.wipro.assignment.mvvm.view.activity.adapter
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.wipro.assignment.mvvm.R
import com.wipro.assignment.mvvm.model.AboutList
import com.wipro.assignment.mvvm.utility.Utility
import com.wipro.assignment.mvvm.view.activity.ItemClickListener
import kotlinx.android.synthetic.main.about_list_row_view.view.*

class AboutAdapter(var about: ArrayList<AboutList>, var context: Context, var itemClickListener: ItemClickListener) : RecyclerView.Adapter<AboutAdapter.UserViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder  =
        UserViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.about_list_row_view,parent,false))

    override fun getItemCount(): Int  = about.size
    override fun onBindViewHolder(holder: UserViewHolder, position: Int)  = holder.bind(about[position])
    fun refreshAdapter(aboutList: List<AboutList>){
        about.clear()
        about.addAll(aboutList)
        notifyDataSetChanged()
    }

    inner  class UserViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        fun bind(about: AboutList){
            val itemLayout=itemView.view_item_adapter
            if (about.title == null && about.description == null && about.imageHref == null) {
                itemView.title_adapter.visibility = View.GONE
                itemView.detail_adapter.visibility = View.GONE
                itemView.image_adapter.visibility = View.GONE
            } else {
                itemView.title_adapter.visibility = View.VISIBLE
                itemView.detail_adapter.visibility = View.VISIBLE
                itemView.image_adapter.visibility = View.VISIBLE
                if (Utility.isConnected()) {
                    itemView.title_adapter.text = about.title
                    itemView.detail_adapter.text = about.description
                    Glide.with(itemView)
                        .load(about.imageHref)
                        .placeholder(R.mipmap.ic_launcher)
                        .into(itemView.image_adapter)
                    itemLayout.setOnClickListener {
                        itemClickListener.setClickedInfo(about)
                    }
                }
            }

        }
    }
}