package com.tasty.recipesapp.ui.recipe

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.tasty.recipesapp.R
import com.tasty.recipesapp.RecipeListViewModel
import com.tasty.recipesapp.data.models.RecipeModel
import com.tasty.recipesapp.databinding.FragmentRecipesBinding
import com.tasty.recipesapp.ui.RecipeListAdapter

class RecipesFragment : Fragment() {

    private lateinit var binding: FragmentRecipesBinding
    private val viewModel: RecipeListViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentRecipesBinding.inflate(layoutInflater)

        val recipess : Array<RecipeModel> = emptyArray();
        val myAdapter = RecipeListAdapter(recipess, onItemClick = { recipe ->
            Log.d("Button click", recipe.toString())
            navigateToRecipeDetail(recipe)
        })

        binding.recipeList.adapter = myAdapter
        binding.recipeList.layoutManager = LinearLayoutManager(requireContext())

        viewModel.liveData.observe(viewLifecycleOwner) { recipes ->
            myAdapter.updateRecipes(recipes)
        }

        viewModel.getAllRecipesFromApi()

        return binding.root
    }

    private fun navigateToRecipeDetail(recipe: RecipeModel) {
        val bundle = Bundle().apply {
            putInt("recipeId", recipe.id)
        }
        findNavController().navigate(R.id.action_recipesFragment_to_recipeDetailFragment, bundle)
    }

}
