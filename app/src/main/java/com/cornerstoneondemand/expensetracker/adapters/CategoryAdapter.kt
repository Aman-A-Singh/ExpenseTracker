package com.cornerstoneondemand.expensetracker.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.cornerstoneondemand.expensetracker.R
import com.cornerstoneondemand.expensetracker.utilities.Category
import de.hdodenhof.circleimageview.CircleImageView

class CategoryAdapter(private val onItemClickListener: (Int) -> Unit) : RecyclerView.Adapter<CategoryAdapter.CategoryViewHoldare>() {

    private var categoryList = Category.values()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHoldare {
        return CategoryViewHoldare(
            LayoutInflater.from(parent.context).inflate(R.layout.category_item, parent, false)
        )
    }

    override fun onBindViewHolder(holder: CategoryViewHoldare, position: Int) {
        val currentCategory = categoryList[position]
        when (currentCategory.value) {
            Category.INVESTMENT.value -> {
                holder.category.setText("Investment")
                holder.categoryImage.setImageResource(R.drawable.investment)
            }
            Category.FOOD_BEVERAGE.value -> {
                holder.category.setText("Food And Beverage")
                holder.categoryImage.setImageResource(R.drawable.food_beverage)
            }
            Category.BILLS.value -> {
                holder.category.setText("Bills")
                holder.categoryImage.setImageResource(R.drawable.bill)
            }
            Category.TRANSPORTATION.value -> {
                holder.category.setText("Transportation")
                holder.categoryImage.setImageResource(R.drawable.transportation)
            }
            Category.SHOPPING.value -> {
                holder.category.setText("Shopping")
                holder.categoryImage.setImageResource(R.drawable.shopping)
            }
            Category.FRIENDS.value -> {
                holder.category.setText("Friends&Love")
                holder.categoryImage.setImageResource(R.drawable.friends)
            }
            Category.ENTERTAINMENT.value -> {
                holder.category.setText("Entertainment")
                holder.categoryImage.setImageResource(R.drawable.entertainment)
            }
            Category.TRAVEL.value -> {
                holder.category.setText("Travel")
                holder.categoryImage.setImageResource(R.drawable.travel)
            }
            Category.HEALTH.value -> {
                holder.category.setText("Health")
                holder.categoryImage.setImageResource(R.drawable.health)
            }
        }
    }

    override fun getItemCount(): Int {
        return categoryList.size
    }

    inner class CategoryViewHoldare(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val category = itemView.findViewById<TextView>(R.id.text_view_category_name)
        val categoryImage = itemView.findViewById<CircleImageView>(R.id.category_image)
        init {
            itemView.setOnClickListener {
                onItemClickListener(adapterPosition)
            }
        }
    }
}