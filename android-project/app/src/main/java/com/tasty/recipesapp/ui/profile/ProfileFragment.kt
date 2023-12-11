package com.tasty.recipesapp.ui.profile

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.tasty.recipesapp.R
import com.tasty.recipesapp.RecipeListViewModel
import com.tasty.recipesapp.data.models.RecipeModel
import com.tasty.recipesapp.databinding.FragmentProfileBinding
import com.tasty.recipesapp.ui.RecipeListAdapter
import kotlinx.coroutines.launch

class ProfileFragment : Fragment() {

    private lateinit var binding: FragmentProfileBinding
    private val viewModel: RecipeListViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentProfileBinding.inflate(layoutInflater)

        val myAdapter = RecipeListAdapter(emptyArray(), onItemClick = { recipe ->
            // Handle item click, navigate to RecipeDetailFragment
            navigateToRecipeDetail(recipe)
        })
        binding.profileRecipeList.adapter = myAdapter
        binding.profileRecipeList.layoutManager = LinearLayoutManager(requireContext())

        lifecycleScope.launch {
            // Load recipes from the Room Database
            val recipes = viewModel.getAllRecipesFromDatabase()

            // Log the size of the recipes list
            Log.d("ProfileFragment", "Number of recipes: ${recipes.size}")

            // Randomly select a few recipes (e.g., 3 recipes)
            val randomRecipes = recipes.shuffled().take(3)

            // Log the randomly selected recipes
            randomRecipes.forEach { recipe ->
                Log.d("ProfileFragment", "Random Recipe: $recipe")
            }

            // Update the UI with the randomly selected recipes
            myAdapter.recipes = randomRecipes.toTypedArray()
            myAdapter.notifyDataSetChanged()

            binding.fabNewRecipe.setOnClickListener {
                // Navigate to NewRecipeFragment
                findNavController().navigate(R.id.action_profileFragment_to_newRecipeFragment)
            }
        }

        return binding.root
    }

    private fun navigateToRecipeDetail(recipe: RecipeModel) {
        val bundle = Bundle().apply {
            putInt("recipeId", recipe.id)
        }
        findNavController().navigate(R.id.action_profileFragment_to_recipeDetailFragment, bundle)
    }
}

