package com.lambda.shoppinglist.model

import java.io.Serializable


data class Grocery(
    val groceryNames: String,
    val groceryId: Int,
    var isAdded: Boolean
) : Serializable{
    val formattedName: String
        get() {
            val re = Regex("[^A-Za-z_ ]")
            return re.replace(this.groceryNames, "").replace("_", " ")
        }
}