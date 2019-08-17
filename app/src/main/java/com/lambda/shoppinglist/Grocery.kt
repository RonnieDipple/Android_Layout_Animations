package com.lambda.shoppinglist


data class Grocery(
    val groceryNames: String,
    val groceryId: Int,
    var isAdded: Boolean
)