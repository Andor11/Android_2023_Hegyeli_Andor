package com.tasty.recipesapp

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tasty.recipesapp.data.models.RecipeEntity
import com.tasty.recipesapp.data.models.RecipeModel
import com.tasty.recipesapp.repo.RecipeRepository
import kotlinx.coroutines.launch

class ProfileViewModel(private val repository: RecipeRepository) : ViewModel() {

    // LiveData to observe changes in the list of recipes
    private val _recipeList = MutableLiveData<List<RecipeModel>>()
    val recipeList: LiveData<List<RecipeModel>> get() = _recipeList

    // Function to insert a new recipe into the Room database
    fun insertRecipe(recipe: RecipeEntity) {
        viewModelScope.launch {
            repository.insertRecipe(recipe)
        }
    }

    // Function to fetch all recipes from the Room database
    fun getAllRecipes() {
        viewModelScope.launch {
            try {
                // Fetch recipes from the repository
                val recipes = repository.getAllRecipes()
                // Update the LiveData with the new list of recipes
                _recipeList.postValue(recipes)
            } catch (e: Exception) {
                // Handle exceptions here, such as logging or displaying an error message
                e.printStackTrace()
            }
        }
    }

     //Function to remove a recipe from the Room database
    fun deleteRecipe(recipe: RecipeEntity) {
        viewModelScope.launch {
            repository.deleteRecipe(recipe)
            // Update the LiveData after deleting
            getAllRecipes()
        }
    }
}
