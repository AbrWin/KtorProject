package com.abrsoftware.repository.recipe

import com.abrsoftware.dao.recipe.RecipeDao
import com.abrsoftware.model.*
import com.abrsoftware.util.Response
import io.ktor.http.*

class RecipeRepositoryImpl(
    private val recipeDao: RecipeDao
) : RecipeRepository {
    override suspend fun insertRecipe(params: RecipeParams): Response<RecipeResponse> {
        val insertedRecipe = recipeDao.insertRecipe(params)
        return if (insertedRecipe == null) {
            Response.Error(
                code = HttpStatusCode.InternalServerError,
                data = RecipeResponse(
                    errorMessage = "Oops, sorry we couldn't save your recipe, try later!"
                )
            )
        } else {
            Response.Succes(
                data = RecipeResponse(
                    data = RecipeResponseData(
                        id = insertedRecipe.id,
                        nameRecipe = insertedRecipe.name,
                        message = "Your recipe was saved correctly in Paladdar"
                    )
                )
            )
        }
    }

    override suspend fun getListRecipe(params: ListRecipeParams): Response<ListRecipeResponse> {
        val listRecipes = recipeDao.getListRecipe(params)

        return if (listRecipes!!.size == 0) {
            Response.Error(
                code = HttpStatusCode.InternalServerError,
                data = ListRecipeResponse(
                    errorMessage = "Oops, There isn't any recipe of this user"
                )
            )
        } else {
            Response.Succes(
                data = ListRecipeResponse(
                    data = ListRecipeResponseData(
                        listRecipes = listRecipes.map { singleRecipe ->
                            RecipeRespose(
                                singleRecipe.id,
                                singleRecipe.name,
                                singleRecipe.userId,
                                singleRecipe.ingredients,
                                singleRecipe.instructions
                            )
                        }
                    )
                )
            )
        }
    }
}