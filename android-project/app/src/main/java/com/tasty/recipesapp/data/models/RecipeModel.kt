package com.tasty.recipesapp.data.models

import java.util.Objects

data class RecipeModel(
    val name: String,
    val id: Int,
    val thumbnail_url: String,
    val description: String,
    val instructions: List<Instruction>
){
    data class Instruction(
        val display_text: String
    )
}