package com.github.jaccek.gerbera.statuspage

import com.github.jaccek.gerbera.entities.Environment
import com.github.jaccek.gerbera.entities.Service
import com.github.jaccek.gerbera.entities.Status

class StatusFetcher {

    private val adaptersMap = mapOf(
        "fcm-subscriber" to DefaultStatusPageParser()
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
            environment = Environment.valueOf(entry.environment),
            status = Status.DOWN,
            version = "unknown"
        )
}
