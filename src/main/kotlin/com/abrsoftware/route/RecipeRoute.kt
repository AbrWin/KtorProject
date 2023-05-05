package com.abrsoftware.route

import com.abrsoftware.model.AuthResponse
import com.abrsoftware.model.ListRecipeParams
import com.abrsoftware.model.RecipeParams
import com.abrsoftware.repository.recipe.RecipeRepository
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import org.koin.ktor.ext.inject

fun Routing.recipeRouting(){
    val recipeRepository by inject<RecipeRepository>()
    route(path = "/insert-recipe"){
        post {
            val params = call.receiveNullable<RecipeParams>()

            if(params == null){
                call.respond(
                    status = HttpStatusCode.BadRequest,
                    message = AuthResponse(
                        errorMessage = "Invalid information"
                    )
                )
                return@post
            }

            val result = recipeRepository.insertRecipe(params = params)
            call.respond(
                status = result.code,
                message = result.data
            )
        }
    }

    route(path = "/get-list-recipes"){
        post {
            val params =  call.receiveNullable<ListRecipeParams>()
            if(params == null){
                call.respond(
                    status = HttpStatusCode.BadRequest,
                    message = AuthResponse(
                        errorMessage = "Invalid information"
                    )
                )
                return@post
            }
            val result = recipeRepository.getListRecipe(params = params)
            call.respond(
                status = result.code,
                message = result.data
            )
        }
    }
}