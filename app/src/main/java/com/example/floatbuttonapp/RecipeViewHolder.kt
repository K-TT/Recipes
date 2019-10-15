package com.example.floatbuttonapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions

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
    private val recipeImageView = itemView.findViewById<ImageView>(R.id.imageView)

    fun bind(recipe: Recipe) {
        recipeNameTextView.text = recipe.name
        recipeDescriptionTextView.text = recipe.description
        val options = RequestOptions().centerCrop()
        Glide.with(itemView).load("https://spoonacular.com/recipeImages/"+recipe.image).diskCacheStrategy(
            DiskCacheStrategy.RESOURCE).apply(options).into(recipeImageView)

//        recipeNameTextView.setTextColor(0xffff)
//        recipeDescriptionTextView.setTextColor(0xffff)
    }

}