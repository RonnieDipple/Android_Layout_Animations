package com.lambda.shoppinglist.adapter

import android.app.Activity
import android.app.ActivityOptions
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.lambda.shoppinglist.model.Grocery
import com.lambda.shoppinglist.R
import com.lambda.shoppinglist.activity.DetailsActivity
import kotlinx.android.synthetic.main.grocery_list_item.view.*

class GroceryAdapter(val groceryList: MutableList<Grocery>) : RecyclerView.Adapter<GroceryAdapter.ViewHolder>() {

    var lastPosition = -1


    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val card: CardView = view.cardView
        val groceryImageView: ImageView = view.icon_image_view
        val groceryNameView: TextView = view.grocery_name_view
        val groceryItemParent: LinearLayout = view.grocery_item_parent


        fun bindModel(grocery: Grocery) {

            groceryImageView.setImageResource(grocery.groceryId)
            groceryNameView.text = grocery.formattedName
            if (grocery.isAdded)
                groceryItemParent.setBackgroundColor(
                    ContextCompat.getColor(
                        itemView.context,
                        R.color.colorAccent
                    )
                )
            else
                groceryItemParent.setBackgroundColor(
                    ContextCompat.getColor(
                        itemView.context,
                        R.color.colorPrimaryDark
                    )
                )
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.grocery_list_item, parent, false) as View
        )
    }

    override fun getItemCount(): Int {
        return groceryList.size
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {

        val grocery = groceryList[position]
        viewHolder.bindModel(grocery)

        //TODO change below if you wish to share to other apps and change color of views(adds to list)
        //viewHolder.groceryNameView.text = grocery.formattedName

      /* viewHolder.groceryItemParent.setOnClickListener {
            if (grocery.isAdded) {
                grocery.isAdded = false
                notifyItemChanged(position)

            } else {

                grocery.isAdded = true
                notifyItemChanged(position)

            }
        }*/

        viewHolder.card.setOnClickListener{view ->
            val intent = Intent(view.context, DetailsActivity::class.java)
            intent.putExtra(DetailsActivity.ITEM_KEY, grocery)
            //view.context.startActivity(intent)

            val optionsBundle: Bundle = ActivityOptions.makeSceneTransitionAnimation(view.context as Activity, viewHolder.groceryImageView,
                "shared_image").toBundle()
            view.context.startActivity(intent, optionsBundle)

        }

        setEnterAnimation(viewHolder.card, position)
    }


    fun setEnterAnimation(viewToAnimate: View, position: Int) {

        if (position > lastPosition) {
            val animation: Animation = AnimationUtils.loadAnimation(viewToAnimate.context,R.anim.my_slide_in)
            viewToAnimate.startAnimation(animation)
            lastPosition = position
        }

    }

}