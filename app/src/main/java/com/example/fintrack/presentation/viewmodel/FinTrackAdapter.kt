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

class FinTrackAdapter : RecyclerView.Adapter<FinTrackAdapter.ExpenseViewHolder>() {

    private var expenses: List<ExpenseWithCategory> = listOf()

    inner class ExpenseViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val categoryColor: View = itemView.findViewById(R.id.categoryColor)
        val categoryIcon: ImageView = itemView.findViewById(R.id.categoryIcon)
        val expenseName: TextView = itemView.findViewById(R.id.expenseName)
        val expenseAmount: TextView = itemView.findViewById(R.id.expenseAmount)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ExpenseViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_expenses, parent, false)
        return ExpenseViewHolder(view)
    }

    override fun onBindViewHolder(holder: ExpenseViewHolder, position: Int) {
        val item = expenses[position]
        holder.categoryColor.setBackgroundColor(Color.parseColor(item.category.color))
        holder.categoryIcon.setImageResource(item.category.icon)
        holder.expenseName.text = item.expense.title
        holder.expenseAmount.text = String.format("-R$%.2f", item.expense.price)
    }

    override fun getItemCount() = expenses.size

    fun setData(newData: List<ExpenseWithCategory>) {
        expenses = newData
        notifyDataSetChanged()
    }
}

data class ExpenseWithCategory(
    val expense: Expenses,
    val category: Category
)


