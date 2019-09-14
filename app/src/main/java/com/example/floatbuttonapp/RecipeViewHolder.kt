package com.example.floatbuttonapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class RecipeViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    constructor(parent: ViewGroup) : this(
        LayoutInflater.from(parent.context).inflate(
            R.layout.item_recipe,
            parent,
            false
        )
    )

    private val recipeNameTextView = itemView.findViewById<TextView>(R.id.nameTextView)
    private val recipeDescriptionTextView = itemView.findViewById<TextView>(R.id.descriptionTextView)

    fun bind(recipe: Recipe) {
        recipeNameTextView.text = recipe.name
        recipeDescriptionTextView.text = recipe.description
//        recipeNameTextView.setTextColor(0xffff)
//        recipeDescriptionTextView.setTextColor(0xffff)
    }

}