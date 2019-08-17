package com.lambda.shoppinglist

class GroceryRepository {
    companion object {
        val groceryList = mutableListOf<Grocery>()
        fun createGroceryList() {
            for (i in 0 until ITEM_NAMES_RAW.size) {
                groceryList.add(Grocery(ITEM_NAMES_RAW[i], ICON_IDS[i], false))
            }
        }
    }
}