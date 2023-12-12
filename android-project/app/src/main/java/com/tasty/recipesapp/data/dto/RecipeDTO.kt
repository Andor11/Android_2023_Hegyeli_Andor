package com.tasty.recipesapp.data.dto

import com.tasty.recipesapp.data.models.RecipeModel
import java.util.Objects

data class RecipeDTO (
    val name: String,
    val id: Int,
    val thumbnail_url: String,
    val description: String,
    val instructions: List<RecipeModel.Instruction>
){
    data class Instruction(
        val display_text: String
    )
    fun toModel(): RecipeModel {
        // Convert RecipeDTO to RecipeModel as needed
        return RecipeModel(name, id, thumbnail_url, description, instructions)
    }
}