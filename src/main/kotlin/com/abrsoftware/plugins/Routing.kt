package com.abrsoftware.plugins

import com.abrsoftware.route.authRouting
import com.abrsoftware.route.recipeRouting
import com.abrsoftware.route.uploadRouting
import io.ktor.server.routing.*
import io.ktor.server.response.*
import io.ktor.server.application.*

fun Application.configureRouting() {
    routing {
        get("/") {
            val debug = call.application.environment.developmentMode
            println("Hi there-> $debug")
            call.respondText("Hello World!")
        }

        authRouting()
        recipeRouting()
        uploadRouting()
    }
}
