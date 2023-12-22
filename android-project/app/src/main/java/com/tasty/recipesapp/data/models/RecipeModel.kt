package com.tasty.recipesapp.data.models

data class RecipeModel(
    val name: String,
    val id: Int,
    val thumbnail_url: String,
    val description: String,
    val instructions: List<Instruction>,
    var isFavorite: Boolean = false
){
    data class Instruction(
        val display_text: String
    )
}