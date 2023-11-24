package com.tasty.recipesapp.data.dto

data class RecipeDTO (
    val name: String,
    val instructions: Array<InstructionDto>){
}