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
            navigateToRecipeDetail(recipe)
        }, onFavoriteClick = { recipeId ->
            viewModel.toggleFavorite(recipeId)
        })
        binding.profileRecipeList.adapter = myAdapter
        binding.profileRecipeList.layoutManager = LinearLayoutManager(requireContext())

        viewModel.favoriteRecipes.observe(viewLifecycleOwner) { favoriteRecipes ->
            Log.d("ProfileFragment", "Number of favorite recipes: ${favoriteRecipes.size}")

            myAdapter.recipes = favoriteRecipes.toTypedArray()
            myAdapter.notifyDataSetChanged()
        }

        lifecycleScope.launch {
            val recipes = viewModel.liveData.value

            Log.d("ProfileFragment", "Number of recipes: ${recipes?.size ?: 0}")

            if (recipes != null) {
                myAdapter.recipes = recipes
                myAdapter.notifyDataSetChanged()
            }

            binding.fabNewRecipe.setOnClickListener {
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

