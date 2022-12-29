package com.cornerstoneondemand.expensetracker.utilities

enum class Category(val value:Int) {
    INVESTMENT(0),
    FOOD_BEVERAGE(1),
    BILLS(2),
    TRANSPORTATION(3),
    SHOPPING(4),
    FRIENDS(5),
    ENTERTAINMENT(6),
    TRAVEL(7),
    HEALTH(8);

    companion object{
        fun fromInt(value:Int):Category?{
            return enumValues<Category>().firstOrNull(){it.value==value}
        }
    }
}