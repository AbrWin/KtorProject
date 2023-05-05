package com.abrsoftware.model

import kotlinx.serialization.Serializable

@Serializable
data class RecipeParams(
    val name: String,
    val userId: Int,
    val ingredients: String,
    val instructions: String
)

@Serializable
data class ListRecipeParams(
    val userId: Int
)

@Serializable
data class ListRecipeResponse(
    val errorMessage: String? = null,
    val data: ListRecipeResponseData? = null
)


@Serializable
data class ListRecipeResponseData(val listRecipes: List<RecipeRespose>)

@Serializable
data class RecipeResponse(
    val data: RecipeResponseData? = null,
    val errorMessage: String? = null
)


@Serializable
data class RecipeResponseData(
    val id: Int,
    val nameRecipe: String,
    val message: String
)

@Serializable
data class RecipeRespose(
    val id: Int,
    val name: String,
    val userId: Int,
    val ingredients: String,
    val instructions: String,
)