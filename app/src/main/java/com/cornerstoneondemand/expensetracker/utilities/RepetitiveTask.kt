package com.cornerstoneondemand.expensetracker.utilities
    fun getCategoryName(value: Int): String {
        var name: String = ""
        return when (value) {
            Category.INVESTMENT.value -> "Investment"
            Category.FOOD_BEVERAGE.value -> "Food And Beverage"
            Category.BILLS.value -> "Bills"
            Category.TRANSPORTATION.value -> "Transportation"
            Category.SHOPPING.value -> "Shopping"
            Category.FRIENDS.value -> "Friends&Love"
            Category.ENTERTAINMENT.value -> "Entertainment"
            Category.TRAVEL.value -> "Travel"
            Category.HEALTH.value -> "Health"
            else -> {
                name
            }
        }
    }