package com.tasty.recipesapp.ui.recipe

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.bumptech.glide.Glide
import com.tasty.recipesapp.R
import com.tasty.recipesapp.RecipeListViewModel
import com.tasty.recipesapp.data.dto.RecipeDTO
import com.tasty.recipesapp.data.models.RecipeModel
import com.tasty.recipesapp.databinding.FragmentRecipeDetailBinding

class RecipeDetailFragment : Fragment() {

    private lateinit var binding: FragmentRecipeDetailBinding
    private val viewModel: RecipeListViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentRecipeDetailBinding.inflate(layoutInflater)

        // Retrieve recipeId from arguments
        val recipeId = arguments?.getInt("recipeId", -1) ?: -1

        // Load recipe details based on recipeId
        viewModel.loadRecipeDetails(requireContext(), recipeId)

        // Observe the LiveData for recipe details
        viewModel.recipeDetail.observe(viewLifecycleOwner) { recipe ->
            // Update UI with recipe details
            if (recipe != null) {
                updateUI(recipe)
            }
        }

        return binding.root
    }

    private fun updateUI(recipe: RecipeDTO) {
        // Update UI elements with recipe details (name and image)
        Log.d("KEP", recipe.thumbnail_url);
        binding.recipeName.text = recipe.name
        Glide.with(requireContext())
            .load(recipe.thumbnail_url)
            .placeholder(R.drawable.logo) // Add a placeholder drawable if needed
            .error(R.drawable.error_image) // Add an error drawable if the image fails to load
            .into(binding.recipeImage)
    }
}



