package com.example.fintrack.presentation.viewmodel

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.fintrack.R
import com.example.fintrack.data.Category
import com.example.fintrack.data.Expenses

data class ExpenseWithCategory(
    val color: String,
    val icon: Int,
    val price: Double,
    val title: String
)

class ExpenseWithCategoryAdapter : RecyclerView.Adapter<ExpenseWithCategoryAdapter.ExpenseViewHolder>() {

    private var items: List<ExpenseWithCategory> = listOf()

    inner class ExpenseViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val categoryColor: View = itemView.findViewById(R.id.categoryColor)
        val categoryIcon: ImageView = itemView.findViewById(R.id.categoryIcon)
        val categoryTitle: TextView = itemView.findViewById(R.id.categoryName)
        val expenseAmount: TextView = itemView.findViewById(R.id.expenseAmount)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ExpenseViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.item_expenses_with_categories, parent, false)
        return ExpenseViewHolder(view)
    }

    override fun onBindViewHolder(holder: ExpenseViewHolder, position: Int) {
        val item = items[position]
        holder.categoryColor.setBackgroundColor(Color.parseColor(item.color))
        holder.categoryIcon.setImageResource(item.icon)
        holder.categoryTitle.text = item.title
        holder.expenseAmount.text = String.format("-R$%.2f", item.price)
    }

    override fun getItemCount() = items.size

    fun setData(newData: List<ExpenseWithCategory>) {
        this.items = newData
        notifyDataSetChanged()
    }
}
