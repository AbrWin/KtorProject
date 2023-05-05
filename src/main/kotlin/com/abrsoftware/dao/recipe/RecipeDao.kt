package com.abrsoftware.dao.recipe

import com.abrsoftware.model.ListRecipeParams
import com.abrsoftware.model.Recipe
import com.abrsoftware.model.RecipeParams

interface RecipeDao {
    suspend fun insertRecipe(params: RecipeParams): Recipe?
    suspend fun getListRecipe(params: ListRecipeParams): List<Recipe>?

}