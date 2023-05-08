package com.abrsoftware.route


import com.abrsoftware.model.ErrorResponse
import io.ktor.http.*
import io.ktor.http.content.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import java.io.File

fun Routing.uploadRouting() {
    var fileDescription = ""
    var fileName = ""
    post("/upload") {

        val multipartData = call.receiveMultipart()

        multipartData.forEachPart { part ->
            when (part) {
                is PartData.FormItem -> {
                    fileDescription = part.value
                }

                is PartData.FileItem -> {
                    fileName = part.originalFileName as String
                    val fileBytes = part.streamProvider().readBytes()
                    try {
                        File("/uploads/$fileName").writeBytes(fileBytes)
                    }catch (e: Exception){
                        call.respond(
                            status = HttpStatusCode.InternalServerError,
                            message = ErrorResponse(
                                errorMessage = "Sorry, we couldn't save your photo right now!"
                            )
                        )
                    }

                }
                else -> {
                    call.respond(
                        status = HttpStatusCode.BadRequest,
                        message = ErrorResponse(
                            errorMessage = "Sorry, we have a problem right now!"
                        )
                    )
                }
            }
            part.dispose()
        }

        call.respondText("$fileDescription is uploaded to 'uploads/$fileName'")

    }

}

