package com.cornerstoneondemand.expensetracker.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.cornerstoneondemand.expensetracker.R
import com.cornerstoneondemand.expensetracker.database.Expense
import com.cornerstoneondemand.expensetracker.utilities.Category
import com.cornerstoneondemand.expensetracker.utilities.getCategoryName
import de.hdodenhof.circleimageview.CircleImageView

class ExpenseAdapter() : RecyclerView.Adapter<ExpenseAdapter.ExpenseViewHolder>() {

    private var expenseList = ArrayList<Expense>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ExpenseViewHolder {
        return ExpenseViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.expense_item, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ExpenseViewHolder, position: Int) {
        val currentExpense = expenseList[position]
        holder.amount.text = String.format("%,.2f", currentExpense.amount)
        holder.note.text = currentExpense.note
        when (currentExpense.category_id) {
            Category.INVESTMENT -> {
                holder.category.setText("Investment")
                holder.categoryImage.setImageResource(R.drawable.investment)
            }
            Category.FOOD_BEVERAGE -> {
                holder.category.setText("Food And Beverage")
                holder.categoryImage.setImageResource(R.drawable.food_beverage)
            }
            Category.BILLS -> {
                holder.category.setText("Bills")
                holder.categoryImage.setImageResource(R.drawable.bill)
            }
            Category.TRANSPORTATION -> {
                holder.category.setText("Transportation")
                holder.categoryImage.setImageResource(R.drawable.transportation)
            }
            Category.SHOPPING -> {
                holder.category.setText("Shopping")
                holder.categoryImage.setImageResource(R.drawable.shopping)
            }
            Category.FRIENDS -> {
                holder.category.setText("Friends&Love")
                holder.categoryImage.setImageResource(R.drawable.friends)
            }
            Category.ENTERTAINMENT -> {
                holder.category.setText("Entertainment")
                holder.categoryImage.setImageResource(R.drawable.entertainment)
            }
            Category.TRAVEL -> {
                holder.category.setText("Travel")
                holder.categoryImage.setImageResource(R.drawable.travel)
            }
            Category.HEALTH -> {
                holder.category.setText("Health")
                holder.categoryImage.setImageResource(R.drawable.health)
            }
        }
    }

    override fun getItemCount(): Int {
        return expenseList.size
    }

    fun setExpense(newExpenseList: List<Expense>) {
        expenseList.clear()
        expenseList.addAll(newExpenseList)

        notifyDataSetChanged()
    }

    inner class ExpenseViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val category = itemView.findViewById<TextView>(R.id.text_view_category_name)
        val amount = itemView.findViewById<TextView>(R.id.text_view_amount)
        val note = itemView.findViewById<TextView>(R.id.text_view_note)
        val categoryImage = itemView.findViewById<CircleImageView>(R.id.category_image)
    }
}