package com.tasty.recipesapp.ui.profile

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.tasty.recipesapp.R
import com.tasty.recipesapp.RecipeListViewModel
import com.tasty.recipesapp.data.models.RecipeModel
import com.tasty.recipesapp.databinding.FragmentProfileBinding
import com.tasty.recipesapp.ui.RecipeListAdapter


class ProfileFragment : Fragment() {

    private lateinit var binding: FragmentProfileBinding
    private val viewModel: RecipeListViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentProfileBinding.inflate(layoutInflater)

        val recipes : Array<RecipeModel> = emptyArray();
        val myAdapter = RecipeListAdapter(recipes, onItemClick = { recipe ->
            // Handle item click, navigate to RecipeDetailFragment
        })
        binding.profileRecipeList.adapter = myAdapter
        binding.profileRecipeList.layoutManager = LinearLayoutManager(requireContext())

        viewModel.liveData.observe(viewLifecycleOwner) { recipes ->
            // Randomly select a few recipes (e.g., 3 recipes)
            val randomRecipes = recipes.toList().shuffled().take(3)

            // Update the UI with the randomly selected recipes
            myAdapter.recipes = randomRecipes.toTypedArray()
            myAdapter.notifyDataSetChanged()
        }

        viewModel.readAllRecipeNames(requireContext())

        return binding.root
    }
}

