package com.tasty.recipesapp.ui.home

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.viewModels
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.tasty.recipesapp.R
import com.tasty.recipesapp.RecipeListViewModel
import com.tasty.recipesapp.data.dto.RecipeDTO
import com.tasty.recipesapp.data.models.RecipeModel
import com.tasty.recipesapp.databinding.ActivityMainBinding
import com.tasty.recipesapp.databinding.FragmentHomeBinding
import com.tasty.recipesapp.ui.RecipeListAdapter


class HomeFragment : Fragment() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    private lateinit var binding: FragmentHomeBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentHomeBinding.inflate(layoutInflater)

//        val name = intent.extras?.getString("name")
        val recipes : Array<RecipeModel> = emptyArray();
        val myAdapter = RecipeListAdapter(recipes)
        binding.recipeList.adapter = myAdapter
        binding.recipeList.layoutManager = LinearLayoutManager(requireContext());

//        val recipes = RecipeRepository(this).readRecipes()
//        recipes.forEach {
//            Log.d("MainBB","recipe: $it")
//        }

        val viewModel: RecipeListViewModel by viewModels()
        val liveData = viewModel.liveData
        liveData.observe(viewLifecycleOwner) {
            myAdapter.recipes = it
            myAdapter.notifyDataSetChanged()
            it.forEach {
                Log.d("tag", it.toString())
            }
        }

        viewModel.readAllRecipes(requireContext())
        // Inflate the layout for this fragment
        return binding.root
    }
}