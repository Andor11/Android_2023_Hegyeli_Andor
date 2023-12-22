package com.tasty.recipesapp.ui.recipe

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.tasty.recipesapp.R
import com.tasty.recipesapp.RecipeListViewModel
import com.tasty.recipesapp.data.models.RecipeModel
import com.tasty.recipesapp.databinding.FragmentRecipesBinding
import com.tasty.recipesapp.ui.RecipeListAdapter

class RecipesFragment : Fragment() {

    private lateinit var binding: FragmentRecipesBinding
    private val viewModel: RecipeListViewModel by viewModels()
    private lateinit var searchView: SearchView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentRecipesBinding.inflate(layoutInflater)

        searchView = binding.root.findViewById(R.id.searchView)
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                viewModel.searchRecipes(newText)
                return true
            }
        })

        val btnSortByName: Button = binding.root.findViewById(R.id.btnSortByName)
        btnSortByName.setOnClickListener {
            viewModel.sortRecipesByName()
        }


        val recipess : Array<RecipeModel> = emptyArray();
        val myAdapter = RecipeListAdapter(recipess, onItemClick = { recipe ->
//            Log.d("Button click", recipe.toString())
            navigateToRecipeDetail(recipe)
        }, onFavoriteClick = { recipeId ->
            viewModel.toggleFavorite(recipeId)
        })

        binding.recipeList.adapter = myAdapter
        binding.recipeList.layoutManager = LinearLayoutManager(requireContext())

        viewModel.liveData.observe(viewLifecycleOwner) { recipes ->
            myAdapter.updateRecipes(recipes)
        }

        viewModel.filteredRecipes.observe(viewLifecycleOwner) { filteredRecipes ->
            if (filteredRecipes != null) {
                myAdapter.updateRecipes(filteredRecipes)
            }
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
