package com.lambda.shoppinglist

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.grocery_list_item.view.*

class GroceryAdapter(val groceryList: MutableList<Grocery>) : RecyclerView.Adapter<GroceryAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.grocery_list_item, parent, false) as View
        )
    }

    override fun getItemCount(): Int {
        return groceryList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val grocery = groceryList[position]
        holder.bindModel(grocery)

        holder.groceryItemParent.setOnClickListener {
            if (grocery.isAdded){
                grocery.isAdded = false
                notifyItemChanged(position)

            }else{

                grocery.isAdded = true
                notifyItemChanged(position)

            }
        }



    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val groceryImageView: ImageView = view.icon_image_view
        val groceryNameView: TextView = view.grocery_name_view
        val groceryItemParent: LinearLayout = view.grocery_item_parent

        fun bindModel(grocery: Grocery) {
            groceryImageView.setImageResource(grocery.groceryId)
            groceryNameView.text = grocery.groceryNames
            if (grocery.isAdded)
                groceryItemParent.setBackgroundColor(ContextCompat.getColor(itemView.context, R.color.colorAccent))
            else
                groceryItemParent.setBackgroundColor(ContextCompat.getColor(itemView.context, R.color.colorPrimaryDark))
        }
    }

}