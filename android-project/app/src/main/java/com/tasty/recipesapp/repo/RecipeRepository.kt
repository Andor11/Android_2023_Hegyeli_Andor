package com.tasty.recipesapp.repo

import android.content.Context
import com.google.gson.Gson
import com.tasty.recipesapp.R
import com.tasty.recipesapp.data.RoomDB.RecipeDatabase
import com.tasty.recipesapp.data.dao.RecipeDao
import com.tasty.recipesapp.data.dto.RecipeDTO
import com.tasty.recipesapp.data.dto.RecipeResultDTO
import com.tasty.recipesapp.data.models.RecipeEntity
import com.tasty.recipesapp.data.models.RecipeModel
import org.json.JSONObject
import java.io.BufferedReader
import java.io.InputStreamReader
import java.io.Reader
import java.io.StringWriter
import java.io.Writer

class RecipeRepository(val context: Context) {

    private val recipeDao: RecipeDao // Add this property
    private val gson = Gson()

    init {
        // Initialize recipeDao with the Room database instance
        val recipeDatabase = RecipeDatabase.getDatabase(context)
        recipeDao = recipeDatabase.recipeDao()
    }
    suspend fun readRecipes():Array<RecipeDTO>{
        val file = context.resources.openRawResource(R.raw.all_recipes)
        val gson = Gson()
        val writer:Writer = StringWriter()
        val buffer = CharArray(1024)
        try {
            val reader:Reader = BufferedReader(InputStreamReader(file, "UTF-8"))
            val result = gson.fromJson<RecipeResultDTO>(reader, RecipeResultDTO::class.java)
            return result.results
        } finally {
            file.close()
        }
    }

    suspend fun insertRecipe(recipe: RecipeEntity) {
        recipeDao.insertRecipe(recipe)
    }

    suspend fun getAllRecipes(): List<RecipeModel> {
        return recipeDao.getAllRecipes().map {
            val recipeDTO = gson.fromJson(it.json, RecipeDTO::class.java)
            return@map recipeDTO.toModel()
        }
    }

    suspend fun deleteRecipe(recipe: RecipeEntity) {
        recipeDao.deleteRecipe(recipe)
    }

    suspend fun getRecipeDetails(recipeId: Int): RecipeDTO? {
        val recipes = readRecipes()
        return recipes.firstOrNull { it.id == recipeId }
    }
}