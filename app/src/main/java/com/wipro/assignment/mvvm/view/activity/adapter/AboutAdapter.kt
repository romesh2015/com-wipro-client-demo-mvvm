package com.wipro.assignment.mvvm.view.activity.adapter
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.*
import com.bumptech.glide.Glide
import com.wipro.assignment.mvvm.R
import com.wipro.assignment.mvvm.repository.data.AboutList
import kotlinx.android.synthetic.main.layot_custom_view.view.*

class AboutAdapter(aboutList: List<AboutList>, private val interaction: Interaction? = null
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
        private val interaction: Interaction?
    ) : RecyclerView.ViewHolder(itemView) {


        fun bind(about: AboutList) {
            itemView.title.text = about.title
            itemView.detail.text=about.description
            Glide
                .with(itemView)
                .load(about.imageHref)
                .into(itemView.image);
            itemView.setOnClickListener {
                interaction?.onItemSelected(adapterPosition,about)
            }
        }
    }
    interface Interaction {
        fun onItemSelected(position: Int, aboutList: AboutList)
    }
}