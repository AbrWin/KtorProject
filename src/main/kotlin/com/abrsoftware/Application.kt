package com.abrsoftware

import com.abrsoftware.dao.DatabaseFactory
import com.abrsoftware.di.configureDI
import io.ktor.server.application.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import com.abrsoftware.plugins.*
import org.h2.engine.Database

fun main() {
    //System.setProperty("io.ktor.development", "true")
    embeddedServer(
        Netty,
        port = 443,
        host = "social-app-paladdar.azurewebsites.net",
        module = Application::module,
    )
        .start(wait = true)
}

fun Application.module() {
    DatabaseFactory.init()
    configureDI()
    configureSerialization()
    configureSecurity()
    configureRouting()
}
