package com.tasty.recipesapp

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tasty.recipesapp.data.dto.RecipeDTO
import com.tasty.recipesapp.data.models.RecipeModel
import com.tasty.recipesapp.repo.RecipeRepository
import kotlinx.coroutines.launch

class RecipeListViewModel: ViewModel() {

    val liveData =MutableLiveData<Array<RecipeModel>> ()

    fun readAllRecipeNames(context: Context){
        viewModelScope.launch {
            val list = RecipeRepository(context).readRecipes()
            val models = list.map {
                RecipeModel(it.name, it.id, it.thumbnail_url)
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
    // Inside your ViewModel class

}