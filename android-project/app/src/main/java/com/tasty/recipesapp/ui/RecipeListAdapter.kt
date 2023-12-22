package com.tasty.recipesapp.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.tasty.recipesapp.R
import com.tasty.recipesapp.data.models.RecipeModel

class RecipeListAdapter(
    var recipes: Array<RecipeModel>,
    private val onItemClick: (RecipeModel) -> Unit,
    private val onFavoriteClick: (Int) -> Unit
) : RecyclerView.Adapter<RecipeListAdapter.ViewHolder>() {

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val title: TextView = itemView.findViewById(R.id.title)
        val favoriteButton: Button = itemView.findViewById(R.id.favoriteButton)
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

        holder.title.text = currentRecipe.name

        holder.itemView.setOnClickListener {
            onItemClick.invoke(currentRecipe)
        }

        holder.favoriteButton.setOnClickListener {
            onFavoriteClick.invoke(currentRecipe.id)
        }
    }

    fun updateRecipes(newRecipes: Array<RecipeModel>) {
        recipes = newRecipes
        notifyDataSetChanged()
    }
}
