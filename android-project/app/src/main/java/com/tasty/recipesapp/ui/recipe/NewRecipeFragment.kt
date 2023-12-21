package com.tasty.recipesapp.ui.recipe

//https://img.buzzfeed.com…_MugCakesFourWays-FB.jpg

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.tasty.recipesapp.ProfileViewModel
import com.tasty.recipesapp.R
import com.tasty.recipesapp.data.models.RecipeEntity
import com.tasty.recipesapp.databinding.FragmentNewRecipeBinding
import kotlinx.coroutines.launch
import org.json.JSONObject

class NewRecipeFragment : Fragment() {

    private lateinit var binding: FragmentNewRecipeBinding
    private lateinit var viewModel: ProfileViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentNewRecipeBinding.inflate(inflater, container, false)

        // Find the "Save" button in the layout
        val buttonSaveRecipe: Button = binding.root.findViewById(R.id.buttonSaveRecipe)

        buttonSaveRecipe.setOnClickListener {
            Log.d("SAVE", "SAVE")

            // Get data from UI components
            val recipeTitle = binding.editTextRecipeName.text.toString()
            val recipePicture = binding.editTextRecipePicture.text.toString()
//            val recipeDescriptiom = binding.editTextDescription.toString()
//            val ins1 = binding.editTextInstruction1.toString()
//            val ins2 = binding.editTextInstruction2.toString()
//            val recpieInstructions: MutableList<RecipeModel.Instruction> = mutableListOf()
//            recpieInstructions.add(RecipeModel.Instruction(ins1))
//            recpieInstructions.add(RecipeModel.Instruction(ins2))

            if (recipeTitle.isNotEmpty() && recipePicture.isNotEmpty()) {
                // Create a Recipe object
//                val recipeModel = RecipeDTO(id= 0, name = recipeTitle, thumbnail_url = recipePicture)
//                Log.d("recipeModel", recipeModel.toString())
                val jjson = JSONObject();
                jjson.put("name", recipeTitle)
                jjson.put("thumbnail_url", recipePicture)
//                jjson.put("description", recipeDescriptiom)
//                jjson.put("instructions", recpieInstructions)
                val recipeEntity = RecipeEntity(0, jjson.toString())

                saveToDatabase(recipeEntity)

            } else {
                Log.d("SAVE", "Title or Ingredients is empty")
            }
        }

        return binding.root
    }

    private fun saveToDatabase(recipe: RecipeEntity) {
        lifecycleScope.launch {
            viewModel.insertRecipe(recipe)
            Log.d("SAVE", "Recipe saved to database")
        }
    }
}

