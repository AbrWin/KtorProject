package com.abrsoftware.repository.recipe

import com.abrsoftware.model.ListRecipeParams
import com.abrsoftware.model.ListRecipeResponse
import com.abrsoftware.model.RecipeParams
import com.abrsoftware.model.RecipeResponse
import com.abrsoftware.util.Response

interface RecipeRepository {
    suspend fun insertRecipe(params: RecipeParams): Response<RecipeResponse>
    suspend fun getListRecipe(params: ListRecipeParams): Response<ListRecipeResponse>

}