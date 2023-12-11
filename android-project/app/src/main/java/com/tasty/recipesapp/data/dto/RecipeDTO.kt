package com.tasty.recipesapp.data.dto

import com.tasty.recipesapp.data.models.RecipeModel

data class RecipeDTO (
    val name: String,
    val id: Int,
    val thumbnail_url: String,
){
    fun toModel(): RecipeModel {
        // Convert RecipeDTO to RecipeModel as needed
        return RecipeModel(name, id, thumbnail_url)
    }
}