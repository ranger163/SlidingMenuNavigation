package me.inassar.slidingcontent.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.nav_item_row_layout.view.*
import me.inassar.slidingcontent.R
import me.inassar.slidingcontent.model.NavItemModel

class NavAdapter(
    private val itemData: ArrayList<NavItemModel>,
    private val itemClick: (NavItemModel) -> Unit
) :
    RecyclerView.Adapter<NavAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.nav_item_row_layout, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return if (itemData.isNotEmpty())
            itemData.size
        else 0
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(itemData[position], itemClick)
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(item: NavItemModel, itemClick: (NavItemModel) -> Unit) {
            // UI Setting Code
            itemView.navItemImg.setImageResource(item.icon)
            itemView.setOnClickListener { itemClick(item) }
        }
    }
}
