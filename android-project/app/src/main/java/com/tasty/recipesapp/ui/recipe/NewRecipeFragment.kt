package com.tasty.recipesapp.ui.recipe

//https://img.buzzfeed.com…_MugCakesFourWays-FB.jpg

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.room.RoomDatabase
import com.google.gson.Gson
import com.tasty.recipesapp.ProfileViewModel
import com.tasty.recipesapp.R
import com.tasty.recipesapp.data.dto.RecipeDTO
import com.tasty.recipesapp.data.models.RecipeEntity
import com.tasty.recipesapp.data.models.RecipeModel
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

        // Set OnClickListener for the "Save" button
        buttonSaveRecipe.setOnClickListener {
            Log.d("SAVE", "SAVE")

            // Get data from UI components
            val recipeTitle = binding.editTextRecipeName.text.toString()
            val recipePicture = binding.editTextRecipePicture.text.toString()

            // Check if data is not empty
            if (recipeTitle.isNotEmpty() && recipePicture.isNotEmpty()) {
                // Create a Recipe object
//                val recipeModel = RecipeDTO(id= 0, name = recipeTitle, thumbnail_url = recipePicture)
//                Log.d("recipeModel", recipeModel.toString())
                val jjson = JSONObject();
                jjson.put("name", recipeTitle)
                jjson.put("thumbnail_url", recipePicture)
                val recipeEntity = RecipeEntity(0, jjson.toString())
                // Save to the database
                saveToDatabase(recipeEntity)

                // Optionally, you can navigate to another screen or perform other actions after saving
            } else {
                // Handle the case where either title or ingredients is empty
                Log.d("SAVE", "Title or Ingredients is empty")
            }
        }

        return binding.root
    }

    private fun saveToDatabase(recipe: RecipeEntity) {
        // Use a Coroutine to perform database operations asynchronously
        lifecycleScope.launch {
            // Access the DAO and insert the recipe
            viewModel.insertRecipe(recipe)

            // Log a message or perform any other action after saving the recipe
            Log.d("SAVE", "Recipe saved to database")
        }
    }
}

