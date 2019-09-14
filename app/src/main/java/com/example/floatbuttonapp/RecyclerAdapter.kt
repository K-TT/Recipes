package com.example.floatbuttonapp

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

//class RecyclerAdapter(private val context: Context) : RecyclerView.Adapter<RecipeViewHolder>() {
class RecyclerAdapter(private val onItemClick: OnItemClick) :
    RecyclerView.Adapter<RecipeViewHolder>() {

    var recipes = emptyList<Recipe>()

    // Викликається при створені елементу списку
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecipeViewHolder {
        return RecipeViewHolder(parent)
    }

    override fun getItemCount(): Int {
        return recipes.size
    }

    // Задаєм дані про елемент списку
    override fun onBindViewHolder(holder: RecipeViewHolder, position: Int) {
        holder.bind(recipes[position])
        holder.itemView.setOnLongClickListener {
            onItemClick.onClick(recipes[position])
            true
//            Toast.makeText(context, "${recipes[position].id}", Toast.LENGTH_SHORT).show()
        }
    }


    fun updateRecipes(recipes: List<Recipe>) {
        this.recipes = recipes
        notifyDataSetChanged()

    }
}