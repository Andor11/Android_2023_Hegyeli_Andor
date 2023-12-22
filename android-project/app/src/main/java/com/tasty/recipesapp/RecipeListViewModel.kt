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

    private val _filteredRecipes = MutableLiveData<Array<RecipeModel>?>()
    val filteredRecipes: MutableLiveData<Array<RecipeModel>?>
        get() = _filteredRecipes

    private val _favoriteRecipes = MutableLiveData<List<RecipeModel>>()
    val favoriteRecipes: LiveData<List<RecipeModel>>
        get() = _favoriteRecipes

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
            val recipeDetail = RecipeRepository(context).getRecipeDetails(recipeId)
            Log.d("recipe Detail", recipeDetail.toString())

            _recipeDetail.value = recipeDetail
        }
    }

    private val recipeRepository: RecipeRepository = RecipeRepository(application)

    suspend fun getAllRecipesFromDatabase(): List<com.tasty.recipesapp.data.models.RecipeModel> {
        return recipeRepository.getAllRecipes()
    }

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

    fun sortRecipesByName() {
        val sortedRecipes = liveData.value?.sortedBy { it.name }
        if (sortedRecipes != null) {
            liveData.value = sortedRecipes.toTypedArray()
        }
    }

    fun searchRecipes(query: String?) {
        viewModelScope.launch {
            val allRecipes = liveData.value
            if (query.isNullOrBlank() || allRecipes.isNullOrEmpty()) {
                _filteredRecipes.value = allRecipes
            } else {
                val filtered = allRecipes.filter { it.name.contains(query, ignoreCase = true) }
                _filteredRecipes.value = filtered.toTypedArray()
            }
        }
    }

    fun toggleFavorite(recipeId: Int) {
        viewModelScope.launch {
            val currentRecipes = liveData.value.orEmpty()
            val updatedRecipes = currentRecipes.map {
                if (it.id == recipeId) {
                    it.copy(isFavorite = !it.isFavorite)
                } else {
                    it
                }
            }
            liveData.value = updatedRecipes.toTypedArray()
            _favoriteRecipes.value = updatedRecipes.filter { it.isFavorite }

            Log.d("RecipeListViewModel", "Updated recipes: $updatedRecipes")
            Log.d("RecipeListViewModel", "Favorite recipes: ${_favoriteRecipes.value}")

        }
    }

}