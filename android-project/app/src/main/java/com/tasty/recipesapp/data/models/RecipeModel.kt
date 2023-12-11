package com.tasty.recipesapp.data.models

data class RecipeModel(
    val name: String,
    val id: Int,
    val thumbnail_url: String,
    val description: String,
//    val original_video_url: String,
)