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

    fun readAllRecipes(context: Context){
        viewModelScope.launch {
            val list = RecipeRepository(context).readRecipes()
            val models = list.map {
                RecipeModel(it.name)
            }
            liveData.value = models.toTypedArray()
        }
    }
}