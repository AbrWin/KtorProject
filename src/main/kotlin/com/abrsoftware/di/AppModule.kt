package com.abrsoftware.di

import com.abrsoftware.dao.recipe.RecipeDao
import com.abrsoftware.dao.recipe.RecipeDaoImpl
import com.abrsoftware.dao.user.UserDao
import com.abrsoftware.dao.user.UserDaoImpl
import com.abrsoftware.repository.recipe.RecipeRepository
import com.abrsoftware.repository.recipe.RecipeRepositoryImpl
import com.abrsoftware.repository.user.UserRepository
import com.abrsoftware.repository.user.UserRepositoryImpl
import org.koin.dsl.module

val appModule = module {
    single<UserRepository> { UserRepositoryImpl(get()) }
    single<UserDao> { UserDaoImpl() }
    single<RecipeRepository> { RecipeRepositoryImpl(get()) }
    single<RecipeDao>{RecipeDaoImpl()}
}