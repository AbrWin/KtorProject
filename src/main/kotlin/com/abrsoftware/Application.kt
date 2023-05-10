package com.abrsoftware

import com.abrsoftware.dao.DatabaseFactory
import com.abrsoftware.di.configureDI
import io.ktor.server.application.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import com.abrsoftware.plugins.*

fun main() {
    //System.setProperty("io.ktor.development", "true")
    embeddedServer(
        Netty,
        port = 8080,
        host = "0.0.0.0",
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
