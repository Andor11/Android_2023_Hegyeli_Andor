package com.tasty.recipesapp

import android.app.Application
import android.content.Context
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tasty.recipesapp.data.dto.RecipeDTO
import com.tasty.recipesapp.data.models.RecipeModel
import com.tasty.recipesapp.repo.RecipeRepository
import kotlinx.coroutines.launch

class RecipeListViewModel(application: Application) : AndroidViewModel(application) {

    val liveData =MutableLiveData<Array<RecipeModel>> ()

    fun readAllRecipeNames(context: Context) {
        viewModelScope.launch {
            val list = RecipeRepository(context).readRecipes()
            val models = list.map { recipeEntity ->
                RecipeModel(
                    name = recipeEntity.name,
                    id = recipeEntity.id,
                    thumbnail_url = recipeEntity.thumbnail_url,
                    description = recipeEntity.description,
                    instructions = recipeEntity.instructions.map { instructionEntity ->
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

            // Update the LiveData with the fetched detailed information
            _recipeDetail.value = recipeDetail
        }
    }

    private val recipeRepository: RecipeRepository = RecipeRepository(application)

    suspend fun getAllRecipesFromDatabase(): List<RecipeModel> {
        return recipeRepository.getAllRecipes()
    }
    // Inside your ViewModel class

}