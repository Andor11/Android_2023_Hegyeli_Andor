package com.tasty.recipesapp.repo

import android.content.Context
import com.google.gson.Gson
import com.tasty.recipesapp.R
import com.tasty.recipesapp.data.dto.RecipeDTO
import com.tasty.recipesapp.data.dto.RecipeResultDTO
import java.io.BufferedReader
import java.io.InputStreamReader
import java.io.Reader
import java.io.StringWriter
import java.io.Writer

class RecipeRepository(val context: Context) {

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

    suspend fun getRecipeDetails(recipeId: Int): RecipeDTO? {
        val recipes = readRecipes()
        return recipes.firstOrNull { it.id == recipeId }
    }

}