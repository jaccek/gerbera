package com.github.jaccek

import com.github.jaccek.entities.Environment
import com.github.jaccek.entities.Service
import com.github.jaccek.entities.Status
import com.github.jaccek.statuspageadapters.DefaultStatusPageAdapter
import io.ktor.application.Application
import io.ktor.application.call
import io.ktor.application.install
import io.ktor.features.CORS
import io.ktor.features.ContentNegotiation
import io.ktor.gson.gson
import io.ktor.http.ContentType
import io.ktor.http.HttpHeaders
import io.ktor.http.HttpMethod
import io.ktor.response.respond
import io.ktor.response.respondRedirect
import io.ktor.response.respondText
import io.ktor.routing.get
import io.ktor.routing.routing
import java.net.HttpURLConnection
import java.net.URL

fun main(args: Array<String>): Unit = io.ktor.server.netty.EngineMain.main(args)

@Suppress("unused") // Referenced in application.conf
@kotlin.jvm.JvmOverloads
fun Application.module(testing: Boolean = false) {
    install(CORS) {
        method(HttpMethod.Options)
        method(HttpMethod.Put)
        method(HttpMethod.Delete)
        method(HttpMethod.Patch)
        header(HttpHeaders.Authorization)
        header("MyCustomHeader")
        allowCredentials = true
        anyHost() // @TODO: Don't do this in production if possible. Try to limit it.
    }

    install(ContentNegotiation) {
        gson {
            setPrettyPrinting()
        }
    }

    val statusPageAdapter = DefaultStatusPageAdapter()

    routing {
        get("/") {
            call.respondRedirect("http://localhost:3000", true)
        }

        get("/status") {
            val serviceStatus = statusPageAdapter.requestStatusPage("fcm-subscriber", URL("http://fcm-subscriber0.dev-trans.rst.com.pl/status"))
            call.respond(listOf(
                serviceStatus,
                Service("fcm-subscriber", "unknown", Status.UP, Environment.PROD)
            ))
        }
    }
}
