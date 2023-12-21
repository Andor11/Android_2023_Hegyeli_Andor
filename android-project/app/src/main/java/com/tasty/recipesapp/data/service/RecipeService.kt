package com.tasty.recipesapp.data.service

import com.tasty.recipesapp.data.dto.RecipeDTO
import com.tasty.recipesapp.data.dto.RecipeResultDTO
import com.tasty.recipesapp.data.models.RecipeModel
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface RecipeService {
    @GET("recipes/list")
    @Headers(
        "X-RapidAPI-Key: 8cdb4a8ec6msh9ecdb23de7479c5p10768djsn1fdcd3077a08",
        "X-RapidAPI-Host: tasty.p.rapidapi.com"
    )
    suspend fun getRecipes(
        @Query("from") from: String,
        @Query("size") size: String,
        @Query("tags") tags: String? = null
    ) : RecipeResultDTO

    @GET("recipes/get-more-info")
    @Headers(
        "X-RapidAPI-Key: 8cdb4a8ec6msh9ecdb23de7479c5p10768djsn1fdcd3077a08",
        "X-RapidAPI-Host: tasty.p.rapidapi.com"
    )
    suspend fun getRecipesDetails(
        @Query("id") id: Int
    ): RecipeDTO

}