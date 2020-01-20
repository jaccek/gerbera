package com.github.jaccek.gerbera.statuspage

import com.github.jaccek.gerbera.config.entities.ServiceEntry
import com.github.jaccek.gerbera.entities.Environment
import com.github.jaccek.gerbera.entities.Service
import com.github.jaccek.gerbera.entities.Status

class StatusFetcher {

    // TODO: configuration
    private val adaptersMap = mapOf(
        "fcm-subscriber0" to DefaultStatusPageParser(),
        "fcm-subscriber1" to DefaultStatusPageParser(),
        "driver-tasks0" to DefaultStatusPageParser(),
        "driver-tasks1" to DefaultStatusPageParser(),
        "trans-task-gateway0" to DefaultStatusPageParser(),
        "trans-task-gateway1" to DefaultStatusPageParser()
    )

    private val requester = StatusPageRequester()

    fun fetchService(entry: ServiceEntry): Service {
        val body = requester.requestStatusPage(entry)
            ?: return createDownService(entry)

        return adaptersMap[entry.serviceName]?.parse(entry, body)
            ?: createDownService(entry)
    }

    private fun createDownService(entry: ServiceEntry): Service =
        Service(
            name = entry.serviceName,
            environment = Environment.valueOf(entry.environment.toUpperCase()), // TODO: what if env is not found?
            status = Status.DOWN,
            version = "unknown"
        )
}
