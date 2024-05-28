package com.example.fintrack.presentation.viewmodel

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.graphics.toColorInt
import androidx.recyclerview.widget.RecyclerView
import com.example.fintrack.R
import com.example.fintrack.data.Category

class CategoryAdapter : RecyclerView.Adapter<CategoryAdapter.CategoryViewHolder>() {

    private var items: List<Category> = listOf()

    inner class CategoryViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val categoryColor: View = itemView.findViewById(R.id.categoryColor)
        val categoryIcon: ImageView = itemView.findViewById(R.id.categoryIcon)
        val categoryName: TextView = itemView.findViewById(R.id.categoryName)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.item_categories_list, parent, false)
        return CategoryViewHolder(view)
    }

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
        if(!items.isNullOrEmpty()){
            val item = items[position]
            holder.categoryColor.setBackgroundColor(Color.parseColor(item.color))
            holder.categoryIcon.setImageResource(item.icon)
            holder.categoryName.text = item.title
        }

    }

    override fun getItemCount() = items.size

    fun setData(newData: List<Category>) {
        this.items = newData
        notifyDataSetChanged()
    }


}
