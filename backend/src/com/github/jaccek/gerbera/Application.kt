package com.github.jaccek.gerbera

import com.github.jaccek.gerbera.entities.Environment
import com.github.jaccek.gerbera.entities.Service
import com.github.jaccek.gerbera.entities.Status
import com.github.jaccek.gerbera.statuspage.ServiceEntry
import com.github.jaccek.gerbera.statuspage.StatusFetcher
import io.ktor.application.Application
import io.ktor.application.call
import io.ktor.application.install
import io.ktor.features.CORS
import io.ktor.features.ContentNegotiation
import io.ktor.gson.gson
import io.ktor.http.HttpHeaders
import io.ktor.http.HttpMethod
import io.ktor.response.respond
import io.ktor.response.respondRedirect
import io.ktor.routing.get
import io.ktor.routing.routing

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
        }
    }

    val statusFetcher = StatusFetcher()

    routing {
        get("/") {
            call.respondRedirect("http://localhost:3000", true)
        }

        get("/status") {
            val entries = listOf(
                ServiceEntry("fcm-subscriber", "DEV", "http://fcm-subscriber0.dev-trans.rst.com.pl/status"),
                ServiceEntry("driver-tasks", "DEV", "http://driver-tasks0.dev-trans.rst.com.pl/status"),
                ServiceEntry("trans-task-gateway", "DEV", "http://trans-task-gateway0.dev-trans.rst.com.pl/status"),
                ServiceEntry("driver-tasks", "RC", "http://driver-tasks0.rc-trans.rst.com.pl/status"),
                ServiceEntry("trans-task-gateway", "RC", "http://trans-task-gateway0.rc-trans.rst.com.pl/status"),
                ServiceEntry("fcm-subscriber", "RC", "http://fcm-subscriber0.rc-trans.rst.com.pl/status")
            )
            val serviceStatuses = entries.map { statusFetcher.fetchService(it) }
//                listOf(
//                    Service(
//                        name = "fcm-subscriber",
//                        environment = Environment.RC,
//                        status = Status.DOWN,
//                        version = "unknown"
//                    ),
//                    Service(
//                        name = "fcm-subscriber",
//                        environment = Environment.RC,
//                        status = Status.UP,
//                        version = "unknown"
//                    ),
//                    Service(
//                        name = "fcm-subscriber",
//                        environment = Environment.RC,
//                        status = Status.UP,
//                        version = "unknown"
//                    ),
//                    Service(
//                        name = "fcm-subscriber",
//                        environment = Environment.RC,
//                        status = Status.DOWN,
//                        version = "unknown"
//                    ),
//                    Service(
//                        name = "fcm-subscriber",
//                        environment = Environment.RC,
//                        status = Status.UP,
//                        version = "unknown"
//                    )
//                )
            call.respond(serviceStatuses)
        }
    }
}
