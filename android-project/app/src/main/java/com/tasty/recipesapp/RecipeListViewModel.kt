package com.tasty.recipesapp

import android.app.Application
import android.content.Context
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.tasty.recipesapp.data.dto.RecipeDTO
import com.tasty.recipesapp.data.models.RecipeModel
import com.tasty.recipesapp.repo.RecipeRepository
import kotlinx.coroutines.launch
import kotlin.math.log

class RecipeListViewModel(application: Application) : AndroidViewModel(application) {

    val liveData =MutableLiveData<Array<com.tasty.recipesapp.data.models.RecipeModel>> ()

    fun readAllRecipeNames(context: Context) {
        viewModelScope.launch {
            val list = RecipeRepository(context).readRecipes()
            val models = list.map { recipeEntity ->
                com.tasty.recipesapp.data.models.RecipeModel(
                    name = recipeEntity.name,
                    id = recipeEntity.id,
                    thumbnail_url = recipeEntity.thumbnail_url,
                    description = recipeEntity.description,
                    instructions = recipeEntity.instructions.toList().map { instructionEntity ->
                        RecipeModel.Instruction(
                            display_text = instructionEntity.display_text
                        )
                    }
                )
            }
            liveData.value = models.toTypedArray()
        }
    }


    private val _recipeDetail = MutableLiveData<RecipeDTO?>()
    val recipeDetail: LiveData<RecipeDTO?>
        get() = _recipeDetail

    fun loadRecipeDetails(context: Context, recipeId: Int) {
        viewModelScope.launch {
            // Fetch detailed information for the specified recipeId
            val recipeDetail = RecipeRepository(context).getRecipeDetails(recipeId)
            Log.d("recipe Detail", recipeDetail.toString())

            // Update the LiveData with the fetched detailed information
            _recipeDetail.value = recipeDetail
        }
    }

    private val recipeRepository: RecipeRepository = RecipeRepository(application)

    suspend fun getAllRecipesFromDatabase(): List<com.tasty.recipesapp.data.models.RecipeModel> {
        return recipeRepository.getAllRecipes()
    }
    // Inside your ViewModel class

    fun getAllRecipesFromApi() {
        viewModelScope.launch {
            val recipes = recipeRepository.getRecipesFromApi("0", "15")
            recipes.forEach {
                Log.d("RECIPE_API", it.toString())
            }
            val models = recipes.map { recipeEntity ->
                com.tasty.recipesapp.data.models.RecipeModel(
                    name = recipeEntity.name,
                    id = recipeEntity.id,
                    thumbnail_url = recipeEntity.thumbnail_url,
                    description = recipeEntity.description,
                    instructions = recipeEntity.instructions.toList().map { instructionEntity ->
                        RecipeModel.Instruction(
                            display_text = instructionEntity.display_text
                        )
                    }
                )
            }
            liveData.value = models.toTypedArray()
        }
    }
}