package com.lambda.shoppinglist.activity

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.renderscript.Element
import android.transition.Explode
import android.view.Window
import androidx.core.content.ContextCompat
import com.lambda.shoppinglist.R
import com.lambda.shoppinglist.model.Grocery
import com.lambda.shoppinglist.model.GroceryRepository
import kotlinx.android.synthetic.main.activity_details.*

class DetailsActivity : AppCompatActivity() {

    companion object {
        const val ITEM_KEY = "SHOPPING_ITEM"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.requestFeature(Window.FEATURE_CONTENT_TRANSITIONS)
        window.enterTransition = Explode()
        window.exitTransition = Explode()

        setContentView(R.layout.activity_details)

        val item = intent.getSerializableExtra(ITEM_KEY) as Grocery

        item_name.text = item.groceryNames
        item_image.setImageDrawable(ContextCompat.getDrawable(this, item.groceryId))
    }
}
