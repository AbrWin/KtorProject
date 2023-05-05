package com.abrsoftware.model

import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.Table

object RecipeRow: Table(name = "recipes") {
    val id = integer("id").autoIncrement()
    val name = varchar("name", 50)
    val ingredients = text(name = "ingredients").default(
        defaultValue = "Tasty!"
    )
    val instructions = text(name = "recipe_instructions").default(
        defaultValue = "Great! this is your first recipe. You'll need to mix up all the ingredients."
    )
    val userId = integer("user_id").references(UserRow.id)

    override val primaryKey: PrimaryKey
        get() = PrimaryKey(id)
}

 fun rowToRecipe(row: ResultRow): Recipe {
    return Recipe(
        id = row[RecipeRow.id],
        name = row[RecipeRow.name],
        userId = row[RecipeRow.userId],
        ingredients = row[RecipeRow.ingredients],
        instructions = row[RecipeRow.instructions]
    )
}

data class Recipe(
    val id: Int,
    val name: String,
    val userId: Int,
    val ingredients: String,
    val instructions: String,
)