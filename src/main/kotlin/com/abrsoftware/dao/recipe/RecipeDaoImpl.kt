package com.abrsoftware.dao.recipe

import com.abrsoftware.dao.DatabaseFactory.dbQuery
import com.abrsoftware.model.*
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.select

class RecipeDaoImpl : RecipeDao{
    override suspend fun insertRecipe(params: RecipeParams): Recipe? {
        return dbQuery{
            val insertStatement = RecipeRow.insert {
                it[name] = params.name
                it[userId] = params.userId
                it[ingredients] = params.ingredients
                it[instructions] = params.instructions
            }

            insertStatement.resultedValues?.singleOrNull()?.let { resultRow ->
                rowToRecipe(resultRow)
            }
        }
    }

    override suspend fun getListRecipe(params: ListRecipeParams): List<Recipe> {
        return dbQuery{
            RecipeRow.select{ RecipeRow.userId eq params.userId}.toList().map { resultRow ->
                rowToRecipe(resultRow)
            }
        }
    }
}