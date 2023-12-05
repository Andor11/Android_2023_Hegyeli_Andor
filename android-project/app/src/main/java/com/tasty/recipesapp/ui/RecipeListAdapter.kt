package com.tasty.recipesapp.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.tasty.recipesapp.R
import com.tasty.recipesapp.data.models.RecipeModel

class RecipeListAdapter(
    var recipes: Array<RecipeModel>,
    private val onItemClick: (RecipeModel) -> Unit
) : RecyclerView.Adapter<RecipeListAdapter.ViewHolder>() {

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val title: TextView = itemView.findViewById(R.id.title)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.recipe_list_item, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return recipes.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentRecipe = recipes[position]

        // Set the recipe name
        holder.title.text = currentRecipe.name

        // Handle item click
        holder.itemView.setOnClickListener {
            onItemClick.invoke(currentRecipe)
        }
    }

    // Update the list of recipes
    fun updateRecipes(newRecipes: Array<RecipeModel>) {
        recipes = newRecipes
        notifyDataSetChanged()
    }
}
